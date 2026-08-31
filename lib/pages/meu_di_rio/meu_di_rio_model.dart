import '/components/bottom_nav/bottom_nav_widget.dart';
import '/components/bottom_nav_child5/bottom_nav_child5_widget.dart';
import '/components/button/button_widget.dart';
import '/components/diary_entry/diary_entry_widget.dart';
import '/components/text_field/text_field_widget.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'dart:ui';
import '/index.dart';
import 'meu_di_rio_widget.dart' show MeuDiRioWidget;
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';

class MeuDiRioModel extends FlutterFlowModel<MeuDiRioWidget> {
  ///  State fields for stateful widgets in this page.

  // Model for TextField.
  late TextFieldModel textFieldModel;
  // Model for Button.
  late ButtonModel buttonModel;
  // Model for DiaryEntry.
  late DiaryEntryModel diaryEntryModel1;
  // Model for DiaryEntry.
  late DiaryEntryModel diaryEntryModel2;
  // Model for DiaryEntry.
  late DiaryEntryModel diaryEntryModel3;
  // Model for DiaryEntry.
  late DiaryEntryModel diaryEntryModel4;
  // Model for BottomNav.
  late BottomNavModel bottomNavModel;

  @override
  void initState(BuildContext context) {
    textFieldModel = createModel(context, () => TextFieldModel());
    buttonModel = createModel(context, () => ButtonModel());
    diaryEntryModel1 = createModel(context, () => DiaryEntryModel());
    diaryEntryModel2 = createModel(context, () => DiaryEntryModel());
    diaryEntryModel3 = createModel(context, () => DiaryEntryModel());
    diaryEntryModel4 = createModel(context, () => DiaryEntryModel());
    bottomNavModel = createModel(context, () => BottomNavModel());
  }

  @override
  void dispose() {
    textFieldModel.dispose();
    buttonModel.dispose();
    diaryEntryModel1.dispose();
    diaryEntryModel2.dispose();
    diaryEntryModel3.dispose();
    diaryEntryModel4.dispose();
    bottomNavModel.dispose();
  }
}
