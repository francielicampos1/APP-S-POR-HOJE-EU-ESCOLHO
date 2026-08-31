import '/components/bottom_nav/bottom_nav_widget.dart';
import '/components/bottom_nav_child8/bottom_nav_child8_widget.dart';
import '/components/button/button_widget.dart';
import '/components/support_link/support_link_widget.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'dart:ui';
import '/index.dart';
import 'preciso_de_ajuda_widget.dart' show PrecisoDeAjudaWidget;
import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';

class PrecisoDeAjudaModel extends FlutterFlowModel<PrecisoDeAjudaWidget> {
  ///  State fields for stateful widgets in this page.

  // Model for Button.
  late ButtonModel buttonModel;
  // Model for SupportLink.
  late SupportLinkModel supportLinkModel1;
  // Model for SupportLink.
  late SupportLinkModel supportLinkModel2;
  // Model for SupportLink.
  late SupportLinkModel supportLinkModel3;
  // Model for SupportLink.
  late SupportLinkModel supportLinkModel4;
  // Model for BottomNav.
  late BottomNavModel bottomNavModel;

  @override
  void initState(BuildContext context) {
    buttonModel = createModel(context, () => ButtonModel());
    supportLinkModel1 = createModel(context, () => SupportLinkModel());
    supportLinkModel2 = createModel(context, () => SupportLinkModel());
    supportLinkModel3 = createModel(context, () => SupportLinkModel());
    supportLinkModel4 = createModel(context, () => SupportLinkModel());
    bottomNavModel = createModel(context, () => BottomNavModel());
  }

  @override
  void dispose() {
    buttonModel.dispose();
    supportLinkModel1.dispose();
    supportLinkModel2.dispose();
    supportLinkModel3.dispose();
    supportLinkModel4.dispose();
    bottomNavModel.dispose();
  }
}
