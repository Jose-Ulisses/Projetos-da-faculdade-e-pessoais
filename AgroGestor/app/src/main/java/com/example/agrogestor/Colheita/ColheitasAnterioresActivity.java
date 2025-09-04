package com.example.agrogestor.Colheita;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agrogestor.Colheita.ColheitaDB.ColheitaDB;
import com.example.agrogestor.Lavouras.LavourasDB.LavouraDB;
import com.example.agrogestor.Panhadores.PanhadorDB.PanhadorDB;
import com.example.agrogestor.R;
import com.example.agrogestor.Talhao.TalhaoDB.TalhaoDB;

public class ColheitasAnterioresActivity extends AppCompatActivity {
    TextView mTextViewColheitas;
    ColheitaDB mColheitaDb;
    LavouraDB mLavouraDb;
    TalhaoDB mTalhaoDb;
    PanhadorDB mPanhadorDb;
    String nomeLavoura, nomeTalhao, nomePanhador, dataColheita;
    Float qntdColheita;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colheitas_anteriores);

        mLavouraDb = new LavouraDB(getBaseContext());
        mTalhaoDb = new TalhaoDB(getBaseContext());
        mPanhadorDb = new PanhadorDB(getBaseContext());
        mColheitaDb = new ColheitaDB(getBaseContext());
        Cursor c = mColheitaDb.queryColheita((String) null, (String[]) null);
        if(c != null){
            try{
                c.moveToFirst();
                while(!c.isAfterLast()){
                    int idLavoura = c.getInt(c.getColumnIndexOrThrow("idLavouraColheita"));
                    nomeLavoura = mLavouraDb.queryNomeLavoura(idLavoura);

                    int idTalhao = c.getInt(c.getColumnIndexOrThrow("idTalhaoColheita"));
                    nomeTalhao = mTalhaoDb.queryNomeTalhao(idTalhao);

                    int idPanhador = c.getInt(c.getColumnIndexOrThrow("idPanhadorColheita"));
                    nomePanhador = mPanhadorDb.queryNomePanhador(idPanhador);

                    dataColheita = c.getString(c.getColumnIndexOrThrow("dataColheita"));
                    qntdColheita = c.getFloat(c.getColumnIndexOrThrow("quantidadeColheita"));

                    mTextViewColheitas = new TextView(this);
                    String text = nomeLavoura + "\n" + nomeTalhao + "\n" + nomePanhador + "\n" + qntdColheita + "\n" + dataColheita;
                    mTextViewColheitas.setText(text);

                    mTextViewColheitas.setTextSize(20);
                    mTextViewColheitas.setPadding(0, 70, 0, 70);

                    LinearLayout.LayoutParams layoutColheitas = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );

                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
                    linearLayout.addView(mTextViewColheitas);

                    c.moveToNext();
                }
            } finally {
                c.close();
            }
        }
    }
}
