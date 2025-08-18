package com.example.agrogestor.Talhao.TalhaoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TalhaoDB {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public TalhaoDB(Context contexto){
        mContext = contexto.getApplicationContext();
        mDatabase = new TalhaoDbHelper(mContext).getWritableDatabase();
    }

    public void addTalhao(String nomeTalhao, int preco){
        ContentValues valores_talhao = new ContentValues();

        //valores_talhao.put(TalhaoDbSchema.TalhoesTbl.Cols.ID_LAVOURA, idLavoura);
        valores_talhao.put(TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO, nomeTalhao);
        valores_talhao.put(TalhaoDbSchema.TalhoesTbl.Cols.PRECO_TALHAO, preco);
        valores_talhao.put(TalhaoDbSchema.TalhoesTbl.Cols.TOTAL_TALHAO, 0);

        mDatabase.insert(TalhaoDbSchema.TalhoesTbl.NOME_TBL, null, valores_talhao);
    }

    public Cursor queryTalhao(String clausulaWhere, String[] argsWhere){
        Cursor cursor = mDatabase.query(TalhaoDbSchema.TalhoesTbl.NOME_TBL,
                null,
                clausulaWhere,
                argsWhere,
                null,
                null,
                null
        );
        return cursor;
    }

    public void deleteTbl(){
        int delete;
        delete = mDatabase.delete(TalhaoDbSchema.TalhoesTbl.NOME_TBL, null, null);
    }
}
