package com.francielicampos.sohoje.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.francielicampos.sohoje.data.db.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class Gatilho(
    val id: Long,
    val data: LocalDate,
    val descricao: String,
    val categoria: String,
    val pensamento: String? = null,
    val situacaoDetalhe: String? = null,
    val sentimentoDetalhe: String? = null,
    val oQueFiz: String? = null,
    val oQueAconteceu: String? = null
)

data class EntradaDiario(val id: Long, val data: LocalDateTime, val texto: String, val humor: String)
data class RegistroDia(val data: LocalDate, val resistiu: Boolean, val valorNaoGasto: Double)
data class RegistroFinanceiro(val id: Long, val data: LocalDate, val tipo: String, val valor: Double)
data class ContatoConfianca(val id: Long, val nome: String, val telefone: String, val preferencia: String = "WhatsApp")

data class PlanoPessoal(
    val contatoParaLigar: String = "",
    val acaoEscolhida: String = "",
    val motivo: String = "",
    val pessoasApoio: String = "",
    val oQueQueroRecuperar: List<String> = emptyList()
)

data class FormasDeProtecao(
    val afastamento: Set<String> = emptySet(),
    val barreiraFinanceira: Set<String> = emptySet(),
    val acoesFavoritas: Set<String> = emptySet(),
    val reduzirEstimulos: Set<String> = emptySet(),
    val autoexclusaoFeita: Boolean = false
)

private fun List<String>.paraTexto() = joinToString("||")
private fun String.paraLista() = if (isBlank()) emptyList() else split("||")
private fun Set<String>.paraTextoConjunto() = joinToString("||")
private fun String.paraConjunto() = if (isBlank()) emptySet() else split("||").toSet()
private val zonaLocal = ZoneId.systemDefault()

/**
 * Estado central do app, guardado em memória (pra a interface reagir instantaneamente)
 * e persistido no banco de dados local (Room) a cada mudança, pra sobreviver ao fechar o app.
 */
class AppState(private val db: AppDatabase, private val escopo: CoroutineScope) {
    val diasConsecutivosSemApostar = mutableStateOf(0)
    val valorTotalNaoGasto = mutableStateOf(0.0)
    val valorApostavaAntes = mutableStateOf(0.0)
    val onboardingConcluido = mutableStateOf(false)

    val gatilhos = mutableStateListOf<Gatilho>()
    val entradasDiario = mutableStateListOf<EntradaDiario>()
    val historico = mutableStateListOf<RegistroDia>()
    val plano = mutableStateOf(PlanoPessoal())
    val registrosFinanceiros = mutableStateListOf<RegistroFinanceiro>()
    val formasDeProtecao = mutableStateOf(FormasDeProtecao())
    val contatosConfianca = mutableStateListOf<ContatoConfianca>()

    private var proximoIdFinanceiro = 1L
    private var proximoIdGatilho = 1L
    private var proximoIdDiario = 1L
    private var proximoIdContato = 1L

    /** Carrega tudo do banco de dados pra memória. Chamar uma vez, ao abrir o app. */
    suspend fun carregar() {
        val meta = db.metaDao().obter()
        if (meta != null) {
            diasConsecutivosSemApostar.value = meta.diasConsecutivosSemApostar
            valorTotalNaoGasto.value = meta.valorTotalNaoGasto
            valorApostavaAntes.value = meta.valorApostavaAntes
            onboardingConcluido.value = meta.onboardingConcluido
        }

        val gatilhosSalvos = db.gatilhoDao().listarTodos()
        gatilhos.clear()
        gatilhos.addAll(gatilhosSalvos.map {
            Gatilho(it.id, LocalDate.ofEpochDay(it.dataEpochDay), it.descricao, it.categoria, it.pensamento, it.situacaoDetalhe, it.sentimentoDetalhe, it.oQueFiz, it.oQueAconteceu)
        })
        proximoIdGatilho = (gatilhosSalvos.maxOfOrNull { it.id } ?: 0) + 1

        val diarioSalvo = db.diarioDao().listarTodos()
        entradasDiario.clear()
        entradasDiario.addAll(diarioSalvo.map {
            EntradaDiario(it.id, LocalDateTime.ofInstant(Instant.ofEpochMilli(it.dataEpochMillis), zonaLocal), it.texto, it.humor)
        })
        proximoIdDiario = (diarioSalvo.maxOfOrNull { it.id } ?: 0) + 1

        val historicoSalvo = db.historicoDao().listarTodos()
        historico.clear()
        historico.addAll(historicoSalvo.map { RegistroDia(LocalDate.ofEpochDay(it.dataEpochDay), it.resistiu, it.valorNaoGasto) })

        val financeiroSalvo = db.financeiroDao().listarTodos()
        registrosFinanceiros.clear()
        registrosFinanceiros.addAll(financeiroSalvo.map { RegistroFinanceiro(it.id, LocalDate.ofEpochDay(it.dataEpochDay), it.tipo, it.valor) })
        proximoIdFinanceiro = (financeiroSalvo.maxOfOrNull { it.id } ?: 0) + 1

        val contatosSalvos = db.contatoDao().listarTodos()
        contatosConfianca.clear()
        contatosConfianca.addAll(contatosSalvos.map { ContatoConfianca(it.id, it.nome, it.telefone, it.preferencia) })
        proximoIdContato = (contatosSalvos.maxOfOrNull { it.id } ?: 0) + 1

        db.planoDao().obter()?.let {
            plano.value = PlanoPessoal(it.contatoParaLigar, it.acaoEscolhida, it.motivo, it.pessoasApoio, it.oQueQueroRecuperar.paraLista())
        }
        db.protecaoDao().obter()?.let {
            formasDeProtecao.value = FormasDeProtecao(
                it.afastamento.paraConjunto(),
                it.barreiraFinanceira.paraConjunto(),
                it.acoesFavoritas.paraConjunto(),
                it.reduzirEstimulos.paraConjunto(),
                it.autoexclusaoFeita
            )
        }
    }

