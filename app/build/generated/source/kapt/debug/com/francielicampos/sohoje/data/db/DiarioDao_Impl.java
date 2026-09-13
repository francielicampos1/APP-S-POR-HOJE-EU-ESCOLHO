package com.francielicampos.sohoje.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DiarioDao_Impl implements DiarioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EntradaDiarioEntity> __insertionAdapterOfEntradaDiarioEntity;

  public DiarioDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEntradaDiarioEntity = new EntityInsertionAdapter<EntradaDiarioEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `entradas_diario` (`id`,`dataEpochMillis`,`texto`,`humor`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EntradaDiarioEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDataEpochMillis());
        if (entity.getTexto() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTexto());
        }
        if (entity.getHumor() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getHumor());
        }
      }
    };
  }

  @Override
  public Object inserir(final EntradaDiarioEntity entrada,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEntradaDiarioEntity.insert(entrada);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object listarTodos(final Continuation<? super List<EntradaDiarioEntity>> $completion) {
    final String _sql = "SELECT * FROM entradas_diario ORDER BY dataEpochMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<EntradaDiarioEntity>>() {
      @Override
      @NonNull
      public List<EntradaDiarioEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDataEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "dataEpochMillis");
          final int _cursorIndexOfTexto = CursorUtil.getColumnIndexOrThrow(_cursor, "texto");
          final int _cursorIndexOfHumor = CursorUtil.getColumnIndexOrThrow(_cursor, "humor");
          final List<EntradaDiarioEntity> _result = new ArrayList<EntradaDiarioEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EntradaDiarioEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDataEpochMillis;
            _tmpDataEpochMillis = _cursor.getLong(_cursorIndexOfDataEpochMillis);
            final String _tmpTexto;
            if (_cursor.isNull(_cursorIndexOfTexto)) {
              _tmpTexto = null;
            } else {
              _tmpTexto = _cursor.getString(_cursorIndexOfTexto);
            }
            final String _tmpHumor;
            if (_cursor.isNull(_cursorIndexOfHumor)) {
              _tmpHumor = null;
            } else {
              _tmpHumor = _cursor.getString(_cursorIndexOfHumor);
            }
            _item = new EntradaDiarioEntity(_tmpId,_tmpDataEpochMillis,_tmpTexto,_tmpHumor);
            _result.add(_item);
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
