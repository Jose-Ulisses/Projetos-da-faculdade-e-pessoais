package com.example.agrogestor.Panhadores;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agrogestor.Panhadores.PanhadorDB.PanhadorDB;
import com.example.agrogestor.R;

public class TodosPanhadoresActivity extends AppCompatActivity{
    PanhadorDB mPanhadorDb;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_panhadores);

        mPanhadorDb = new PanhadorDB(getBaseContext());
        Cursor c = mPanhadorDb.queryPanhador((String) null, (String[]) null);
        if (c != null){
            try{
                c.moveToFirst();
                while(!c.isAfterLast()){
                    String nomePanhador = c.getString(c.getColumnIndexOrThrow("nomePanhador"));

                    mTextView = new TextView(this);
                    mTextView.setText(nomePanhador);
                    mTextView.setTextSize(35);
                    mTextView.setPadding(0,70,0,70);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );

                    mTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TodosPanhadoresActivity.this, InfoPanhadorActivity.class);
                            intent.putExtra("nomePanhador", nomePanhador);
                            startActivity(intent);
                        }
                    });

                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_todos_panhadores);
                    linearLayout.addView(mTextView);

                    c.moveToNext();
                }
            } finally {
                c.close();
            }
        }
    }
}
