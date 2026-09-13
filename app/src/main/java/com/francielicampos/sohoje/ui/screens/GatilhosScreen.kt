package com.francielicampos.sohoje.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.francielicampos.sohoje.data.AppState

private val categorias = linkedMapOf(
    "Emocional" to listOf("Ansiedade", "Tédio", "Tristeza", "Raiva", "Solidão", "Estresse", "Frustração", "Euforia"),
    "Situação" to listOf("Ficar sozinho(a)", "Problemas ou discussões", "Depois de um dia difícil", "Fim de semana", "Ver alguém apostando", "Receber uma notícia ruim", "Estar com amigos"),
    "Horário" to listOf("Manhã", "Tarde", "Noite", "Madrugada", "Depois do trabalho", "Fim de semana"),
    "Dinheiro" to listOf("Receber salário", "Receber dinheiro inesperado", "Ter perdido dinheiro", "Pensar em recuperar uma perda", "Estar preocupado com dinheiro"),
    "Álcool e bebida" to listOf("Depois de beber", "Enquanto estou bebendo", "Festa ou bar", "Futebol + bebida", "Estar com pessoas que estão apostando"),
    "Ambiente digital" to listOf("Ver propaganda de apostas", "Redes sociais", "Grupos de mensagens", "Ver jogos", "Receber uma oferta ou promoção de aposta")
)

private val opcoesPensamento = listOf(
    "Só vou apostar uma vez.", "Dessa vez eu ganho.", "Preciso recuperar o que perdi.",
    "Vou apostar só um pouquinho.", "Estou precisando de dinheiro.",
    "Se eu parar agora, vou perder a chance.", "Eu consigo controlar."
)

