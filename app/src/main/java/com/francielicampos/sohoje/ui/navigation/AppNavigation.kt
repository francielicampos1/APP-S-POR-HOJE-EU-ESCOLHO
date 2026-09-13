package com.francielicampos.sohoje.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Tela(val rota: String, val titulo: String, val icone: ImageVector) {
    data object Inicio : Tela("inicio", "Início", Icons.Filled.Home)
    data object MeuPlano : Tela("meu_plano", "Meu Plano", Icons.Filled.Checklist)
    data object Gatilhos : Tela("gatilhos", "Gatilhos", Icons.Filled.Warning)
    data object Diario : Tela("diario", "Diário", Icons.Filled.Book)
    data object Ajuda : Tela("ajuda", "Ajuda", Icons.Filled.SupportAgent)
}

// Telas acessíveis a partir da tela Início ou de outros pontos do app,
// mas que não ficam fixas na barra inferior (pra não sobrecarregar a navegação)
sealed class TelaSecundaria(val rota: String) {
    data object EstouComVontade : TelaSecundaria("estou_com_vontade")
    data object Progresso : TelaSecundaria("progresso")
    data object MeuDinheiro : TelaSecundaria("meu_dinheiro")
    data object EntendaOVicio : TelaSecundaria("entenda_o_vicio")
    data object ProtejaSe : TelaSecundaria("proteja_se")
    data object Protecao : TelaSecundaria("protecao")
    data object AjudarAlguem : TelaSecundaria("ajudar_alguem")
    data object EstouBem : TelaSecundaria("estou_bem")
    data object Configuracoes : TelaSecundaria("configuracoes")
}

val itensBottomNav = listOf(
    Tela.Inicio,
    Tela.MeuPlano,
    Tela.Gatilhos,
    Tela.Diario,
    Tela.Ajuda
)
