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
public final class FinanceiroDao_Impl implements FinanceiroDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RegistroFinanceiroEntity> __insertionAdapterOfRegistroFinanceiroEntity;

  public FinanceiroDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRegistroFinanceiroEntity = new EntityInsertionAdapter<RegistroFinanceiroEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `registros_financeiros` (`id`,`dataEpochDay`,`tipo`,`valor`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RegistroFinanceiroEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDataEpochDay());
        if (entity.getTipo() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTipo());
        }
        statement.bindDouble(4, entity.getValor());
      }
    };
  }

  @Override
  public Object inserir(final RegistroFinanceiroEntity registro,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRegistroFinanceiroEntity.insert(registro);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object listarTodos(
      final Continuation<? super List<RegistroFinanceiroEntity>> $completion) {
    final String _sql = "SELECT * FROM registros_financeiros ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RegistroFinanceiroEntity>>() {
      @Override
      @NonNull
      public List<RegistroFinanceiroEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDataEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dataEpochDay");
          final int _cursorIndexOfTipo = CursorUtil.getColumnIndexOrThrow(_cursor, "tipo");
          final int _cursorIndexOfValor = CursorUtil.getColumnIndexOrThrow(_cursor, "valor");
          final List<RegistroFinanceiroEntity> _result = new ArrayList<RegistroFinanceiroEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RegistroFinanceiroEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDataEpochDay;
            _tmpDataEpochDay = _cursor.getLong(_cursorIndexOfDataEpochDay);
            final String _tmpTipo;
            if (_cursor.isNull(_cursorIndexOfTipo)) {
              _tmpTipo = null;
            } else {
              _tmpTipo = _cursor.getString(_cursorIndexOfTipo);
            }
            final double _tmpValor;
            _tmpValor = _cursor.getDouble(_cursorIndexOfValor);
            _item = new RegistroFinanceiroEntity(_tmpId,_tmpDataEpochDay,_tmpTipo,_tmpValor);
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
