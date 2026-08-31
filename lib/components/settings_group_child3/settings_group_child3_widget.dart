import '/components/settings_item/settings_item_widget.dart';
import '/custom_code/actions/privacy_policy_page.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';
import 'settings_group_child3_model.dart';
export 'settings_group_child3_model.dart';

class SettingsGroupChild3Widget extends StatefulWidget {
  const SettingsGroupChild3Widget({super.key});

  @override
  State<SettingsGroupChild3Widget> createState() =>
      _SettingsGroupChild3WidgetState();
}

class _SettingsGroupChild3WidgetState extends State<SettingsGroupChild3Widget> {
  late SettingsGroupChild3Model _model;

  @override
  void setState(VoidCallback callback) {
    super.setState(callback);
    _model.onUpdate();
  }

  @override
  void initState() {
    super.initState();
    _model = createModel(context, () => SettingsGroupChild3Model());
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
              Icons.info_outline_rounded,
              color: FlutterFlowTheme.of(context).secondaryText,
              size: 22.0,
            ),
            label: 'Sobre o Só por Hoje',
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
              Icons.help,
              color: FlutterFlowTheme.of(context).secondaryText,
              size: 22.0,
            ),
            label: 'Termos de Uso',
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
              Icons.help_outline_rounded,
              color: FlutterFlowTheme.of(context).secondaryText,
              size: 22.0,
            ),
            label: 'Central de Ajuda',
            showArrow: 'Show Arrow',
            showArrowPresent: true,
            value: 'ativadas',
            isLast: false,
          ),
        ),
        GestureDetector(
          onTap: () {
            Navigator.of(context).push(
              MaterialPageRoute(builder: (_) => const PrivacyPolicyPage()),
            );
          },
          child: wrapWithModel(
            model: _model.settingsItemModel3,
            updateCallback: () => safeSetState(() {}),
            child: SettingsItemWidget(
              icon: Icon(
                Icons.privacy_tip_outlined,
                color: FlutterFlowTheme.of(context).secondaryText,
                size: 22.0,
              ),
              label: 'Política de Privacidade',
              showArrow: 'Show Arrow',
              showArrowPresent: true,
              value: 'ativadas',
              isLast: true,
            ),
          ),
        ),
      ],
    );
  }
}
