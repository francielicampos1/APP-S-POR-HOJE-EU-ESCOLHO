import 'dart:convert';
import 'package:shared_preferences/shared_preferences.dart';

class TriggerLogItem {
  TriggerLogItem({
    required this.id,
    required this.createdAt,
    required this.situation,
    required this.feeling,
    required this.thought,
  });

  final String id;
  final DateTime createdAt;
  final String situation;
  final String feeling;
  final String thought;

  Map<String, dynamic> toJson() => {
        'id': id,
        'createdAt': createdAt.toIso8601String(),
        'situation': situation,
        'feeling': feeling,
        'thought': thought,
      };

  factory TriggerLogItem.fromJson(Map<String, dynamic> json) => TriggerLogItem(
        id: json['id'] as String,
        createdAt: DateTime.tryParse(json['createdAt'] as String? ?? '') ??
            DateTime.now(),
        situation: json['situation'] as String? ?? '',
        feeling: json['feeling'] as String? ?? '',
        thought: json['thought'] as String? ?? '',
      );
}

const List<String> kFeelingOptions = [
  'Ansioso(a)', 'Estressado(a)', 'Tédio', 'Solidão', 'Euforia',
];

const _kStorageKey = 'trigger_logs';

Future<List<TriggerLogItem>> loadTriggerLogs() async {
  final prefs = await SharedPreferences.getInstance();
  final raw = prefs.getString(_kStorageKey);
  if (raw == null || raw.isEmpty) return [];
  try {
    final list = jsonDecode(raw) as List<dynamic>;
    final entries = list
        .map((e) => TriggerLogItem.fromJson(e as Map<String, dynamic>))
        .toList();
    entries.sort((a, b) => b.createdAt.compareTo(a.createdAt));
    return entries;
  } catch (_) {
    return [];
  }
}

Future<void> saveTriggerLogs(List<TriggerLogItem> logs) async {
  final prefs = await SharedPreferences.getInstance();
  final raw = jsonEncode(logs.map((e) => e.toJson()).toList());
  await prefs.setString(_kStorageKey, raw);
}
