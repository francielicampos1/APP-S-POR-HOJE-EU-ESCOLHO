package com.francielicampos.sohoje.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
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
public final class GatilhoDao_Impl implements GatilhoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<GatilhoEntity> __insertionAdapterOfGatilhoEntity;

  private final SharedSQLiteStatement __preparedStmtOfRemover;

  public GatilhoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGatilhoEntity = new EntityInsertionAdapter<GatilhoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `gatilhos` (`id`,`dataEpochDay`,`descricao`,`categoria`,`pensamento`,`situacaoDetalhe`,`sentimentoDetalhe`,`oQueFiz`,`oQueAconteceu`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final GatilhoEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDataEpochDay());
        if (entity.getDescricao() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescricao());
        }
        if (entity.getCategoria() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCategoria());
        }
        if (entity.getPensamento() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPensamento());
        }
        if (entity.getSituacaoDetalhe() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSituacaoDetalhe());
        }
        if (entity.getSentimentoDetalhe() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getSentimentoDetalhe());
        }
        if (entity.getOQueFiz() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getOQueFiz());
        }
        if (entity.getOQueAconteceu() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getOQueAconteceu());
        }
      }
    };
    this.__preparedStmtOfRemover = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM gatilhos WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object inserir(final GatilhoEntity gatilho, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfGatilhoEntity.insert(gatilho);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object remover(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRemover.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfRemover.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object listarTodos(final Continuation<? super List<GatilhoEntity>> $completion) {
    final String _sql = "SELECT * FROM gatilhos ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<GatilhoEntity>>() {
      @Override
      @NonNull
      public List<GatilhoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDataEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dataEpochDay");
          final int _cursorIndexOfDescricao = CursorUtil.getColumnIndexOrThrow(_cursor, "descricao");
          final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
          final int _cursorIndexOfPensamento = CursorUtil.getColumnIndexOrThrow(_cursor, "pensamento");
          final int _cursorIndexOfSituacaoDetalhe = CursorUtil.getColumnIndexOrThrow(_cursor, "situacaoDetalhe");
          final int _cursorIndexOfSentimentoDetalhe = CursorUtil.getColumnIndexOrThrow(_cursor, "sentimentoDetalhe");
          final int _cursorIndexOfOQueFiz = CursorUtil.getColumnIndexOrThrow(_cursor, "oQueFiz");
          final int _cursorIndexOfOQueAconteceu = CursorUtil.getColumnIndexOrThrow(_cursor, "oQueAconteceu");
          final List<GatilhoEntity> _result = new ArrayList<GatilhoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final GatilhoEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDataEpochDay;
            _tmpDataEpochDay = _cursor.getLong(_cursorIndexOfDataEpochDay);
            final String _tmpDescricao;
            if (_cursor.isNull(_cursorIndexOfDescricao)) {
              _tmpDescricao = null;
            } else {
              _tmpDescricao = _cursor.getString(_cursorIndexOfDescricao);
            }
            final String _tmpCategoria;
            if (_cursor.isNull(_cursorIndexOfCategoria)) {
              _tmpCategoria = null;
            } else {
              _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
            }
            final String _tmpPensamento;
            if (_cursor.isNull(_cursorIndexOfPensamento)) {
              _tmpPensamento = null;
            } else {
              _tmpPensamento = _cursor.getString(_cursorIndexOfPensamento);
            }
            final String _tmpSituacaoDetalhe;
            if (_cursor.isNull(_cursorIndexOfSituacaoDetalhe)) {
              _tmpSituacaoDetalhe = null;
            } else {
              _tmpSituacaoDetalhe = _cursor.getString(_cursorIndexOfSituacaoDetalhe);
            }
            final String _tmpSentimentoDetalhe;
            if (_cursor.isNull(_cursorIndexOfSentimentoDetalhe)) {
              _tmpSentimentoDetalhe = null;
            } else {
              _tmpSentimentoDetalhe = _cursor.getString(_cursorIndexOfSentimentoDetalhe);
            }
            final String _tmpOQueFiz;
            if (_cursor.isNull(_cursorIndexOfOQueFiz)) {
              _tmpOQueFiz = null;
            } else {
              _tmpOQueFiz = _cursor.getString(_cursorIndexOfOQueFiz);
            }
            final String _tmpOQueAconteceu;
            if (_cursor.isNull(_cursorIndexOfOQueAconteceu)) {
              _tmpOQueAconteceu = null;
            } else {
              _tmpOQueAconteceu = _cursor.getString(_cursorIndexOfOQueAconteceu);
            }
            _item = new GatilhoEntity(_tmpId,_tmpDataEpochDay,_tmpDescricao,_tmpCategoria,_tmpPensamento,_tmpSituacaoDetalhe,_tmpSentimentoDetalhe,_tmpOQueFiz,_tmpOQueAconteceu);
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
