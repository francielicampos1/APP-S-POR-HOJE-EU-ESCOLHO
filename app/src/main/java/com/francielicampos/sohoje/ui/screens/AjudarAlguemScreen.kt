package com.francielicampos.sohoje.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.francielicampos.sohoje.TopoComVoltar
import com.francielicampos.sohoje.util.compartilharApp

private val dicasParaAjudar = listOf(
    "Converse sem julgamento. Escolha um momento tranquilo e demonstre preocupação.",
    "Evite ameaças, humilhações e cobranças constantes. Isso pode aumentar o isolamento.",
    "Não tente resolver tudo pela pessoa. Incentive-a a buscar ajuda e participar das decisões sobre a própria recuperação.",
    "Não cubra prejuízos das apostas, quando isso puder alimentar o problema.",
    "Ajude a criar barreiras ao acesso às apostas, quando a própria pessoa estiver disposta a isso.",
    "Cuide de você também. A situação pode afetar emocional e financeiramente toda a família."
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjudarAlguemScreen(aoVoltar: () -> Unit, aoIrParaAjuda: () -> Unit) {
    val context = LocalContext.current

    Scaffold(topBar = { TopoComVoltar("Quero ajudar alguém", aoVoltar) }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(20.dp)
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                "🤝 Como ajudar alguém que está apostando",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(Modifier.height(16.dp))

            dicasParaAjudar.forEach { dica ->
                Card(Modifier.fillMaxWidth().padding(bottom = 10.dp), shape = RoundedCornerShape(14.dp)) {
                    Text(dica, Modifier.padding(16.dp), style = MaterialTheme.typography.bodyLarge)
                }
            }

            Spacer(Modifier.height(20.dp))
            Card(
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Column(Modifier.padding(18.dp)) {
                    Text("🆘 Precisa de ajuda agora?", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Se as apostas estão causando sofrimento, dívidas, conflitos familiares ou perda de controle, procurar ajuda profissional pode fazer diferença.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(14.dp))
                    Button(onClick = aoIrParaAjuda, modifier = Modifier.fillMaxWidth()) {
                        Text("Encontrar ajuda e informações")
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
            OutlinedButton(onClick = { compartilharApp(context) }, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Filled.Share, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Compartilhar este app")
            }
        }
    }
}
