import 'package:flutter/material.dart';
import 'trigger_log_store.dart';

Future<void> showTriggerLogFormSheet(
  BuildContext context, {
  required void Function(TriggerLogItem) onSalvar,
}) {
  final situacaoController = TextEditingController();
  final pensamentoController = TextEditingController();
  String sentimentoEscolhido = kFeelingOptions.first;
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
                  'Registrar gatilho',
                  style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                ),
                const SizedBox(height: 16),
                TextFormField(
                  controller: situacaoController,
                  decoration: const InputDecoration(
                      labelText: 'O que estava acontecendo?'),
                  validator: (v) =>
                      (v == null || v.trim().isEmpty) ? 'Descreva a situação' : null,
                ),
                const SizedBox(height: 16),
                const Text('Como você estava se sentindo?',
                    style: TextStyle(fontWeight: FontWeight.w600)),
                const SizedBox(height: 8),
                Wrap(
                  spacing: 8,
                  runSpacing: 8,
                  children: kFeelingOptions.map((sentimento) {
                    final selecionado = sentimento == sentimentoEscolhido;
                    return GestureDetector(
                      onTap: () =>
                          setModalState(() => sentimentoEscolhido = sentimento),
                      child: Container(
                        padding:
                            const EdgeInsets.symmetric(horizontal: 14, vertical: 8),
                        decoration: BoxDecoration(
                          color: selecionado
                              ? const Color(0xFF2D5A52)
                              : const Color(0xFFEFEFEF),
                          borderRadius: BorderRadius.circular(999),
                        ),
                        child: Text(
                          sentimento,
                          style: TextStyle(
                            color: selecionado ? Colors.white : Colors.black54,
                            fontWeight: FontWeight.w600,
                          ),
                        ),
                      ),
                    );
                  }).toList(),
                ),
                const SizedBox(height: 12),
                TextFormField(
                  controller: pensamentoController,
                  decoration: const InputDecoration(
                      labelText: 'O que você pensou? (opcional)'),
                  maxLines: 2,
                ),
                const SizedBox(height: 20),
                ElevatedButton(
                  onPressed: () {
                    if (!(formKey.currentState?.validate() ?? false)) return;
                    final log = TriggerLogItem(
                      id: DateTime.now().millisecondsSinceEpoch.toString(),
                      createdAt: DateTime.now(),
                      situation: situacaoController.text.trim(),
                      feeling: sentimentoEscolhido,
                      thought: pensamentoController.text.trim(),
                    );
                    onSalvar(log);
                    Navigator.of(ctx).pop();
                  },
                  child: const Text('Registrar'),
                ),
              ],
            ),
          ),
        ),
      );
    },
  );
}