    private fun salvarMeta() {
        escopo.launch {
            db.metaDao().salvar(MetaEntity(0, diasConsecutivosSemApostar.value, valorTotalNaoGasto.value, onboardingConcluido.value, valorApostavaAntes.value))
        }
    }

    fun concluirOnboarding() {
        onboardingConcluido.value = true
        salvarMeta()
    }

    fun salvarValorApostavaAntes(valor: Double) {
        valorApostavaAntes.value = valor
        salvarMeta()
    }

    fun adicionarRegistroFinanceiro(tipo: String, valor: Double) {
        val id = proximoIdFinanceiro++
        val data = LocalDate.now()
        registrosFinanceiros.add(0, RegistroFinanceiro(id, data, tipo, valor))
        escopo.launch { db.financeiroDao().inserir(RegistroFinanceiroEntity(id, data.toEpochDay(), tipo, valor)) }
    }

    /** Registro rápido: tocar num item da lista de categorias. Sempre cria um novo registro (não substitui). */
    fun adicionarGatilho(descricao: String, categoria: String) {
        val id = proximoIdGatilho++
        val data = LocalDate.now()
        gatilhos.add(0, Gatilho(id, data, descricao, categoria))
        escopo.launch { db.gatilhoDao().inserir(GatilhoEntity(id, data.toEpochDay(), descricao, categoria)) }
    }

    /** Registro detalhado: as 5 perguntas (situação, sentimento, pensamento, o que fiz, o que aconteceu). */
    fun registrarEpisodioGatilho(
        categoria: String,
        descricao: String,
        situacaoDetalhe: String,
        sentimentoDetalhe: String,
        pensamento: String,
        oQueFiz: String,
        oQueAconteceu: String
    ) {
        val id = proximoIdGatilho++
        val data = LocalDate.now()
        val gatilho = Gatilho(id, data, descricao, categoria, pensamento, situacaoDetalhe, sentimentoDetalhe, oQueFiz, oQueAconteceu)
        gatilhos.add(0, gatilho)
        escopo.launch {
            db.gatilhoDao().inserir(
                GatilhoEntity(id, data.toEpochDay(), descricao, categoria, pensamento, situacaoDetalhe, sentimentoDetalhe, oQueFiz, oQueAconteceu)
            )
        }
    }

    fun removerGatilho(id: Long) {
        gatilhos.removeAll { it.id == id }
        escopo.launch { db.gatilhoDao().remover(id) }
    }

    fun adicionarEntradaDiario(texto: String, humor: String) {
        val id = proximoIdDiario++
        val data = LocalDateTime.now()
        entradasDiario.add(0, EntradaDiario(id, data, texto, humor))
        val millis = data.atZone(zonaLocal).toInstant().toEpochMilli()
        escopo.launch { db.diarioDao().inserir(EntradaDiarioEntity(id, millis, texto, humor)) }
    }

    fun registrarDiaResistido(valorNaoGasto: Double) {
        diasConsecutivosSemApostar.value += 1
        valorTotalNaoGasto.value += valorNaoGasto
        val data = LocalDate.now()
        historico.add(0, RegistroDia(data, true, valorNaoGasto))
        salvarMeta()
        escopo.launch { db.historicoDao().inserir(RegistroDiaEntity(dataEpochDay = data.toEpochDay(), resistiu = true, valorNaoGasto = valorNaoGasto)) }
    }

    fun adicionarContato(nome: String, telefone: String, preferencia: String = "WhatsApp") {
        val id = proximoIdContato++
        contatosConfianca.add(ContatoConfianca(id, nome, telefone, preferencia))
        escopo.launch { db.contatoDao().inserir(ContatoConfiancaEntity(id, nome, telefone, preferencia)) }
    }

    fun removerContato(id: Long) {
        contatosConfianca.removeAll { it.id == id }
        escopo.launch { db.contatoDao().remover(id) }
    }

    fun salvarPlano(novoPlano: PlanoPessoal) {
        plano.value = novoPlano
        escopo.launch {
            db.planoDao().salvar(
                PlanoEntity(
                    contatoParaLigar = novoPlano.contatoParaLigar,
                    acaoEscolhida = novoPlano.acaoEscolhida,
                    motivo = novoPlano.motivo,
                    pessoasApoio = novoPlano.pessoasApoio,
                    oQueQueroRecuperar = novoPlano.oQueQueroRecuperar.paraTexto()
                )
            )
        }
    }

    fun salvarProtecao(novaProtecao: FormasDeProtecao) {
        formasDeProtecao.value = novaProtecao
        escopo.launch {
            db.protecaoDao().salvar(
                ProtecaoEntity(
                    afastamento = novaProtecao.afastamento.paraTextoConjunto(),
                    barreiraFinanceira = novaProtecao.barreiraFinanceira.paraTextoConjunto(),
                    acoesFavoritas = novaProtecao.acoesFavoritas.paraTextoConjunto(),
                    reduzirEstimulos = novaProtecao.reduzirEstimulos.paraTextoConjunto(),
                    autoexclusaoFeita = novaProtecao.autoexclusaoFeita
                )
            )
        }
    }

    companion object {
        val formatoData: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm")
    }
}
