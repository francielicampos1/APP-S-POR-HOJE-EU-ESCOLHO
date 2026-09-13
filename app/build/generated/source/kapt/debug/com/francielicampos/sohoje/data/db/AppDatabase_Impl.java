package com.francielicampos.sohoje.data.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile GatilhoDao _gatilhoDao;

  private volatile DiarioDao _diarioDao;

  private volatile HistoricoDao _historicoDao;

  private volatile FinanceiroDao _financeiroDao;

  private volatile ContatoDao _contatoDao;

  private volatile PlanoDao _planoDao;

  private volatile ProtecaoDao _protecaoDao;

  private volatile MetaDao _metaDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `gatilhos` (`id` INTEGER NOT NULL, `dataEpochDay` INTEGER NOT NULL, `descricao` TEXT NOT NULL, `categoria` TEXT NOT NULL, `pensamento` TEXT, `situacaoDetalhe` TEXT, `sentimentoDetalhe` TEXT, `oQueFiz` TEXT, `oQueAconteceu` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `entradas_diario` (`id` INTEGER NOT NULL, `dataEpochMillis` INTEGER NOT NULL, `texto` TEXT NOT NULL, `humor` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `historico` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dataEpochDay` INTEGER NOT NULL, `resistiu` INTEGER NOT NULL, `valorNaoGasto` REAL NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `registros_financeiros` (`id` INTEGER NOT NULL, `dataEpochDay` INTEGER NOT NULL, `tipo` TEXT NOT NULL, `valor` REAL NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `contatos_confianca` (`id` INTEGER NOT NULL, `nome` TEXT NOT NULL, `telefone` TEXT NOT NULL, `preferencia` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `plano` (`id` INTEGER NOT NULL, `contatoParaLigar` TEXT NOT NULL, `acaoEscolhida` TEXT NOT NULL, `motivo` TEXT NOT NULL, `pessoasApoio` TEXT NOT NULL, `oQueQueroRecuperar` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `protecao` (`id` INTEGER NOT NULL, `afastamento` TEXT NOT NULL, `barreiraFinanceira` TEXT NOT NULL, `acoesFavoritas` TEXT NOT NULL, `reduzirEstimulos` TEXT NOT NULL, `autoexclusaoFeita` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `meta` (`id` INTEGER NOT NULL, `diasConsecutivosSemApostar` INTEGER NOT NULL, `valorTotalNaoGasto` REAL NOT NULL, `onboardingConcluido` INTEGER NOT NULL, `valorApostavaAntes` REAL NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'cf9103640b0f793cbc8a02a0a57fb5fd')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `gatilhos`");
        db.execSQL("DROP TABLE IF EXISTS `entradas_diario`");
        db.execSQL("DROP TABLE IF EXISTS `historico`");
        db.execSQL("DROP TABLE IF EXISTS `registros_financeiros`");
        db.execSQL("DROP TABLE IF EXISTS `contatos_confianca`");
        db.execSQL("DROP TABLE IF EXISTS `plano`");
        db.execSQL("DROP TABLE IF EXISTS `protecao`");
        db.execSQL("DROP TABLE IF EXISTS `meta`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsGatilhos = new HashMap<String, TableInfo.Column>(9);
        _columnsGatilhos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGatilhos.put("dataEpochDay", new TableInfo.Column("dataEpochDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGatilhos.put("descricao", new TableInfo.Column("descricao", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGatilhos.put("categoria", new TableInfo.Column("categoria", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGatilhos.put("pensamento", new TableInfo.Column("pensamento", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGatilhos.put("situacaoDetalhe", new TableInfo.Column("situacaoDetalhe", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGatilhos.put("sentimentoDetalhe", new TableInfo.Column("sentimentoDetalhe", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGatilhos.put("oQueFiz", new TableInfo.Column("oQueFiz", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGatilhos.put("oQueAconteceu", new TableInfo.Column("oQueAconteceu", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGatilhos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGatilhos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGatilhos = new TableInfo("gatilhos", _columnsGatilhos, _foreignKeysGatilhos, _indicesGatilhos);
        final TableInfo _existingGatilhos = TableInfo.read(db, "gatilhos");
        if (!_infoGatilhos.equals(_existingGatilhos)) {
          return new RoomOpenHelper.ValidationResult(false, "gatilhos(com.francielicampos.sohoje.data.db.GatilhoEntity).\n"
                  + " Expected:\n" + _infoGatilhos + "\n"
                  + " Found:\n" + _existingGatilhos);
        }
        final HashMap<String, TableInfo.Column> _columnsEntradasDiario = new HashMap<String, TableInfo.Column>(4);
        _columnsEntradasDiario.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEntradasDiario.put("dataEpochMillis", new TableInfo.Column("dataEpochMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEntradasDiario.put("texto", new TableInfo.Column("texto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEntradasDiario.put("humor", new TableInfo.Column("humor", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEntradasDiario = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEntradasDiario = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEntradasDiario = new TableInfo("entradas_diario", _columnsEntradasDiario, _foreignKeysEntradasDiario, _indicesEntradasDiario);
        final TableInfo _existingEntradasDiario = TableInfo.read(db, "entradas_diario");
        if (!_infoEntradasDiario.equals(_existingEntradasDiario)) {
          return new RoomOpenHelper.ValidationResult(false, "entradas_diario(com.francielicampos.sohoje.data.db.EntradaDiarioEntity).\n"
                  + " Expected:\n" + _infoEntradasDiario + "\n"
                  + " Found:\n" + _existingEntradasDiario);
        }
        final HashMap<String, TableInfo.Column> _columnsHistorico = new HashMap<String, TableInfo.Column>(4);
        _columnsHistorico.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistorico.put("dataEpochDay", new TableInfo.Column("dataEpochDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistorico.put("resistiu", new TableInfo.Column("resistiu", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistorico.put("valorNaoGasto", new TableInfo.Column("valorNaoGasto", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHistorico = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHistorico = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHistorico = new TableInfo("historico", _columnsHistorico, _foreignKeysHistorico, _indicesHistorico);
        final TableInfo _existingHistorico = TableInfo.read(db, "historico");
        if (!_infoHistorico.equals(_existingHistorico)) {
          return new RoomOpenHelper.ValidationResult(false, "historico(com.francielicampos.sohoje.data.db.RegistroDiaEntity).\n"
                  + " Expected:\n" + _infoHistorico + "\n"
                  + " Found:\n" + _existingHistorico);
        }
        final HashMap<String, TableInfo.Column> _columnsRegistrosFinanceiros = new HashMap<String, TableInfo.Column>(4);
        _columnsRegistrosFinanceiros.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRegistrosFinanceiros.put("dataEpochDay", new TableInfo.Column("dataEpochDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRegistrosFinanceiros.put("tipo", new TableInfo.Column("tipo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRegistrosFinanceiros.put("valor", new TableInfo.Column("valor", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRegistrosFinanceiros = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRegistrosFinanceiros = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRegistrosFinanceiros = new TableInfo("registros_financeiros", _columnsRegistrosFinanceiros, _foreignKeysRegistrosFinanceiros, _indicesRegistrosFinanceiros);
        final TableInfo _existingRegistrosFinanceiros = TableInfo.read(db, "registros_financeiros");
        if (!_infoRegistrosFinanceiros.equals(_existingRegistrosFinanceiros)) {
          return new RoomOpenHelper.ValidationResult(false, "registros_financeiros(com.francielicampos.sohoje.data.db.RegistroFinanceiroEntity).\n"
                  + " Expected:\n" + _infoRegistrosFinanceiros + "\n"
                  + " Found:\n" + _existingRegistrosFinanceiros);
        }
        final HashMap<String, TableInfo.Column> _columnsContatosConfianca = new HashMap<String, TableInfo.Column>(4);
        _columnsContatosConfianca.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContatosConfianca.put("nome", new TableInfo.Column("nome", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContatosConfianca.put("telefone", new TableInfo.Column("telefone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContatosConfianca.put("preferencia", new TableInfo.Column("preferencia", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysContatosConfianca = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesContatosConfianca = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoContatosConfianca = new TableInfo("contatos_confianca", _columnsContatosConfianca, _foreignKeysContatosConfianca, _indicesContatosConfianca);
        final TableInfo _existingContatosConfianca = TableInfo.read(db, "contatos_confianca");
        if (!_infoContatosConfianca.equals(_existingContatosConfianca)) {
          return new RoomOpenHelper.ValidationResult(false, "contatos_confianca(com.francielicampos.sohoje.data.db.ContatoConfiancaEntity).\n"
                  + " Expected:\n" + _infoContatosConfianca + "\n"
                  + " Found:\n" + _existingContatosConfianca);
        }
        final HashMap<String, TableInfo.Column> _columnsPlano = new HashMap<String, TableInfo.Column>(6);
        _columnsPlano.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlano.put("contatoParaLigar", new TableInfo.Column("contatoParaLigar", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlano.put("acaoEscolhida", new TableInfo.Column("acaoEscolhida", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlano.put("motivo", new TableInfo.Column("motivo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlano.put("pessoasApoio", new TableInfo.Column("pessoasApoio", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlano.put("oQueQueroRecuperar", new TableInfo.Column("oQueQueroRecuperar", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlano = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlano = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlano = new TableInfo("plano", _columnsPlano, _foreignKeysPlano, _indicesPlano);
        final TableInfo _existingPlano = TableInfo.read(db, "plano");
        if (!_infoPlano.equals(_existingPlano)) {
          return new RoomOpenHelper.ValidationResult(false, "plano(com.francielicampos.sohoje.data.db.PlanoEntity).\n"
                  + " Expected:\n" + _infoPlano + "\n"
                  + " Found:\n" + _existingPlano);
        }
        final HashMap<String, TableInfo.Column> _columnsProtecao = new HashMap<String, TableInfo.Column>(6);
        _columnsProtecao.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProtecao.put("afastamento", new TableInfo.Column("afastamento", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProtecao.put("barreiraFinanceira", new TableInfo.Column("barreiraFinanceira", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProtecao.put("acoesFavoritas", new TableInfo.Column("acoesFavoritas", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProtecao.put("reduzirEstimulos", new TableInfo.Column("reduzirEstimulos", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProtecao.put("autoexclusaoFeita", new TableInfo.Column("autoexclusaoFeita", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProtecao = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProtecao = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProtecao = new TableInfo("protecao", _columnsProtecao, _foreignKeysProtecao, _indicesProtecao);
        final TableInfo _existingProtecao = TableInfo.read(db, "protecao");
        if (!_infoProtecao.equals(_existingProtecao)) {
          return new RoomOpenHelper.ValidationResult(false, "protecao(com.francielicampos.sohoje.data.db.ProtecaoEntity).\n"
                  + " Expected:\n" + _infoProtecao + "\n"
                  + " Found:\n" + _existingProtecao);
        }
        final HashMap<String, TableInfo.Column> _columnsMeta = new HashMap<String, TableInfo.Column>(5);
        _columnsMeta.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeta.put("diasConsecutivosSemApostar", new TableInfo.Column("diasConsecutivosSemApostar", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeta.put("valorTotalNaoGasto", new TableInfo.Column("valorTotalNaoGasto", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeta.put("onboardingConcluido", new TableInfo.Column("onboardingConcluido", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeta.put("valorApostavaAntes", new TableInfo.Column("valorApostavaAntes", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMeta = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMeta = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMeta = new TableInfo("meta", _columnsMeta, _foreignKeysMeta, _indicesMeta);
        final TableInfo _existingMeta = TableInfo.read(db, "meta");
        if (!_infoMeta.equals(_existingMeta)) {
          return new RoomOpenHelper.ValidationResult(false, "meta(com.francielicampos.sohoje.data.db.MetaEntity).\n"
                  + " Expected:\n" + _infoMeta + "\n"
                  + " Found:\n" + _existingMeta);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "cf9103640b0f793cbc8a02a0a57fb5fd", "56a46c6a35cdc4f31deed1763843e88e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "gatilhos","entradas_diario","historico","registros_financeiros","contatos_confianca","plano","protecao","meta");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `gatilhos`");
      _db.execSQL("DELETE FROM `entradas_diario`");
      _db.execSQL("DELETE FROM `historico`");
      _db.execSQL("DELETE FROM `registros_financeiros`");
      _db.execSQL("DELETE FROM `contatos_confianca`");
      _db.execSQL("DELETE FROM `plano`");
      _db.execSQL("DELETE FROM `protecao`");
      _db.execSQL("DELETE FROM `meta`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(GatilhoDao.class, GatilhoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DiarioDao.class, DiarioDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(HistoricoDao.class, HistoricoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FinanceiroDao.class, FinanceiroDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ContatoDao.class, ContatoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PlanoDao.class, PlanoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProtecaoDao.class, ProtecaoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MetaDao.class, MetaDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public GatilhoDao gatilhoDao() {
    if (_gatilhoDao != null) {
      return _gatilhoDao;
    } else {
      synchronized(this) {
        if(_gatilhoDao == null) {
          _gatilhoDao = new GatilhoDao_Impl(this);
        }
        return _gatilhoDao;
      }
    }
  }

  @Override
  public DiarioDao diarioDao() {
    if (_diarioDao != null) {
      return _diarioDao;
    } else {
      synchronized(this) {
        if(_diarioDao == null) {
          _diarioDao = new DiarioDao_Impl(this);
        }
        return _diarioDao;
      }
    }
  }

  @Override
  public HistoricoDao historicoDao() {
    if (_historicoDao != null) {
      return _historicoDao;
    } else {
      synchronized(this) {
        if(_historicoDao == null) {
          _historicoDao = new HistoricoDao_Impl(this);
        }
        return _historicoDao;
      }
    }
  }

  @Override
  public FinanceiroDao financeiroDao() {
    if (_financeiroDao != null) {
      return _financeiroDao;
    } else {
      synchronized(this) {
        if(_financeiroDao == null) {
          _financeiroDao = new FinanceiroDao_Impl(this);
        }
        return _financeiroDao;
      }
    }
  }

  @Override
  public ContatoDao contatoDao() {
    if (_contatoDao != null) {
      return _contatoDao;
    } else {
      synchronized(this) {
        if(_contatoDao == null) {
          _contatoDao = new ContatoDao_Impl(this);
        }
        return _contatoDao;
      }
    }
  }

  @Override
  public PlanoDao planoDao() {
    if (_planoDao != null) {
      return _planoDao;
    } else {
      synchronized(this) {
        if(_planoDao == null) {
          _planoDao = new PlanoDao_Impl(this);
        }
        return _planoDao;
      }
    }
  }

  @Override
  public ProtecaoDao protecaoDao() {
    if (_protecaoDao != null) {
      return _protecaoDao;
    } else {
      synchronized(this) {
        if(_protecaoDao == null) {
          _protecaoDao = new ProtecaoDao_Impl(this);
        }
        return _protecaoDao;
      }
    }
  }

  @Override
  public MetaDao metaDao() {
    if (_metaDao != null) {
      return _metaDao;
    } else {
      synchronized(this) {
        if(_metaDao == null) {
          _metaDao = new MetaDao_Impl(this);
        }
        return _metaDao;
      }
    }
  }
}
