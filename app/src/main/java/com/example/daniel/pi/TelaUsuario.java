package com.example.daniel.pi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TelaUsuario extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ListView listView1;

    private ArrayList<Artigo> listaArtigo = new ArrayList<Artigo>();
    private ArrayAdapter<Artigo> listaAdapterPessoa;
    private DataSnapshot artigosSnapshot;
    //String artigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_usuario);

        firebaseDatabase = FirebaseDatabase.getInstance();
//    databaseReference = firebaseDatabase.getReference("Artigos/"+ artigos);
        databaseReference = firebaseDatabase.getReference("Artigos/");
        firebaseAuth = FirebaseAuth.getInstance();

        eventoDataBase();

        listView1= findViewById(R.id.listView);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(TelaUsuario.this, itemSelecionado.class);
                i.putExtra("titulo", listaArtigo.get(position).getTitulo());
                i.putExtra("desc", listaArtigo.get(position).getDescricao());
                i.putExtra("tipoArtigo", listaArtigo.get(position).getTipoArtigo());
                i.putExtra("cat", listaArtigo.get(position).getCategoria());
                i.putExtra("publico", listaArtigo.get(position).getPublico());
                startActivity(i);
            }
        });

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                artigosSnapshot = dataSnapshot;
//                listar();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(TelaUsuario.this, "Erro ao listar", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void eventoDataBase(){
        databaseReference.child("Artigo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaArtigo.clear();

                for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Artigo artigo = postSnapshot.getValue(Artigo.class);
                    listaArtigo.add(artigo);
                }
                ArtigoAdapter listaAdpaterArtigo = new ArtigoAdapter ( getApplicationContext(), R.layout.template_item_lista_artigo , listaArtigo);
                listView1.setAdapter(listaAdpaterArtigo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void deslogar(View view) {
        firebaseAuth.signOut();
        Intent i = new Intent(this, telaLogin.class);
        startActivity(i);
    }

//
//    public void listar(){
//        if (artigosSnapshot== null) return;
//        listaArtigo.clear();
//
//        for (DataSnapshot postSnapshot : artigosSnapshot.getChildren()){
//
//            Artigo artigo = artigosSnapshot.getValue(Artigo.class);
//
//            listaArtigo.add(artigo);
//        }
//        ArtigoAdapter lancamentoAdapter = new ArtigoAdapter(this, listaArtigo);
//
//        listView1.setAdapter(lancamentoAdapter);
//    }
}
