package com.francielicampos.sohoje.ui.screens

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.francielicampos.sohoje.TopoComVoltar
import com.francielicampos.sohoje.data.AppState
import com.francielicampos.sohoje.data.FormasDeProtecao
import com.francielicampos.sohoje.util.abrirBuscaGoogle
import com.francielicampos.sohoje.util.abrirDiscador
import com.francielicampos.sohoje.util.abrirWhatsApp

private val opcoesAfastamento = listOf(
    "Bloquear sites de apostas no celular", "Bloquear aplicativos", "Remover aplicativos de apostas", "Sair das contas", "Não salvar senhas"
)
private val opcoesBarreiraFinanceira = listOf(
    "Tirar cartões salvos das plataformas", "Reduzir facilidade de transferência imediata",
    "Evitar deixar dinheiro disponível na conta usada para apostar",
    "Pedir a alguém de confiança para ajudar temporariamente com o controle financeiro",
    "Separar o dinheiro das despesas essenciais assim que receber"
)
private val opcoesReduzirEstimulos = listOf(
    "Silenciar anúncios e conteúdos sobre apostas", "Deixar de seguir perfis relacionados a apostas",
    "Sair de grupos que incentivam apostas", "Evitar assistir conteúdos que despertam vontade", "Bloquear notificações de plataformas"
)
private val preferenciasContato = listOf("WhatsApp", "Ligação")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProtecaoScreen(appState: AppState, aoVoltar: () -> Unit, aoIrParaGatilhos: () -> Unit, aoIrParaMeuPlano: () -> Unit) {
    val context = LocalContext.current
    val protecaoSalva by appState.formasDeProtecao
    var afastamento by remember { mutableStateOf(protecaoSalva.afastamento) }
    var barreira by remember { mutableStateOf(protecaoSalva.barreiraFinanceira) }
    var estimulos by remember { mutableStateOf(protecaoSalva.reduzirEstimulos) }
    var autoexclusaoFeita by remember { mutableStateOf(protecaoSalva.autoexclusaoFeita) }
    var mostrarDialogoContato by remember { mutableStateOf(false) }

    fun salvar() {
        appState.salvarProtecao(FormasDeProtecao(afastamento, barreira, protecaoSalva.acoesFavoritas, estimulos, autoexclusaoFeita))
    }

    Scaffold(topBar = { TopoComVoltar("Minhas formas de me proteger", aoVoltar) }) { padding ->
        Column(Modifier.padding(padding).padding(20.dp).navigationBarsPadding().verticalScroll(rememberScrollState())) {
            Text(
                "Pequenas barreiras podem criar distância entre você e a aposta. Escolha as que fazem sentido pra você.",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(20.dp))

            SecaoProtecao("📱 Bloquear sites e aplicativos de apostas", "Quanto mais difícil for chegar até a aposta, mais tempo você ganha para a vontade passar.") {
                opcoesAfastamento.forEach { opcao ->
                    LinhaCheckbox(opcao, opcao in afastamento) {
                        afastamento = if (opcao in afastamento) afastamento - opcao else afastamento + opcao
                        salvar()
                    }
                }
                Spacer(Modifier.height(6.dp))
                var mostrarPassoAPasso by remember { mutableStateOf(false) }
                TextButton(onClick = { mostrarPassoAPasso = true }) { Text("Como fazer, passo a passo →") }
                if (mostrarPassoAPasso) {
                    DialogoPassoAPasso(
                        titulo = "Como bloquear",
                        aoFechar = { mostrarPassoAPasso = false },
                        passos = listOf(
                            "Bloquear apps: Configurações → Bem-estar digital e controles parentais → ative o \"Modo foco\" e selecione os apps de apostas.",
                            "Bloquear sites: instale um app bloqueador de sites (ex: BlockSite, na Play Store) e adicione os sites de apostas na lista.",
                            "Bloquear notificações: Configurações → Apps → escolha o app de apostas → Notificações → desative.",
                            "Sair das contas e remover apps: abra cada app/site, faça logout, depois desinstale."
                        )
                    )
                }
            }

            SecaoProtecao("🔐 Autoexclusão", "É uma forma de pedir para não ter acesso às plataformas de apostas participantes.") {
                OutlinedButton(onClick = { abrirBuscaGoogle(context, "autoexclusão apostas online como funciona") }, modifier = Modifier.fillMaxWidth()) {
                    Text("VER COMO FUNCIONA")
                }
                Spacer(Modifier.height(10.dp))
                Text("Já fiz minha autoexclusão?", style = MaterialTheme.typography.bodyMedium)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = !autoexclusaoFeita, onClick = { autoexclusaoFeita = false; salvar() })
                    Text("Ainda não", Modifier.padding(end = 16.dp))
                    RadioButton(selected = autoexclusaoFeita, onClick = { autoexclusaoFeita = true; salvar() })
                    Text("Já fiz")
                }
            }

            SecaoProtecao("💳 Proteger meu dinheiro", "O objetivo não é controlar sua vida. É criar uma barreira nos momentos em que você pode estar mais vulnerável.") {
                opcoesBarreiraFinanceira.forEach { opcao ->
                    LinhaCheckbox(opcao, opcao in barreira) {
                        barreira = if (opcao in barreira) barreira - opcao else barreira + opcao
                        salvar()
                    }
                }
            }

            SecaoProtecao("🚫 Reduzir estímulos", "Evite o que alimenta a vontade.") {
                opcoesReduzirEstimulos.forEach { opcao ->
                    LinhaCheckbox(opcao, opcao in estimulos) {
                        estimulos = if (opcao in estimulos) estimulos - opcao else estimulos + opcao
                        salvar()
                    }
                }
                Spacer(Modifier.height(6.dp))
                var mostrarPassoAPasso by remember { mutableStateOf(false) }
                TextButton(onClick = { mostrarPassoAPasso = true }) { Text("Como fazer, passo a passo →") }
                if (mostrarPassoAPasso) {
                    DialogoPassoAPasso(
                        titulo = "Como reduzir estímulos",
                        aoFechar = { mostrarPassoAPasso = false },
                        passos = listOf(
                            "Silenciar anúncios: nas redes sociais, toque nos três pontinhos do anúncio de aposta → \"Não tenho interesse\" ou \"Ocultar anúncio\".",
                            "Deixar de seguir perfis: entre no perfil relacionado a apostas → toque em \"Seguindo\" → \"Deixar de seguir\".",
                            "Sair de grupos: abra o grupo no WhatsApp/Telegram → menu → \"Sair do grupo\".",
                            "Bloquear notificações: Configurações → Apps → escolha o app → Notificações → desative."
                        )
                    )
                }
            }

            Text("👤 Pessoa de confiança", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            Text("Escolha alguém que possa ajudar quando estiver difícil.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            Spacer(Modifier.height(10.dp))
            appState.contatosConfianca.forEach { contato ->
                Card(Modifier.fillMaxWidth().padding(bottom = 8.dp), shape = RoundedCornerShape(12.dp)) {
                    Row(Modifier.padding(14.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text(contato.nome, style = MaterialTheme.typography.bodyLarge)
                            Text("${contato.telefone} · prefere ${contato.preferencia}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                        }
                        Row {
                            IconButton(onClick = { abrirDiscador(context, contato.telefone) }) { Icon(Icons.Filled.Call, contentDescription = "Ligar") }
                            IconButton(onClick = { abrirWhatsApp(context, contato.telefone) }) { Icon(Icons.Filled.Chat, contentDescription = "WhatsApp") }
                            IconButton(onClick = { appState.removerContato(contato.id) }) { Icon(Icons.Filled.Close, contentDescription = "Remover") }
                        }
                    }
                }
            }
            OutlinedButton(onClick = { mostrarDialogoContato = true }, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Filled.Add, contentDescription = null)
                Spacer(Modifier.width(6.dp))
                Text("Adicionar pessoa de confiança")
            }

            Spacer(Modifier.height(28.dp))
            HorizontalDivider()
            Spacer(Modifier.height(20.dp))

            LinkParaOutraTela(
                emoji = "🌱",
                titulo = "Meu plano de proteção",
                legenda = "Seu motivo, o que quer recuperar e o passo a passo completo estão em Meu Plano.",
                aoClicar = aoIrParaMeuPlano
            )
            Spacer(Modifier.height(32.dp))
        }
    }

    if (mostrarDialogoContato) {
        DialogoNovoContato(
            aoFechar = { mostrarDialogoContato = false },
            aoSalvar = { nome, telefone, preferencia ->
                appState.adicionarContato(nome, telefone, preferencia)
                mostrarDialogoContato = false
            }
        )
    }
}

@Composable
private fun SecaoProtecao(titulo: String, legenda: String, conteudo: @Composable () -> Unit) {
    Column(Modifier.padding(bottom = 24.dp)) {
        Text(titulo, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
        Text(legenda, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        Spacer(Modifier.height(8.dp))
        conteudo()
    }
}

@Composable
private fun LinhaCheckbox(texto: String, marcado: Boolean, aoMudar: () -> Unit) {
    Row(Modifier.fillMaxWidth().padding(vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = marcado, onCheckedChange = { aoMudar() })
        Text(texto, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun LinkParaOutraTela(emoji: String, titulo: String, legenda: String, aoClicar: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp), onClick = aoClicar) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(emoji, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(titulo, style = MaterialTheme.typography.titleMedium)
                Text(legenda, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f))
            }
            Icon(Icons.Filled.ChevronRight, contentDescription = null)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialogoPassoAPasso(titulo: String, passos: List<String>, aoFechar: () -> Unit) {
    AlertDialog(
        onDismissRequest = aoFechar,
        title = { Text(titulo) },
        text = {
            Column {
                passos.forEachIndexed { indice, passo ->
                    Text("${indice + 1}. $passo", Modifier.padding(vertical = 4.dp), style = MaterialTheme.typography.bodyMedium)
                }
            }
        },
        confirmButton = { TextButton(onClick = aoFechar) { Text("Entendi") } }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialogoNovoContato(aoFechar: () -> Unit, aoSalvar: (String, String, String) -> Unit) {
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var preferencia by remember { mutableStateOf(preferenciasContato.first()) }

    AlertDialog(
        onDismissRequest = aoFechar,
        title = { Text("Nova pessoa de confiança") },
        text = {
            Column {
                OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") }, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(10.dp))
                OutlinedTextField(value = telefone, onValueChange = { telefone = it }, label = { Text("Telefone") }, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(10.dp))
                Text("Como prefere falar?", style = MaterialTheme.typography.labelLarge)
                Row {
                    preferenciasContato.forEach { opcao ->
                        FilterChip(selected = preferencia == opcao, onClick = { preferencia = opcao }, label = { Text(opcao) }, modifier = Modifier.padding(end = 6.dp))
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { if (nome.isNotBlank()) aoSalvar(nome, telefone, preferencia) }, enabled = nome.isNotBlank()) { Text("Salvar") }
        },
        dismissButton = { TextButton(onClick = aoFechar) { Text("Cancelar") } }
    )
}
