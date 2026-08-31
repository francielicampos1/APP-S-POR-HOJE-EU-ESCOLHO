import 'package:flutter/material.dart';
import 'contacts_store.dart';

/// Abre um formulário (de baixo pra cima) pra adicionar ou editar um
/// contato de apoio. Retorna o contato salvo, ou null se cancelou.
/// Se [existente] tiver um id vazio de exclusão marcado, quem chama trata.
Future<SupportContact?> showContactFormSheet(
  BuildContext context, {
  SupportContact? existente,
  required void Function(SupportContact) onSalvar,
  required void Function(String id)? onExcluir,
}) {
  final nomeController = TextEditingController(text: existente?.name ?? '');
  final papelController = TextEditingController(text: existente?.role ?? '');
  final telefoneController =
      TextEditingController(text: existente?.phone ?? '');
  final formKey = GlobalKey<FormState>();

  return showModalBottomSheet<SupportContact?>(
    context: context,
    isScrollControlled: true,
    backgroundColor: Colors.white,
    shape: const RoundedRectangleBorder(
      borderRadius: BorderRadius.vertical(top: Radius.circular(20)),
    ),
    builder: (ctx) {
      return Padding(
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
                existente == null ? 'Adicionar contato' : 'Editar contato',
                style: const TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 16),
              TextFormField(
                controller: nomeController,
                decoration: const InputDecoration(labelText: 'Nome'),
                validator: (v) =>
                    (v == null || v.trim().isEmpty) ? 'Digite o nome' : null,
              ),
              const SizedBox(height: 12),
              TextFormField(
                controller: papelController,
                decoration: const InputDecoration(
                    labelText: 'Relação (ex: Esposa, Psicólogo)'),
              ),
              const SizedBox(height: 12),
              TextFormField(
                controller: telefoneController,
                decoration: const InputDecoration(labelText: 'Telefone'),
                keyboardType: TextInputType.phone,
                validator: (v) =>
                    (v == null || v.trim().isEmpty) ? 'Digite o telefone' : null,
              ),
              const SizedBox(height: 20),
              Row(
                children: [
                  Expanded(
                    child: ElevatedButton(
                      onPressed: () {
                        if (!(formKey.currentState?.validate() ?? false)) return;
                        final contato = SupportContact(
                          id: existente?.id ??
                              DateTime.now().millisecondsSinceEpoch.toString(),
                          name: nomeController.text.trim(),
                          role: papelController.text.trim(),
                          phone: telefoneController.text.trim(),
                        );
                        onSalvar(contato);
                        Navigator.of(ctx).pop(contato);
                      },
                      child: const Text('Salvar'),
                    ),
                  ),
                ],
              ),
              if (existente != null && onExcluir != null) ...[
                const SizedBox(height: 8),
                TextButton(
                  onPressed: () {
                    onExcluir(existente.id);
                    Navigator.of(ctx).pop(null);
                  },
                  style: TextButton.styleFrom(foregroundColor: Colors.red),
                  child: const Text('Excluir contato'),
                ),
              ],
            ],
          ),
        ),
      );
    },
  );
}
