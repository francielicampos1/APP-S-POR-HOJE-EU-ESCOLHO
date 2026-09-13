package com.francielicampos.sohoje.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00c6\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011\u00a8\u0006\u001e"}, d2 = {"Lcom/francielicampos/sohoje/data/db/MetaEntity;", "", "id", "", "diasConsecutivosSemApostar", "valorTotalNaoGasto", "", "onboardingConcluido", "", "valorApostavaAntes", "(IIDZD)V", "getDiasConsecutivosSemApostar", "()I", "getId", "getOnboardingConcluido", "()Z", "getValorApostavaAntes", "()D", "getValorTotalNaoGasto", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "", "app_debug"})
@androidx.room.Entity(tableName = "meta")
public final class MetaEntity {
    @androidx.room.PrimaryKey()
    private final int id = 0;
    private final int diasConsecutivosSemApostar = 0;
    private final double valorTotalNaoGasto = 0.0;
    private final boolean onboardingConcluido = false;
    private final double valorApostavaAntes = 0.0;
    
    public MetaEntity(int id, int diasConsecutivosSemApostar, double valorTotalNaoGasto, boolean onboardingConcluido, double valorApostavaAntes) {
        super();
    }
    
    public final int getId() {
        return 0;
    }
    
    public final int getDiasConsecutivosSemApostar() {
        return 0;
    }
    
    public final double getValorTotalNaoGasto() {
        return 0.0;
    }
    
    public final boolean getOnboardingConcluido() {
        return false;
    }
    
    public final double getValorApostavaAntes() {
        return 0.0;
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final double component3() {
        return 0.0;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.francielicampos.sohoje.data.db.MetaEntity copy(int id, int diasConsecutivosSemApostar, double valorTotalNaoGasto, boolean onboardingConcluido, double valorApostavaAntes) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}