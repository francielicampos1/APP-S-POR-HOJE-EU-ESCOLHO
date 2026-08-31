import '/components/settings_item/settings_item_widget.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';
import 'settings_group_child2_model.dart';
export 'settings_group_child2_model.dart';

class SettingsGroupChild2Widget extends StatefulWidget {
  const SettingsGroupChild2Widget({super.key});

  @override
  State<SettingsGroupChild2Widget> createState() =>
      _SettingsGroupChild2WidgetState();
}

class _SettingsGroupChild2WidgetState extends State<SettingsGroupChild2Widget> {
  late SettingsGroupChild2Model _model;

  @override
  void setState(VoidCallback callback) {
    super.setState(callback);
    _model.onUpdate();
  }

  @override
  void initState() {
    super.initState();
    _model = createModel(context, () => SettingsGroupChild2Model());
  }

  @override
  void dispose() {
    _model.maybeDispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: [
        wrapWithModel(
          model: _model.settingsItemModel1,
          updateCallback: () => safeSetState(() {}),
          child: SettingsItemWidget(
            icon: Icon(
              Icons.track_changes_rounded,
              color: FlutterFlowTheme.of(context).secondaryText,
              size: 22.0,
            ),
            label: 'Metas de Progresso',
            showArrow: 'Show Arrow',
            showArrowPresent: true,
            value: 'ativadas',
            isLast: false,
          ),
        ),
        wrapWithModel(
          model: _model.settingsItemModel2,
          updateCallback: () => safeSetState(() {}),
          child: SettingsItemWidget(
            icon: Icon(
              Icons.contact_support_rounded,
              color: FlutterFlowTheme.of(context).secondaryText,
              size: 22.0,
            ),
            label: 'Contatos de Emergência',
            showArrow: 'Show Arrow',
            showArrowPresent: true,
            value: 'ativadas',
            isLast: false,
          ),
        ),
        wrapWithModel(
          model: _model.settingsItemModel3,
          updateCallback: () => safeSetState(() {}),
          child: SettingsItemWidget(
            icon: Icon(
              Icons.psychology_rounded,
              color: FlutterFlowTheme.of(context).secondaryText,
              size: 22.0,
            ),
            label: 'Meus Gatilhos Salvos',
            showArrow: 'Show Arrow',
            showArrowPresent: true,
            value: 'ativadas',
            isLast: true,
          ),
        ),
      ],
    );
  }
}
