package com.example.agrogestor.Talhao;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agrogestor.Lavouras.LavourasDB.LavouraDB;
import com.example.agrogestor.Lavouras.LavourasDB.LavourasDbHelper;
import com.example.agrogestor.R;
import com.example.agrogestor.Talhao.TalhaoDB.TalhaoDB;

import java.util.ArrayList;
import java.util.List;

public class AddTalhaoActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    LavouraDB mLavouraDb;
    TalhaoDB mTalhaoDb;
    EditText inputNomeTalhao;
    EditText inputPrecoTalhao;
    private Button mBotaoAddTalhao;
    int idLavoura;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_talhao);

        //autocomplete lavouras
        autoCompleteTextView = findViewById(R.id.auto_complete_textview);
        List<String> nomes = new ArrayList<>();
        mLavouraDb = new LavouraDB(getBaseContext());
        Cursor c = mLavouraDb.queryLavoura((String) null, (String[]) null);
        if(c != null){
            try{
                c.moveToFirst();
                while(!c.isAfterLast()){
                    nomes.add(c.getString(c.getColumnIndexOrThrow("nomeLavoura")));
                    c.moveToNext();
                }
            } finally {
                c.close();
            }
        }

        List<String> item = nomes;

        //query lavoura id
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item_lavoura, item);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
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
            }
        });

        mBotaoAddTalhao = (Button) findViewById(R.id.botao_add_talhao);
        mBotaoAddTalhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNomeTalhao = (EditText) findViewById(R.id.input_nome_talhao);
                String nomeTalhao = inputNomeTalhao.getText().toString();

                inputPrecoTalhao = (EditText) findViewById(R.id.input_preco_talhao);
                int precoTalhao = Integer.parseInt(inputPrecoTalhao.getText().toString());

                adicionaTalhao(nomeTalhao, precoTalhao, idLavoura);
                finish();
            }
        });
    }

    void adicionaTalhao(String nomeTalhao, int precoTalhao, int idLavoura){
        mTalhaoDb = new TalhaoDB(this);
        mTalhaoDb.addTalhao(nomeTalhao, precoTalhao, idLavoura);
    }
}
