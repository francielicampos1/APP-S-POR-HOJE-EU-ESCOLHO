package com.francielicampos.sohoje.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.francielicampos.sohoje.TopoComVoltar

private enum class EtapaEstouBem { PERGUNTA, FECHAMENTO }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstouBemScreen(aoVoltar: () -> Unit, aoIrParaDiario: () -> Unit, aoIrParaProgresso: () -> Unit) {
    var etapa by remember { mutableStateOf(EtapaEstouBem.PERGUNTA) }

    Scaffold(topBar = { TopoComVoltar("Estou bem 🌱", aoVoltar) }) { padding ->
        Column(
            Modifier.padding(padding).padding(20.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            if (etapa == EtapaEstouBem.PERGUNTA) {
                Text("Que bom saber disso.", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold))
                Spacer(Modifier.height(8.dp))
                Text("O que você gostaria de fazer agora?", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(28.dp))

                OutlinedButton(onClick = aoIrParaDiario, modifier = Modifier.fillMaxWidth().height(56.dp)) {
                    Text("📔 Registrar meu dia")
                }
                Spacer(Modifier.height(12.dp))
                OutlinedButton(onClick = aoIrParaProgresso, modifier = Modifier.fillMaxWidth().height(56.dp)) {
                    Text("📊 Ver meu progresso")
                }
                Spacer(Modifier.height(12.dp))
                Button(onClick = { etapa = EtapaEstouBem.FECHAMENTO }, modifier = Modifier.fillMaxWidth().height(56.dp)) {
                    Text("🌱 Só quero continuar meu dia")
                }
            } else {
                Text(
                    "Então siga em frente.\nAproveite seu dia.",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    "E, se precisar, você pode voltar aqui quando quiser.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Spacer(Modifier.height(28.dp))
                Card(
                    Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text(
                        "\"Só por hoje, continue escolhendo você.\"",
                        Modifier.padding(20.dp).fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}
