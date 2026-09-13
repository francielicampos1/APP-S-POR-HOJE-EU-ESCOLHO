package com.francielicampos.sohoje.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.francielicampos.sohoje.TopoComVoltar
import com.francielicampos.sohoje.data.AppState

private sealed class EtapaFluxo {
    data object Pausa : EtapaFluxo()
    data object Sentimentos : EtapaFluxo()
    data object Pensamento : EtapaFluxo()
    data object EscolherAcao : EtapaFluxo()
    data class Atividade(val tipo: AtividadePausa) : EtapaFluxo()
    data object EAgora : EtapaFluxo()
    data object AindaComVontade : EtapaFluxo()
    data object Apostei : EtapaFluxo()
}

private val opcoesSentimentos = listOf(
    "😰 Ansiedade", "😡 Raiva", "😔 Tristeza", "🥱 Tédio", "😩 Estresse",
    "💰 Quero recuperar dinheiro", "🔥 Quero sentir a emoção", "🧍 Solidão", "❓ Não sei"
)
private val opcoesPensamento = listOf(
    "Vou ganhar dessa vez.", "Preciso recuperar o que perdi.", "É só uma aposta.",
    "Estou quase ganhando.", "Depois dessa eu paro.", "Preciso de dinheiro."
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstouComVontadeScreen(
    appState: AppState,
    aoVoltar: () -> Unit,
    aoIrParaGatilhos: () -> Unit,
    aoIrParaAjuda: () -> Unit
) {
    var etapa by remember { mutableStateOf<EtapaFluxo>(EtapaFluxo.Pausa) }
    var sentimentos by remember { mutableStateOf(setOf<String>()) }
    var pensamentos by remember { mutableStateOf(setOf<String>()) }
    var outroPensamento by remember { mutableStateOf("") }
    var mostrarContatos by remember { mutableStateOf(false) }

    fun voltarEtapa() {
        etapa = when (etapa) {
            is EtapaFluxo.Pausa -> { aoVoltar(); return }
            is EtapaFluxo.Sentimentos -> EtapaFluxo.Pausa
            is EtapaFluxo.Pensamento -> EtapaFluxo.Sentimentos
            is EtapaFluxo.EscolherAcao -> EtapaFluxo.Pensamento
            is EtapaFluxo.Atividade -> EtapaFluxo.EscolherAcao
            is EtapaFluxo.EAgora -> EtapaFluxo.EscolherAcao
            is EtapaFluxo.AindaComVontade -> EtapaFluxo.EAgora
            is EtapaFluxo.Apostei -> EtapaFluxo.EAgora
        }
    }

    Scaffold(topBar = { TopoComVoltar("Vontade ≠ ação", aoVoltar = { voltarEtapa() }) }) { padding ->
        Column(Modifier.padding(padding).padding(20.dp).fillMaxSize()) {
            AnimatedContent(targetState = etapa, label = "etapaVontade", modifier = Modifier.weight(1f)) { etapaAtual ->
                when (etapaAtual) {
                    is EtapaFluxo.Pausa -> TelaPausa(aoComecar = { etapa = EtapaFluxo.Sentimentos })

                    is EtapaFluxo.Sentimentos -> TelaSentimentos(
                        selecionados = sentimentos,
                        aoMudar = { sentimentos = it },
                        aoContinuar = {
                            sentimentos.forEach { opcao ->
                                val descricaoLimpa = opcao.substringAfter(" ").trim()
                                appState.adicionarGatilho(descricaoLimpa, "Emocional")
                            }
                            etapa = EtapaFluxo.Pensamento
                        }
                    )

                    is EtapaFluxo.Pensamento -> TelaPensamento(
                        selecionados = pensamentos,
                        aoMudar = { pensamentos = it },
                        outro = outroPensamento,
                        aoMudarOutro = { outroPensamento = it },
                        aoContinuar = {
                            pensamentos.forEach { appState.adicionarGatilho(it, "Pensamento") }
                            if (outroPensamento.isNotBlank()) appState.adicionarGatilho(outroPensamento, "Pensamento")
                            etapa = EtapaFluxo.EscolherAcao
                        }
                    )

                    is EtapaFluxo.EscolherAcao -> TelaEscolherAtividade(
                        aoEscolher = { etapa = EtapaFluxo.Atividade(it) }
                    )

                    is EtapaFluxo.Atividade -> {
                        val aoTerminar = { etapa = EtapaFluxo.EAgora }
                        when (etapaAtual.tipo) {
                            AtividadePausa.RESPIRAR -> TelaRespirar(aoTerminar)
                            AtividadePausa.BEBER_AGUA -> TelaBeberAgua(aoTerminar)
                            AtividadePausa.MUDAR_AMBIENTE -> TelaMudarAmbiente(aoTerminar)
                            AtividadePausa.LAVAR_ROSTO -> TelaLavarRosto(aoTerminar)
                            AtividadePausa.MUSICA -> TelaMusica(aoTerminar)
                            AtividadePausa.AFASTAR_CELULAR -> TelaAfastarCelular(aoTerminar)
                            AtividadePausa.FALAR_COM_ALGUEM -> TelaFalarComAlguem(appState, aoTerminar)
                            AtividadePausa.TRES_COISAS_BOAS -> TelaTresCoisasBoas(aoTerminar)
                        }
                    }

                    is EtapaFluxo.EAgora -> TelaEAgora(
                        aoMelhorou = { appState.registrarDiaResistido(0.0); aoVoltar() },
                        aoContinuaComVontade = { etapa = EtapaFluxo.EscolherAcao },
                        aoMuitoDificil = { etapa = EtapaFluxo.AindaComVontade },
                        aoApostei = { etapa = EtapaFluxo.Apostei }
                    )

                    is EtapaFluxo.AindaComVontade -> TelaAindaComVontade(
                        aoTentarOutraPausa = { etapa = EtapaFluxo.EscolherAcao },
                        aoFalarComAlguem = { mostrarContatos = true },
                        aoPedirAjuda = aoIrParaAjuda
                    )

                    is EtapaFluxo.Apostei -> TelaApostei(aoIrParaGatilhos = aoIrParaGatilhos)
                }
            }
        }
    }

    if (mostrarContatos) {
        DialogoContatos(appState = appState, aoFechar = { mostrarContatos = false })
    }
}

@Composable
private fun TelaPausa(aoComecar: () -> Unit) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("Pare por alguns minutos.", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold))
        Spacer(Modifier.height(10.dp))
        Text(
            "A vontade não precisa virar uma aposta.",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
        )
        Spacer(Modifier.height(40.dp))
        Button(onClick = aoComecar, modifier = Modifier.fillMaxWidth().height(56.dp)) {
            Text("COMEÇAR MINHA PAUSA", style = MaterialTheme.typography.titleMedium)
        }
        Spacer(Modifier.height(14.dp))
        Text(
            "Não preciso decidir agora.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun TelaSentimentos(selecionados: Set<String>, aoMudar: (Set<String>) -> Unit, aoContinuar: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Text("O que você está sentindo neste momento?", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(6.dp))
        Text("Pode escolher mais de um.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        Spacer(Modifier.height(16.dp))
        Column(Modifier.weight(1f)) {
            opcoesSentimentos.forEach { opcao ->
                LinhaSelecionavel(opcao, opcao in selecionados) {
                    aoMudar(if (opcao in selecionados) selecionados - opcao else selecionados + opcao)
                }
            }
        }
        Text("Não precisa explicar. Só identificar.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        Spacer(Modifier.height(12.dp))
        Button(onClick = aoContinuar, modifier = Modifier.fillMaxWidth()) { Text("Continuar") }
    }
}

@Composable
private fun TelaPensamento(
    selecionados: Set<String>,
    aoMudar: (Set<String>) -> Unit,
    outro: String,
    aoMudarOutro: (String) -> Unit,
    aoContinuar: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        Text("Qual pensamento está aparecendo agora?", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        Column(Modifier.weight(1f)) {
            opcoesPensamento.forEach { opcao ->
                LinhaSelecionavel(opcao, opcao in selecionados) {
                    aoMudar(if (opcao in selecionados) selecionados - opcao else selecionados + opcao)
                }
            }
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = outro, onValueChange = aoMudarOutro, label = { Text("Outro") }, modifier = Modifier.fillMaxWidth())
        }
        Spacer(Modifier.height(8.dp))
        Text(
            "Pensamentos podem aparecer sem que você precise agir sobre eles.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Spacer(Modifier.height(12.dp))
        Button(onClick = aoContinuar, modifier = Modifier.fillMaxWidth()) { Text("Continuar") }
    }
}

@Composable
private fun TelaEAgora(aoMelhorou: () -> Unit, aoContinuaComVontade: () -> Unit, aoMuitoDificil: () -> Unit, aoApostei: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Text("🌱 E agora?", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(6.dp))
        Text("A vontade diminuiu?", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(24.dp))

        OpcaoEAgora("🟢", "Sim, estou mais tranquilo(a)", MaterialTheme.colorScheme.primaryContainer, aoMelhorou)
        Spacer(Modifier.height(10.dp))
        OpcaoEAgora("🟡", "Ainda estou com vontade", MaterialTheme.colorScheme.secondaryContainer, aoContinuaComVontade)
        Spacer(Modifier.height(10.dp))
        OpcaoEAgora("🔴", "Está muito difícil", MaterialTheme.colorScheme.tertiaryContainer, aoMuitoDificil)
        Spacer(Modifier.height(20.dp))
        TextButton(onClick = aoApostei) { Text("Eu apostei") }
    }
}

