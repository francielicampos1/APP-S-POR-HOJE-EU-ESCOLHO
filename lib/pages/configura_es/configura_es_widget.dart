import '/components/bottom_nav/bottom_nav_widget.dart';
import '/components/bottom_nav_child9/bottom_nav_child9_widget.dart';
import '/components/button/button_widget.dart';
import '/components/settings_group/settings_group_widget.dart';
import '/components/settings_group_child/settings_group_child_widget.dart';
import '/components/settings_group_child2/settings_group_child2_widget.dart';
import '/components/settings_group_child3/settings_group_child3_widget.dart';
import '/custom_code/actions/local_profile_store.dart';
import '/custom_code/actions/small_ad_footer.dart';
import '/custom_code/actions/pix_copia_cola.dart';
import 'package:flutter/services.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'dart:ui';
import '/index.dart';
import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';
import 'configura_es_model.dart';
export 'configura_es_model.dart';

class ConfiguraEsWidget extends StatefulWidget {
  const ConfiguraEsWidget({super.key});

  static String routeName = 'ConfiguraEs';
  static String routePath = '/configuraEs';

  @override
  State<ConfiguraEsWidget> createState() => _ConfiguraEsWidgetState();
}

class _ConfiguraEsWidgetState extends State<ConfiguraEsWidget> {
  late ConfiguraEsModel _model;

  final scaffoldKey = GlobalKey<ScaffoldState>();

  LocalProfile? _perfil;

  @override
  void initState() {
    super.initState();
    _model = createModel(context, () => ConfiguraEsModel());
    _carregarPerfil();
  }

  Future<void> _carregarPerfil() async {
    final perfil = await loadLocalProfile();
    if (mounted) setState(() => _perfil = perfil);
  }

