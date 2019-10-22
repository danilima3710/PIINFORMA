package com.example.daniel.pi;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cadastrar extends AppCompatActivity {
    artigo objetoArtigo = new artigo();
    DatabaseReference databaseReference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void cadastrar(View view) {
        final EditText t = (EditText) findViewById(R.id.titulo);
        final EditText d = (EditText) findViewById(R.id.descricao);
        final Boolean estagio = (Boolean) findViewById(R.id.estagio).isSelected();
        final Boolean bolsa = (Boolean) findViewById(R.id.bolsa).isSelected();
        final Boolean palestra = (Boolean) findViewById(R.id.palestra).isSelected();
        final Boolean projetoExtensao = (Boolean) findViewById(R.id.projetoExtensao).isSelected();
        final Boolean intervaloCultura = (Boolean) findViewById(R.id.intervaloCultural).isSelected();
        final Boolean evento = (Boolean) findViewById(R.id.evento).isSelected();
        final Boolean noticia = (Boolean) findViewById(R.id.noticia).isSelected();
        final Boolean externo = (Boolean) findViewById(R.id.externo).isSelected();
        final Boolean interno = (Boolean) findViewById(R.id.interno).isSelected();

        if (estagio) {
            objetoArtigo.categoria = " Estágio ";
        }else {
            if (bolsa){
                objetoArtigo.categoria = "Bolsa";
            } else {
                if (palestra) {
                    objetoArtigo.categoria = "Palestra";
                }else{
                    if (projetoExtensao){
                        objetoArtigo.categoria = "Projeto de Extensão";
                    }else
                    if (intervaloCultura){
                        objetoArtigo.categoria = "Intervalo Cultural";
                    }
                }
            }
        }

        if (evento){
            objetoArtigo.tipoArtigo = "Evento";
        }else{
            if (noticia){
                objetoArtigo.tipoArtigo = "Notícia";
            }
        }

        if (interno){
            objetoArtigo.publico = "Interno";
        }else{
            if (externo){
                objetoArtigo.publico = "Externo";
            }
        }

        objetoArtigo.setTitulo(t.getText().toString());
        objetoArtigo.setDescricao(d.getText().toString());


        databaseReference.child("Categoria").push().setValue(objetoArtigo.tipoArtigo).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("Erro na Categoria", e.getMessage());
            }
        });

        databaseReference.child("Publico").push().setValue(objetoArtigo.publico).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("Erro no Publico", e.getMessage());
            }
        });

        databaseReference.child("tipoArtigo").push().setValue(objetoArtigo.tipoArtigo).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("Erro no Tipo do Artigo", e.getMessage());
            }
        });

        databaseReference.child("Titulo").push().setValue(objetoArtigo.titulo).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("Erro no Titulo", e.getMessage());
            }
        });

        databaseReference.child("Descricao").push().setValue(objetoArtigo.descricao).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("Erro na Descricao", e.getMessage());
            }
        });


    }
}
