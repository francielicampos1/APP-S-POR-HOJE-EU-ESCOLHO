import 'package:flutter/material.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(const StartupTestApp());
}

class StartupTestApp extends StatelessWidget {
  const StartupTestApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Só por Hoje, Eu Escolho',
      home: Scaffold(
        backgroundColor: const Color(0xFFFCF9F4),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: const [
              Icon(
                Icons.check_circle_outline_rounded,
                color: Color(0xFF2D6A4F),
                size: 72,
              ),
              SizedBox(height: 20),
              Text(
                'TESTE DE ABERTURA',
                style: TextStyle(
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                  color: Color(0xFF1B2E26),
                ),
              ),
              SizedBox(height: 10),
              Text(
                'Se você está vendo esta tela,\no Flutter abriu corretamente.',
                textAlign: TextAlign.center,
                style: TextStyle(
                  fontSize: 16,
                  color: Color(0xFF5C6B64),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
