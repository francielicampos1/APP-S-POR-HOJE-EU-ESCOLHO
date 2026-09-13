package com.francielicampos.sohoje.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.francielicampos.sohoje.TopoComVoltar
import com.francielicampos.sohoje.data.AppState
import com.francielicampos.sohoje.ui.components.BannerAnuncio
import java.text.NumberFormat
import java.util.Locale

private val tiposRegistro = listOf("Apostei", "Perdi", "Recuperei", "Não gastei hoje")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeuDinheiroScreen(appState: AppState, aoVoltar: () -> Unit) {
    var mostrarDialogo by remember { mutableStateOf(false) }
    val formatoMoeda = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    val totalNaoGasto = appState.registrosFinanceiros.filter { it.tipo == "Não gastei hoje" }.sumOf { it.valor }
    val totalPerdido = appState.registrosFinanceiros.filter { it.tipo == "Perdi" }.sumOf { it.valor }

    Scaffold(
        topBar = { TopoComVoltar("Meu Dinheiro", aoVoltar) },
        floatingActionButton = {
            FloatingActionButton(onClick = { mostrarDialogo = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Novo registro")
            }
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(20.dp).navigationBarsPadding().verticalScroll(rememberScrollState())) {
            Text("Não é sobre culpa. É sobre consciência.", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            Spacer(Modifier.height(6.dp))
            Text(
                "Quando o dinheiro entra no ciclo das apostas, pode ser difícil perceber quanto está sendo gasto de verdade. Pequenos valores podem parecer inofensivos separadamente, mas acumulados podem representar uma diferença importante no seu mês.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(20.dp))

            Row(Modifier.fillMaxWidth()) {
                CartaoValor(formatoMoeda.format(totalNaoGasto), "preservado até agora", Modifier.weight(1f), MaterialTheme.colorScheme.primaryContainer)
                Spacer(Modifier.width(12.dp))
                CartaoValor(formatoMoeda.format(totalPerdido), "perdido registrado", Modifier.weight(1f), MaterialTheme.colorScheme.secondaryContainer)
            }

            Spacer(Modifier.height(20.dp))
            BotaoQuantoApostavaAntes(appState)

            Spacer(Modifier.height(20.dp))
            Card(shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)) {
                Column(Modifier.padding(16.dp)) {
                    Text("⚠️ Cuidado com a tentativa de recuperar perdas", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Depois de perder, pode surgir uma vontade muito forte de apostar novamente pra tentar recuperar o dinheiro: \"Só preciso ganhar de volta o que perdi.\" Mas apostar novamente coloca ainda mais dinheiro em risco. Uma perda não precisa virar outra aposta.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(Modifier.height(20.dp))
            Text("O dinheiro também pode representar escolhas", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            Spacer(Modifier.height(6.dp))
            Text(
                "Imagine o que poderia acontecer se o dinheiro que iria para apostas permanecesse disponível para outras prioridades: contas, comida, casa, lazer, um objetivo, uma reserva, ou simplesmente mais tranquilidade. Não se trata de apagar o passado — trata-se de começar a tomar consciência das escolhas daqui pra frente.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(24.dp))
            Text("Histórico", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(10.dp))
            if (appState.registrosFinanceiros.isEmpty()) {
                Text("Nenhum registro ainda. Toque no + pra adicionar o primeiro.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            } else {
                appState.registrosFinanceiros.forEach { registro ->
                    Card(Modifier.fillMaxWidth().padding(bottom = 8.dp), shape = RoundedCornerShape(12.dp)) {
                        Row(Modifier.padding(14.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                Text(registro.tipo, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium))
                                Text(registro.data.toString(), style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                            }
                            Text(formatoMoeda.format(registro.valor), style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
            BannerAnuncio()
        }
    }

    if (mostrarDialogo) {
        DialogoNovoRegistro(
            aoFechar = { mostrarDialogo = false },
            aoSalvar = { tipo, valor -> appState.adicionarRegistroFinanceiro(tipo, valor); mostrarDialogo = false }
        )
    }
}

@Composable
private fun CartaoValor(titulo: String, rotulo: String, modifier: Modifier, cor: androidx.compose.ui.graphics.Color) {
    Card(modifier = modifier, shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = cor)) {
        Column(Modifier.padding(16.dp)) {
            Text(titulo, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
            Text(rotulo, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun DialogoNovoRegistro(aoFechar: () -> Unit, aoSalvar: (String, Double) -> Unit) {
    var tipoSelecionado by remember { mutableStateOf(tiposRegistro.first()) }
    var valorTexto by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = aoFechar,
        title = { Text("Novo registro") },
        text = {
            Column {
                LazyRow {
                    items(tiposRegistro) { tipo ->
                        FilterChip(selected = tipo == tipoSelecionado, onClick = { tipoSelecionado = tipo }, label = { Text(tipo) }, modifier = Modifier.padding(end = 6.dp))
                    }
                }
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = valorTexto,
                    onValueChange = { valorTexto = it.filter { c -> c.isDigit() || c == '.' || c == ',' } },
                    label = { Text("Valor (R\$)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val valor = valorTexto.replace(",", ".").toDoubleOrNull()
                if (valor != null) aoSalvar(tipoSelecionado, valor)
            }) { Text("Salvar") }
        },
        dismissButton = { TextButton(onClick = aoFechar) { Text("Cancelar") } }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BotaoQuantoApostavaAntes(appState: AppState) {
    var mostrarDialogo by remember { mutableStateOf(false) }
    val valorAtual by appState.valorApostavaAntes
    val formatoMoeda = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp), onClick = { mostrarDialogo = true }) {
        Column(Modifier.padding(16.dp)) {
            Text("Quanto eu apostava antes", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            Text(
                if (valorAtual > 0) "${formatoMoeda.format(valorAtual)} — toque pra atualizar" else "Toque pra registrar, uma vez só",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
            )
        }
    }

    if (mostrarDialogo) {
        var texto by remember { mutableStateOf(if (valorAtual > 0) valorAtual.toString() else "") }
        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            title = { Text("Quanto eu apostava antes") },
            text = {
                OutlinedTextField(
                    value = texto,
                    onValueChange = { texto = it.filter { c -> c.isDigit() || c == '.' || c == ',' } },
                    label = { Text("Valor (R\$)") },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    val valor = texto.replace(",", ".").toDoubleOrNull()
                    if (valor != null) appState.salvarValorApostavaAntes(valor)
                    mostrarDialogo = false
                }) { Text("Salvar") }
            },
            dismissButton = { TextButton(onClick = { mostrarDialogo = false }) { Text("Cancelar") } }
        )
    }
}
