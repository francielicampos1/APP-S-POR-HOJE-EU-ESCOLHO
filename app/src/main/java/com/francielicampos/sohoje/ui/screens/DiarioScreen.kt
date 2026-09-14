package com.francielicampos.sohoje.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.francielicampos.sohoje.data.AppState
import com.francielicampos.sohoje.data.EntradaDiario

private val opcoesHumor = listOf("😌 Tranquilo(a)", "😰 Ansioso(a)", "😊 Orgulhoso(a)", "😔 Triste", "🎯 Com vontade")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiarioScreen(appState: AppState) {
    var mostrarDialogo by remember { mutableStateOf(false) }
    var entradaEditando by remember { mutableStateOf<EntradaDiario?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { mostrarDialogo = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Nova entrada")
            }
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(20.dp)) {
            Text("Meu Diário", style = MaterialTheme.typography.headlineMedium)
            Text(
                "Escrever pode ajudar a colocar em palavras aquilo que, na hora, parece confuso. Você não precisa escrever bonito. Não precisa escrever muito. Pode ser apenas uma frase.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            Spacer(Modifier.height(16.dp))

            if (appState.entradasDiario.isEmpty()) {
                Text(
                    "Nenhuma entrada ainda. Toque no + para escrever a primeira.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.padding(top = 24.dp)
                )
            } else {
                LazyColumn {
                    items(appState.entradasDiario, key = { it.id }) { entrada ->
                        Card(Modifier.fillMaxWidth().padding(bottom = 10.dp), shape = RoundedCornerShape(14.dp)) {
                            Column(Modifier.padding(16.dp)) {
                                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(entrada.humor, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                                        Spacer(Modifier.width(10.dp))
                                        Text(entrada.data.format(AppState.formatoData), style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                                    }
                                    Row {
                                        IconButton(onClick = { entradaEditando = entrada }) {
                                            Icon(Icons.Filled.Edit, contentDescription = "Editar")
                                        }
                                        IconButton(onClick = { appState.removerEntradaDiario(entrada.id) }) {
                                            Icon(Icons.Filled.Close, contentDescription = "Excluir")
                                        }
                                    }
                                }
                                Spacer(Modifier.height(6.dp))
                                Text(entrada.texto, style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }
            }
        }
    }

    if (mostrarDialogo) {
        DialogoEntradaDiario(
            aoFechar = { mostrarDialogo = false },
            aoSalvar = { texto, humor -> appState.adicionarEntradaDiario(texto, humor); mostrarDialogo = false }
        )
    }

    entradaEditando?.let { entrada ->
        DialogoEntradaDiario(
            textoInicial = entrada.texto,
            humorInicial = entrada.humor,
            aoFechar = { entradaEditando = null },
            aoSalvar = { texto, humor -> appState.editarEntradaDiario(entrada.id, texto, humor); entradaEditando = null }
        )
    }
}

@Composable
private fun DialogoEntradaDiario(
    textoInicial: String = "",
    humorInicial: String = opcoesHumor.first(),
    aoFechar: () -> Unit,
    aoSalvar: (String, String) -> Unit
) {
    var texto by remember { mutableStateOf(textoInicial) }
    var humorSelecionado by remember { mutableStateOf(humorInicial) }

    AlertDialog(
        onDismissRequest = aoFechar,
        title = { Text(if (textoInicial.isEmpty()) "Hoje eu estou..." else "Editar entrada") },
        text = {
            Column {
                LazyRow {
                    items(opcoesHumor) { humor ->
                        FilterChip(
                            selected = humor == humorSelecionado,
                            onClick = { humorSelecionado = humor },
                            label = { Text(humor) },
                            modifier = Modifier.padding(end = 6.dp)
                        )
                    }
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    "Se quiser, conte um pouco mais: o que aconteceu hoje, o que você está sentindo, teve algum momento de vontade de apostar, o que você fez.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = texto,
                    onValueChange = { texto = it },
                    label = { Text("Escreva como você está") },
                    minLines = 4,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { if (texto.isNotBlank()) aoSalvar(texto, humorSelecionado) }, enabled = texto.isNotBlank()) { Text("Salvar") }
        },
        dismissButton = { TextButton(onClick = aoFechar) { Text("Cancelar") } }
    )
}
