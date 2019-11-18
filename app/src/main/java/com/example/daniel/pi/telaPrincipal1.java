package com.example.daniel.pi;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class telaPrincipal1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ListView listView;
    Artigo artigoSelecionado = new Artigo();
    EditText titulo, descricao, categoria, tipoArtigo, url, id, publico;

    private ArrayList<Artigo> listaArtigo = new ArrayList<Artigo>();
    private ArrayAdapter<Artigo> listaAdapterPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tela_principal1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listView);
        setSupportActionBar(toolbar);



        iniciaFireBase();
        eventoDataBase();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

                ArtigoAdapter adapter = new ArtigoAdapter(
                        getApplicationContext(), R.layout.list_item_artigo,listaArtigo
                );

                listaAdapterPessoa = new ArrayAdapter<Artigo>(telaPrincipal1.this, android.R.layout.simple_list_item_1, listaArtigo);
                listView.setAdapter(listaAdapterPessoa);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        databaseReference.child("Artigo").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

    private void iniciaFireBase() {
        FirebaseApp.initializeApp(telaPrincipal1.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseDatabase.setPersistenceEnabled(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_principal1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void cadastrar(MenuItem item) {
        Intent i = new Intent(this, ArtigoCadastrarActivity.class);
        startActivity(i);
    }

    public void sair(MenuItem item) {
        firebaseAuth.signOut();
        Intent i = new Intent(this, telaLogin.class);
        startActivity(i);
    }

    public void selecionar(View view) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemSelecionado item = new itemSelecionado();
                artigoSelecionado = (Artigo)parent.getItemAtPosition(position);
                item.setTitulo(artigoSelecionado.getTitulo());
                item.setDescricao(artigoSelecionado.getDescricao());
                item.setCategoria(artigoSelecionado.getCategoria());
                item.setPublico(artigoSelecionado.getPublico());
                item.setUrl(artigoSelecionado.getUrl());
                item.setId(artigoSelecionado.getId_artigo());
            }
        });
        Intent i = new Intent(this, itemSelecionado.class);
        startActivity(i);
    }
}
