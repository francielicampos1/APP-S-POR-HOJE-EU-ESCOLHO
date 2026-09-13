package com.francielicampos.sohoje.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

/**
 * ID de anúncio de BANNER de TESTE, oficial do Google — sempre retorna um anúncio de exemplo,
 * nunca um anúncio real, então é seguro deixar aqui durante o desenvolvimento.
 *
 * IMPORTANTE: antes de publicar o app de verdade na Play Store, troque por um Ad Unit ID
 * real, criado na sua conta AdMob (admob.google.com) — um ID diferente pode ser criado
 * pra cada tela, se você quiser medir o desempenho de cada uma separadamente.
 *
 * Na sua conta AdMob, configure também o bloqueio de categorias de anúncio pra excluir:
 * Apostas e jogos de azar, Crédito e empréstimos — em Bloqueios > Categorias de conteúdo.
 */
private const val ID_BANNER_TESTE = "ca-app-pub-3940256099942544/6300978111"

@Composable
fun BannerAnuncio(modifier: Modifier = Modifier, idAnuncio: String = ID_BANNER_TESTE) {
    val context = LocalContext.current
    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = {
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = idAnuncio
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}