@Composable
fun GatilhosScreen(appState: AppState, aoIrParaProtecao: () -> Unit) {
    var mostrarDialogoOutro by remember { mutableStateOf(false) }
    var mostrarDialogoOutroPensamento by remember { mutableStateOf(false) }
    var mostrarRegistroDetalhado by remember { mutableStateOf(false) }

    val contagem = remember(appState.gatilhos.size) {
        appState.gatilhos.groupingBy { it.descricao }.eachCount()
    }
    // Só pro resumo "Meu Padrão": ignora Pensamento/Outro, que não são padrões situacionais.
    val contagemPadroes = remember(appState.gatilhos.size) {
        appState.gatilhos.filter { it.categoria != "Pensamento" && it.categoria != "Outro" }
            .groupingBy { it.descricao }.eachCount()
    }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text("Meus Gatilhos", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Você não está aqui para se julgar. Está aqui para se conhecer.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
        )
        Spacer(Modifier.height(20.dp))
        Text("O que costuma despertar sua vontade?", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(14.dp))

        categorias.forEach { (categoria, opcoes) ->
            Text(categoria, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            Spacer(Modifier.height(8.dp))
            opcoes.forEach { opcao ->
                val vezes = contagem[opcao] ?: 0
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp),
                    shape = RoundedCornerShape(12.dp),
                    onClick = { appState.adicionarGatilho(opcao, categoria) }
                ) {
                    Row(Modifier.padding(14.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(opcao, style = MaterialTheme.typography.bodyMedium)
                        if (vezes > 0) {
                            Text("$vezes×", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
            Spacer(Modifier.height(14.dp))
        }

        OutlinedButton(onClick = { mostrarDialogoOutro = true }, modifier = Modifier.fillMaxWidth()) {
            Text("+ Outro gatilho")
        }

        Spacer(Modifier.height(28.dp))
        Text("O que acontece dentro da minha cabeça?", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        opcoesPensamento.forEach { pensamento ->
            val vezes = contagem[pensamento] ?: 0
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp),
                shape = RoundedCornerShape(12.dp),
                onClick = { appState.adicionarGatilho(pensamento, "Pensamento") }
            ) {
                Row(Modifier.padding(14.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(pensamento, style = MaterialTheme.typography.bodyMedium)
                    if (vezes > 0) Text("$vezes×", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                }
            }
        }
        OutlinedButton(onClick = { mostrarDialogoOutroPensamento = true }, modifier = Modifier.fillMaxWidth()) {
            Text("+ Outro pensamento")
        }
        appState.gatilhos.filter { it.categoria == "Pensamento" && it.descricao !in opcoesPensamento }.forEach { registro ->
            Spacer(Modifier.height(6.dp))
            Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) {
                Text(registro.descricao, Modifier.padding(14.dp))
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(
            "Isso ajuda a perceber que o gatilho não é só a situação — existe também o pensamento que aparece logo depois.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        Spacer(Modifier.height(16.dp))
        TextButton(onClick = { mostrarRegistroDetalhado = true }) {
            Text("Registrar com mais detalhes →")
        }

        val totalRegistros = appState.gatilhos.count { it.categoria != "Pensamento" && it.categoria != "Outro" }
        if (totalRegistros >= 3) {
            Spacer(Modifier.height(28.dp))
            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Column(Modifier.padding(18.dp)) {
                    Text("🧩 O que você já percebeu sobre você", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                    Spacer(Modifier.height(10.dp))
                    contagemPadroes.entries.sortedByDescending { it.value }.take(4).forEach { (nome, vezes) ->
                        Text("$nome — $vezes ${if (vezes == 1) "vez" else "vezes"}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(vertical = 2.dp))
                    }
                    val maisFrequente = contagemPadroes.entries.maxByOrNull { it.value }
                    if (maisFrequente != null) {
                        Spacer(Modifier.height(12.dp))
                        Text("🔎 Seu padrão", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                        Spacer(Modifier.height(6.dp))
                        Text(
                            "Você percebeu que \"${maisFrequente.key}\" aparece com mais frequência. Isso pode ser um sinal para criar uma barreira antes desses momentos.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(Modifier.height(14.dp))
                        Button(onClick = aoIrParaProtecao, modifier = Modifier.fillMaxWidth()) {
                            Text("CRIAR UMA FORMA DE ME PROTEGER")
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        Text(
            "Você não precisa eliminar todos os gatilhos.\nPrimeiro, aprenda a reconhecê-los. Depois, podemos pensar em como se proteger deles. 🌱",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }

    if (mostrarDialogoOutro) {
        DialogoTextoLivre(
            titulo = "Novo gatilho",
            aoFechar = { mostrarDialogoOutro = false },
            aoSalvar = { texto -> appState.adicionarGatilho(texto, "Outro"); mostrarDialogoOutro = false }
        )
    }
    if (mostrarDialogoOutroPensamento) {
        DialogoTextoLivre(
            titulo = "Outro pensamento",
            aoFechar = { mostrarDialogoOutroPensamento = false },
            aoSalvar = { texto -> appState.adicionarGatilho(texto, "Pensamento"); mostrarDialogoOutroPensamento = false }
        )
    }
    if (mostrarRegistroDetalhado) {
        DialogoRegistroDetalhado(
            aoFechar = { mostrarRegistroDetalhado = false },
            aoSalvar = { situacao, sentimento, pensamento, oQueFiz, oQueAconteceu ->
                appState.registrarEpisodioGatilho("Detalhado", situacao, situacao, sentimento, pensamento, oQueFiz, oQueAconteceu)
                mostrarRegistroDetalhado = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialogoTextoLivre(titulo: String, aoFechar: () -> Unit, aoSalvar: (String) -> Unit) {
    var texto by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = aoFechar,
        title = { Text(titulo) },
        text = { OutlinedTextField(value = texto, onValueChange = { texto = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Descreva...") }) },
        confirmButton = { TextButton(onClick = { if (texto.isNotBlank()) aoSalvar(texto) }, enabled = texto.isNotBlank()) { Text("Salvar") } },
        dismissButton = { TextButton(onClick = aoFechar) { Text("Cancelar") } }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialogoRegistroDetalhado(aoFechar: () -> Unit, aoSalvar: (String, String, String, String, String) -> Unit) {
    var situacao by remember { mutableStateOf("") }
    var sentimento by remember { mutableStateOf("") }
    var pensamento by remember { mutableStateOf("") }
    var oQueFiz by remember { mutableStateOf("") }
    var oQueAconteceu by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = aoFechar,
        title = { Text("O que aconteceu?") },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                CampoDetalhado("O que aconteceu antes?", situacao) { situacao = it }
                CampoDetalhado("O que eu estava sentindo?", sentimento) { sentimento = it }
                CampoDetalhado("O que pensei?", pensamento) { pensamento = it }
                CampoDetalhado("O que fiz?", oQueFiz) { oQueFiz = it }
                CampoDetalhado("O que aconteceu depois?", oQueAconteceu) { oQueAconteceu = it }
            }
        },
        confirmButton = {
            TextButton(
                onClick = { aoSalvar(situacao, sentimento, pensamento, oQueFiz, oQueAconteceu) },
                enabled = situacao.isNotBlank()
            ) { Text("Salvar") }
        },
        dismissButton = { TextButton(onClick = aoFechar) { Text("Cancelar") } }
    )
}

@Composable
private fun CampoDetalhado(label: String, valor: String, aoMudar: (String) -> Unit) {
    Text(label, style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(top = 8.dp))
    OutlinedTextField(value = valor, onValueChange = aoMudar, modifier = Modifier.fillMaxWidth(), singleLine = true)
}
