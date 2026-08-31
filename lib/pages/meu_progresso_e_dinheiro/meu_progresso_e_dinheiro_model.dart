import '/components/bottom_nav/bottom_nav_widget.dart';
import '/components/bottom_nav_child4/bottom_nav_child4_widget.dart';
import '/components/button/button_widget.dart';
import '/components/history_card/history_card_widget.dart';
import '/components/progress_metric/progress_metric_widget.dart';
import '/flutter_flow/flutter_flow_charts.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'dart:ui';
import '/index.dart';
import 'meu_progresso_e_dinheiro_widget.dart' show MeuProgressoEDinheiroWidget;
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';

class MeuProgressoEDinheiroModel
    extends FlutterFlowModel<MeuProgressoEDinheiroWidget> {
  ///  State fields for stateful widgets in this page.

  // Model for ProgressMetric.
  late ProgressMetricModel progressMetricModel1;
  // Model for ProgressMetric.
  late ProgressMetricModel progressMetricModel2;
  // Model for Button.
  late ButtonModel buttonModel;
  // Model for HistoryCard.
  late HistoryCardModel historyCardModel1;
  // Model for HistoryCard.
  late HistoryCardModel historyCardModel2;
  // Model for HistoryCard.
  late HistoryCardModel historyCardModel3;
  // Model for BottomNav.
  late BottomNavModel bottomNavModel;

  @override
  void initState(BuildContext context) {
    progressMetricModel1 = createModel(context, () => ProgressMetricModel());
    progressMetricModel2 = createModel(context, () => ProgressMetricModel());
    buttonModel = createModel(context, () => ButtonModel());
    historyCardModel1 = createModel(context, () => HistoryCardModel());
    historyCardModel2 = createModel(context, () => HistoryCardModel());
    historyCardModel3 = createModel(context, () => HistoryCardModel());
    bottomNavModel = createModel(context, () => BottomNavModel());
  }

  @override
  void dispose() {
    progressMetricModel1.dispose();
    progressMetricModel2.dispose();
    buttonModel.dispose();
    historyCardModel1.dispose();
    historyCardModel2.dispose();
    historyCardModel3.dispose();
    bottomNavModel.dispose();
  }
}
