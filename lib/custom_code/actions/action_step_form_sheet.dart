import 'package:flutter/material.dart';
import 'action_steps_store.dart';

Future<void> showActionStepFormSheet(
  BuildContext context, {
  ActionStepItem? existente,
  required void Function(ActionStepItem) onSalvar,
  required void Function(String id)? onExcluir,
}) {
  final tituloController = TextEditingController(text: existente?.title ?? '');
  final subtituloController =
      TextEditingController(text: existente?.subtitle ?? '');
  String iconeEscolhido = existente?.iconKey ?? 'timer';
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
                Text(
                  existente == null ? 'Adicionar passo' : 'Editar passo',
                  style:
                      const TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                ),
                const SizedBox(height: 16),
                TextFormField(
                  controller: tituloController,
                  decoration:
                      const InputDecoration(labelText: 'Título (ex: Ligar pra um amigo)'),
                  validator: (v) =>
                      (v == null || v.trim().isEmpty) ? 'Digite um título' : null,
                ),
                const SizedBox(height: 12),
                TextFormField(
                  controller: subtituloController,
                  decoration: const InputDecoration(
                      labelText: 'Como fazer esse passo'),
                  maxLines: 2,
                ),
                const SizedBox(height: 16),
                const Text('Ícone', style: TextStyle(fontWeight: FontWeight.w600)),
                const SizedBox(height: 8),
                Wrap(
                  spacing: 10,
                  children: kActionStepIcons.entries.map((entry) {
                    final selecionado = entry.key == iconeEscolhido;
                    return GestureDetector(
                      onTap: () => setModalState(() => iconeEscolhido = entry.key),
                      child: CircleAvatar(
                        radius: 22,
                        backgroundColor:
                            selecionado ? const Color(0xFF2D5A52) : const Color(0xFFEFEFEF),
                        child: Icon(
                          entry.value,
                          color: selecionado ? Colors.white : Colors.black54,
                        ),
                      ),
                    );
                  }).toList(),
                ),
                const SizedBox(height: 20),
                ElevatedButton(
                  onPressed: () {
                    if (!(formKey.currentState?.validate() ?? false)) return;
                    final passo = ActionStepItem(
                      id: existente?.id ??
                          DateTime.now().millisecondsSinceEpoch.toString(),
                      title: tituloController.text.trim(),
                      subtitle: subtituloController.text.trim(),
                      iconKey: iconeEscolhido,
                    );
                    onSalvar(passo);
                    Navigator.of(ctx).pop();
                  },
                  child: const Text('Salvar'),
                ),
                if (existente != null && onExcluir != null) ...[
                  const SizedBox(height: 8),
                  TextButton(
                    onPressed: () {
                      onExcluir(existente.id);
                      Navigator.of(ctx).pop();
                    },
                    style: TextButton.styleFrom(foregroundColor: Colors.red),
                    child: const Text('Excluir passo'),
                  ),
                ],
              ],
            ),
          ),
        ),
      );
    },
  );
}
