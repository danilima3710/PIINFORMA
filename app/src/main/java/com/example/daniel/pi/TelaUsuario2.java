package com.example.daniel.pi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TelaUsuario2 extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ListView listView1;

    private ArrayList<Artigo> listaArtigo = new ArrayList<Artigo>();
    private ArrayAdapter<Artigo> listaAdapterPessoa;
    private DataSnapshot artigosSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_usuario2);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        eventoDataBase();

        listView1= findViewById(R.id.listaViewArtigos);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(TelaUsuario2.this, itemSelecionado.class);
                i.putExtra("titulo", listaArtigo.get(position).getTitulo());
                i.putExtra("descricao", listaArtigo.get(position).getDescricao());
                i.putExtra("categoria", listaArtigo.get(position).getCategoria());
                i.putExtra("tipoArtigo", listaArtigo.get(position).getTipoArtigo());
                i.putExtra("publico", listaArtigo.get(position).getPublico());

                startActivity(i);
            }
        });

    }

    private void eventoDataBase() {
        databaseReference.child("Artigo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaArtigo.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Artigo artigo = objSnapshot.getValue(Artigo.class);
                    listaArtigo.add(artigo);
                }

                ArtigoAdapter listaArtigoAdapter = new
                        ArtigoAdapter(getBaseContext(), R.layout.template_item_lista_artigo, listaArtigo);
                listView1.setAdapter(listaArtigoAdapter);

                listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(TelaUsuario2.this, itemSelecionado.class);
                        i.putExtra("titulo", listaArtigo.get(position).getTitulo());
                        i.putExtra("descricao", listaArtigo.get(position).getDescricao());
                        i.putExtra("categoria", listaArtigo.get(position).getCategoria());
                        i.putExtra("tipoArtigo", listaArtigo.get(position).getTipoArtigo());
                        i.putExtra("publico", listaArtigo.get(position).getPublico());
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void sair(View view) {
        firebaseAuth.signOut();
        Intent i = new Intent(this, TelaLogin.class);
        startActivity(i);
    }
}
