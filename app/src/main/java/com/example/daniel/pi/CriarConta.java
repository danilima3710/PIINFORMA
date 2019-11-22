package com.example.daniel.pi;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CriarConta extends AppCompatActivity {
    Conta conta = new Conta();
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = FirebaseAuth.getInstance();


    }

    public void criaConta(View view) {
        final EditText login = (EditText) findViewById(R.id.login);
        final EditText senha = (EditText) findViewById(R.id.senha);
        final EditText senha2 = (EditText) findViewById(R.id.senha2);
        String log = login.getText().toString();
        String sen = senha.getText().toString();
        String sen2 = senha2.getText().toString();

        //Verifica se o login está vazio
        if (log.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "Por favor insira um login", Toast.LENGTH_LONG);
            toast.show();
        }else{
            //Verifica se a senha está vazia
            if (sen.isEmpty()){
                Toast toast = Toast.makeText(getApplicationContext(), "Por favor insira uma senha", Toast.LENGTH_LONG);
                toast.show();
            }else {
                //Verifica se a senha é igual a senha2
                if (!sen.equals(sen2)){
                    Toast toast = Toast.makeText(getApplicationContext(), "As senhas não são compativeis", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    //Verifica se não tem algum campo vazio
                    if (!sen.isEmpty() && !sen2.isEmpty() && !log.isEmpty()){
                        firebaseAuth.createUserWithEmailAndPassword(log, sen).addOnCompleteListener(CriarConta.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()){
                                    Toast toast = Toast.makeText(getApplicationContext(), "Não foi possivel criar a conta", Toast.LENGTH_LONG);
                                    toast.show();
                                }else{
                                    Intent i = new Intent(CriarConta.this, TelaLogin.class);
                                    startActivity(i);
                                }
                            }
                        });
                    }
                }
            }
        }


        if (!log.isEmpty()){
            //if (senha.equals(senha2)){
                conta.setLogin(login.getText().toString());
                conta.setSenha(senha.getText().toString());

                firebaseAuth.createUserWithEmailAndPassword(conta.getLogin(), conta.getSenha());
                //firebaseAuth.createUserWithEmailAndPassword("a@a.com", "as123");
//            }else{
//                Toast toast = Toast.makeText(getApplicationContext(), "As senhas não são compativeis", Toast.LENGTH_LONG);
//                toast.show();
//            }
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Por favor insira um login", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
