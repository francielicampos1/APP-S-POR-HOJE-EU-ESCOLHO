import 'dart:convert';
import 'package:shared_preferences/shared_preferences.dart';

/// Não existe login/conta nesse app — tudo fica só no aparelho da pessoa.
/// Este é o "perfil" local: nome (opcional), a data em que ela começou
/// a contar os dias sem apostar, e quanto ela gastava em média por dia
/// (opcional, usado só pra calcular a economia).
class LocalProfile {
  LocalProfile({
    required this.name,
    required this.quitDate,
    this.dailySpend,
    this.debtPaid,
  });

  final String name;
  final DateTime quitDate;
  final double? dailySpend;
  final double? debtPaid;

  int get daysSinceQuit {
    final hoje = DateTime.now();
    final inicio = DateTime(quitDate.year, quitDate.month, quitDate.day);
    final agora = DateTime(hoje.year, hoje.month, hoje.day);
    return agora.difference(inicio).inDays.clamp(0, 999999);
  }

  double get economiaEstimada =>
      dailySpend == null ? 0 : dailySpend! * daysSinceQuit;

  LocalProfile copyWith({
    String? name,
    DateTime? quitDate,
    double? dailySpend,
    double? debtPaid,
  }) =>
      LocalProfile(
        name: name ?? this.name,
        quitDate: quitDate ?? this.quitDate,
        dailySpend: dailySpend ?? this.dailySpend,
        debtPaid: debtPaid ?? this.debtPaid,
      );

  Map<String, dynamic> toJson() => {
        'name': name,
        'quitDate': quitDate.toIso8601String(),
        'dailySpend': dailySpend,
        'debtPaid': debtPaid,
      };

  factory LocalProfile.fromJson(Map<String, dynamic> json) => LocalProfile(
        name: json['name'] as String? ?? '',
        quitDate: DateTime.tryParse(json['quitDate'] as String? ?? '') ??
            DateTime.now(),
        dailySpend: (json['dailySpend'] as num?)?.toDouble(),
        debtPaid: (json['debtPaid'] as num?)?.toDouble(),
      );
}

const _kProfileKey = 'local_profile';

Future<LocalProfile> loadLocalProfile() async {
  final prefs = await SharedPreferences.getInstance();
  final raw = prefs.getString(_kProfileKey);
  if (raw == null || raw.isEmpty) {
    // primeira vez: comeca a contar a partir de hoje
    return LocalProfile(name: '', quitDate: DateTime.now());
  }
  try {
    return LocalProfile.fromJson(jsonDecode(raw) as Map<String, dynamic>);
  } catch (_) {
    return LocalProfile(name: '', quitDate: DateTime.now());
  }
}

Future<void> saveLocalProfile(LocalProfile profile) async {
  final prefs = await SharedPreferences.getInstance();
  await prefs.setString(_kProfileKey, jsonEncode(profile.toJson()));
}

/// Um registro de recaída: quando aconteceu, e quantos dias a sequência
/// anterior tinha durado. Guardamos isso em vez de apagar, pra pessoa
/// poder ver o próprio histórico sem julgamento.
class RelapseRecord {
  RelapseRecord({required this.date, required this.streakDays});

  final DateTime date;
  final int streakDays;

  Map<String, dynamic> toJson() =>
      {'date': date.toIso8601String(), 'streakDays': streakDays};

  factory RelapseRecord.fromJson(Map<String, dynamic> json) => RelapseRecord(
        date: DateTime.tryParse(json['date'] as String? ?? '') ?? DateTime.now(),
        streakDays: json['streakDays'] as int? ?? 0,
      );
}

const _kRelapsesKey = 'relapses';

Future<List<RelapseRecord>> loadRelapses() async {
  final prefs = await SharedPreferences.getInstance();
  final raw = prefs.getString(_kRelapsesKey);
  if (raw == null || raw.isEmpty) return [];
  try {
    final list = jsonDecode(raw) as List<dynamic>;
    final registros = list
        .map((e) => RelapseRecord.fromJson(e as Map<String, dynamic>))
        .toList();
    registros.sort((a, b) => b.date.compareTo(a.date));
    return registros;
  } catch (_) {
    return [];
  }
}

Future<void> _saveRelapses(List<RelapseRecord> relapses) async {
  final prefs = await SharedPreferences.getInstance();
  final raw = jsonEncode(relapses.map((r) => r.toJson()).toList());
  await prefs.setString(_kRelapsesKey, raw);
}

/// Registra uma recaída: guarda quantos dias a sequência atual durou,
/// e reinicia a contagem a partir de hoje (mantendo nome e gasto médio).
/// Devolve o perfil novo, já salvo.
Future<LocalProfile> registrarRecaida(LocalProfile perfilAtual) async {
  final relapses = await loadRelapses();
  relapses.insert(
    0,
    RelapseRecord(date: DateTime.now(), streakDays: perfilAtual.daysSinceQuit),
  );
  await _saveRelapses(relapses);

  final novoPerfil = perfilAtual.copyWith(quitDate: DateTime.now());
  await saveLocalProfile(novoPerfil);
  return novoPerfil;
}

/// Apaga TODOS os dados salvos no aparelho (perfil, contatos, passos,
/// diário, gatilhos, recaídas) — usado no botão "Apagar meus dados".
Future<void> clearAllLocalData() async {
  final prefs = await SharedPreferences.getInstance();
  await prefs.clear();
}

const List<String> kMesesPortugues = [
  'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho',
  'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro',
];

String mesAtualPortugues() => kMesesPortugues[DateTime.now().month - 1];
