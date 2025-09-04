package com.example.agrogestor.Lavouras;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agrogestor.Lavouras.LavourasDB.LavouraDB;
import com.example.agrogestor.R;

public class TodasLavourasActivity extends AppCompatActivity {
    LavouraDB mLavouraDb;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todas_lavouras);

        mLavouraDb = new LavouraDB(getBaseContext());
        Cursor c = mLavouraDb.queryLavoura((String) null, (String[]) null);
        if (c != null){
            try{
                c.moveToFirst();
                while(!c.isAfterLast()){
                    String nomeLavoura = c.getString(c.getColumnIndexOrThrow("nomeLavoura"));

                    mTextView = new TextView(this);
                    mTextView.setText(nomeLavoura);
                    mTextView.setTextSize(45);
                    mTextView.setPadding(0,70,0,70);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );

                    mTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TodasLavourasActivity.this, InfoLavouraActivity.class);
                            intent.putExtra("nomeLavoura", nomeLavoura);
                            startActivity(intent);
                        }
                    });

                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_todas_lavouras);
                    linearLayout.addView(mTextView);

                    c.moveToNext();
                }
            } finally {
                c.close();
            }
        }
    }

}
