package com.francielicampos.sohoje.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

private data class PaginaOnboarding(
    val titulo: String,
    val texto: String,
    val botao: String
)

private val paginas = listOf(
    PaginaOnboarding(
        "SÓ POR HOJE, EU NÃO APOSTO",
        "Você não precisa decidir sobre o resto da sua vida agora.",
        "COMEÇAR"
    ),
    PaginaOnboarding(
        "Você está no lugar certo",
        "Aqui você não será julgado(a). Este aplicativo foi criado para ajudar você a entender sua relação com as apostas, reconhecer seus gatilhos e encontrar formas de lidar com a vontade de apostar. Um passo de cada vez.",
        "CONTINUAR"
    ),
    PaginaOnboarding(
        "Não precisa ser perfeito",
        "Você não precisa conseguir tudo de uma vez. Talvez você já tenha tentado parar. Talvez tenha conseguido por um tempo. Talvez tenha voltado a apostar. Isso não significa que você não possa tentar novamente.",
        "CONTINUAR"
    ),
    PaginaOnboarding(
        "Vamos entender você",
        "Antes de mudar um comportamento, precisamos entender o que acontece antes dele. Vamos descobrir: o que desperta sua vontade, o que você pensa nesses momentos, o que você sente. Quanto mais você se conhece, mais cedo pode perceber o começo do ciclo.",
        "VAMOS COMEÇAR"
    )
)

private val opcoesMotivo = listOf(
    "Quero parar de apostar",
    "Quero diminuir minhas apostas",
    "Estou preocupado(a) com meu comportamento",
    "Quero entender por que não consigo parar",
    "Estou tentando ajudar alguém",
    "Ainda não sei"
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(aoConcluir: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { paginas.size + 1 }) // +1 = tela final "Uma escolha para hoje"
    val escopo = rememberCoroutineScope()
    var motivoSelecionado by remember { mutableStateOf<String?>(null) }

    Scaffold { padding ->
        HorizontalPager(state = pagerState, modifier = Modifier.padding(padding).fillMaxSize(), userScrollEnabled = false) { pagina ->
            if (pagina < paginas.size) {
                val p = paginas[pagina]
                Column(
                    Modifier.fillMaxSize().padding(28.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(p.titulo, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))
                    Spacer(Modifier.height(16.dp))
                    Text(p.texto, style = MaterialTheme.typography.bodyLarge)
                    Spacer(Modifier.height(40.dp))
                    Button(
                        onClick = { escopo.launch { pagerState.animateScrollToPage(pagina + 1) } },
                        modifier = Modifier.fillMaxWidth().height(52.dp)
                    ) { Text(p.botao) }
                }
            } else {
                // Última tela: "Uma escolha para hoje"
                Column(
                    Modifier.fillMaxSize().padding(28.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("E hoje?", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Você não precisa prometer que nunca mais vai apostar.\n\nSó por hoje, você pode escolher não apostar.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(Modifier.height(28.dp))
                    Button(onClick = aoConcluir, modifier = Modifier.fillMaxWidth().height(52.dp)) {
                        Text("EU ESCOLHO NÃO APOSTAR HOJE")
                    }
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "Já apostei hoje? Tudo bem. Posso começar daqui.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )

                    Spacer(Modifier.height(28.dp))
                    HorizontalDivider()
                    Spacer(Modifier.height(20.dp))
                    Text("O que trouxe você até aqui?", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(10.dp))
                    opcoesMotivo.forEach { opcao ->
                        FilterChip(
                            selected = motivoSelecionado == opcao,
                            onClick = { motivoSelecionado = opcao },
                            label = { Text(opcao) },
                            modifier = Modifier.padding(vertical = 3.dp)
                        )
                    }
                }
            }
        }
    }
}
