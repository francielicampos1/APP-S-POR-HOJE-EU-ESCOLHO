package com.francielicampos.sohoje.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.francielicampos.sohoje.data.AppState
import com.francielicampos.sohoje.data.PlanoPessoal

private val opcoesRecuperar = listOf(
    "Dinheiro", "Tranquilidade", "Tempo", "Relacionamentos", "Confiança", "Organização", "Objetivos"
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MeuPlanoScreen(appState: AppState, aoAbrirProtecao: () -> Unit) {
    val planoSalvo by appState.plano
    var editando by remember { mutableStateOf(false) }

    var contatoParaLigar by remember(editando) { mutableStateOf(planoSalvo.contatoParaLigar) }
    var acaoEscolhida by remember(editando) { mutableStateOf(planoSalvo.acaoEscolhida) }
    var motivo by remember(editando) { mutableStateOf(planoSalvo.motivo) }
    var pessoasApoio by remember(editando) { mutableStateOf(planoSalvo.pessoasApoio) }
    var recuperar by remember(editando) { mutableStateOf(planoSalvo.oQueQueroRecuperar.toSet()) }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Meu Plano", style = MaterialTheme.typography.headlineMedium)
            if (!editando) {
                IconButton(onClick = { editando = true }) {
                    Icon(Icons.Filled.Edit, contentDescription = "Editar")
                }
            }
        }
        Text(
            "Você criou isso num momento tranquilo. Quando a vontade aparecer, é só seguir um passo de cada vez.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Spacer(Modifier.height(20.dp))

        // A ideia é dar esperança pra pessoa — começamos pelo motivo e o que ela quer recuperar.
        if (editando) {
            SecaoPlano(titulo = "Meu motivo", pergunta = "Por que eu quero parar?") {
                OutlinedTextField(value = motivo, onValueChange = { motivo = it }, modifier = Modifier.fillMaxWidth(), minLines = 3, placeholder = { Text("Escreva com suas palavras...") })
            }
            SecaoPlano(titulo = "Pessoas que posso procurar", pergunta = "Quem pode me apoiar?") {
                OutlinedTextField(value = pessoasApoio, onValueChange = { pessoasApoio = it }, modifier = Modifier.fillMaxWidth(), minLines = 2, placeholder = { Text("Nomes de pessoas de confiança...") })
            }
            SecaoPlano(titulo = "O que quero recuperar na minha vida?", pergunta = "Selecione o que fizer sentido") {
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    opcoesRecuperar.forEach { opcao ->
                        FilterChip(
                            selected = opcao in recuperar,
                            onClick = { recuperar = if (opcao in recuperar) recuperar - opcao else recuperar + opcao },
                            label = { Text(opcao) }
                        )
                    }
                }
            }
        } else {
            SecaoPlanoLeitura("Meu motivo", planoSalvo.motivo.ifBlank { "Ainda não preenchido." })
            SecaoPlanoLeitura("Pessoas que posso procurar", planoSalvo.pessoasApoio.ifBlank { "Ainda não preenchido." })
            SecaoPlanoLeitura("O que quero recuperar na minha vida", if (planoSalvo.oQueQueroRecuperar.isEmpty()) "Ainda não selecionado." else planoSalvo.oQueQueroRecuperar.joinToString(", "))
        }

        Spacer(Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(Modifier.height(20.dp))

        Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
            Column(Modifier.padding(18.dp)) {
                Text("Quando eu sentir vontade de apostar, vou:", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                Spacer(Modifier.height(12.dp))
                PassoFixo(1, "Fechar o aplicativo/site de apostas.")
                PassoFixo(2, "Fazer minha pausa de 10 minutos.")
                if (editando) {
                    PassoComCampo(3, "Ligar para", contatoParaLigar) { contatoParaLigar = it }
                    PassoComCampo(4, "Fazer", acaoEscolhida) { acaoEscolhida = it }
                } else {
                    PassoFixo(3, "Ligar para ${contatoParaLigar.ifBlank { "___" }}.")
                    PassoFixo(4, "Fazer ${acaoEscolhida.ifBlank { "___" }}.")
                }
                PassoFixo(5, "Se a vontade continuar forte, vou procurar ajuda.")
            }
        }

        Spacer(Modifier.height(12.dp))
        OutlinedButton(onClick = aoAbrirProtecao, modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Filled.Shield, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Configurar minhas formas de me proteger")
        }

        if (editando) {
            Spacer(Modifier.height(20.dp))
            Row {
                OutlinedButton(onClick = { editando = false }, modifier = Modifier.weight(1f)) { Text("Cancelar") }
                Spacer(Modifier.width(12.dp))
                Button(
                    onClick = {
                        appState.salvarPlano(PlanoPessoal(contatoParaLigar, acaoEscolhida, motivo, pessoasApoio, recuperar.toList()))
                        editando = false
                    },
                    modifier = Modifier.weight(1f)
                ) { Text("Salvar") }
            }
        }
    }
}

@Composable
private fun PassoFixo(numero: Int, texto: String) {
    Row(Modifier.padding(vertical = 4.dp)) {
        Text("$numero. ", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
        Text(texto, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun PassoComCampo(numero: Int, prefixo: String, valor: String, aoMudar: (String) -> Unit) {
    Column(Modifier.padding(vertical = 4.dp)) {
        Text("$numero. $prefixo:", style = MaterialTheme.typography.bodyLarge)
        OutlinedTextField(value = valor, onValueChange = aoMudar, modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 4.dp), singleLine = true, placeholder = { Text("...") })
    }
}

@Composable
private fun SecaoPlano(titulo: String, pergunta: String, conteudo: @Composable () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), shape = RoundedCornerShape(16.dp)) {
        Column(Modifier.padding(18.dp)) {
            Text(titulo, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            Text(pergunta, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            Spacer(Modifier.height(10.dp))
            conteudo()
        }
    }
}

@Composable
private fun SecaoPlanoLeitura(titulo: String, valor: String) {
    Card(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), shape = RoundedCornerShape(16.dp)) {
        Column(Modifier.padding(18.dp)) {
            Text(titulo, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            Spacer(Modifier.height(6.dp))
            Text(valor, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
