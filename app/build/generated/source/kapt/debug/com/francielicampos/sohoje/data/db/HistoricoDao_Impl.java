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
public final class HistoricoDao_Impl implements HistoricoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RegistroDiaEntity> __insertionAdapterOfRegistroDiaEntity;

  public HistoricoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRegistroDiaEntity = new EntityInsertionAdapter<RegistroDiaEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `historico` (`id`,`dataEpochDay`,`resistiu`,`valorNaoGasto`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RegistroDiaEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDataEpochDay());
        final int _tmp = entity.getResistiu() ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindDouble(4, entity.getValorNaoGasto());
      }
    };
  }

  @Override
  public Object inserir(final RegistroDiaEntity registro,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRegistroDiaEntity.insert(registro);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object listarTodos(final Continuation<? super List<RegistroDiaEntity>> $completion) {
    final String _sql = "SELECT * FROM historico ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RegistroDiaEntity>>() {
      @Override
      @NonNull
      public List<RegistroDiaEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDataEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dataEpochDay");
          final int _cursorIndexOfResistiu = CursorUtil.getColumnIndexOrThrow(_cursor, "resistiu");
          final int _cursorIndexOfValorNaoGasto = CursorUtil.getColumnIndexOrThrow(_cursor, "valorNaoGasto");
          final List<RegistroDiaEntity> _result = new ArrayList<RegistroDiaEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RegistroDiaEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDataEpochDay;
            _tmpDataEpochDay = _cursor.getLong(_cursorIndexOfDataEpochDay);
            final boolean _tmpResistiu;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfResistiu);
            _tmpResistiu = _tmp != 0;
            final double _tmpValorNaoGasto;
            _tmpValorNaoGasto = _cursor.getDouble(_cursorIndexOfValorNaoGasto);
            _item = new RegistroDiaEntity(_tmpId,_tmpDataEpochDay,_tmpResistiu,_tmpValorNaoGasto);
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
