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
public final class PlanoDao_Impl implements PlanoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PlanoEntity> __insertionAdapterOfPlanoEntity;

  public PlanoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlanoEntity = new EntityInsertionAdapter<PlanoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `plano` (`id`,`contatoParaLigar`,`acaoEscolhida`,`motivo`,`pessoasApoio`,`oQueQueroRecuperar`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlanoEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getContatoParaLigar() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getContatoParaLigar());
        }
        if (entity.getAcaoEscolhida() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAcaoEscolhida());
        }
        if (entity.getMotivo() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getMotivo());
        }
        if (entity.getPessoasApoio() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPessoasApoio());
        }
        if (entity.getOQueQueroRecuperar() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getOQueQueroRecuperar());
        }
      }
    };
  }

  @Override
  public Object salvar(final PlanoEntity plano, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPlanoEntity.insert(plano);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object obter(final Continuation<? super PlanoEntity> $completion) {
    final String _sql = "SELECT * FROM plano WHERE id = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PlanoEntity>() {
      @Override
      @Nullable
      public PlanoEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfContatoParaLigar = CursorUtil.getColumnIndexOrThrow(_cursor, "contatoParaLigar");
          final int _cursorIndexOfAcaoEscolhida = CursorUtil.getColumnIndexOrThrow(_cursor, "acaoEscolhida");
          final int _cursorIndexOfMotivo = CursorUtil.getColumnIndexOrThrow(_cursor, "motivo");
          final int _cursorIndexOfPessoasApoio = CursorUtil.getColumnIndexOrThrow(_cursor, "pessoasApoio");
          final int _cursorIndexOfOQueQueroRecuperar = CursorUtil.getColumnIndexOrThrow(_cursor, "oQueQueroRecuperar");
          final PlanoEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpContatoParaLigar;
            if (_cursor.isNull(_cursorIndexOfContatoParaLigar)) {
              _tmpContatoParaLigar = null;
            } else {
              _tmpContatoParaLigar = _cursor.getString(_cursorIndexOfContatoParaLigar);
            }
            final String _tmpAcaoEscolhida;
            if (_cursor.isNull(_cursorIndexOfAcaoEscolhida)) {
              _tmpAcaoEscolhida = null;
            } else {
              _tmpAcaoEscolhida = _cursor.getString(_cursorIndexOfAcaoEscolhida);
            }
            final String _tmpMotivo;
            if (_cursor.isNull(_cursorIndexOfMotivo)) {
              _tmpMotivo = null;
            } else {
              _tmpMotivo = _cursor.getString(_cursorIndexOfMotivo);
            }
            final String _tmpPessoasApoio;
            if (_cursor.isNull(_cursorIndexOfPessoasApoio)) {
              _tmpPessoasApoio = null;
            } else {
              _tmpPessoasApoio = _cursor.getString(_cursorIndexOfPessoasApoio);
            }
            final String _tmpOQueQueroRecuperar;
            if (_cursor.isNull(_cursorIndexOfOQueQueroRecuperar)) {
              _tmpOQueQueroRecuperar = null;
            } else {
              _tmpOQueQueroRecuperar = _cursor.getString(_cursorIndexOfOQueQueroRecuperar);
            }
            _result = new PlanoEntity(_tmpId,_tmpContatoParaLigar,_tmpAcaoEscolhida,_tmpMotivo,_tmpPessoasApoio,_tmpOQueQueroRecuperar);
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
