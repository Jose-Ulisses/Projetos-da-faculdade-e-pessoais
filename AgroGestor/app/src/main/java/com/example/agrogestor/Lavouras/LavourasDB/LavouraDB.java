package com.example.agrogestor.Lavouras.LavourasDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LavouraDB {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public LavouraDB(Context contexto){
        mContext = contexto.getApplicationContext();
        mDatabase = new LavourasDbHelper(mContext).getWritableDatabase();
    }

    public void addLavoura(String nome){
        ContentValues valores_lavoura = new ContentValues();
        valores_lavoura.put(LavourasDbSchema.LavourasTbl.Cols.NOME_LAVOURA, nome);
        valores_lavoura.put(LavourasDbSchema.LavourasTbl.Cols.TOTAL_LAVOURA, 0);

        mDatabase.insert(LavourasDbSchema.LavourasTbl.NOME_TBL, null, valores_lavoura);
    }

    public Cursor queryLavoura(String clausulaWhere, String[] argsWhere){
        Cursor cursor = mDatabase.query(
                LavourasDbSchema.LavourasTbl.NOME_TBL,
                null,
                clausulaWhere,
                argsWhere,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor queryIdLavoura(String nomeLavoura){
        String[] projection = { LavourasDbSchema.LavourasTbl.Cols.ID_LAVOURA};
        String selection = LavourasDbSchema.LavourasTbl.Cols.NOME_LAVOURA + " = ?";
        String[] selectionArgs = { nomeLavoura };

        Cursor cursorID = null;
        try{
            cursorID = mDatabase.query(
                    LavourasDbSchema.LavourasTbl.NOME_TBL,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );
        } catch (Exception e){
            Log.e("LavouraDB", "Erro!");
            return null;
        }
        return cursorID;
    }

    void deleteTbl(){
        int delete;
        delete = mDatabase.delete(LavourasDbSchema.LavourasTbl.NOME_TBL, null, null);
    }
}
