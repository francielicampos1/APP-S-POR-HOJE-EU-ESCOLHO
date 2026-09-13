package com.francielicampos.sohoje.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.francielicampos.sohoje.TopoComVoltar
import kotlinx.coroutines.launch

// Chave Pix nunca é exibida na tela — só copiada pra área de transferência quando solicitado.
private const val CHAVE_PIX = "14988294067"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiguracoesScreen(aoVoltar: () -> Unit) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val escopo = rememberCoroutineScope()

    Scaffold(
        topBar = { TopoComVoltar("Configurações", aoVoltar) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(Modifier.padding(padding).padding(20.dp).navigationBarsPadding()) {
            ListItem(headlineContent = { Text("Notificações") }, supportingContent = { Text("Lembretes diários de check-in") })
            HorizontalDivider()
            ListItem(headlineContent = { Text("Privacidade") }, supportingContent = { Text("Seus dados ficam salvos só no seu aparelho") })
            HorizontalDivider()
            ListItem(
                headlineContent = { Text("Sobre o app") },
                supportingContent = {
                    Text(
                        "Só Por Hoje, Eu Escolho — versão 1.0. Este app é uma ferramenta de apoio e educação. " +
                            "Ele não diagnostica, não substitui acompanhamento profissional de saúde e não promete " +
                            "cura do vício em apostas. Em caso de sofrimento intenso, procure ajuda profissional " +
                            "ou os recursos indicados na aba Ajuda."
                    )
                }
            )
            HorizontalDivider()

            Spacer(Modifier.height(16.dp))
            Card(shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(18.dp)) {
                    Row {
                        Icon(Icons.Filled.Favorite, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                        Spacer(Modifier.width(10.dp))
                        Text("Apoie este projeto", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Se esse aplicativo foi útil pra você e te ajudou de alguma forma, e se você quiser, "
                            + "contribua com qualquer quantia via Pix.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(12.dp))
                    Button(onClick = {
                        copiarParaAreaDeTransferencia(context, CHAVE_PIX)
                        escopo.launch { snackbarHostState.showSnackbar("Chave Pix copiada!") }
                    }) {
                        Icon(Icons.Filled.ContentCopy, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Copiar chave Pix")
                    }
                }
            }
        }
    }
}

private fun copiarParaAreaDeTransferencia(context: Context, texto: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText("Chave Pix", texto))
}