  void _editarNome() {
    final controller = TextEditingController(text: _perfil?.name ?? '');
    showDialog(
      context: context,
      builder: (ctx) => AlertDialog(
        title: Text('Seu nome'),
        content: TextField(
          controller: controller,
          decoration: InputDecoration(hintText: 'Como quer ser chamado(a)?'),
          autofocus: true,
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.of(ctx).pop(),
            child: Text('Cancelar'),
          ),
          TextButton(
            onPressed: () async {
              final novoPerfil = LocalProfile(
                name: controller.text.trim(),
                quitDate: _perfil?.quitDate ?? DateTime.now(),
              );
              await saveLocalProfile(novoPerfil);
              if (mounted) setState(() => _perfil = novoPerfil);
              if (ctx.mounted) Navigator.of(ctx).pop();
            },
            child: Text('Salvar'),
          ),
        ],
      ),
    );
  }

  void _apagarDados() {
    showDialog(
      context: context,
      builder: (ctx) => AlertDialog(
        title: Text('Apagar meus dados'),
        content: Text(
          'Isso apaga tudo que está salvo neste aparelho — diário, gatilhos, contatos, passos do plano e seu progresso. Não tem como desfazer. Tem certeza?',
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.of(ctx).pop(),
            child: Text('Cancelar'),
          ),
          TextButton(
            onPressed: () async {
              await clearAllLocalData();
              if (ctx.mounted) Navigator.of(ctx).pop();
              if (mounted) context.goNamed(InCioWidget.routeName);
            },
            style: TextButton.styleFrom(foregroundColor: Colors.red),
            child: Text('Apagar tudo'),
          ),
        ],
      ),
    );
  }

  @override
  void dispose() {
    _model.dispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        FocusScope.of(context).unfocus();
        FocusManager.instance.primaryFocus?.unfocus();
      },
      child: Scaffold(
        key: scaffoldKey,
        backgroundColor: FlutterFlowTheme.of(context).primaryBackground,
        body: Column(
          mainAxisSize: MainAxisSize.max,
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Container(
              decoration: BoxDecoration(
                color: FlutterFlowTheme.of(context).secondaryBackground,
                shape: BoxShape.rectangle,
              ),
              child: InkWell(
                splashColor: Colors.transparent,
                focusColor: Colors.transparent,
                hoverColor: Colors.transparent,
                highlightColor: Colors.transparent,
                onTap: () async {
                  context.pushNamed(InCioWidget.routeName);
                },
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: [
                    Padding(
                      padding: EdgeInsets.all(24.0),
                      child: Container(
                        child: Row(
                          mainAxisSize: MainAxisSize.max,
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: [
                            Row(
                              mainAxisSize: MainAxisSize.max,
                              mainAxisAlignment: MainAxisAlignment.start,
                              crossAxisAlignment: CrossAxisAlignment.center,
                              children: [
                                FlutterFlowIconButton(
                                  borderRadius: 8.0,
                                  buttonSize: 40.0,
                                  fillColor: Colors.transparent,
                                  icon: Icon(
                                    Icons.arrow_back_rounded,
                                    color: FlutterFlowTheme.of(context)
                                        .primaryText,
                                    size: 24.0,
                                  ),
                                  onPressed: () async {
                                    context.safePop();
                                  },
                                ),
                                InkWell(
                                  splashColor: Colors.transparent,
                                  focusColor: Colors.transparent,
                                  hoverColor: Colors.transparent,
                                  highlightColor: Colors.transparent,
                                  onTap: () async {
                                    context.pushNamed(InCioWidget.routeName);
                                  },
                                  child: Text(
                                    'Configurações',
                                    style: FlutterFlowTheme.of(context)
                                        .titleLarge
                                        .override(
                                          font: GoogleFonts.plusJakartaSans(
                                            fontWeight: FontWeight.bold,
                                            fontStyle:
                                                FlutterFlowTheme.of(context)
                                                    .titleLarge
                                                    .fontStyle,
                                          ),
                                          letterSpacing: 0.0,
                                          fontWeight: FontWeight.bold,
                                          fontStyle:
                                              FlutterFlowTheme.of(context)
                                                  .titleLarge
                                                  .fontStyle,
                                          lineHeight: 1.3,
                                        ),
                                  ),
                                ),
                              ].divide(SizedBox(width: 16.0)),
                            ),
                          ].divide(SizedBox(width: 16.0)),
                        ),
                      ),
                    ),
                    Container(
                      height: 1.0,
                      decoration: BoxDecoration(
                        color: FlutterFlowTheme.of(context).alternate,
                        shape: BoxShape.rectangle,
                      ),
                    ),
                  ],
                ),
              ),
            ),
            Expanded(
              flex: 1,
              child: Container(
                child: SingleChildScrollView(
                  primary: false,
                  child: Column(
                    mainAxisSize: MainAxisSize.min,
                    mainAxisAlignment: MainAxisAlignment.start,
                    crossAxisAlignment: CrossAxisAlignment.stretch,
                    children: [
                      Padding(
                        padding: EdgeInsets.all(24.0),
                        child: Container(
                          child: Column(
                            mainAxisSize: MainAxisSize.min,
                            mainAxisAlignment: MainAxisAlignment.start,
                            crossAxisAlignment: CrossAxisAlignment.stretch,
                            children: [
                              GestureDetector(
                                onTap: _editarNome,
                                child: Container(
                                decoration: BoxDecoration(
                                  color: FlutterFlowTheme.of(context)
                                      .secondaryBackground,
                                  borderRadius: BorderRadius.circular(24.0),
                                  shape: BoxShape.rectangle,
                                  border: Border.all(
                                    color:
                                        FlutterFlowTheme.of(context).alternate,
                                    width: 1.0,
                                  ),
                                ),
                                child: Padding(
                                  padding: EdgeInsets.all(24.0),
                                  child: Container(
                                    child: Row(
                                      mainAxisSize: MainAxisSize.max,
                                      mainAxisAlignment:
                                          MainAxisAlignment.start,
                                      crossAxisAlignment:
                                          CrossAxisAlignment.center,
                                      children: [
                                        Container(
                                          width: 56.0,
                                          height: 56.0,
                                          decoration: BoxDecoration(
                                            color: FlutterFlowTheme.of(context)
                                                .secondary,
                                            shape: BoxShape.circle,
                                          ),
                                          alignment:
                                              AlignmentDirectional(0.0, 0.0),
                                          child: Text(
                                            (_perfil?.name.trim().isNotEmpty ?? false)
                                                ? _perfil!.name.trim()[0].toUpperCase()
                                                : '🙂',
                                            textAlign: TextAlign.center,
                                            maxLines: 1,
                                            style: FlutterFlowTheme.of(context)
                                                .labelMedium
                                                .override(
                                                  font: GoogleFonts.manrope(
                                                    fontWeight: FontWeight.w600,
                                                    fontStyle:
                                                        FlutterFlowTheme.of(
                                                                context)
                                                            .labelMedium
                                                            .fontStyle,
                                                  ),
                                                  color: FlutterFlowTheme.of(
                                                          context)
                                                      .onSecondary,
                                                  fontSize: 21.28,
                                                  letterSpacing: 0.0,
                                                  fontWeight: FontWeight.w600,
                                                  fontStyle:
                                                      FlutterFlowTheme.of(
                                                              context)
                                                          .labelMedium
                                                          .fontStyle,
                                                  lineHeight: 1.3,
                                                ),
                                            overflow: TextOverflow.clip,
                                          ),
                                        ),
                                        Column(
                                          mainAxisSize: MainAxisSize.min,
                                          mainAxisAlignment:
                                              MainAxisAlignment.start,
                                          crossAxisAlignment:
                                              CrossAxisAlignment.start,
                                          children: [
                                            Text(
                                              (_perfil?.name.trim().isNotEmpty ?? false)
                                                  ? _perfil!.name.trim()
                                                  : 'Toque para adicionar seu nome',
                                              style:
                                                  FlutterFlowTheme.of(context)
                                                      .titleMedium
                                                      .override(
                                                        font: GoogleFonts
                                                            .plusJakartaSans(
                                                          fontWeight:
                                                              FontWeight.bold,
                                                          fontStyle:
                                                              FlutterFlowTheme.of(
                                                                      context)
                                                                  .titleMedium
                                                                  .fontStyle,
                                                        ),
                                                        letterSpacing: 0.0,
                                                        fontWeight:
                                                            FontWeight.bold,
                                                        fontStyle:
                                                            FlutterFlowTheme.of(
                                                                    context)
                                                                .titleMedium
                                                                .fontStyle,
                                                        lineHeight: 1.4,
                                                      ),
                                            ),
                                            Text(
                                              'Seus dados ficam salvos só neste aparelho',
                                              style: FlutterFlowTheme.of(
                                                      context)
                                                  .bodySmall
                                                  .override(
                                                    font: GoogleFonts.manrope(
                                                      fontWeight:
                                                          FlutterFlowTheme.of(
                                                                  context)
                                                              .bodySmall
                                                              .fontWeight,
                                                      fontStyle:
                                                          FlutterFlowTheme.of(
                                                                  context)
                                                              .bodySmall
                                                              .fontStyle,
                                                    ),
                                                    color: FlutterFlowTheme.of(
                                                            context)
                                                        .secondaryText,
                                                    letterSpacing: 0.0,
                                                    fontWeight:
                                                        FlutterFlowTheme.of(
                                                                context)
                                                            .bodySmall
                                                            .fontWeight,
                                                    fontStyle:
                                                        FlutterFlowTheme.of(
                                                                context)
                                                            .bodySmall
                                                            .fontStyle,
                                                    lineHeight: 1.5,
                                                  ),
                                            ),
                                          ].divide(SizedBox(height: 4.0)),
                                        ),
                                      ].divide(SizedBox(width: 16.0)),
                                    ),
                                  ),
                                ),
                              )),
                              wrapWithModel(
                                model: _model.settingsGroupModel1,
                                updateCallback: () => safeSetState(() {}),
                                child: SettingsGroupWidget(
                                  title: 'Conta e Segurança',
                                  child: () => SettingsGroupChildWidget(),
                                ),
                              ),
                              wrapWithModel(
                                model: _model.settingsGroupModel2,
                                updateCallback: () => safeSetState(() {}),
                                child: SettingsGroupWidget(
                                  title: 'Preferências do Plano',
                                  child: () => SettingsGroupChild2Widget(),
                                ),
                              ),
                              wrapWithModel(
                                model: _model.settingsGroupModel3,
                                updateCallback: () => safeSetState(() {}),
                                child: SettingsGroupWidget(
                                  title: 'Suporte e Informações',
                                  child: () => SettingsGroupChild3Widget(),
                                ),
                              ),
                              GestureDetector(
                                onTap: () async {
                                  // Chave Pix nunca aparece em texto na tela —
                                  // só vira esse código, copiado direto.
                                  final codigo = gerarPixCopiaECola(
                                    pixKey: '39762124820',
                                  );
                                  await Clipboard.setData(
                                      ClipboardData(text: codigo));
                                  if (context.mounted) {
                                    ScaffoldMessenger.of(context).showSnackBar(
                                      SnackBar(
                                        content: Text(
                                            'Código Pix copiado! Cole no app do seu banco pra doar.'),
                                      ),
                                    );
                                  }
                                },
                                child: Container(
                                  padding: EdgeInsets.all(20.0),
                                  decoration: BoxDecoration(
                                    color: FlutterFlowTheme.of(context).primary10,
                                    borderRadius: BorderRadius.circular(20.0),
                                    border: Border.all(
                                      color: FlutterFlowTheme.of(context).primary20,
                                      width: 1.0,
                                    ),
                                  ),
                                  child: Row(
                                    children: [
                                      Icon(Icons.favorite_rounded,
                                          color:
                                              FlutterFlowTheme.of(context).primary),
                                      SizedBox(width: 12),
                                      Expanded(
                                        child: Column(
                                          crossAxisAlignment:
                                              CrossAxisAlignment.start,
                                          children: [
                                            Text(
                                              'Esse app te ajudou?',
                                              style: FlutterFlowTheme.of(context)
                                                  .bodyMedium
                                                  .override(
                                                    font: GoogleFonts.manrope(
                                                      fontWeight:
                                                          FontWeight.w600,
                                                    ),
                                                    fontWeight: FontWeight.w600,
                                                  ),
                                            ),
                                            Text(
                                              'Toque pra copiar o código Pix e contribuir com o valor que quiser',
                                              style: FlutterFlowTheme.of(context)
                                                  .bodySmall
                                                  .override(
                                                    font: GoogleFonts.manrope(),
                                                    color: FlutterFlowTheme.of(
                                                            context)
                                                        .secondaryText,
                                                  ),
                                            ),
                                          ],
                                        ),
                                      ),
                                      Icon(Icons.copy_rounded,
                                          color: FlutterFlowTheme.of(context)
                                              .secondaryText),
                                    ],
                                  ),
                                ),
                              ),
                              Padding(
                                padding: EdgeInsetsDirectional.fromSTEB(
                                    0.0, 0.0, 0.0, 32.0),
                                child: Container(
                                  child: Container(
                                    child: GestureDetector(
                                      onTap: _apagarDados,
                                      child: wrapWithModel(
                                        model: _model.buttonModel,
                                        updateCallback: () =>
                                            safeSetState(() {}),
                                        child: ButtonWidget(
                                          icon: Icon(
                                            Icons.delete_outline_rounded,
                                            color: FlutterFlowTheme.of(context)
                                                .error,
                                            size: 24.0,
                                          ),
                                          iconPresent: true,
                                          iconEndPresent: false,
                                          content: 'Apagar meus dados',
                                          variant: 'ghost',
                                          size: 'medium',
                                          fullWidth: true,
                                          loading: false,
                                          disabled: false,
                                        ),
                                      ),
                                    ),
                                  ),
                                ),
                              ),
                              Padding(
                                padding: const EdgeInsets.only(top: 8),
                                child: SmallAdFooter(),
                              ),
                            ].divide(SizedBox(height: 24.0)),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
            Align(
              alignment: AlignmentDirectional(0.0, 1.0),
              child: Container(
                child: wrapWithModel(
                  model: _model.bottomNavModel,
                  updateCallback: () => safeSetState(() {}),
                  child: BottomNavWidget(
                    child: () => BottomNavChild9Widget(),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
