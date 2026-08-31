import '/components/settings_item/settings_item_widget.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';
import 'settings_group_child_model.dart';
export 'settings_group_child_model.dart';

class SettingsGroupChildWidget extends StatefulWidget {
  const SettingsGroupChildWidget({super.key});

  @override
  State<SettingsGroupChildWidget> createState() =>
      _SettingsGroupChildWidgetState();
}

class _SettingsGroupChildWidgetState extends State<SettingsGroupChildWidget> {
  late SettingsGroupChildModel _model;

  @override
  void setState(VoidCallback callback) {
    super.setState(callback);
    _model.onUpdate();
  }

  @override
  void initState() {
    super.initState();
    _model = createModel(context, () => SettingsGroupChildModel());
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
              Icons.person_outline_rounded,
              color: FlutterFlowTheme.of(context).secondaryText,
              size: 22.0,
            ),
            label: 'Editar Perfil',
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
              Icons.notifications_none_rounded,
              color: FlutterFlowTheme.of(context).secondaryText,
              size: 22.0,
            ),
            label: 'Notificações Diárias',
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
              Icons.lock_outline_rounded,
              color: FlutterFlowTheme.of(context).secondaryText,
              size: 22.0,
            ),
            label: 'Privacidade e Senha',
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
