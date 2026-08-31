import '/components/action_card/action_card_widget.dart';
import '/components/bottom_nav/bottom_nav_widget.dart';
import '/components/bottom_nav_child/bottom_nav_child_widget.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'dart:ui';
import '/index.dart';
import 'in_cio_widget.dart' show InCioWidget;
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';

class InCioModel extends FlutterFlowModel<InCioWidget> {
  ///  State fields for stateful widgets in this page.

  // Model for ActionCard.
  late ActionCardModel actionCardModel1;
  // Model for ActionCard.
  late ActionCardModel actionCardModel2;
  // Model for ActionCard.
  late ActionCardModel actionCardModel3;
  // Model for BottomNav.
  late BottomNavModel bottomNavModel;

  @override
  void initState(BuildContext context) {
    actionCardModel1 = createModel(context, () => ActionCardModel());
    actionCardModel2 = createModel(context, () => ActionCardModel());
    actionCardModel3 = createModel(context, () => ActionCardModel());
    bottomNavModel = createModel(context, () => BottomNavModel());
  }

  @override
  void dispose() {
    actionCardModel1.dispose();
    actionCardModel2.dispose();
    actionCardModel3.dispose();
    bottomNavModel.dispose();
  }
}
