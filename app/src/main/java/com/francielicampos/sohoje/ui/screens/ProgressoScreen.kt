package com.francielicampos.sohoje.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.francielicampos.sohoje.TopoComVoltar
import com.francielicampos.sohoje.data.AppState
import com.francielicampos.sohoje.ui.components.BannerAnuncio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressoScreen(appState: AppState, aoVoltar: () -> Unit) {
    val dias by appState.diasConsecutivosSemApostar
    val valorNaoGasto by appState.valorTotalNaoGasto
    val valorApostavaAntes by appState.valorApostavaAntes
    val formatoMoeda = java.text.NumberFormat.getCurrencyInstance(java.util.Locale("pt", "BR"))

    Scaffold(topBar = { TopoComVoltar("Meu Progresso", aoVoltar) }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(20.dp)
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(Modifier.padding(24.dp)) {
                    Text("$dias", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))
                    Text(if (dias == 1) "momento superado" else "momentos superados", style = MaterialTheme.typography.bodyLarge)
                }
            }

            if (valorApostavaAntes > 0) {
                Spacer(Modifier.height(12.dp))
                Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                    Text(
                        "Comparado ao que você apostava antes (${formatoMoeda.format(valorApostavaAntes)}), você já preservou ${formatoMoeda.format(valorNaoGasto)}.",
                        Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
            Bloco(
                "Seu progresso não é apenas contar dias",
                "Parar ou diminuir as apostas não acontece necessariamente em linha reta. Existem dias tranquilos, dias difíceis e dias em que você pode voltar a apostar. Por isso, seu progresso também pode estar em: perceber um gatilho; reconhecer uma vontade antes de apostar; conseguir esperar alguns minutos; deixar uma vontade passar; evitar uma situação de risco; pedir ajuda; escolher outra atividade; entender melhor o que aconteceu depois de uma aposta."
            )
            Bloco(
                "Pequenos passos também contam",
                "Talvez hoje você consiga interromper o ciclo por alguns minutos. Talvez amanhã consiga evitar uma aposta. Talvez você perceba algo sobre você que nunca tinha percebido antes. Isso também é progresso."
            )
            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)) {
                Column(Modifier.padding(18.dp)) {
                    Text("Se você apostar novamente", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Não significa que tudo foi perdido. Em vez de pensar \"Eu fracassei\", tente perguntar \"O que aconteceu?\" O objetivo não é ser perfeito. É entender cada vez melhor o seu caminho.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            Bloco("Só por hoje", "Você não precisa pensar em todos os dias que virão. Qual é o próximo passo que você consegue dar hoje?")

            Spacer(Modifier.height(12.dp))
            Text("Histórico recente", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(10.dp))
            if (appState.historico.isEmpty()) {
                Text("Seus dias registrados vão aparecer aqui.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            } else {
                appState.historico.forEach { registro ->
                    Card(Modifier.fillMaxWidth().padding(bottom = 8.dp), shape = RoundedCornerShape(12.dp)) {
                        Text(registro.data.toString(), Modifier.padding(14.dp))
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
            BannerAnuncio()
        }
    }
}

@Composable
private fun Bloco(titulo: String, texto: String) {
    Column(Modifier.padding(bottom = 20.dp)) {
        Text(titulo, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
        Spacer(Modifier.height(6.dp))
        Text(texto, style = MaterialTheme.typography.bodyLarge)
    }
}
