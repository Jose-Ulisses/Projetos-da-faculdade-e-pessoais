package com.example.agrogestor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agrogestor.Colheita.ColheitaActivity;
import com.example.agrogestor.Lavouras.LavourasActivity;

public class MainActivity extends AppCompatActivity {
    private Button mButtonColheita;
    private Button mButtonLavouras;
    private Button mButtonPanhadores;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonColheita = (Button) findViewById(R.id.botao_colheita);
        mButtonColheita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ColheitaActivity.class);
                startActivity(intent);
            }
        });

        mButtonLavouras = (Button) findViewById(R.id.botao_lavouras);
        mButtonLavouras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LavourasActivity.class);
                startActivity(intent);
            }
        });
    }
}
