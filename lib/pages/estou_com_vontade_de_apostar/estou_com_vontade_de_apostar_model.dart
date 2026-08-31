import '/components/action_step/action_step_widget.dart';
import '/components/button/button_widget.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'dart:ui';
import '/index.dart';
import 'estou_com_vontade_de_apostar_widget.dart'
    show EstouComVontadeDeApostarWidget;
import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:percent_indicator/percent_indicator.dart';
import 'package:provider/provider.dart';

class EstouComVontadeDeApostarModel
    extends FlutterFlowModel<EstouComVontadeDeApostarWidget> {
  ///  State fields for stateful widgets in this page.

  // Model for ActionStep.
  late ActionStepModel actionStepModel1;
  // Model for ActionStep.
  late ActionStepModel actionStepModel2;
  // Model for ActionStep.
  late ActionStepModel actionStepModel3;
  // Model for Button.
  late ButtonModel buttonModel1;
  // Model for Button.
  late ButtonModel buttonModel2;

  @override
  void initState(BuildContext context) {
    actionStepModel1 = createModel(context, () => ActionStepModel());
    actionStepModel2 = createModel(context, () => ActionStepModel());
    actionStepModel3 = createModel(context, () => ActionStepModel());
    buttonModel1 = createModel(context, () => ButtonModel());
    buttonModel2 = createModel(context, () => ButtonModel());
  }

  @override
  void dispose() {
    actionStepModel1.dispose();
    actionStepModel2.dispose();
    actionStepModel3.dispose();
    buttonModel1.dispose();
    buttonModel2.dispose();
  }
}
