package com.example.daniel.pi;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class itemSelecionado extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selecionado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        String titulo = (String) i.getSerializableExtra("titulo");
        String descricao = (String) i.getSerializableExtra("descricao");
        String categoria = (String) i.getSerializableExtra("categoria");
        String tipoArtigo = (String) i.getSerializableExtra("tipoArtigo");
        String publico = (String) i.getSerializableExtra("publico");

        TextView wTitulo = (TextView) findViewById(R.id.titulo);
        TextView wDescricao = (TextView) findViewById(R.id.descricao);
        TextView wCategoria = (TextView) findViewById(R.id.categoria);
        TextView wArtigo = (TextView) findViewById(R.id.tipoArtigo);
        TextView wPublico = (TextView) findViewById(R.id.publicoAlvo);


        wTitulo.setText(titulo);
        wDescricao.setText(descricao);
        wCategoria.setText(categoria);
        wArtigo.setText(tipoArtigo);
        wPublico.setText(publico);
        wDescricao.setText(descricao);
        wCategoria.setText(categoria);
    }

}
