package com.example.daniel.pi;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import dmax.dialog.SpotsDialog;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class ArtigoCadastrarActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_CODE = 1;
    private static final int PICK_IMAGE_REQUEST = 1;
    Artigo objetoArtigo = new Artigo();
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    StorageReference storageReference;
    Button btn_upload;
    ImageView imageView;
    Uri uri;
    AlertDialog dialog;
    String url;
    Button btn_cadastrar;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inicializaDataBase();

        dialog = new SpotsDialog.Builder().setContext(this).build();
        btn_upload = (Button) findViewById(R.id.button2);

        btn_cadastrar = (Button) findViewById(R.id.button);
//        findViewById(R.id.button).setEnabled(false);
        findViewById(R.id.button).setEnabled(true);
    }

    private void inicializaDataBase() {
        FirebaseApp.initializeApp(ArtigoCadastrarActivity.this);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        storageReference = FirebaseStorage.getInstance().getReference("image_upload"); //Firebase Storage
    }

    public void cadastrar(View view) {
    }


    public void cad(View view) {


        final EditText t = (EditText) findViewById(R.id.titulo);
        final EditText d = (EditText) findViewById(R.id.descricao);
        final RadioButton estagio = (RadioButton) findViewById(R.id.estagio);
        final RadioButton bolsa = (RadioButton) findViewById(R.id.bolsa);
        final RadioButton palestra = (RadioButton) findViewById(R.id.palestra);
        final RadioButton projetoExtensao = (RadioButton) findViewById(R.id.projetoExtensao);
        final RadioButton intervaloCultural = (RadioButton) findViewById(R.id.intervaloCultural);
        final RadioButton evento = (RadioButton) findViewById(R.id.evento);
        final RadioButton noticia = (RadioButton) findViewById(R.id.noticia);
        final RadioButton externo = (RadioButton) findViewById(R.id.externo);
        final RadioButton interno = (RadioButton) findViewById(R.id.interno);


        Artigo artigo = new Artigo();
        artigo.setId_artigo(UUID.randomUUID().toString());
        artigo.setTitulo(t.getText().toString());
        artigo.setDescricao(d.getText().toString());

        if (estagio.isChecked() == true) {
            artigo.setCategoria("Estagio");
        } else {
            if (bolsa.isChecked() == true) {
                artigo.setCategoria("Bolsa");
            } else {
                if (palestra.isChecked() == true) {
                    artigo.setCategoria("Palestra");
                } else {
                    if (projetoExtensao.isChecked() == true) {
                        artigo.setCategoria("Projeto de Extensão");
                    } else if (intervaloCultural.isChecked() == true) {
                        artigo.setCategoria("Intervalo Cultural");
                    }
                }
            }
        }

        if (evento.isChecked() == true) {
            artigo.setTipoArtigo("Evento");
        } else {
            if (noticia.isChecked() == true) {
                artigo.setTipoArtigo("Notícia");
            }
        }

        if (interno.isChecked() == true) {
            artigo.setPublico("Interno");
        } else {
            if (externo.isChecked() == true) {
                artigo.setPublico("Externo");
            }
        }

        artigo.setUrl(url);


        databaseReference.child("Artigo").child(artigo.getId_artigo()).setValue(artigo).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("Erro insert", e.getMessage());
            }
        });

        Toast toast = Toast.makeText(getApplicationContext(), "Artigo cadastrado com sucesso!!!", Toast.LENGTH_LONG);
        toast.show();

        Intent i = new Intent(this, ArtigoCadastrarActivity.class);
        startActivity(i);
    }

    public void upload(View view) {
        findViewById(R.id.button).setEnabled(true);
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(i.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);

    }


    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            dialog.show();

            UploadTask uploadTask = storageReference.putFile(data.getData());

            Task<Uri>task = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){
                        Toast.makeText(ArtigoCadastrarActivity.this, "Falhou", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    return  storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        url = task.getResult().toString().substring(0,task.getResult().toString().indexOf("&token"));
                        Log.d("Link", url);
//                        Picasso.get().load(url).into(imageView);

                        dialog.dismiss();
                    }
                }
            });
        dialog.dismiss();
        }

        }


