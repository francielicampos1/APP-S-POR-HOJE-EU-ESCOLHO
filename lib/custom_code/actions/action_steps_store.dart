import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

/// Um passo de ação do "Meu Plano" (ex: Pausa de 10 Minutos). A pessoa que
/// usa o app pode editar, adicionar e remover os próprios passos — fica
/// salvo só no aparelho dela.
class ActionStepItem {
  ActionStepItem({
    required this.id,
    required this.title,
    required this.subtitle,
    required this.iconKey,
  });

  final String id;
  final String title;
  final String subtitle;
  final String iconKey;

  Map<String, dynamic> toJson() =>
      {'id': id, 'title': title, 'subtitle': subtitle, 'iconKey': iconKey};

  factory ActionStepItem.fromJson(Map<String, dynamic> json) => ActionStepItem(
        id: json['id'] as String,
        title: json['title'] as String? ?? '',
        subtitle: json['subtitle'] as String? ?? '',
        iconKey: json['iconKey'] as String? ?? 'timer',
      );
}

/// Paleta fixa de ícones/tons disponíveis pra escolher em cada passo —
/// mantém o visual consistente com o resto do app.
const Map<String, IconData> kActionStepIcons = {
  'timer': Icons.timer_rounded,
  'walk': Icons.directions_walk_rounded,
  'book': Icons.auto_stories_rounded,
  'call': Icons.call_rounded,
  'heart': Icons.favorite_rounded,
  'meditation': Icons.self_improvement_rounded,
  'water': Icons.water_drop_rounded,
  'music': Icons.music_note_rounded,
};

const List<Color> kActionStepTones = [
  Color(0xFF2D5A52),
  Color(0xFF6B8E23),
  Color(0xFFA0522D),
  Color(0xFF4A6FA5),
  Color(0xFF8B5A8C),
];

Color toneForIndex(int index) =>
    kActionStepTones[index % kActionStepTones.length];

const _kStorageKey = 'action_steps';

List<ActionStepItem> defaultActionSteps() => [
      ActionStepItem(
        id: 'default-1',
        title: 'Pausa de 10 Minutos',
        subtitle: 'Respire fundo e espere a urgência passar. A vontade é passageira.',
        iconKey: 'timer',
      ),
      ActionStepItem(
        id: 'default-2',
        title: 'Mudar de Ambiente',
        subtitle: 'Saia do celular ou do computador. Vá para um espaço aberto.',
        iconKey: 'walk',
      ),
      ActionStepItem(
        id: 'default-3',
        title: 'Relembrar Motivação',
        subtitle: 'Leia seu diário sobre por que você escolheu parar hoje.',
        iconKey: 'book',
      ),
    ];

Future<List<ActionStepItem>> loadActionSteps() async {
  final prefs = await SharedPreferences.getInstance();
  final raw = prefs.getString(_kStorageKey);
  if (raw == null || raw.isEmpty) return defaultActionSteps();
  try {
    final list = jsonDecode(raw) as List<dynamic>;
    if (list.isEmpty) return [];
    return list
        .map((e) => ActionStepItem.fromJson(e as Map<String, dynamic>))
        .toList();
  } catch (_) {
    return defaultActionSteps();
  }
}

Future<void> saveActionSteps(List<ActionStepItem> steps) async {
  final prefs = await SharedPreferences.getInstance();
  final raw = jsonEncode(steps.map((s) => s.toJson()).toList());
  await prefs.setString(_kStorageKey, raw);
}
