import '/components/bottom_nav/bottom_nav_widget.dart';
import '/components/bottom_nav_child6/bottom_nav_child6_widget.dart';
import '/components/button/button_widget.dart';
import '/components/lesson_card/lesson_card_widget.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'dart:ui';
import '/index.dart';
import 'entenda_o_v_cio_widget.dart' show EntendaOVCioWidget;
import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';

class EntendaOVCioModel extends FlutterFlowModel<EntendaOVCioWidget> {
  ///  State fields for stateful widgets in this page.

  // Model for LessonCard.
  late LessonCardModel lessonCardModel1;
  // Model for LessonCard.
  late LessonCardModel lessonCardModel2;
  // Model for LessonCard.
  late LessonCardModel lessonCardModel3;
  // Model for LessonCard.
  late LessonCardModel lessonCardModel4;
  // Model for Button.
  late ButtonModel buttonModel;
  // Model for BottomNav.
  late BottomNavModel bottomNavModel;

  @override
  void initState(BuildContext context) {
    lessonCardModel1 = createModel(context, () => LessonCardModel());
    lessonCardModel2 = createModel(context, () => LessonCardModel());
    lessonCardModel3 = createModel(context, () => LessonCardModel());
    lessonCardModel4 = createModel(context, () => LessonCardModel());
    buttonModel = createModel(context, () => ButtonModel());
    bottomNavModel = createModel(context, () => BottomNavModel());
  }

  @override
  void dispose() {
    lessonCardModel1.dispose();
    lessonCardModel2.dispose();
    lessonCardModel3.dispose();
    lessonCardModel4.dispose();
    buttonModel.dispose();
    bottomNavModel.dispose();
  }
}
