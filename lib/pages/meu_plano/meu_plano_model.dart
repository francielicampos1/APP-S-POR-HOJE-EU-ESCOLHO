import '/components/action_step2/action_step2_widget.dart';
import '/components/bottom_nav/bottom_nav_widget.dart';
import '/components/bottom_nav_child3/bottom_nav_child3_widget.dart';
import '/components/contact_card/contact_card_widget.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'dart:ui';
import '/index.dart';
import 'meu_plano_widget.dart' show MeuPlanoWidget;
import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';

class MeuPlanoModel extends FlutterFlowModel<MeuPlanoWidget> {
  ///  State fields for stateful widgets in this page.

  // Model for ActionStep.
  late ActionStep2Model actionStepModel1;
  // Model for ActionStep.
  late ActionStep2Model actionStepModel2;
  // Model for ActionStep.
  late ActionStep2Model actionStepModel3;
  // Model for ContactCard.
  late ContactCardModel contactCardModel1;
  // Model for ContactCard.
  late ContactCardModel contactCardModel2;
  // Model for BottomNav.
  late BottomNavModel bottomNavModel;

  @override
  void initState(BuildContext context) {
    actionStepModel1 = createModel(context, () => ActionStep2Model());
    actionStepModel2 = createModel(context, () => ActionStep2Model());
    actionStepModel3 = createModel(context, () => ActionStep2Model());
    contactCardModel1 = createModel(context, () => ContactCardModel());
    contactCardModel2 = createModel(context, () => ContactCardModel());
    bottomNavModel = createModel(context, () => BottomNavModel());
  }

  @override
  void dispose() {
    actionStepModel1.dispose();
    actionStepModel2.dispose();
    actionStepModel3.dispose();
    contactCardModel1.dispose();
    contactCardModel2.dispose();
    bottomNavModel.dispose();
  }
}
