package com.francielicampos.sohoje.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.francielicampos.sohoje.data.AppState
import com.francielicampos.sohoje.ui.components.BannerAnuncio
import java.text.NumberFormat
import java.util.Locale

private data class OpcaoCheckIn(
    val icone: ImageVector,
    val titulo: String,
    val legenda: String,
    val cor: Color
)

@Composable
fun InicioScreen(
    appState: AppState,
    aoClicarEstouComVontade: () -> Unit,
    aoClicarDificuldade: () -> Unit,
    aoClicarProgresso: () -> Unit,
    aoClicarDinheiro: () -> Unit,
    aoClicarEntenda: () -> Unit,
    aoClicarProtejaSe: () -> Unit,
    aoClicarConfiguracoes: () -> Unit,
    aoClicarAjudarAlguem: () -> Unit,
    aoClicarEstouBem: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Só por hoje, eu escolho. 🌱", style = MaterialTheme.typography.headlineMedium)
                Text(
                    "Só por hoje, eu não aposto.",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
            IconButton(onClick = aoClicarConfiguracoes) {
                Icon(Icons.Filled.Settings, contentDescription = "Configurações")
            }
        }

        Spacer(Modifier.height(12.dp))
        Text(
            "Você não precisa resolver tudo agora. Este é um espaço para parar, respirar, entender o que está acontecendo e escolher o próximo passo.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
        )

        Spacer(Modifier.height(28.dp))
        Text("Como você está agora?", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(14.dp))

        CartaoCheckIn(
            opcao = OpcaoCheckIn(Icons.Filled.Spa, "Estou bem", "Continue cuidando de você.", MaterialTheme.colorScheme.primary),
            selecionado = false,
            aoClicar = aoClicarEstouBem
        )
        Spacer(Modifier.height(10.dp))
        CartaoCheckIn(
            opcao = OpcaoCheckIn(Icons.Filled.Psychology, "Estou com vontade de apostar", "Pare por alguns minutos. A vontade não precisa virar uma aposta.", MaterialTheme.colorScheme.tertiary),
            selecionado = false,
            aoClicar = aoClicarEstouComVontade
        )
        Spacer(Modifier.height(10.dp))
        CartaoCheckIn(
            opcao = OpcaoCheckIn(Icons.Filled.Favorite, "Estou tendo dificuldade para parar", "Você não precisa enfrentar isso sozinho.", MaterialTheme.colorScheme.secondary),
            selecionado = false,
            aoClicar = aoClicarDificuldade
        )
        Spacer(Modifier.height(10.dp))
        CartaoCheckIn(
            opcao = OpcaoCheckIn(Icons.Filled.VolunteerActivism, "Quero ajudar alguém", "Alguém que você ama está enfrentando problemas com apostas?", MaterialTheme.colorScheme.primary),
            selecionado = false,
            aoClicar = aoClicarAjudarAlguem
        )

        Spacer(Modifier.height(16.dp))
        Text(
            "Não importa como você chegou até aqui. Você pode começar daqui.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        Spacer(Modifier.height(28.dp))
        HorizontalDivider()
        Spacer(Modifier.height(20.dp))

        ResumoProgresso(appState = appState, aoClicar = aoClicarProgresso)

        Spacer(Modifier.height(20.dp))
        Text("Explorar", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
        Spacer(Modifier.height(10.dp))
        AtalhoLinha(titulo = "Meu Dinheiro", icone = Icons.Filled.Savings, aoClicar = aoClicarDinheiro)
        AtalhoLinha(titulo = "Entenda o vício", icone = Icons.Filled.MenuBook, aoClicar = aoClicarEntenda)
        AtalhoLinha(titulo = "Proteja-se das apostas", icone = Icons.Filled.Shield, aoClicar = aoClicarProtejaSe)

        Spacer(Modifier.height(20.dp))
        Spacer(Modifier.height(20.dp))
        BannerAnuncio()
    }
}

@Composable
private fun CartaoCheckIn(opcao: OpcaoCheckIn, selecionado: Boolean, aoClicar: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { aoClicar() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selecionado) opcao.cor.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surface
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, opcao.cor.copy(alpha = 0.3f))
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(opcao.icone, contentDescription = null, tint = opcao.cor)
            Spacer(Modifier.width(14.dp))
            Column {
                Text(opcao.titulo, style = MaterialTheme.typography.titleMedium)
                Text(opcao.legenda, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f))
            }
        }
    }
}

@Composable
private fun ResumoProgresso(appState: AppState, aoClicar: () -> Unit) {
    val dias by appState.diasConsecutivosSemApostar
    val valorNaoGasto by appState.valorTotalNaoGasto
    val formatoMoeda = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    Card(
        modifier = Modifier.fillMaxWidth().clickable { aoClicar() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(Modifier.padding(18.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text("$dias", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                Text(if (dias == 1) "momento superado" else "momentos superados", style = MaterialTheme.typography.bodyMedium)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(formatoMoeda.format(valorNaoGasto), style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                Text("preservado", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
private fun AtalhoLinha(titulo: String, icone: ImageVector, aoClicar: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { aoClicar() }
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icone, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(14.dp))
        Text(titulo, style = MaterialTheme.typography.bodyLarge)
    }
}
