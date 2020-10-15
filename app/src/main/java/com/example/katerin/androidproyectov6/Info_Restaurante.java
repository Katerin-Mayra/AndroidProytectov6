package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Info_Restaurante extends AppCompatActivity {
    Button crear,ver,edit,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__restaurante);

        crear = findViewById(R.id.crearmenuIR);
        ver = findViewById(R.id.vermenuIR);
        edit = findViewById(R.id.editarmenuIR);
        delete = findViewById(R.id.elimenuIR);
        final TextView nombre=(TextView) findViewById(R.id.nombreIR);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        final TextView calle=(TextView) findViewById(R.id.calleIR);
        calle.setText( getIntent().getExtras().getString("calle"));
        final TextView telefono=(TextView) findViewById(R.id.telefonoIR);
        telefono.setText( getIntent().getExtras().getString("telefono"));
        final TextView propietario=(TextView) findViewById(R.id.propertyIR);
        propietario.setText( getIntent().getExtras().getString("propietario"));

        String _id_de_mi_restaurant=getIntent().getExtras().getString("_id");

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Info_Restaurante.this,CrearMenu.class));

            }
        });
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Info_Restaurante.this,VerMenu.class));

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Info_Restaurante.this,EditarMenu.class));
            }
        });
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Info_Restaurante.this,EliminarMenu.class));

            }
        });

    }

}