package com.francielicampos.sohoje.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MetaDao_Impl implements MetaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MetaEntity> __insertionAdapterOfMetaEntity;

  public MetaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMetaEntity = new EntityInsertionAdapter<MetaEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `meta` (`id`,`diasConsecutivosSemApostar`,`valorTotalNaoGasto`,`onboardingConcluido`,`valorApostavaAntes`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MetaEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDiasConsecutivosSemApostar());
        statement.bindDouble(3, entity.getValorTotalNaoGasto());
        final int _tmp = entity.getOnboardingConcluido() ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindDouble(5, entity.getValorApostavaAntes());
      }
    };
  }

  @Override
  public Object salvar(final MetaEntity meta, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMetaEntity.insert(meta);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object obter(final Continuation<? super MetaEntity> $completion) {
    final String _sql = "SELECT * FROM meta WHERE id = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MetaEntity>() {
      @Override
      @Nullable
      public MetaEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDiasConsecutivosSemApostar = CursorUtil.getColumnIndexOrThrow(_cursor, "diasConsecutivosSemApostar");
          final int _cursorIndexOfValorTotalNaoGasto = CursorUtil.getColumnIndexOrThrow(_cursor, "valorTotalNaoGasto");
          final int _cursorIndexOfOnboardingConcluido = CursorUtil.getColumnIndexOrThrow(_cursor, "onboardingConcluido");
          final int _cursorIndexOfValorApostavaAntes = CursorUtil.getColumnIndexOrThrow(_cursor, "valorApostavaAntes");
          final MetaEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpDiasConsecutivosSemApostar;
            _tmpDiasConsecutivosSemApostar = _cursor.getInt(_cursorIndexOfDiasConsecutivosSemApostar);
            final double _tmpValorTotalNaoGasto;
            _tmpValorTotalNaoGasto = _cursor.getDouble(_cursorIndexOfValorTotalNaoGasto);
            final boolean _tmpOnboardingConcluido;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfOnboardingConcluido);
            _tmpOnboardingConcluido = _tmp != 0;
            final double _tmpValorApostavaAntes;
            _tmpValorApostavaAntes = _cursor.getDouble(_cursorIndexOfValorApostavaAntes);
            _result = new MetaEntity(_tmpId,_tmpDiasConsecutivosSemApostar,_tmpValorTotalNaoGasto,_tmpOnboardingConcluido,_tmpValorApostavaAntes);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
