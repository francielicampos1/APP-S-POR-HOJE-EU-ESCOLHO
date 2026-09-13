package com.francielicampos.sohoje.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.francielicampos.sohoje.data.AppState
import com.francielicampos.sohoje.util.abrirBuscaGoogle
import com.francielicampos.sohoje.util.abrirBuscaMaps
import com.francielicampos.sohoje.util.abrirUrl
import com.francielicampos.sohoje.util.abrirWhatsApp

private sealed class TelaAjuda {
    data object Hub : TelaAjuda()
    data object FalarComAlguem : TelaAjuda()
    data object CanaisDeApoio : TelaAjuda()
    data object DificultarAcesso : TelaAjuda()
}

@Composable
fun PrecisoDeAjudaScreen(appState: AppState, aoIrParaProtecao: () -> Unit) {
    var tela by remember { mutableStateOf<TelaAjuda>(TelaAjuda.Hub) }

    AnimatedContent(targetState = tela, label = "telaAjuda") { telaAtual ->
        when (telaAtual) {
            is TelaAjuda.Hub -> HubAjuda(
                aoEscolherFalar = { tela = TelaAjuda.FalarComAlguem },
                aoEscolherCanais = { tela = TelaAjuda.CanaisDeApoio },
                aoEscolherDificultar = { tela = TelaAjuda.DificultarAcesso }
            )
            is TelaAjuda.FalarComAlguem -> TelaAjudaFalarComAlguem(appState, aoVoltar = { tela = TelaAjuda.Hub })
            is TelaAjuda.CanaisDeApoio -> TelaAjudaCanais(aoVoltar = { tela = TelaAjuda.Hub })
            is TelaAjuda.DificultarAcesso -> TelaAjudaDificultarAcesso(aoVoltar = { tela = TelaAjuda.Hub }, aoIrParaProtecao = aoIrParaProtecao)
        }
    }
}

