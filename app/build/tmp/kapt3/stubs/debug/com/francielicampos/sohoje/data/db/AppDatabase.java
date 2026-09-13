package com.francielicampos.sohoje.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&\u00a8\u0006\u0014"}, d2 = {"Lcom/francielicampos/sohoje/data/db/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "contatoDao", "Lcom/francielicampos/sohoje/data/db/ContatoDao;", "diarioDao", "Lcom/francielicampos/sohoje/data/db/DiarioDao;", "financeiroDao", "Lcom/francielicampos/sohoje/data/db/FinanceiroDao;", "gatilhoDao", "Lcom/francielicampos/sohoje/data/db/GatilhoDao;", "historicoDao", "Lcom/francielicampos/sohoje/data/db/HistoricoDao;", "metaDao", "Lcom/francielicampos/sohoje/data/db/MetaDao;", "planoDao", "Lcom/francielicampos/sohoje/data/db/PlanoDao;", "protecaoDao", "Lcom/francielicampos/sohoje/data/db/ProtecaoDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.francielicampos.sohoje.data.db.GatilhoEntity.class, com.francielicampos.sohoje.data.db.EntradaDiarioEntity.class, com.francielicampos.sohoje.data.db.RegistroDiaEntity.class, com.francielicampos.sohoje.data.db.RegistroFinanceiroEntity.class, com.francielicampos.sohoje.data.db.ContatoConfiancaEntity.class, com.francielicampos.sohoje.data.db.PlanoEntity.class, com.francielicampos.sohoje.data.db.ProtecaoEntity.class, com.francielicampos.sohoje.data.db.MetaEntity.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.francielicampos.sohoje.data.db.AppDatabase instancia;
    @org.jetbrains.annotations.NotNull()
    public static final com.francielicampos.sohoje.data.db.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.francielicampos.sohoje.data.db.GatilhoDao gatilhoDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.francielicampos.sohoje.data.db.DiarioDao diarioDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.francielicampos.sohoje.data.db.HistoricoDao historicoDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.francielicampos.sohoje.data.db.FinanceiroDao financeiroDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.francielicampos.sohoje.data.db.ContatoDao contatoDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.francielicampos.sohoje.data.db.PlanoDao planoDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.francielicampos.sohoje.data.db.ProtecaoDao protecaoDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.francielicampos.sohoje.data.db.MetaDao metaDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/francielicampos/sohoje/data/db/AppDatabase$Companion;", "", "()V", "instancia", "Lcom/francielicampos/sohoje/data/db/AppDatabase;", "obter", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.francielicampos.sohoje.data.db.AppDatabase obter(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}