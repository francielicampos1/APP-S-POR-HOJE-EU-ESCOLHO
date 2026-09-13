package com.francielicampos.sohoje

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.francielicampos.sohoje.data.AppState
import com.francielicampos.sohoje.data.db.AppDatabase
import com.francielicampos.sohoje.ui.navigation.Tela
import com.francielicampos.sohoje.ui.navigation.TelaSecundaria
import com.francielicampos.sohoje.ui.navigation.itensBottomNav
import com.francielicampos.sohoje.ui.screens.*
import com.francielicampos.sohoje.ui.theme.SoPorHojeTheme
import com.google.android.gms.ads.MobileAds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this) {}
        val database = AppDatabase.obter(applicationContext)
        setContent {
            SoPorHojeTheme {
                AppRoot(database)
            }
        }
    }
}

@Composable
fun AppRoot(database: AppDatabase) {
    val escopo = rememberCoroutineScope()
    val appState = remember { AppState(database, escopo) }
    var carregado by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        appState.carregar()
        carregado = true
    }

    if (!carregado) {
        Box(Modifier.fillMaxSize()) {} // tela em branco rápida enquanto carrega (geralmente instantâneo)
    } else if (!appState.onboardingConcluido.value) {
        OnboardingScreen(aoConcluir = { appState.concluirOnboarding() })
    } else {
        TelasPrincipais(appState)
    }
}

@Composable
private fun TelasPrincipais(appState: AppState) {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val rotaAtual = backStackEntry?.destination?.route
    val ehTelaPrincipal = itensBottomNav.any { it.rota == rotaAtual }

    fun irParaAbaPrincipal(rota: String) {
        navController.navigate(rota) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    Scaffold(
        bottomBar = {
            if (ehTelaPrincipal) {
                NavigationBar {
                    itensBottomNav.forEach { tela ->
                        val selecionado = backStackEntry?.destination?.hierarchy
                            ?.any { it.route == tela.rota } == true
                        NavigationBarItem(
                            selected = selecionado,
                            onClick = { irParaAbaPrincipal(tela.rota) },
                            icon = { Icon(tela.icone, contentDescription = tela.titulo) },
                            label = { Text(tela.titulo) }
                        )
                    }
                }
            }
        }
    ) { paddingInterno ->
        NavHost(
            navController = navController,
            startDestination = Tela.Inicio.rota,
            modifier = Modifier.padding(paddingInterno)
        ) {
            composable(Tela.Inicio.rota) {
                InicioScreen(
                    appState = appState,
                    aoClicarEstouComVontade = { navController.navigate(TelaSecundaria.EstouComVontade.rota) },
                    aoClicarDificuldade = { irParaAbaPrincipal(Tela.Ajuda.rota) },
                    aoClicarProgresso = { navController.navigate(TelaSecundaria.Progresso.rota) },
                    aoClicarDinheiro = { navController.navigate(TelaSecundaria.MeuDinheiro.rota) },
                    aoClicarEntenda = { navController.navigate(TelaSecundaria.EntendaOVicio.rota) },
                    aoClicarProtejaSe = { navController.navigate(TelaSecundaria.ProtejaSe.rota) },
                    aoClicarConfiguracoes = { navController.navigate(TelaSecundaria.Configuracoes.rota) },
                    aoClicarAjudarAlguem = { navController.navigate(TelaSecundaria.AjudarAlguem.rota) },
                    aoClicarEstouBem = { navController.navigate(TelaSecundaria.EstouBem.rota) }
                )
            }
            composable(Tela.MeuPlano.rota) {
                MeuPlanoScreen(appState, aoAbrirProtecao = { navController.navigate(TelaSecundaria.Protecao.rota) })
            }
            composable(Tela.Gatilhos.rota) {
                GatilhosScreen(appState, aoIrParaProtecao = { navController.navigate(TelaSecundaria.Protecao.rota) })
            }
            composable(Tela.Diario.rota) { DiarioScreen(appState) }
            composable(Tela.Ajuda.rota) {
                PrecisoDeAjudaScreen(
                    appState = appState,
                    aoIrParaProtecao = { navController.navigate(TelaSecundaria.Protecao.rota) }
                )
            }

            composable(TelaSecundaria.EstouComVontade.rota) {
                EstouComVontadeScreen(
                    appState = appState,
                    aoVoltar = { navController.popBackStack() },
                    aoIrParaGatilhos = { irParaAbaPrincipal(Tela.Gatilhos.rota) },
                    aoIrParaAjuda = { irParaAbaPrincipal(Tela.Ajuda.rota) }
                )
            }
            composable(TelaSecundaria.Progresso.rota) {
                ProgressoScreen(appState = appState, aoVoltar = { navController.popBackStack() })
            }
            composable(TelaSecundaria.MeuDinheiro.rota) {
                MeuDinheiroScreen(appState = appState, aoVoltar = { navController.popBackStack() })
            }
            composable(TelaSecundaria.EntendaOVicio.rota) {
                EntendaOVicioScreen(aoVoltar = { navController.popBackStack() })
            }
            composable(TelaSecundaria.ProtejaSe.rota) {
                ProtejaSeScreen(aoVoltar = { navController.popBackStack() })
            }
            composable(TelaSecundaria.Protecao.rota) {
                ProtecaoScreen(
                    appState = appState,
                    aoVoltar = { navController.popBackStack() },
                    aoIrParaGatilhos = { irParaAbaPrincipal(Tela.Gatilhos.rota) }
                )
            }
            composable(TelaSecundaria.AjudarAlguem.rota) {
                AjudarAlguemScreen(
                    aoVoltar = { navController.popBackStack() },
                    aoIrParaAjuda = { irParaAbaPrincipal(Tela.Ajuda.rota) }
                )
            }
            composable(TelaSecundaria.EstouBem.rota) {
                EstouBemScreen(
                    aoVoltar = { navController.popBackStack() },
                    aoIrParaDiario = { irParaAbaPrincipal(Tela.Diario.rota) },
                    aoIrParaProgresso = { navController.navigate(TelaSecundaria.Progresso.rota) }
                )
            }
            composable(TelaSecundaria.Configuracoes.rota) {
                ConfiguracoesScreen(aoVoltar = { navController.popBackStack() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopoComVoltar(titulo: String, aoVoltar: () -> Unit) {
    TopAppBar(
        title = { Text(titulo) },
        navigationIcon = {
            IconButton(onClick = aoVoltar) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
            }
        }
    )
}
