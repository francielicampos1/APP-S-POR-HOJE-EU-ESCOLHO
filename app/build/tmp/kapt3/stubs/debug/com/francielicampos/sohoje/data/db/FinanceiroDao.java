package com.francielicampos.sohoje.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/francielicampos/sohoje/data/db/FinanceiroDao;", "", "inserir", "", "registro", "Lcom/francielicampos/sohoje/data/db/RegistroFinanceiroEntity;", "(Lcom/francielicampos/sohoje/data/db/RegistroFinanceiroEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listarTodos", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface FinanceiroDao {
    
    @androidx.room.Query(value = "SELECT * FROM registros_financeiros ORDER BY id DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object listarTodos(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.francielicampos.sohoje.data.db.RegistroFinanceiroEntity>> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object inserir(@org.jetbrains.annotations.NotNull()
    com.francielicampos.sohoje.data.db.RegistroFinanceiroEntity registro, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}