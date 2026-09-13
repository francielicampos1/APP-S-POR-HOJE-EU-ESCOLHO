package com.francielicampos.sohoje.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.francielicampos.sohoje.TopoComVoltar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProtejaSeScreen(aoVoltar: () -> Unit) {
    Scaffold(topBar = { TopoComVoltar("Proteja-se das Apostas", aoVoltar) }) { padding ->
        Column(Modifier.padding(padding).padding(20.dp).navigationBarsPadding().verticalScroll(rememberScrollState())) {
            Bloco(
                "Não dependa apenas da força de vontade",
                "Quando a vontade está forte, tomar decisões pode ser muito mais difícil. Por isso, uma estratégia importante é criar barreiras antes que a vontade apareça. Quanto mais difícil for chegar até a aposta, maior pode ser o espaço entre a vontade e a ação."
            )

            Text("🔒 Crie distância", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            Spacer(Modifier.height(8.dp))
            listOf(
                "Excluir aplicativos de apostas — remova do celular aquilo que facilita o acesso.",
                "Bloquear sites e aplicativos — use ferramentas de bloqueio para criar uma barreira adicional.",
                "Sair das contas — não deixe logins e acessos salvos.",
                "Remover formas de pagamento — evite deixar cartões ou outros meios de pagamento disponíveis nas plataformas.",
                "Ativar recursos de autoexclusão — quando disponíveis, utilize os mecanismos oficiais das plataformas."
            ).forEach { ItemLista(it) }
            Spacer(Modifier.height(20.dp))

            Text("💳 Proteja o acesso ao dinheiro", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            Spacer(Modifier.height(8.dp))
            Text(
                "Se possível, diminua a facilidade de usar dinheiro em momentos de vontade. Algumas pessoas também escolhem compartilhar temporariamente determinadas decisões financeiras com alguém de confiança. O importante é encontrar uma barreira que funcione para você.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(20.dp))

            Text("📵 Proteja também sua atenção", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            Spacer(Modifier.height(8.dp))
            Text(
                "Ver propaganda, vídeos, grupos ou conteúdos relacionados a apostas pode despertar uma vontade que estava quieta. Quando perceber que determinado conteúdo é um gatilho: deixe de seguir, silencie, bloqueie ou se afaste.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(24.dp))

            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)) {
                Column(Modifier.padding(18.dp)) {
                    Text("❤️ E principalmente", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Não tente enfrentar tudo sozinho. Escolha uma pessoa em quem confia e conte o que está acontecendo. Pedir ajuda não é fraqueza. É uma forma de se proteger.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemLista(texto: String) {
    Text("•  $texto", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(vertical = 3.dp))
}

@Composable
private fun Bloco(titulo: String, texto: String) {
    Column(Modifier.padding(bottom = 20.dp)) {
        Text(titulo, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
        Spacer(Modifier.height(6.dp))
        Text(texto, style = MaterialTheme.typography.bodyLarge)
    }
}
