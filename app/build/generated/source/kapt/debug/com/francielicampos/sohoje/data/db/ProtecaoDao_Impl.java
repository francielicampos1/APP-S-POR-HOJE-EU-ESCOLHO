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
public final class ProtecaoDao_Impl implements ProtecaoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProtecaoEntity> __insertionAdapterOfProtecaoEntity;

  public ProtecaoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProtecaoEntity = new EntityInsertionAdapter<ProtecaoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `protecao` (`id`,`afastamento`,`barreiraFinanceira`,`acoesFavoritas`,`reduzirEstimulos`,`autoexclusaoFeita`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProtecaoEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getAfastamento() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getAfastamento());
        }
        if (entity.getBarreiraFinanceira() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBarreiraFinanceira());
        }
        if (entity.getAcoesFavoritas() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getAcoesFavoritas());
        }
        if (entity.getReduzirEstimulos() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getReduzirEstimulos());
        }
        final int _tmp = entity.getAutoexclusaoFeita() ? 1 : 0;
        statement.bindLong(6, _tmp);
      }
    };
  }

  @Override
  public Object salvar(final ProtecaoEntity protecao,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfProtecaoEntity.insert(protecao);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object obter(final Continuation<? super ProtecaoEntity> $completion) {
    final String _sql = "SELECT * FROM protecao WHERE id = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ProtecaoEntity>() {
      @Override
      @Nullable
      public ProtecaoEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAfastamento = CursorUtil.getColumnIndexOrThrow(_cursor, "afastamento");
          final int _cursorIndexOfBarreiraFinanceira = CursorUtil.getColumnIndexOrThrow(_cursor, "barreiraFinanceira");
          final int _cursorIndexOfAcoesFavoritas = CursorUtil.getColumnIndexOrThrow(_cursor, "acoesFavoritas");
          final int _cursorIndexOfReduzirEstimulos = CursorUtil.getColumnIndexOrThrow(_cursor, "reduzirEstimulos");
          final int _cursorIndexOfAutoexclusaoFeita = CursorUtil.getColumnIndexOrThrow(_cursor, "autoexclusaoFeita");
          final ProtecaoEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpAfastamento;
            if (_cursor.isNull(_cursorIndexOfAfastamento)) {
              _tmpAfastamento = null;
            } else {
              _tmpAfastamento = _cursor.getString(_cursorIndexOfAfastamento);
            }
            final String _tmpBarreiraFinanceira;
            if (_cursor.isNull(_cursorIndexOfBarreiraFinanceira)) {
              _tmpBarreiraFinanceira = null;
            } else {
              _tmpBarreiraFinanceira = _cursor.getString(_cursorIndexOfBarreiraFinanceira);
            }
            final String _tmpAcoesFavoritas;
            if (_cursor.isNull(_cursorIndexOfAcoesFavoritas)) {
              _tmpAcoesFavoritas = null;
            } else {
              _tmpAcoesFavoritas = _cursor.getString(_cursorIndexOfAcoesFavoritas);
            }
            final String _tmpReduzirEstimulos;
            if (_cursor.isNull(_cursorIndexOfReduzirEstimulos)) {
              _tmpReduzirEstimulos = null;
            } else {
              _tmpReduzirEstimulos = _cursor.getString(_cursorIndexOfReduzirEstimulos);
            }
            final boolean _tmpAutoexclusaoFeita;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfAutoexclusaoFeita);
            _tmpAutoexclusaoFeita = _tmp != 0;
            _result = new ProtecaoEntity(_tmpId,_tmpAfastamento,_tmpBarreiraFinanceira,_tmpAcoesFavoritas,_tmpReduzirEstimulos,_tmpAutoexclusaoFeita);
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
