package com.example.agrogestor.Lavouras;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agrogestor.Lavouras.LavourasDB.LavouraDB;
import com.example.agrogestor.R;
import com.example.agrogestor.Talhao.TalhaoDB.TalhaoDB;
import com.example.agrogestor.Talhao.TalhaoDB.TalhaoDbSchema;

public class InfoLavouraActivity extends AppCompatActivity {
    LavouraDB mLavouraDb;
    TalhaoDB mTalhaoDb;
    private TextView mTextView, mTextViewLavoura;
    int idLavoura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_lavoura);

        String nomeLavoura = getIntent().getStringExtra("nomeLavoura");

        mTextViewLavoura = (TextView) findViewById(R.id.textViewLavoura);
        mTextViewLavoura.setText(nomeLavoura);

        mLavouraDb = new LavouraDB(getBaseContext());
        Cursor c = mLavouraDb.queryIdLavoura(nomeLavoura);
        if(c != null){
            try{
                if(c.moveToFirst()){
                    int idColumnIndex = c.getColumnIndex("_id");
                    if(idColumnIndex != -1)
                        idLavoura = c.getInt(idColumnIndex);
                }
            } finally {
                c.close();
            }
        }

        mTalhaoDb = new TalhaoDB(getBaseContext());
        Cursor cursorId = mTalhaoDb.queryTalhao((String) null, (String[]) null);
        if(cursorId != null){
            try{
                cursorId.moveToFirst();
                while(!cursorId.isAfterLast()){
                    int idLavouraTalhao = cursorId.getInt(cursorId.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.ID_LAVOURA));
                    if(idLavouraTalhao == idLavoura){
                        String nomeTalhao = cursorId.getString(cursorId.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO));
                        double totalTalhao = cursorId.getInt(cursorId.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.TOTAL_TALHAO));

                        mTextView = new TextView(this);
                        mTextView.setText(nomeTalhao + " = " + totalTalhao);
                        mTextView.setTextSize(35);
                        mTextView.setPadding(0, 25, 0, 25);

                        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );

                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_info_lavoura);
                        linearLayout.addView(mTextView);
                    }
                    cursorId.moveToNext();
                }
            } finally {
                cursorId.close();
            }
        }
    }
}