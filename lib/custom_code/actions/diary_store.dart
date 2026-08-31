import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class DiaryEntryItem {
  DiaryEntryItem({
    required this.id,
    required this.createdAt,
    required this.mood,
    required this.tag,
    required this.content,
  });

  final String id;
  final DateTime createdAt;
  final String mood;
  final String tag;
  final String content;

  Map<String, dynamic> toJson() => {
        'id': id,
        'createdAt': createdAt.toIso8601String(),
        'mood': mood,
        'tag': tag,
        'content': content,
      };

  factory DiaryEntryItem.fromJson(Map<String, dynamic> json) => DiaryEntryItem(
        id: json['id'] as String,
        createdAt: DateTime.tryParse(json['createdAt'] as String? ?? '') ??
            DateTime.now(),
        mood: json['mood'] as String? ?? '',
        tag: json['tag'] as String? ?? 'Diário',
        content: json['content'] as String? ?? '',
      );
}

/// Tags fixas disponíveis, cada uma com uma cor — mantém o visual
/// consistente com o resto do app.
const Map<String, Color> kDiaryTags = {
  'Vitória': Color(0xFF2E7D32),
  'Gatilhos': Color(0xFFE67E22),
  'Diário': Color(0xFF4A6FA5),
  'Apoio': Color(0xFFC0392B),
  'Reflexão': Color(0xFF6A1B9A),
};

const _kStorageKey = 'diary_entries';

Future<List<DiaryEntryItem>> loadDiaryEntries() async {
  final prefs = await SharedPreferences.getInstance();
  final raw = prefs.getString(_kStorageKey);
  if (raw == null || raw.isEmpty) return [];
  try {
    final list = jsonDecode(raw) as List<dynamic>;
    final entries = list
        .map((e) => DiaryEntryItem.fromJson(e as Map<String, dynamic>))
        .toList();
    entries.sort((a, b) => b.createdAt.compareTo(a.createdAt));
    return entries;
  } catch (_) {
    return [];
  }
}

Future<void> saveDiaryEntries(List<DiaryEntryItem> entries) async {
  final prefs = await SharedPreferences.getInstance();
  final raw = jsonEncode(entries.map((e) => e.toJson()).toList());
  await prefs.setString(_kStorageKey, raw);
}

const List<String> _kDiasSemanaAbrev = [
  'seg', 'ter', 'qua', 'qui', 'sex', 'sáb', 'dom',
];
const List<String> _kMesesAbrev = [
  'jan', 'fev', 'mar', 'abr', 'mai', 'jun',
  'jul', 'ago', 'set', 'out', 'nov', 'dez',
];

/// Formata a data de forma amigável: "Hoje, 14:20", "Ontem, 21:15" ou
/// "12 de out, 09:00".
String formatarDataDiario(DateTime data) {
  final agora = DateTime.now();
  final hoje = DateTime(agora.year, agora.month, agora.day);
  final dia = DateTime(data.year, data.month, data.day);
  final diff = hoje.difference(dia).inDays;
  final hora =
      '${data.hour.toString().padLeft(2, '0')}:${data.minute.toString().padLeft(2, '0')}';
  if (diff == 0) return 'Hoje, $hora';
  if (diff == 1) return 'Ontem, $hora';
  return '${data.day} de ${_kMesesAbrev[data.month - 1]}, $hora';
}
