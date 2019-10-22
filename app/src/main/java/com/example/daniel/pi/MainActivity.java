package com.example.daniel.pi;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    String login;
    String senha;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        databaseReference.child("Teste").setValue("rati").addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("Erro", e.getMessage());
            }
        });
    }

    public void entrar(View view) {
        final EditText l = (EditText) findViewById(R.id.login);
        final EditText s = (EditText) findViewById(R.id.senha);
        login = l.getText().toString();
        senha = s.getText().toString();

        if (login.equals("vilson.l") && (senha.equals("1234"))){
            Intent i = new Intent(this, telaPrincipal.class);
            startActivity(i);
        }else {

            Toast toast = Toast.makeText(getApplicationContext(), "Login ou Senha Incorreto(s)", Toast.LENGTH_LONG);
            toast.show();

        }
    }
}
