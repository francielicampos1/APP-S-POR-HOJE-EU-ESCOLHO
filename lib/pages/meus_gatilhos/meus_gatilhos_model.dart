import '/components/bottom_nav/bottom_nav_widget.dart';
import '/components/bottom_nav_child2/bottom_nav_child2_widget.dart';
import '/components/button/button_widget.dart';
import '/components/pattern_card/pattern_card_widget.dart';
import '/components/trigger_chip/trigger_chip_widget.dart';
import '/flutter_flow/flutter_flow_charts.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'dart:ui';
import '/index.dart';
import 'meus_gatilhos_widget.dart' show MeusGatilhosWidget;
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';

class MeusGatilhosModel extends FlutterFlowModel<MeusGatilhosWidget> {
  ///  State fields for stateful widgets in this page.

  // Model for PatternCard.
  late PatternCardModel patternCardModel1;
  // Model for PatternCard.
  late PatternCardModel patternCardModel2;
  // Model for TriggerChip.
  late TriggerChipModel triggerChipModel1;
  // Model for TriggerChip.
  late TriggerChipModel triggerChipModel2;
  // Model for TriggerChip.
  late TriggerChipModel triggerChipModel3;
  // Model for TriggerChip.
  late TriggerChipModel triggerChipModel4;
  // Model for Button.
  late ButtonModel buttonModel;
  // Model for BottomNav.
  late BottomNavModel bottomNavModel;

  @override
  void initState(BuildContext context) {
    patternCardModel1 = createModel(context, () => PatternCardModel());
    patternCardModel2 = createModel(context, () => PatternCardModel());
    triggerChipModel1 = createModel(context, () => TriggerChipModel());
    triggerChipModel2 = createModel(context, () => TriggerChipModel());
    triggerChipModel3 = createModel(context, () => TriggerChipModel());
    triggerChipModel4 = createModel(context, () => TriggerChipModel());
    buttonModel = createModel(context, () => ButtonModel());
    bottomNavModel = createModel(context, () => BottomNavModel());
  }

  @override
  void dispose() {
    patternCardModel1.dispose();
    patternCardModel2.dispose();
    triggerChipModel1.dispose();
    triggerChipModel2.dispose();
    triggerChipModel3.dispose();
    triggerChipModel4.dispose();
    buttonModel.dispose();
    bottomNavModel.dispose();
  }
}
