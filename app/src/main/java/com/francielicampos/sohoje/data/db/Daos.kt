package com.francielicampos.sohoje.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GatilhoDao {
    @Query("SELECT * FROM gatilhos ORDER BY id DESC")
    suspend fun listarTodos(): List<GatilhoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(gatilho: GatilhoEntity)

    @Query("DELETE FROM gatilhos WHERE id = :id")
    suspend fun remover(id: Long)
}

@Dao
interface DiarioDao {
    @Query("SELECT * FROM entradas_diario ORDER BY dataEpochMillis DESC")
    suspend fun listarTodos(): List<EntradaDiarioEntity>

    @Insert
    suspend fun inserir(entrada: EntradaDiarioEntity)

    @Update
    suspend fun atualizar(entrada: EntradaDiarioEntity)

    @Query("DELETE FROM entradas_diario WHERE id = :id")
    suspend fun remover(id: Long)
}

@Dao
interface HistoricoDao {
    @Query("SELECT * FROM historico ORDER BY id DESC")
    suspend fun listarTodos(): List<RegistroDiaEntity>

    @Insert
    suspend fun inserir(registro: RegistroDiaEntity)
}

@Dao
interface FinanceiroDao {
    @Query("SELECT * FROM registros_financeiros ORDER BY id DESC")
    suspend fun listarTodos(): List<RegistroFinanceiroEntity>

    @Insert
    suspend fun inserir(registro: RegistroFinanceiroEntity)
}

@Dao
interface ContatoDao {
    @Query("SELECT * FROM contatos_confianca ORDER BY id ASC")
    suspend fun listarTodos(): List<ContatoConfiancaEntity>

    @Insert
    suspend fun inserir(contato: ContatoConfiancaEntity)

    @Query("DELETE FROM contatos_confianca WHERE id = :id")
    suspend fun remover(id: Long)
}

@Dao
interface PlanoDao {
    @Query("SELECT * FROM plano WHERE id = 0")
    suspend fun obter(): PlanoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvar(plano: PlanoEntity)
}

@Dao
interface ProtecaoDao {
    @Query("SELECT * FROM protecao WHERE id = 0")
    suspend fun obter(): ProtecaoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvar(protecao: ProtecaoEntity)
}

@Dao
interface MetaDao {
    @Query("SELECT * FROM meta WHERE id = 0")
    suspend fun obter(): MetaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvar(meta: MetaEntity)
}
