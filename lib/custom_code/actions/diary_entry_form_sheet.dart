import 'package:flutter/material.dart';
import 'diary_store.dart';

Future<void> showDiaryEntryFormSheet(
  BuildContext context, {
  required void Function(DiaryEntryItem) onSalvar,
}) {
  final humorController = TextEditingController();
  final conteudoController = TextEditingController();
  String tagEscolhida = kDiaryTags.keys.first;
  final formKey = GlobalKey<FormState>();

  return showModalBottomSheet<void>(
    context: context,
    isScrollControlled: true,
    backgroundColor: Colors.white,
    shape: const RoundedRectangleBorder(
      borderRadius: BorderRadius.vertical(top: Radius.circular(20)),
    ),
    builder: (ctx) {
      return StatefulBuilder(
        builder: (ctx, setModalState) => Padding(
          padding: EdgeInsets.only(
            left: 24,
            right: 24,
            top: 24,
            bottom: MediaQuery.of(ctx).viewInsets.bottom + 24,
          ),
          child: Form(
            key: formKey,
            child: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                const Text(
                  'Como você se sente hoje?',
                  style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                ),
                const SizedBox(height: 16),
                TextFormField(
                  controller: humorController,
                  decoration: const InputDecoration(
                      labelText: 'Em poucas palavras (ex: Aliviado, Ansioso)'),
                  validator: (v) =>
                      (v == null || v.trim().isEmpty) ? 'Digite algo' : null,
                ),
                const SizedBox(height: 12),
                TextFormField(
                  controller: conteudoController,
                  decoration:
                      const InputDecoration(labelText: 'O que aconteceu?'),
                  maxLines: 4,
                  validator: (v) =>
                      (v == null || v.trim().isEmpty) ? 'Escreva algo' : null,
                ),
                const SizedBox(height: 16),
                const Text('Categoria', style: TextStyle(fontWeight: FontWeight.w600)),
                const SizedBox(height: 8),
                Wrap(
                  spacing: 8,
                  runSpacing: 8,
                  children: kDiaryTags.entries.map((entry) {
                    final selecionado = entry.key == tagEscolhida;
                    return GestureDetector(
                      onTap: () => setModalState(() => tagEscolhida = entry.key),
                      child: Container(
                        padding:
                            const EdgeInsets.symmetric(horizontal: 14, vertical: 8),
                        decoration: BoxDecoration(
                          color: selecionado ? entry.value : const Color(0xFFEFEFEF),
                          borderRadius: BorderRadius.circular(999),
                        ),
                        child: Text(
                          entry.key,
                          style: TextStyle(
                            color: selecionado ? Colors.white : Colors.black54,
                            fontWeight: FontWeight.w600,
                          ),
                        ),
                      ),
                    );
                  }).toList(),
                ),
                const SizedBox(height: 20),
                ElevatedButton(
                  onPressed: () {
                    if (!(formKey.currentState?.validate() ?? false)) return;
                    final entrada = DiaryEntryItem(
                      id: DateTime.now().millisecondsSinceEpoch.toString(),
                      createdAt: DateTime.now(),
                      mood: humorController.text.trim(),
                      tag: tagEscolhida,
                      content: conteudoController.text.trim(),
                    );
                    onSalvar(entrada);
                    Navigator.of(ctx).pop();
                  },
                  child: const Text('Salvar no diário'),
                ),
              ],
            ),
          ),
        ),
      );
    },
  );
}
