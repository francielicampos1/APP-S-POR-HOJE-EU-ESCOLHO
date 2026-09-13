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
import com.francielicampos.sohoje.ui.components.BannerAnuncio

private data class PassoCiclo(val numero: Int, val titulo: String, val texto: String)

private val cicloDaAposta = listOf(
    PassoCiclo(1, "Gatilho", "Algo acontece: tédio, ansiedade, estresse, solidão, problemas financeiros ou alguma situação emocional."),
    PassoCiclo(2, "Vontade", "Surge a vontade de apostar. Pode aparecer aquele pensamento: \"Só uma vez.\""),
    PassoCiclo(3, "Aposta", "A pessoa aposta buscando diversão, alívio, emoção ou a esperança de ganhar."),
    PassoCiclo(4, "Resultado", "Pode ganhar ou perder. Mesmo uma vitória pode reforçar a vontade de apostar novamente."),
    PassoCiclo(5, "Perda e tentativa de recuperar", "Depois de perder, pode surgir o pensamento: \"Preciso recuperar o que perdi.\" Esse pensamento pode levar a novas apostas e aumentar ainda mais o prejuízo."),
    PassoCiclo(6, "O ciclo recomeça", "E tudo pode voltar ao início, com um novo gatilho.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntendaOVicioScreen(aoVoltar: () -> Unit) {
    Scaffold(topBar = { TopoComVoltar("Entenda o Vício", aoVoltar) }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(20.dp)
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
        ) {
            Bloco(
                titulo = "Não é simplesmente falta de força de vontade",
                texto = "As apostas podem ativar no cérebro mecanismos relacionados à recompensa, expectativa e emoção. Por isso, mesmo quando a pessoa percebe que está fazendo mal, pode ser difícil simplesmente \"parar\"."
            )

            Text("Como o ciclo pode acontecer", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            Spacer(Modifier.height(12.dp))
            cicloDaAposta.forEach { passo ->
                Card(Modifier.fillMaxWidth().padding(bottom = 10.dp), shape = RoundedCornerShape(14.dp)) {
                    Row(Modifier.padding(16.dp)) {
                        Text(
                            "${passo.numero}",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(end = 14.dp)
                        )
                        Column {
                            Text(passo.titulo, style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.height(4.dp))
                            Text(passo.texto, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
            Spacer(Modifier.height(12.dp))

            Bloco(
                titulo = "O que acontece nesse momento?",
                texto = "A vontade de apostar pode parecer urgente, como se fosse necessário agir imediatamente. Mas vontade não é obrigação. Uma vontade pode surgir, aumentar e depois diminuir sem que você precise apostar."
            )
            Bloco(
                titulo = "O primeiro passo é perceber",
                texto = "Quando você consegue identificar \"Estou entediado\", \"Estou ansioso\" ou \"Estou pensando em recuperar meu dinheiro\", você começa a perceber o ciclo antes que ele vire uma aposta."
            )

            Card(
                Modifier.fillMaxWidth().padding(bottom = 22.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Column(Modifier.padding(18.dp)) {
                    Text("E se eu já tiver apostado?", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Tudo bem. Vamos entender o que aconteceu. Uma recaída não apaga todo o caminho que você já percorreu. Em vez de pensar \"já estraguei tudo\", podemos perguntar:",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(10.dp))
                    listOf(
                        "O que aconteceu antes da aposta?",
                        "O que eu estava sentindo?",
                        "O que eu estava pensando?",
                        "O que poderia me ajudar da próxima vez?"
                    ).forEach {
                        Text("•  $it", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(vertical = 2.dp))
                    }
                }
            }

            Bloco(
                titulo = "Lembre-se",
                texto = "Você não precisa resolver tudo hoje. Só precisa escolher o próximo passo. Você pode começar daqui."
            )

            BannerAnuncio()
        }
    }
}

@Composable
private fun Bloco(titulo: String, texto: String) {
    Column(Modifier.padding(bottom = 22.dp)) {
        Text(titulo, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
        Spacer(Modifier.height(6.dp))
        Text(texto, style = MaterialTheme.typography.bodyLarge)
    }
}
