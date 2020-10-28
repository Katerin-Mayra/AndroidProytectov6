package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.net.URI;

public class Info_RestauranteCA extends AppCompatActivity {
    Button crear,ver,edit,llamarR;
    ImageButton atrasIRC2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__restaurante_c);
        crear = findViewById(R.id.crearmenuIR);
        ver = findViewById(R.id.vermenuIR);
        edit = findViewById(R.id.editarmenuIR);

        llamarR = findViewById(R.id.llamarR);


        atrasIRC2 = findViewById(R.id.atrasIRC2);
        atrasIRC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Info_RestauranteCA.this,Ver_Restaurante_Cliente.class));

            }
        });

        final TextView nombre=(TextView) findViewById(R.id.nombreIRV);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        final TextView calle=(TextView) findViewById(R.id.calleIRV);
        calle.setText( getIntent().getExtras().getString("calle"));
        final TextView telefono=(TextView) findViewById(R.id.telefonoIRV);
        telefono.setText( getIntent().getExtras().getString("telefono"));

        final TextView propietario=(TextView) findViewById(R.id.propertyIRV);
        propietario.setText( getIntent().getExtras().getString("propietario"));


        String _id_de_mi_restaurant=getIntent().getExtras().getString("_id");


        llamarR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+telefono.getText()));
                startActivity(intent);

            }
        });
        /*
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
        });*/

        //ocultar navegacion kato
        getSupportActionBar().hide();
    }
}