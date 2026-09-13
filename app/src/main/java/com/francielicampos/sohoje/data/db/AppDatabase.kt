package com.francielicampos.sohoje.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        GatilhoEntity::class,
        EntradaDiarioEntity::class,
        RegistroDiaEntity::class,
        RegistroFinanceiroEntity::class,
        ContatoConfiancaEntity::class,
        PlanoEntity::class,
        ProtecaoEntity::class,
        MetaEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gatilhoDao(): GatilhoDao
    abstract fun diarioDao(): DiarioDao
    abstract fun historicoDao(): HistoricoDao
    abstract fun financeiroDao(): FinanceiroDao
    abstract fun contatoDao(): ContatoDao
    abstract fun planoDao(): PlanoDao
    abstract fun protecaoDao(): ProtecaoDao
    abstract fun metaDao(): MetaDao

    companion object {
        @Volatile
        private var instancia: AppDatabase? = null

        fun obter(context: Context): AppDatabase {
            return instancia ?: synchronized(this) {
                instancia ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sohoje.db"
                )
                    // App ainda em desenvolvimento, sem usuários reais publicados —
                    // se a estrutura do banco mudar, é seguro recriar do zero.
                    .fallbackToDestructiveMigration()
                    .build().also { instancia = it }
            }
        }
    }
}
