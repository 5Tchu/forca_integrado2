package com.example.forca_integrado;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class TelaJogo extends AppCompatActivity implements View.OnClickListener {
    private ImageView imagem;
    private ArrayList<Integer> listaImagens, listaIdsButtons;
    private ArrayList<String> listaPalavras;
    private int indiceListaImagens, contaAcerto, contaErro;
    private TextView texto, txAcerto, txErro;
    private String palavra;
    private char[] estado;

    private Button b1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_jogo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imagem = findViewById(R.id.imageView2);
        txAcerto = findViewById(R.id.txAcerto);
        txErro = findViewById(R.id.txErro);
        contaAcerto = 0;
        contaErro = 0;
        indiceListaImagens = 0;
        listaImagens = new ArrayList<Integer>();
        listaImagens.add(R.drawable.forca_1_9);
        listaImagens.add(R.drawable.forca_2_9);
        listaImagens.add(R.drawable.forca_3_9);
        listaImagens.add(R.drawable.forca_4_9);
        listaImagens.add(R.drawable.forca_5_9);
        listaImagens.add(R.drawable.forca_6_9);
        listaImagens.add(R.drawable.forca_7_9);
        listaImagens.add(R.drawable.forca_9_9);
        listaImagens.add(R.drawable.forca_10_9);
        listaImagens.add(R.drawable.forca_11_9);

        listaPalavras =  new ArrayList<String>();
        listaPalavras.add("CAIXA");
        listaPalavras.add("BOLA");
        listaPalavras.add("TOMATE");
        listaPalavras.add("ABACAXI");
        listaPalavras.add("CARRETA");
        listaPalavras.add("ELEFANTE");
        listaPalavras.add("MACACO");
        listaPalavras.add("MELANCIA");
        listaPalavras.add("ASFALTO");
        listaPalavras.add("FORMULA");
        listaPalavras.add("TREMEMBE");
        listaPalavras.add("ESCOLA");
        listaPalavras.add("UNIVERSIDADE");

        texto = findViewById(R.id.textView3);

        listaIdsButtons = new ArrayList<Integer>();
        listaIdsButtons.add(R.id.id1);
        listaIdsButtons.add(R.id.id2);
        listaIdsButtons.add(R.id.id3);
        listaIdsButtons.add(R.id.id4);
        listaIdsButtons.add(R.id.id5);
        listaIdsButtons.add(R.id.id6);
        listaIdsButtons.add(R.id.id7);
        listaIdsButtons.add(R.id.id8);
        listaIdsButtons.add(R.id.id9);
        listaIdsButtons.add(R.id.id10);
        listaIdsButtons.add(R.id.id11);
        listaIdsButtons.add(R.id.id12);
        listaIdsButtons.add(R.id.id13);
        listaIdsButtons.add(R.id.id14);
        listaIdsButtons.add(R.id.id15);
        listaIdsButtons.add(R.id.id16);
        listaIdsButtons.add(R.id.id17);
        listaIdsButtons.add(R.id.id18);
        listaIdsButtons.add(R.id.id19);
        listaIdsButtons.add(R.id.id20);
        listaIdsButtons.add(R.id.id21);
        listaIdsButtons.add(R.id.id22);
        listaIdsButtons.add(R.id.id23);
        listaIdsButtons.add(R.id.id24);
        listaIdsButtons.add(R.id.id25);
        listaIdsButtons.add(R.id.id26);

        for(int j=0; j<listaIdsButtons.size();j++){
            Button b = findViewById(listaIdsButtons.get(j));
            b.setOnClickListener(this);
        }
        inicializaJogo();

    }
    public void inicializaJogo(){
        imagem.setImageResource(R.drawable.forca_0_9);
        indiceListaImagens = 0;
        palavra = sorteiaPalavra();
        estado = new char[palavra.length()];
        for(int i =0; i<estado.length;i++){
            estado[i] = '_';
        }
        contaErro =0;
        contaAcerto = 0;
        txAcerto.setText(Integer.toString(contaAcerto));
        txErro.setText(Integer.toString(contaErro)+"/"+Integer.toString(listaImagens.size()));
        atualizaTexto();
        for(int j=0; j<listaIdsButtons.size();j++){
            Button b = findViewById(listaIdsButtons.get(j));
            b.setEnabled(true);
        }
    }
    public void checaSeTerminou(){
        boolean verifica = false;
        for(int i=0; i<estado.length;i++){
            if(estado[i]=='_'){
                //se der true, é pq ainda tem jogo
                verifica = true;
            }
        }
        //se o verifica estiver false, siginifica que ganhou
        if(!verifica){
            AlertDialog.Builder caixa = new AlertDialog.Builder(this);
            caixa.setTitle("Você Venceu!!!!!!");
            caixa.setMessage("Deseja jogar novamente?");
            caixa.setPositiveButton("Jogar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    inicializaJogo();
                }
            });
            caixa.show();
        }
        if(contaErro >= listaImagens.size())
        {
            AlertDialog.Builder caixa = new AlertDialog.Builder(this);
            caixa.setTitle("Você Perdeu Playboy!!!!!!");
            caixa.setMessage("Deseja jogar novamente?");
            caixa.setPositiveButton("Jogar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    inicializaJogo();
                }
            });
            caixa.show();
        }

    }
    public void verificaLetra(char c){
        boolean status = false;
        for(int i=0; i<palavra.length();i++){
            if(palavra.charAt(i)==c){
                status = true;
                estado[i] = c;
            }
        }
        if(!status){
            atualizaForca();
            contaErro++;
            txErro.setText(Integer.toString(contaErro)+"/"+Integer.toString(listaImagens.size()));
        }
        else {
            atualizaTexto();
            contaAcerto++;
            txAcerto.setText(Integer.toString(contaAcerto));
        }
        checaSeTerminou();


    }
    public void atualizaTexto(){
        String temporaria= new String();
        temporaria="";
        for(int i =0; i<estado.length; i++){
            temporaria+= estado[i] + " ";
        }
        texto.setText(temporaria);
    }
    public String sorteiaPalavra(){
        String retorno = new String();
        Collections.shuffle(listaPalavras);
        retorno = listaPalavras.get(0);
        return retorno;
    }
    public void atualizaForca(){
        imagem.setImageResource(listaImagens.get(indiceListaImagens));
        indiceListaImagens++;
    }

    @Override
    public void onClick(View view) {
       Button b = (Button) view;
       verificaLetra(b.getText().toString().charAt(0));
       b.setEnabled(false);

    }
}