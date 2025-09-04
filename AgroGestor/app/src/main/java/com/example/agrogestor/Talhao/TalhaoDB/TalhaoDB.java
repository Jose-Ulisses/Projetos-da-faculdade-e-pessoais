package com.example.agrogestor.Talhao.TalhaoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.agrogestor.Lavouras.LavourasDB.LavourasDbSchema;

public class TalhaoDB {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public TalhaoDB(Context contexto){
        mContext = contexto.getApplicationContext();
        mDatabase = new TalhaoDbHelper(mContext).getWritableDatabase();
    }

    public void addTalhao(String nomeTalhao, int preco, int idLavoura){
        ContentValues valores_talhao = new ContentValues();

        valores_talhao.put(TalhaoDbSchema.TalhoesTbl.Cols.ID_LAVOURA, idLavoura);
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

    public Cursor queryIdTalhao(String nomeTalhao){
        String[] projection = { TalhaoDbSchema.TalhoesTbl.Cols.ID_TALHAO};
        String selection = TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO + " = ?";
        String[] selectionArgs = { nomeTalhao };
        Cursor cursorID = null;
        try{
            cursorID = mDatabase.query(
                    TalhaoDbSchema.TalhoesTbl.NOME_TBL,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );
        } catch (Exception e){
            Log.e("TalhaoDB", "Erro!");
            return null;
        }
        return cursorID;
    }

    public String queryNomeTalhao(int idTalhao){
        String[] projection = { TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO};
        String selection = TalhaoDbSchema.TalhoesTbl.Cols.ID_TALHAO + " = ?";
        String[] selectionArgs = { String.valueOf(idTalhao) };
        String nomeTalhao;

        Cursor cursorID = null;
        try{
            cursorID = mDatabase.query(
                    TalhaoDbSchema.TalhoesTbl.NOME_TBL,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if (cursorID != null && cursorID.moveToFirst()) {
                int columnIndex = cursorID.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO);
                nomeTalhao = cursorID.getString(columnIndex);
                return nomeTalhao;
            }

        } catch (Exception e){
            Log.e("LavouraDB", "Erro queryNomeLavoura!");
            cursorID.close();

        }
        return null;
    }


    public void deleteTbl(){
        int delete;
        delete = mDatabase.delete(TalhaoDbSchema.TalhoesTbl.NOME_TBL, null, null);
    }
}
