package com.example.daniel.pi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TelaLogin extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    String login;
    String senha;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        firebaseAuth = FirebaseAuth.getInstance();
        Button btnEntrar = findViewById(R.id.entrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrar(v);
            }
        });



       // firebaseAuth.createUserWithEmailAndPassword("teste@teste.com", "1234mudar");



        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    public void entrar(View view) {
        final EditText l = (EditText) findViewById(R.id.login);
        final EditText s = (EditText) findViewById(R.id.senha);
        login = l.getText().toString();
        senha = s.getText().toString();
        login = "rbeninca@gmail.com";
        senha = "123mudar";

        if (login.equals("admin") && (senha.equals("admin")) ){
            Intent i = new Intent(getApplicationContext(), telaPrincipal2.class);
            startActivity(i);
        }else{
            if (!login.isEmpty() && !senha.isEmpty()){
                firebaseAuth.signInWithEmailAndPassword(login, senha);
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Intent i = new Intent(getApplicationContext(), TelaUsuario2.class);
                    startActivity(i);
                } else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Login ou Senha Incorreto(s)", Toast.LENGTH_LONG);
                    toast.show();
                }
            }else {
                if (login.isEmpty() && !senha.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Login está vazio", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    if (!login.isEmpty() && senha.isEmpty()){
                        Toast toast = Toast.makeText(getApplicationContext(), "Senha está vazia", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        if (login.isEmpty() && senha.isEmpty()){
                            Toast toast = Toast.makeText(getApplicationContext(), "Favor inserir o login e a senha", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                }
            }
        }

    }

    public void criarConta(View view) {
        Intent i = new Intent(this, criarConta.class);
        startActivity(i);
    }
}