@Composable
private fun OpcaoEAgora(emoji: String, texto: String, cor: androidx.compose.ui.graphics.Color, aoClicar: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = cor), onClick = aoClicar) {
        Row(Modifier.padding(18.dp)) {
            Text(emoji, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.width(12.dp))
            Text(texto, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
private fun TelaAindaComVontade(aoTentarOutraPausa: () -> Unit, aoFalarComAlguem: () -> Unit, aoPedirAjuda: () -> Unit) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("Tudo bem. A vontade ainda está aí.", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(
            "Você não precisa enfrentar isso sozinho(a). Não fique só com isso — afaste-se das apostas e procure alguém de confiança.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
        )
        Spacer(Modifier.height(28.dp))
        Button(onClick = aoTentarOutraPausa, modifier = Modifier.fillMaxWidth()) { Text("Fazer outra pausa") }
        Spacer(Modifier.height(10.dp))
        OutlinedButton(onClick = aoFalarComAlguem, modifier = Modifier.fillMaxWidth()) { Text("Falar com alguém") }
        Spacer(Modifier.height(10.dp))
        OutlinedButton(onClick = aoPedirAjuda, modifier = Modifier.fillMaxWidth()) { Text("Preciso de ajuda") }
    }
}

@Composable
private fun TelaApostei(aoIrParaGatilhos: () -> Unit) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("Tudo bem. Vamos entender o que aconteceu.", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(10.dp))
        Text("Uma recaída não apaga o caminho que você já percorreu.", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(28.dp))
        Button(onClick = aoIrParaGatilhos, modifier = Modifier.fillMaxWidth()) { Text("O que aconteceu antes da aposta?") }
    }
}

@Composable
private fun LinhaSelecionavel(texto: String, selecionado: Boolean, aoClicar: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selecionado) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        ),
        onClick = aoClicar
    ) {
        Text(texto, Modifier.padding(14.dp), style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun DialogoContatos(appState: AppState, aoFechar: () -> Unit) {
    AlertDialog(
        onDismissRequest = aoFechar,
        title = { Text("Falar com alguém") },
        text = {
            Column {
                if (appState.contatosConfianca.isEmpty()) {
                    Text("Você ainda não cadastrou uma pessoa de confiança. Configure em \"Minhas formas de me proteger\", dentro de Meu Plano.")
                } else {
                    appState.contatosConfianca.forEach { contato ->
                        Text("${contato.nome} — ${contato.telefone}", Modifier.padding(vertical = 6.dp))
                    }
                }
                Spacer(Modifier.height(10.dp))
                Text("CVV — apoio emocional 24h, gratuito e sigiloso: 188", style = MaterialTheme.typography.bodyMedium)
            }
        },
        confirmButton = { TextButton(onClick = aoFechar) { Text("Fechar") } }
    )
}
