import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';

import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:flutter_web_plugins/url_strategy.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import 'flutter_flow/flutter_flow_util.dart';
import 'flutter_flow/nav/nav.dart';
import 'index.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  GoRouter.optionURLReflectsImperativeAPIs = true;
  usePathUrlStrategy();

  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();

  static _MyAppState of(BuildContext context) =>
      context.findAncestorStateOfType<_MyAppState>()!;
}

class _MyAppState extends State<MyApp> {
  ThemeMode _themeMode = ThemeMode.system;
  AppStateNotifier? _appStateNotifier;
  GoRouter? _router;
  Object? _startupError;

  @override
  void initState() {
    super.initState();

    // Primeiro deixa o Flutter desenhar uma tela própria. Só depois criamos
    // o GoRouter, para que um erro na configuração de rotas não deixe o
    // Android preso na tela de splash/NormalTheme.
    WidgetsBinding.instance.addPostFrameCallback((_) {
      if (!mounted) return;
      try {
        final notifier = AppStateNotifier.instance;
        final router = createRouter(notifier);
        if (!mounted) return;
        safeSetState(() {
          _appStateNotifier = notifier;
          _router = router;
        });
      } catch (error) {
        if (!mounted) return;
        safeSetState(() {
          _startupError = error;
        });
      }
    });

    // SharedPreferences também fica fora do caminho crítico da primeira tela.
    WidgetsBinding.instance.addPostFrameCallback((_) async {
      try {
        await FlutterFlowTheme.initialize();
        if (!mounted) return;
        safeSetState(() {
          _themeMode = FlutterFlowTheme.themeMode;
        });
      } catch (_) {
        // Mantém o tema do sistema se o armazenamento local falhar.
      }
    });
  }

  String getRoute([RouteMatch? routeMatch]) {
    final router = _router!;
    final RouteMatch lastMatch =
        routeMatch ?? router.routerDelegate.currentConfiguration.last;
    final RouteMatchList matchList = lastMatch is ImperativeRouteMatch
        ? lastMatch.matches
        : router.routerDelegate.currentConfiguration;
    return matchList.uri.path;
  }

  List<String> getRouteStack() =>
      _router!.routerDelegate.currentConfiguration.matches
          .map((e) => getRoute(e))
          .toList();

  void setThemeMode(ThemeMode mode) => safeSetState(() {
        _themeMode = mode;
        FlutterFlowTheme.saveThemeMode(mode);
      });

  ThemeData _lightTheme() => ThemeData(
        brightness: Brightness.light,
        useMaterial3: false,
      );

  ThemeData _darkTheme() => ThemeData(
        brightness: Brightness.dark,
        useMaterial3: false,
      );

  @override
  Widget build(BuildContext context) {
    final router = _router;

    if (router == null) {
      final error = _startupError;
      return MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Só por Hoje, Eu Escolho',
        theme: _lightTheme(),
        darkTheme: _darkTheme(),
        themeMode: _themeMode,
        home: error == null
            ? const _StartupScreen()
            : _StartupErrorScreen(error: error),
      );
    }

    return MaterialApp.router(
      debugShowCheckedModeBanner: false,
      title: 'Só por Hoje, Eu Escolho',
      localizationsDelegates: const [
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: const [Locale('pt', 'BR')],
      theme: _lightTheme(),
      darkTheme: _darkTheme(),
      themeMode: _themeMode,
      routerConfig: router,
    );
  }
}

class _StartupScreen extends StatelessWidget {
  const _StartupScreen();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFFCF9F4),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            const Icon(
              Icons.self_improvement_rounded,
              size: 56,
              color: Color(0xFF2D6A4F),
            ),
            const SizedBox(height: 20),
            Text(
              'Só por hoje, EU escolho.',
              textAlign: TextAlign.center,
              style: const TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.w600,
                color: Color(0xFF1B2E26),
              ),
            ),
            const SizedBox(height: 8),
            const Text(
              'Preparando o aplicativo...',
              style: TextStyle(color: Color(0xFF5C6B64)),
            ),
            const SizedBox(height: 24),
            const SizedBox(
              width: 28,
              height: 28,
              child: CircularProgressIndicator(strokeWidth: 2.5),
            ),
          ],
        ),
      ),
    );
  }
}

class _StartupErrorScreen extends StatelessWidget {
  const _StartupErrorScreen({required this.error});

  final Object error;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFFCF9F4),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(24),
          child: Center(
            child: SingleChildScrollView(
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  const Icon(
                    Icons.error_outline_rounded,
                    size: 56,
                    color: Color(0xFFE07A5F),
                  ),
                  const SizedBox(height: 16),
                  const Text(
                    'Não foi possível iniciar a navegação.',
                    textAlign: TextAlign.center,
                    style: TextStyle(
                      fontSize: 22,
                      fontWeight: FontWeight.w600,
                      color: Color(0xFF1B2E26),
                    ),
                  ),
                  const SizedBox(height: 12),
                  SelectableText(
                    error.toString(),
                    textAlign: TextAlign.center,
                    style: const TextStyle(color: Color(0xFF5C6B64)),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