@Composable
private fun HubAjuda(
    aoEscolherFalar: () -> Unit,
    aoEscolherCanais: () -> Unit,
    aoEscolherDificultar: () -> Unit
) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp)) {
        Text("Preciso de Ajuda", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Você não precisa resolver tudo sozinho(a). Escolha o tipo de ajuda que precisa agora.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        OpcaoHub("🤝", "Falar com alguém", "Peça companhia pra uma pessoa de confiança agora.", aoEscolherFalar)
        Spacer(Modifier.height(10.dp))
        OpcaoHub("📞", "Ver canais de apoio", "CVV, CAPS, SUS e Jogadores Anônimos.", aoEscolherCanais)
        Spacer(Modifier.height(10.dp))
        OpcaoHub("📱", "Dificultar meu acesso às apostas", "Um checklist rápido pra agir agora.", aoEscolherDificultar)

        Spacer(Modifier.height(24.dp))
        Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)) {
            Column(Modifier.padding(18.dp)) {
                Text("🌱 Se estiver difícil agora", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                Spacer(Modifier.height(6.dp))
                Text(
                    "Você não precisa resolver sua vida inteira neste momento. Pare. Respire. Afaste-se da aposta e procure alguém.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    "Se houver risco imediato à sua segurança ou uma emergência, procure um serviço de emergência da sua região.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
private fun OpcaoHub(emoji: String, titulo: String, legenda: String, aoClicar: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp), onClick = aoClicar) {
        Row(Modifier.padding(16.dp)) {
            Text(emoji, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.width(14.dp))
            Column {
                Text(titulo, style = MaterialTheme.typography.titleMedium)
                Text(legenda, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f))
            }
        }
    }
}

@Composable
private fun TelaAjudaFalarComAlguem(appState: AppState, aoVoltar: () -> Unit) {
    val context = LocalContext.current
    val contato = appState.contatosConfianca.firstOrNull()
    val mensagem = "Oi. Estou passando por um momento difícil e estou com vontade de apostar. Pode conversar comigo um pouco?"

    Column(Modifier.fillMaxSize().padding(20.dp)) {
        TextButton(onClick = aoVoltar) { Text("← Voltar") }
        Spacer(Modifier.height(20.dp))
        Text("Precisa de companhia agora?", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text("Fale com alguém em quem você confia.", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(20.dp))
        if (contato != null) {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Text("\"$mensagem\"", Modifier.padding(16.dp))
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = { abrirWhatsApp(context, contato.telefone, mensagem) }, modifier = Modifier.fillMaxWidth()) {
                Text("ENVIAR MENSAGEM")
            }
        } else {
            Text("Você ainda não cadastrou uma pessoa de confiança. Configure em Meu Plano → Minhas formas de me proteger.")
        }
    }
}

@Composable
private fun TelaAjudaCanais(aoVoltar: () -> Unit) {
    val context = LocalContext.current
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp)) {
        TextButton(onClick = aoVoltar) { Text("← Voltar") }
        Spacer(Modifier.height(12.dp))
        Text(
            "Não sabe por onde começar? Um serviço de saúde pode ajudar você a entender o que está acontecendo e encontrar o suporte adequado.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        RecursoAjuda(Icons.Filled.Call, "CVV — Centro de Valorização da Vida", "Apoio gratuito e sigiloso: 188, ou cvv.org.br.", "ACESSAR CVV") {
            abrirUrl(context, "https://www.cvv.org.br")
        }
        RecursoAjuda(Icons.Filled.LocalHospital, "Encontre atendimento pelo SUS", "Procure a UBS mais próxima.", "ENCONTRAR ATENDIMENTO") {
            abrirBuscaMaps(context, "UBS - Unidade Básica de Saúde")
        }
        RecursoAjuda(Icons.Filled.Psychology, "Saúde mental — CAPS", "Porta de entrada pra cuidados em saúde mental pelo SUS.", "ENCONTRAR CAPS") {
            abrirBuscaMaps(context, "CAPS - Centro de Atenção Psicossocial")
        }
        RecursoAjuda(Icons.Filled.Groups, "Jogadores Anônimos", "Apoio entre pessoas com problemas relacionados ao jogo.", "ACESSAR JOGADORES ANÔNIMOS") {
            abrirBuscaGoogle(context, "Jogadores Anônimos perto de mim")
        }
    }
}

@Composable
private fun RecursoAjuda(icone: androidx.compose.ui.graphics.vector.ImageVector, titulo: String, descricao: String, botao: String, aoClicar: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp), shape = RoundedCornerShape(16.dp)) {
        Column(Modifier.padding(18.dp)) {
            Row {
                Icon(icone, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(titulo, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(4.dp))
                    Text(descricao, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(Modifier.height(12.dp))
            Button(onClick = aoClicar, modifier = Modifier.fillMaxWidth()) { Text(botao) }
        }
    }
}

private val itensBloqueio = listOf(
    "Ativar bloqueio de sites", "Ativar bloqueio de aplicativos", "Sair das contas de apostas",
    "Remover aplicativos de apostas", "Retirar cartões/meios de pagamento salvos", "Ativar autoexclusão quando disponível"
)

@Composable
private fun TelaAjudaDificultarAcesso(aoVoltar: () -> Unit, aoIrParaProtecao: () -> Unit) {
    var marcados by remember { mutableStateOf(setOf<String>()) }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp)) {
        TextButton(onClick = aoVoltar) { Text("← Voltar") }
        Spacer(Modifier.height(12.dp))
        Text("Quero dificultar meu acesso às apostas", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        itensBloqueio.forEach { item ->
            Row(Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Checkbox(
                    checked = item in marcados,
                    onCheckedChange = { marcados = if (item in marcados) marcados - item else marcados + item }
                )
                Text(item, Modifier.padding(top = 12.dp), style = MaterialTheme.typography.bodyLarge)
            }
        }
        Spacer(Modifier.height(20.dp))
        Button(onClick = aoIrParaProtecao, modifier = Modifier.fillMaxWidth()) { Text("COMEÇAR AGORA") }
    }
}
