package com.example.agrogestor.Colheita;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agrogestor.Colheita.ColheitaDB.ColheitaDB;
import com.example.agrogestor.Lavouras.LavourasDB.LavouraDB;
import com.example.agrogestor.Panhadores.PanhadorDB.PanhadorDB;
import com.example.agrogestor.R;
import com.example.agrogestor.Talhao.TalhaoDB.TalhaoDB;
import com.example.agrogestor.Talhao.TalhaoDB.TalhaoDbSchema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AdicionarColheitaActivity extends AppCompatActivity {
    ColheitaDB mColheitaDb;
    AutoCompleteTextView AutoCompTextViewLavoura, autoCompTextViewTalhao, autCompTextViewPanhador;
    LavouraDB mLavouraDb;
    TalhaoDB mTalhaoDb;
    PanhadorDB mPanhadorDb;
    ArrayAdapter<String> adapterItems;
    String nomeLavoura, nomeTalhao, nomePanhador;
    int idLavoura, idTalhao, idPanhador;
    private Button mBotaoAddColheita;
    LocalDateTime dataHoraAtual;
    DateTimeFormatter formatador;
    EditText inputQntd;

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
                nomeLavoura = adapterView.getItemAtPosition(i).toString();
                //get id lavoura
                Cursor cursorId = mLavouraDb.queryIdLavoura(nomeLavoura);
                idLavoura = getId(cursorId);

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
                autoCompTextViewTalhao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        nomeTalhao = adapterView.getItemAtPosition(i).toString();

                        Cursor cursorId = mTalhaoDb.queryIdTalhao(nomeTalhao);
                        idTalhao = getId(cursorId);
                    }
                });
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
        autCompTextViewPanhador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nomePanhador = adapterView.getItemAtPosition(i).toString();

                Cursor cursorId = mPanhadorDb.queryIdPanhador(nomePanhador);
                idPanhador = getId(cursorId);
            }
        });

        mBotaoAddColheita = (Button) findViewById(R.id.botao_add_colheita);
        mBotaoAddColheita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataHoraAtual = LocalDateTime.now();
                formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                String data = dataHoraAtual.format(formatador);

                inputQntd = (EditText) findViewById(R.id.input_qtd);
                double qntd = Double.parseDouble(inputQntd.getText().toString());

                adicionarColheita(idLavoura, idTalhao, idPanhador, qntd, data);
                finish();
            }
        });
    }

    private int getId(Cursor cursorId){
        int id = -1;
        if(cursorId != null){
            try{
                if(cursorId.moveToFirst()){
                    int idColumnIndex = cursorId.getColumnIndex("_id");
                    if(idColumnIndex != -1)
                        id = cursorId.getInt(idColumnIndex);
                }
            } finally {
                cursorId.close();
            }
        }
        return id;
    }

    private void adicionarColheita(int idLavoura, int idTalhao, int idPanhador, double qntd, String data){
        mColheitaDb = new ColheitaDB(this);
        mColheitaDb.addColheita(idLavoura, idTalhao, idPanhador, qntd, data);
    }
}
