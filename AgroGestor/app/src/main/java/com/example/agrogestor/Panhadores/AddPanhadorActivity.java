package com.example.agrogestor.Panhadores;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agrogestor.Panhadores.PanhadorDB.PanhadorDB;
import com.example.agrogestor.R;

public class AddPanhadorActivity extends AppCompatActivity {
    private Button mBotaoAddPanhador;
    EditText inputNome, inputCpf, inputNumero, inputChavePix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_panhador);

        inputNome = (EditText) findViewById(R.id.input_nome_panhador);
        inputCpf = (EditText) findViewById(R.id.input_cpf_panhador);
        inputNumero = (EditText) findViewById(R.id.input_numero_panhador);
        inputChavePix = (EditText) findViewById(R.id.input_chavePix_panhador);

        mBotaoAddPanhador = (Button) findViewById(R.id.botao_add_panhador);
        mBotaoAddPanhador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomePanhador = inputNome.getText().toString();
                String cpfPanhador = inputCpf.getText().toString();
                String numeroPanhador = inputNumero.getText().toString();
                String chavePixPanhador = inputChavePix.getText().toString();

                adicionaPanhador(nomePanhador, cpfPanhador, numeroPanhador, chavePixPanhador);
                finish();
            }
        });
    }

    private void adicionaPanhador(String nome, String cpf, String numero, String chavePix){
        PanhadorDB panhadorDb = new PanhadorDB(this);
        panhadorDb.addPanhador(nome, cpf, numero, chavePix);
    }
}
