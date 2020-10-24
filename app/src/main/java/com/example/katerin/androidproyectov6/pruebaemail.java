package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class pruebaemail extends AppCompatActivity {
    EditText correo,titulo,texto;
    Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebaemail);

        correo=(EditText) findViewById(R.id.correoFac);
        titulo=(EditText) findViewById(R.id.titulofac);
        texto=(EditText) findViewById(R.id.textofac);
        enviar=(Button) findViewById(R.id.enviarfac);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mandarMail();
            }
        });

    }
    private void mandarMail(){
        Intent email=new Intent(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto:"));
        email.setType("text/plain");
        email.putExtra(Intent.EXTRA_EMAIL,correo.getText());
        email.putExtra(Intent.EXTRA_SUBJECT,titulo.getText());
        email.putExtra(Intent.EXTRA_TEXT,texto.getText());

        startActivity(Intent.createChooser(email,"Send Email"));

    }

}