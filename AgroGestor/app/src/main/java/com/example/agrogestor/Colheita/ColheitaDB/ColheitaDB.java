package com.example.agrogestor.Colheita.ColheitaDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ColheitaDB {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public ColheitaDB(Context contexto){
        mContext = contexto.getApplicationContext();
        mDatabase = new ColheitaDbHelper(mContext).getWritableDatabase();
    }

    public void addColheita(int idLavoura, int idTalhao, int idPanhador, double qntd, String data){
        ContentValues valores_colheita = new ContentValues();

        valores_colheita.put(ColheitaDbSchema.ColheitasTbl.Cols.ID_LAVOURA_COLHEITA, idLavoura);
        valores_colheita.put(ColheitaDbSchema.ColheitasTbl.Cols.ID_TAlHAO_COLHEITA, idTalhao);
        valores_colheita.put(ColheitaDbSchema.ColheitasTbl.Cols.ID_PANHADOR_COLHEITA, idPanhador);
        valores_colheita.put(ColheitaDbSchema.ColheitasTbl.Cols.QUANTIDADE, qntd);
        valores_colheita.put(ColheitaDbSchema.ColheitasTbl.Cols.DATA, data);

        mDatabase.insert(ColheitaDbSchema.ColheitasTbl.NOME_TBL, null, valores_colheita);
    }

    public Cursor queryColheita(String clausulaWhere, String[] argsWhere){
        Cursor cursor = mDatabase.query(ColheitaDbSchema.ColheitasTbl.NOME_TBL,
                null,
                clausulaWhere,
                argsWhere,
                null,
                null,
                null
        );
        return cursor;
    }
}
