import 'package:flutter/material.dart';
import 'package:google_mobile_ads/google_mobile_ads.dart';

/// ATENÇÃO: este é o ID DE TESTE oficial do Google (sempre devolve um
/// anúncio de exemplo, nunca um anúncio real). Antes de publicar de
/// verdade, troque pelo ID do seu próprio bloco de anúncio, criado em
/// admob.google.com (Apps > seu app > Ad units).
///
/// IMPORTANTE, faça isso antes de publicar de verdade: dentro do AdMob,
/// em Blocking controls > Manage sensitive categories, desmarque
/// "Gambling & Betting" e "Social Casino Games" pra esse app nunca
/// mostrar anúncio de aposta/cassino.
const String kTestBannerAdUnitId = 'ca-app-pub-3940256099942544/6300978111';

/// Rodapé pequeno de anúncio (banner, o menor tamanho padrão: 320x50).
/// Use só em telas neutras — nunca nas de crise (Preciso de Ajuda,
/// Estou com Vontade de Apostar).
class SmallAdFooter extends StatefulWidget {
  const SmallAdFooter({super.key, this.adUnitId = kTestBannerAdUnitId});

  final String adUnitId;

  @override
  State<SmallAdFooter> createState() => _SmallAdFooterState();
}

class _SmallAdFooterState extends State<SmallAdFooter> {
  BannerAd? _banner;
  bool _carregado = false;

  @override
  void initState() {
    super.initState();
    _carregarAnuncio();
  }

  void _carregarAnuncio() {
    _banner = BannerAd(
      adUnitId: widget.adUnitId,
      size: AdSize.banner, // 320x50 — o menor tamanho fixo padrão
      request: const AdRequest(),
      listener: BannerAdListener(
        onAdLoaded: (_) {
          if (mounted) setState(() => _carregado = true);
        },
        onAdFailedToLoad: (ad, error) {
          ad.dispose();
        },
      ),
    )..load();
  }

  @override
  void dispose() {
    _banner?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    if (!_carregado || _banner == null) return const SizedBox.shrink();
    return Container(
      alignment: Alignment.center,
      width: _banner!.size.width.toDouble(),
      height: _banner!.size.height.toDouble(),
      margin: const EdgeInsets.symmetric(vertical: 4),
      child: AdWidget(ad: _banner!),
    );
  }
}
