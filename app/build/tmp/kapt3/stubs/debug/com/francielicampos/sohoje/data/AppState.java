package com.francielicampos.sohoje.data;

/**
 * Estado central do app, guardado em memória (pra a interface reagir instantaneamente)
 * e persistido no banco de dados local (Room) a cada mudança, pra sobreviver ao fechar o app.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b!\u0018\u0000 S2\u00020\u0001:\u0001SB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J \u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002032\b\b\u0002\u00105\u001a\u000203J\u0016\u00106\u001a\u0002012\u0006\u00107\u001a\u0002032\u0006\u00108\u001a\u000203J\u0016\u00109\u001a\u0002012\u0006\u0010:\u001a\u0002032\u0006\u0010;\u001a\u000203J\u0016\u0010<\u001a\u0002012\u0006\u0010=\u001a\u0002032\u0006\u0010>\u001a\u00020,J\u000e\u0010?\u001a\u000201H\u0086@\u00a2\u0006\u0002\u0010@J\u0006\u0010A\u001a\u000201J\u000e\u0010B\u001a\u0002012\u0006\u0010C\u001a\u00020,J>\u0010D\u001a\u0002012\u0006\u0010;\u001a\u0002032\u0006\u0010:\u001a\u0002032\u0006\u0010E\u001a\u0002032\u0006\u0010F\u001a\u0002032\u0006\u0010G\u001a\u0002032\u0006\u0010H\u001a\u0002032\u0006\u0010I\u001a\u000203J\u000e\u0010J\u001a\u0002012\u0006\u0010K\u001a\u00020$J\u000e\u0010L\u001a\u0002012\u0006\u0010K\u001a\u00020$J\b\u0010M\u001a\u000201H\u0002J\u000e\u0010N\u001a\u0002012\u0006\u0010O\u001a\u00020!J\u000e\u0010P\u001a\u0002012\u0006\u0010Q\u001a\u00020\u0015J\u000e\u0010R\u001a\u0002012\u0006\u0010>\u001a\u00020,R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000bR\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u000bR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0010R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0010R\u000e\u0010#\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u000bR\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0010R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020,0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0010\u00a8\u0006T"}, d2 = {"Lcom/francielicampos/sohoje/data/AppState;", "", "db", "Lcom/francielicampos/sohoje/data/db/AppDatabase;", "escopo", "Lkotlinx/coroutines/CoroutineScope;", "(Lcom/francielicampos/sohoje/data/db/AppDatabase;Lkotlinx/coroutines/CoroutineScope;)V", "contatosConfianca", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Lcom/francielicampos/sohoje/data/ContatoConfianca;", "getContatosConfianca", "()Landroidx/compose/runtime/snapshots/SnapshotStateList;", "diasConsecutivosSemApostar", "Landroidx/compose/runtime/MutableState;", "", "getDiasConsecutivosSemApostar", "()Landroidx/compose/runtime/MutableState;", "entradasDiario", "Lcom/francielicampos/sohoje/data/EntradaDiario;", "getEntradasDiario", "formasDeProtecao", "Lcom/francielicampos/sohoje/data/FormasDeProtecao;", "getFormasDeProtecao", "gatilhos", "Lcom/francielicampos/sohoje/data/Gatilho;", "getGatilhos", "historico", "Lcom/francielicampos/sohoje/data/RegistroDia;", "getHistorico", "onboardingConcluido", "", "getOnboardingConcluido", "plano", "Lcom/francielicampos/sohoje/data/PlanoPessoal;", "getPlano", "proximoIdContato", "", "proximoIdDiario", "proximoIdFinanceiro", "proximoIdGatilho", "registrosFinanceiros", "Lcom/francielicampos/sohoje/data/RegistroFinanceiro;", "getRegistrosFinanceiros", "valorApostavaAntes", "", "getValorApostavaAntes", "valorTotalNaoGasto", "getValorTotalNaoGasto", "adicionarContato", "", "nome", "", "telefone", "preferencia", "adicionarEntradaDiario", "texto", "humor", "adicionarGatilho", "descricao", "categoria", "adicionarRegistroFinanceiro", "tipo", "valor", "carregar", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "concluirOnboarding", "registrarDiaResistido", "valorNaoGasto", "registrarEpisodioGatilho", "situacaoDetalhe", "sentimentoDetalhe", "pensamento", "oQueFiz", "oQueAconteceu", "removerContato", "id", "removerGatilho", "salvarMeta", "salvarPlano", "novoPlano", "salvarProtecao", "novaProtecao", "salvarValorApostavaAntes", "Companion", "app_debug"})
public final class AppState {
    @org.jetbrains.annotations.NotNull()
    private final com.francielicampos.sohoje.data.db.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope escopo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState<java.lang.Integer> diasConsecutivosSemApostar = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState<java.lang.Double> valorTotalNaoGasto = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState<java.lang.Double> valorApostavaAntes = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState<java.lang.Boolean> onboardingConcluido = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.snapshots.SnapshotStateList<com.francielicampos.sohoje.data.Gatilho> gatilhos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.snapshots.SnapshotStateList<com.francielicampos.sohoje.data.EntradaDiario> entradasDiario = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.snapshots.SnapshotStateList<com.francielicampos.sohoje.data.RegistroDia> historico = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState<com.francielicampos.sohoje.data.PlanoPessoal> plano = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.snapshots.SnapshotStateList<com.francielicampos.sohoje.data.RegistroFinanceiro> registrosFinanceiros = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState<com.francielicampos.sohoje.data.FormasDeProtecao> formasDeProtecao = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.snapshots.SnapshotStateList<com.francielicampos.sohoje.data.ContatoConfianca> contatosConfianca = null;
    private long proximoIdFinanceiro = 1L;
    private long proximoIdGatilho = 1L;
    private long proximoIdDiario = 1L;
    private long proximoIdContato = 1L;
    @org.jetbrains.annotations.NotNull()
    private static final java.time.format.DateTimeFormatter formatoData = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.francielicampos.sohoje.data.AppState.Companion Companion = null;
    
    public AppState(@org.jetbrains.annotations.NotNull()
    com.francielicampos.sohoje.data.db.AppDatabase db, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope escopo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.MutableState<java.lang.Integer> getDiasConsecutivosSemApostar() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.MutableState<java.lang.Double> getValorTotalNaoGasto() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.MutableState<java.lang.Double> getValorApostavaAntes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.MutableState<java.lang.Boolean> getOnboardingConcluido() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.snapshots.SnapshotStateList<com.francielicampos.sohoje.data.Gatilho> getGatilhos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.snapshots.SnapshotStateList<com.francielicampos.sohoje.data.EntradaDiario> getEntradasDiario() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.snapshots.SnapshotStateList<com.francielicampos.sohoje.data.RegistroDia> getHistorico() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.MutableState<com.francielicampos.sohoje.data.PlanoPessoal> getPlano() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.snapshots.SnapshotStateList<com.francielicampos.sohoje.data.RegistroFinanceiro> getRegistrosFinanceiros() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.MutableState<com.francielicampos.sohoje.data.FormasDeProtecao> getFormasDeProtecao() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.snapshots.SnapshotStateList<com.francielicampos.sohoje.data.ContatoConfianca> getContatosConfianca() {
        return null;
    }
    
    /**
     * Carrega tudo do banco de dados pra memória. Chamar uma vez, ao abrir o app.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object carregar(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void salvarMeta() {
    }
    
    public final void concluirOnboarding() {
    }
    
    public final void salvarValorApostavaAntes(double valor) {
    }
    
    public final void adicionarRegistroFinanceiro(@org.jetbrains.annotations.NotNull()
    java.lang.String tipo, double valor) {
    }
    
    /**
     * Registro rápido: tocar num item da lista de categorias. Sempre cria um novo registro (não substitui).
     */
    public final void adicionarGatilho(@org.jetbrains.annotations.NotNull()
    java.lang.String descricao, @org.jetbrains.annotations.NotNull()
    java.lang.String categoria) {
    }
    
    /**
     * Registro detalhado: as 5 perguntas (situação, sentimento, pensamento, o que fiz, o que aconteceu).
     */
    public final void registrarEpisodioGatilho(@org.jetbrains.annotations.NotNull()
    java.lang.String categoria, @org.jetbrains.annotations.NotNull()
    java.lang.String descricao, @org.jetbrains.annotations.NotNull()
    java.lang.String situacaoDetalhe, @org.jetbrains.annotations.NotNull()
    java.lang.String sentimentoDetalhe, @org.jetbrains.annotations.NotNull()
    java.lang.String pensamento, @org.jetbrains.annotations.NotNull()
    java.lang.String oQueFiz, @org.jetbrains.annotations.NotNull()
    java.lang.String oQueAconteceu) {
    }
    
    public final void removerGatilho(long id) {
    }
    
    public final void adicionarEntradaDiario(@org.jetbrains.annotations.NotNull()
    java.lang.String texto, @org.jetbrains.annotations.NotNull()
    java.lang.String humor) {
    }
    
    public final void registrarDiaResistido(double valorNaoGasto) {
    }
    
    public final void adicionarContato(@org.jetbrains.annotations.NotNull()
    java.lang.String nome, @org.jetbrains.annotations.NotNull()
    java.lang.String telefone, @org.jetbrains.annotations.NotNull()
    java.lang.String preferencia) {
    }
    
    public final void removerContato(long id) {
    }
    
    public final void salvarPlano(@org.jetbrains.annotations.NotNull()
    com.francielicampos.sohoje.data.PlanoPessoal novoPlano) {
    }
    
    public final void salvarProtecao(@org.jetbrains.annotations.NotNull()
    com.francielicampos.sohoje.data.FormasDeProtecao novaProtecao) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/francielicampos/sohoje/data/AppState$Companion;", "", "()V", "formatoData", "Ljava/time/format/DateTimeFormatter;", "getFormatoData", "()Ljava/time/format/DateTimeFormatter;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.time.format.DateTimeFormatter getFormatoData() {
            return null;
        }
    }
}