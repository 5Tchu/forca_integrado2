package com.example.forca_integrado;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaCadastro extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {

    private EditText textoDaPalavra, textoDaDica;
    private Button btnCadastrar, btnListar;
    private RadioGroup grupo;
    private String categoriaSelecionada, palavra;
    private BD bd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bd = new BD(TelaCadastro.this);

        textoDaPalavra = findViewById(R.id.textPalavra);
        btnCadastrar = findViewById(R.id.button2);
        btnCadastrar.setOnClickListener(this);
        btnListar = findViewById(R.id.button3);
        btnListar.setOnClickListener(this);
        grupo = findViewById(R.id.id_grupo);
        grupo.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnCadastrar){
            String texto = textoDaPalavra.getText().toString();
            int conta = texto.length();
            String nivel;
            if(conta <= 4){
                nivel = "FACIL";
            }
            if(conta <= 7){
                nivel = "MEDIO";
            }
            else {
                nivel = "DIFICIL";
            }

            String dica = textoDaDica.getText().toString();
            boolean temDica = false;
            if (dica != null) {
                temDica = true;
            }

            boolean temTextoDigitado = false;
            if(texto.isEmpty()) {
                Toast.makeText(this, "Faltou a palavra pora", Toast.LENGTH_SHORT).show();
            }else {
                temTextoDigitado = true;
            }
            //testar os rádios pra ver se tem algum selecionado
                RadioButton r = findViewById(R.id.radioButton);
                RadioButton r1 = findViewById(R.id.radioButton2);
                RadioButton r2 = findViewById(R.id.radioButton3);
                RadioButton r3 = findViewById(R.id.radioButton4);
                RadioButton r4 = findViewById(R.id.radioButton5);

                boolean temRadioChecado = false;
                if(r.isChecked() || r1.isChecked() || r2.isChecked() || r3.isChecked() || r4.isChecked()){
                    temRadioChecado = true;
            }else{
                    Toast.makeText(this, "Faltou marcar categoria", Toast.LENGTH_SHORT).show();
                }
                if(temTextoDigitado && temRadioChecado && temDica) {
                    //aqui pode salvar no BD
                    Palavra palavra1 = new Palavra();
                    palavra1.setPalavraDigitada(texto);
                    palavra1.setDica(dica);
                    palavra1.setCategoria(categoriaSelecionada);
                    palavra1.setNivel(nivel);
                    bd.salvarPalavra(palavra1);
                    textoDaPalavra.setText("");
                    textoDaDica.setText("");
                    Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
                }



        }


        if(view == btnListar){
            startActivity(new Intent(this, TelaRecycler.class));

        }

    }

    @Override
    public void onCheckedChanged(@NonNull RadioGroup radioGroup, int i) {
        if(radioGroup == grupo){
            RadioButton temporario = findViewById(i);
            categoriaSelecionada = temporario.getText().toString();
            Toast.makeText(TelaCadastro.this, temporario.getText().toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }
}





