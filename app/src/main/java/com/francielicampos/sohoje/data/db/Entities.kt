package com.francielicampos.sohoje.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

// Cada toque num gatilho vira um registro novo (com data), não uma marcação fixa —
// isso permite contar quantas vezes cada gatilho se repete, pro "Meu Padrão".
@Entity(tableName = "gatilhos")
data class GatilhoEntity(
    @PrimaryKey val id: Long,
    val dataEpochDay: Long,
    val descricao: String,
    val categoria: String,
    val pensamento: String? = null,
    val situacaoDetalhe: String? = null,
    val sentimentoDetalhe: String? = null,
    val oQueFiz: String? = null,
    val oQueAconteceu: String? = null
)

@Entity(tableName = "entradas_diario")
data class EntradaDiarioEntity(
    @PrimaryKey val id: Long,
    val dataEpochMillis: Long,
    val texto: String,
    val humor: String
)

@Entity(tableName = "historico")
data class RegistroDiaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dataEpochDay: Long,
    val resistiu: Boolean,
    val valorNaoGasto: Double
)

@Entity(tableName = "registros_financeiros")
data class RegistroFinanceiroEntity(
    @PrimaryKey val id: Long,
    val dataEpochDay: Long,
    val tipo: String,
    val valor: Double
)

@Entity(tableName = "contatos_confianca")
data class ContatoConfiancaEntity(
    @PrimaryKey val id: Long,
    val nome: String,
    val telefone: String,
    val preferencia: String = "WhatsApp"
)

// Tabela de uma linha só (id sempre 0), guarda o plano pessoal
@Entity(tableName = "plano")
data class PlanoEntity(
    @PrimaryKey val id: Int = 0,
    val contatoParaLigar: String,
    val acaoEscolhida: String,
    val motivo: String,
    val pessoasApoio: String,
    val oQueQueroRecuperar: String // lista separada por "||"
)

// Tabela de uma linha só (id sempre 0), guarda as formas de proteção configuradas
@Entity(tableName = "protecao")
data class ProtecaoEntity(
    @PrimaryKey val id: Int = 0,
    val afastamento: String, // conjunto separado por "||"
    val barreiraFinanceira: String,
    val acoesFavoritas: String,
    val reduzirEstimulos: String = "",
    val autoexclusaoFeita: Boolean = false
)

// Tabela de uma linha só (id sempre 0), guarda o estado geral do app
@Entity(tableName = "meta")
data class MetaEntity(
    @PrimaryKey val id: Int = 0,
    val diasConsecutivosSemApostar: Int,
    val valorTotalNaoGasto: Double,
    val onboardingConcluido: Boolean,
    val valorApostavaAntes: Double = 0.0
)
