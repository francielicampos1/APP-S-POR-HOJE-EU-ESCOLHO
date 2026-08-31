import '/components/barrier_card/barrier_card_widget.dart';
import '/components/bottom_nav/bottom_nav_widget.dart';
import '/components/bottom_nav_child7/bottom_nav_child7_widget.dart';
import '/components/button/button_widget.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'dart:ui';
import '/index.dart';
import 'proteja_se_das_apostas_widget.dart' show ProtejaSeDasApostasWidget;
import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';

class ProtejaSeDasApostasModel
    extends FlutterFlowModel<ProtejaSeDasApostasWidget> {
  ///  State fields for stateful widgets in this page.

  // Model for BarrierCard.
  late BarrierCardModel barrierCardModel1;
  // Model for BarrierCard.
  late BarrierCardModel barrierCardModel2;
  // Model for BarrierCard.
  late BarrierCardModel barrierCardModel3;
  // Model for Button.
  late ButtonModel buttonModel;
  // Model for BottomNav.
  late BottomNavModel bottomNavModel;

  @override
  void initState(BuildContext context) {
    barrierCardModel1 = createModel(context, () => BarrierCardModel());
    barrierCardModel2 = createModel(context, () => BarrierCardModel());
    barrierCardModel3 = createModel(context, () => BarrierCardModel());
    buttonModel = createModel(context, () => ButtonModel());
    bottomNavModel = createModel(context, () => BottomNavModel());
  }

  @override
  void dispose() {
    barrierCardModel1.dispose();
    barrierCardModel2.dispose();
    barrierCardModel3.dispose();
    buttonModel.dispose();
    bottomNavModel.dispose();
  }
}
