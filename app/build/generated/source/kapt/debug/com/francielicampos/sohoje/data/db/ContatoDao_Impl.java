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
public final class ContatoDao_Impl implements ContatoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ContatoConfiancaEntity> __insertionAdapterOfContatoConfiancaEntity;

  private final SharedSQLiteStatement __preparedStmtOfRemover;

  public ContatoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfContatoConfiancaEntity = new EntityInsertionAdapter<ContatoConfiancaEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `contatos_confianca` (`id`,`nome`,`telefone`,`preferencia`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ContatoConfiancaEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNome() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNome());
        }
        if (entity.getTelefone() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTelefone());
        }
        if (entity.getPreferencia() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPreferencia());
        }
      }
    };
    this.__preparedStmtOfRemover = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM contatos_confianca WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object inserir(final ContatoConfiancaEntity contato,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfContatoConfiancaEntity.insert(contato);
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
  public Object listarTodos(final Continuation<? super List<ContatoConfiancaEntity>> $completion) {
    final String _sql = "SELECT * FROM contatos_confianca ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ContatoConfiancaEntity>>() {
      @Override
      @NonNull
      public List<ContatoConfiancaEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfTelefone = CursorUtil.getColumnIndexOrThrow(_cursor, "telefone");
          final int _cursorIndexOfPreferencia = CursorUtil.getColumnIndexOrThrow(_cursor, "preferencia");
          final List<ContatoConfiancaEntity> _result = new ArrayList<ContatoConfiancaEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ContatoConfiancaEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNome;
            if (_cursor.isNull(_cursorIndexOfNome)) {
              _tmpNome = null;
            } else {
              _tmpNome = _cursor.getString(_cursorIndexOfNome);
            }
            final String _tmpTelefone;
            if (_cursor.isNull(_cursorIndexOfTelefone)) {
              _tmpTelefone = null;
            } else {
              _tmpTelefone = _cursor.getString(_cursorIndexOfTelefone);
            }
            final String _tmpPreferencia;
            if (_cursor.isNull(_cursorIndexOfPreferencia)) {
              _tmpPreferencia = null;
            } else {
              _tmpPreferencia = _cursor.getString(_cursorIndexOfPreferencia);
            }
            _item = new ContatoConfiancaEntity(_tmpId,_tmpNome,_tmpTelefone,_tmpPreferencia);
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
