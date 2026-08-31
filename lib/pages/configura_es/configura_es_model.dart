import '/components/bottom_nav/bottom_nav_widget.dart';
import '/components/bottom_nav_child9/bottom_nav_child9_widget.dart';
import '/components/button/button_widget.dart';
import '/components/settings_group/settings_group_widget.dart';
import '/components/settings_group_child/settings_group_child_widget.dart';
import '/components/settings_group_child2/settings_group_child2_widget.dart';
import '/components/settings_group_child3/settings_group_child3_widget.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'dart:ui';
import '/index.dart';
import 'configura_es_widget.dart' show ConfiguraEsWidget;
import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';

class ConfiguraEsModel extends FlutterFlowModel<ConfiguraEsWidget> {
  ///  State fields for stateful widgets in this page.

  // Model for SettingsGroup.
  late SettingsGroupModel settingsGroupModel1;
  // Model for SettingsGroup.
  late SettingsGroupModel settingsGroupModel2;
  // Model for SettingsGroup.
  late SettingsGroupModel settingsGroupModel3;
  // Model for Button.
  late ButtonModel buttonModel;
  // Model for BottomNav.
  late BottomNavModel bottomNavModel;

  @override
  void initState(BuildContext context) {
    settingsGroupModel1 = createModel(context, () => SettingsGroupModel());
    settingsGroupModel2 = createModel(context, () => SettingsGroupModel());
    settingsGroupModel3 = createModel(context, () => SettingsGroupModel());
    buttonModel = createModel(context, () => ButtonModel());
    bottomNavModel = createModel(context, () => BottomNavModel());
  }

  @override
  void dispose() {
    settingsGroupModel1.dispose();
    settingsGroupModel2.dispose();
    settingsGroupModel3.dispose();
    buttonModel.dispose();
    bottomNavModel.dispose();
  }
}
