# Só Por Hoje, Eu Não Aposto — versão nativa Android (Kotlin)

App de apoio à recuperação do vício em apostas, construído nativo em **Kotlin + Jetpack Compose** —
sem Flutter, sem o motor gráfico Impeller, então o bug da tela verde não se aplica aqui.

## O que já está pronto
- **Onboarding** de 5 telas na primeira abertura (boas-vindas, "você está no lugar certo", "não precisa
  ser perfeito", "vamos entender você", "uma escolha para hoje" com a pergunta "o que trouxe você até aqui").
- **Início** como tela de check-in ("Como você está agora?" — Estou bem / Com vontade / Com dificuldade),
  com atalhos discretos pra Meu Dinheiro, Entenda o Vício e Proteja-se, e resumo de progresso.
- **Estou com Vontade de Apostar**: fluxo guiado de 5 telas (Pausa → Sentimentos → Pensamento →
  cronômetro de 10 minutos com ações → "como você está agora", com 3 ramificações: melhorou/vai continuar,
  ainda com vontade forte, ou apostou — cada uma tratada sem julgamento).
- **Meu Plano**: plano de ação numerado (1 a 5, com os passos 3 e 4 personalizáveis) + seção reflexiva
  (motivo, pessoas de apoio, o que quer recuperar na vida) + atalho pra configurar a Proteção.
- **Minhas formas de me proteger**: configuração prévia (afastamento das apostas, barreira financeira,
  ações favoritas, contatos de confiança) usada dentro do fluxo de vontade.
- **Meus Gatilhos**: agora é um registro histórico (cada toque conta uma vez), com categorias atualizadas
  (incluindo Ambiente digital), a etapa "O que acontece dentro da minha cabeça?" (pensamentos), registro
  detalhado de episódios (5 perguntas), e "Meu Padrão" (mostra os gatilhos mais frequentes e sugere criar
  uma forma de proteção).
- **Minhas formas de me proteger**: unificado — bloqueio de sites/apps, autoexclusão (com "já fiz/ainda não"),
  proteger o dinheiro, reduzir estímulos, pessoa de confiança (com preferência de contato), e links diretos
  pra Meus Gatilhos e Meu Plano em vez de duplicar os mesmos campos.
- **Preciso de Ajuda**: virou uma central de saída com 5 opções (Falar com alguém, Ver canais de apoio,
  Dificultar meu acesso às apostas, Não sei qual ajuda preciso, e a nota de emergência separada).
- **Meu Plano**: agora com o padrão Editar/Salvar — mostra o que já foi salvo em modo leitura, só grava de
  verdade quando você aperta "Salvar".
- **Meu Progresso**, **Meu Diário**, **Meu Dinheiro**, **Entenda o Vício** (com o ciclo de 6 passos),
  **Proteja-se das Apostas**, **Preciso de Ajuda** (CVV, SUS, CAPS, Jogadores Anônimos) — todos com o
  texto completo que você escreveu.
- **Configurações**: aviso de que o app não diagnostica/substitui profissionais, e "Apoie este projeto"
  com Pix oculto (só copia, nunca mostra o número).
- Anúncios (AdMob) com IDs de teste, nas 4 telas aprovadas (Início, Progresso, Dinheiro, Entenda o Vício).
- Paleta visual: off-white, verde sálvia, azul-petróleo, terracota suave.

## O que ainda falta (próximos passos)
- **Persistência permanente**: hoje os dados somem se você fechar o app (inclusive o onboarding reaparece
  toda vez). O próximo passo é ligar isso a um banco local (Room).
- Botões "Ligar para 188" / "ACESSAR CVV" / etc. ainda não abrem o telefone/link de verdade — só o desenho
  está pronto (fácil de ligar depois).
- Ícone do app: hoje é um desenho simples em vetor.

## Sobre o Pix ("Apoie este projeto")
A chave Pix está no código (`ConfiguracoesScreen.kt`, constante `CHAVE_PIX`) mas **nunca aparece na tela** —
só é copiada pra área de transferência quando a pessoa toca em "Copiar chave Pix".

## Sobre os anúncios (AdMob)
Já funciona com **IDs de teste oficiais do Google**. Antes de publicar de verdade:
1. Criar conta em https://admob.google.com (gratuita).
2. Trocar o App ID de teste no `AndroidManifest.xml` (`com.google.android.gms.ads.APPLICATION_ID`) pelo seu.
3. Trocar o Ad Unit ID de teste em `ui/components/BannerAnuncio.kt` (constante `ID_BANNER_TESTE`) pelo seu.
4. Na conta AdMob, em Bloqueios → Categorias de conteúdo, bloquear "Apostas e jogos de azar" e
   "Crédito e empréstimos".

## Como abrir e testar
1. Baixe e instale o **Android Studio** (gratuito): https://developer.android.com/studio
2. Abra o Android Studio → **Open** → selecione a pasta deste projeto (lembre de tirar acentos do
   nome da pasta, como fizemos da última vez).
3. Espere o "Gradle Sync" terminar.
4. Conecte o celular via USB (Depuração USB já ativada).
5. Clique no botão verde de "Run" (▶).

## Publicar na Google Play (quando estiver pronto)
Esse projeto já mira no Android 16 (API 36), exigido pelo Google pra apps novos desde 31/08/2026.
1. Criar conta de desenvolvedor no Google Play Console (taxa única de US$25).
2. Gerar uma versão assinada (Android Studio: Build → Generate Signed App Bundle).
3. Preencher a ficha da loja (nome, descrição, capturas de tela, política de privacidade).
4. Responder o questionário de classificação de conteúdo.
5. Enviar pra revisão.

Como o app não oferece apostas, ele não se enquadra nas regras especiais de licença de jogo do Google.
