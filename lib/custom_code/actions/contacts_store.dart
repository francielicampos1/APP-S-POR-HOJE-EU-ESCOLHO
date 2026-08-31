import 'dart:convert';
import 'package:shared_preferences/shared_preferences.dart';

/// Um contato de apoio (ex: familiar, psicólogo) cadastrado pela própria
/// pessoa que usa o app. Fica salvo só no aparelho dela (shared_preferences),
/// não em nenhum servidor.
class SupportContact {
  SupportContact({
    required this.id,
    required this.name,
    required this.role,
    required this.phone,
  });

  final String id;
  final String name;
  final String role;
  final String phone;

  String get initial =>
      name.trim().isNotEmpty ? name.trim()[0].toUpperCase() : '?';

  Map<String, dynamic> toJson() => {
        'id': id,
        'name': name,
        'role': role,
        'phone': phone,
      };

  factory SupportContact.fromJson(Map<String, dynamic> json) => SupportContact(
        id: json['id'] as String,
        name: json['name'] as String? ?? '',
        role: json['role'] as String? ?? '',
        phone: json['phone'] as String? ?? '',
      );

  SupportContact copyWith({String? name, String? role, String? phone}) =>
      SupportContact(
        id: id,
        name: name ?? this.name,
        role: role ?? this.role,
        phone: phone ?? this.phone,
      );
}

const _kStorageKey = 'support_contacts';

Future<List<SupportContact>> loadSupportContacts() async {
  final prefs = await SharedPreferences.getInstance();
  final raw = prefs.getString(_kStorageKey);
  if (raw == null || raw.isEmpty) return [];
  try {
    final list = jsonDecode(raw) as List<dynamic>;
    return list
        .map((e) => SupportContact.fromJson(e as Map<String, dynamic>))
        .toList();
  } catch (_) {
    return [];
  }
}

Future<void> saveSupportContacts(List<SupportContact> contacts) async {
  final prefs = await SharedPreferences.getInstance();
  final raw = jsonEncode(contacts.map((c) => c.toJson()).toList());
  await prefs.setString(_kStorageKey, raw);
}
