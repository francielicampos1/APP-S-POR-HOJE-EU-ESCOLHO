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
 * Ad Unit ID real do banner, da conta AdMob de Francieli (admob.google.com).
 *
 * Lembrete: configure o bloqueio de categorias de anúncio pra excluir Apostas e jogos de
 * azar, Crédito e empréstimos — em Bloqueios > Categorias de conteúdo, na conta AdMob.
 */
private const val ID_BANNER_PRODUCAO = "ca-app-pub-7519869927873123/5969652096"

@Composable
fun BannerAnuncio(modifier: Modifier = Modifier, idAnuncio: String = ID_BANNER_PRODUCAO) {
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
