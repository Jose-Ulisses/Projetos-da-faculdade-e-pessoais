package com.example.agrogestor.Panhadores.PanhadorDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.agrogestor.Lavouras.LavourasDB.LavourasDbSchema;

public class PanhadorDB {
    private final SQLiteDatabase mDatabase;

    public PanhadorDB(Context contexto){
        Context mContext = contexto.getApplicationContext();
        mDatabase = new PanhadorDbHelper(mContext).getWritableDatabase();
    }

    public void addPanhador(String nome, String cpf, String numero, String chavePix){
        ContentValues valores_panhador = new ContentValues();
        valores_panhador.put(PanhadorDbSchema.PanhadorTbl.Cols.NOME_PANHADOR, nome);
        valores_panhador.put(PanhadorDbSchema.PanhadorTbl.Cols.CPF_PANHADOR, cpf);
        valores_panhador.put(PanhadorDbSchema.PanhadorTbl.Cols.NUMERO_PANHADOR, numero);
        valores_panhador.put(PanhadorDbSchema.PanhadorTbl.Cols.CHAVE_PIX, chavePix);

        mDatabase.insert(PanhadorDbSchema.PanhadorTbl.NOME_TBL, null, valores_panhador);
    }

    public Cursor queryPanhador(String clausulaWhere, String[] argsWhere){
        Cursor cursor = mDatabase.query(
                PanhadorDbSchema.PanhadorTbl.NOME_TBL,
                null,
                clausulaWhere,
                argsWhere,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor queryIdPanhador(String nomePanhador){
        String[] projection = { PanhadorDbSchema.PanhadorTbl.Cols.ID_PANHADOR};
        String selection = PanhadorDbSchema.PanhadorTbl.Cols.NOME_PANHADOR + " = ?";
        String[] selectionArgs = { nomePanhador };
        Cursor cursorID;
        try{
            cursorID = mDatabase.query(
                    PanhadorDbSchema.PanhadorTbl.NOME_TBL,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );
        } catch (Exception e){
            Log.e("PanhadorDB", "Erro!");
            return null;
        }
        return cursorID;
    }

    public String queryNomePanhador(int idPanhador){
        String[] projection = { PanhadorDbSchema.PanhadorTbl.Cols.NOME_PANHADOR};
        String selection = PanhadorDbSchema.PanhadorTbl.Cols.ID_PANHADOR + " = ?";
        String[] selectionArgs = { String.valueOf(idPanhador) };
        String nomePanhador;

        Cursor cursorID = null;
        try{
            cursorID = mDatabase.query(
                    PanhadorDbSchema.PanhadorTbl.NOME_TBL,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if (cursorID != null && cursorID.moveToFirst()) {
                int columnIndex = cursorID.getColumnIndexOrThrow(PanhadorDbSchema.PanhadorTbl.Cols.NOME_PANHADOR);
                nomePanhador = cursorID.getString(columnIndex);
                return nomePanhador;
            }

        } catch (Exception e){
            Log.e("LavouraDB", "Erro queryNomeLavoura!");
            cursorID.close();
        }
        return null;
    }


}
