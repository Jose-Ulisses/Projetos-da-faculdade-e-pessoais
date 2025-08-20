package com.example.agrogestor.Colheita;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agrogestor.Lavouras.LavourasDB.LavouraDB;
import com.example.agrogestor.Panhadores.PanhadorDB.PanhadorDB;
import com.example.agrogestor.R;
import com.example.agrogestor.Talhao.TalhaoDB.TalhaoDB;
import com.example.agrogestor.Talhao.TalhaoDB.TalhaoDbSchema;
import java.util.ArrayList;
import java.util.List;

public class AdicionarColheitaActivity extends AppCompatActivity {
    AutoCompleteTextView AutoCompTextViewLavoura, autoCompTextViewTalhao, autCompTextViewPanhador;
    LavouraDB mLavouraDb;
    TalhaoDB mTalhaoDb;
    PanhadorDB mPanhadorDb;
    ArrayAdapter<String> adapterItems;
    int idLavoura;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_colheita);

        //autocomplete lavouras
        AutoCompTextViewLavoura = findViewById(R.id.ac_textView_lavoura);
        List<String> nomesLavouras = new ArrayList<>();
        mLavouraDb = new LavouraDB(getBaseContext());
        Cursor cursorL = mLavouraDb.queryLavoura((String) null, (String[]) null);
        if(cursorL != null){
            try{
                cursorL.moveToFirst();
                while(!cursorL.isAfterLast()){
                    nomesLavouras.add(cursorL.getString(cursorL.getColumnIndexOrThrow("nomeLavoura")));
                    cursorL.moveToNext();
                }
            } finally {
                cursorL.close();
            }
        }

        List<String> itemLavoura = nomesLavouras;
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, itemLavoura);
        AutoCompTextViewLavoura.setAdapter(adapterItems);
        AutoCompTextViewLavoura.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nomeLavoura = adapterView.getItemAtPosition(i).toString();

                Cursor cursorId = mLavouraDb.queryIdLavoura(nomeLavoura);
                if(cursorId != null){
                    try{
                        if(cursorId.moveToFirst()){
                            int idColumnIndex = cursorId.getColumnIndex("_id");
                            if(idColumnIndex != -1)
                                idLavoura = cursorId.getInt(idColumnIndex);
                        }
                    } finally {
                        cursorId.close();
                    }
                }

                //autoComplete talhao
                autoCompTextViewTalhao = findViewById(R.id.ac_textView_talhao);
                List<String> nomesTalhao = new ArrayList<>();
                mTalhaoDb = new TalhaoDB(getBaseContext());
                Cursor cursorT = mTalhaoDb.queryTalhao((String) null, (String[]) null);
                if(cursorT != null){
                    try{
                        cursorT.moveToFirst();
                        while(!cursorT.isAfterLast()){
                            int idLavouraTalhao = cursorT.getInt(cursorT.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.ID_LAVOURA));
                            if(idLavouraTalhao == idLavoura){
                                nomesTalhao.add(cursorT.getString(cursorT.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO)));
                            }
                            cursorT.moveToNext();
                        }
                    } finally {
                        cursorT.close();
                    }
                }

                List<String> itemTalhao = nomesTalhao;
                adapterItems = new ArrayAdapter<>(AdicionarColheitaActivity.this, R.layout.list_item, itemTalhao);
                autoCompTextViewTalhao.setAdapter(adapterItems);
            }
        });

        //autocomplete panhador
        autCompTextViewPanhador = findViewById(R.id.ac_textView_panhador);
        List<String> nomesPanhadores = new ArrayList<>();
        mPanhadorDb = new PanhadorDB(getBaseContext());
        Cursor cursorP = mPanhadorDb.queryPanhador((String) null, (String[]) null);
        if(cursorP != null){
            try{
                cursorP.moveToFirst();
                while(!cursorP.isAfterLast()){
                    nomesPanhadores.add(cursorP.getString(cursorP.getColumnIndexOrThrow("nomePanhador")));
                    cursorP.moveToNext();
                }
            } finally {
                cursorP.close();
            }
        }
        List<String> itemPanhador = nomesPanhadores;
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, itemPanhador);
        autCompTextViewPanhador.setAdapter(adapterItems);
    }
}
