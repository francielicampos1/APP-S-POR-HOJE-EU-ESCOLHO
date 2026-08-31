import 'package:flutter/material.dart';

/// Tela de Política de Privacidade — acessível de dentro do app (exigência
/// do Google Play e do AdMob: a política precisa estar tanto num link
/// público quanto disponível como texto dentro do próprio app).
class PrivacyPolicyPage extends StatelessWidget {
  const PrivacyPolicyPage({super.key});

  static const String texto = '''
Última atualização: 30 de agosto de 2026

Este aplicativo foi criado para apoiar pessoas em recuperação do vício em apostas. Quase tudo que você registra aqui fica só no seu aparelho — não em nenhum servidor nosso.


1. QUAIS DADOS O APP GUARDA

O app permite registrar, por vontade própria: entradas de diário, gatilhos, contatos de apoio, passos do seu plano, e informações de progresso (data de início, gasto médio diário, dívidas pagas, recaídas).

Esses dados:
• Ficam guardados SOMENTE no seu aparelho.
• Nunca são enviados para nenhum servidor, nosso ou de terceiros.
• Nunca são compartilhados com ninguém.
• Podem ser apagados por você a qualquer momento, em Configurações → Apagar meus dados.

O app não exige cadastro, login, e-mail ou qualquer identificação pessoal para ser usado.


2. ANÚNCIOS (Google AdMob)

Este app exibe anúncios fornecidos pelo Google AdMob. Para isso funcionar, o AdMob pode coletar automaticamente: identificador de publicidade do aparelho, endereço IP, informações gerais do dispositivo (modelo, sistema operacional) e dados de interação com o anúncio.

Essas informações são coletadas e usadas pelo Google de acordo com a política de privacidade do próprio Google: https://policies.google.com/privacy

Você pode limitar a personalização de anúncios nas configurações de privacidade do seu Android (Configurações do aparelho → Google → Anúncios).

Este app bloqueia manualmente anúncios das categorias "Apostas e Jogos de Azar" e "Cassino Social" — eles não devem aparecer aqui.


3. LINKS EXTERNOS

Algumas telas contêm links para serviços externos de apoio (CVV, CAPS, Unidades Básicas de Saúde, Jogadores Anônimos, Jog-Anon, e a plataforma de autoexclusão do governo federal em gov.br) e permitem ligar diretamente para números de telefone. Esses serviços são independentes, com políticas de privacidade próprias — não temos acesso ao que acontece neles.


4. CONTRIBUIÇÕES (Pix)

O app tem um botão opcional que copia um código Pix para a área de transferência do seu aparelho, caso você queira contribuir com o desenvolvimento. Nenhum dado seu é enviado a nós nesse processo — é só uma cópia de texto local.


5. CRIANÇAS

Este app não é direcionado a menores de 18 anos e não coleta intencionalmente dados de crianças.


6. ALTERAÇÕES NESTA POLÍTICA

Esta política pode ser atualizada eventualmente. Mudanças relevantes serão refletidas na data no topo desta página.


7. CONTATO

Dúvidas sobre privacidade? Fale com: francielicampos1@gmail.com
''';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Política de Privacidade'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_rounded),
          onPressed: () => Navigator.of(context).pop(),
        ),
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(24),
        child: Text(texto, style: const TextStyle(fontSize: 14, height: 1.5)),
      ),
    );
  }
}
