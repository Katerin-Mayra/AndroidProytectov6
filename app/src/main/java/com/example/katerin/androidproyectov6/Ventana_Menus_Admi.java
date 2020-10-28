package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Ventana_Menus_Admi extends AppCompatActivity {
    Button CrearMenuAD,VerMenuAD;

    TextView nombre;
    TextView ci;
    TextView telefono;
    TextView calle;
    TextView tipo,_id;
    ImageButton atrasAD;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana__menus__admi);

        atrasAD = findViewById(R.id.atrasAD);
        atrasAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ventana_Menus_Admi.this,Ver_Restaurante_Admi.class));

            }
        });

        CrearMenuAD =  findViewById(R.id.CrearMenuAD);
        VerMenuAD =  findViewById(R.id.VerMenuAD);


        nombre=(TextView) findViewById(R.id.nombreRAD);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        telefono=(TextView) findViewById(R.id.telefonoRAD);
        telefono.setText( getIntent().getExtras().getString("telefono"));
        calle=(TextView) findViewById(R.id.calleRAD);
        calle.setText( getIntent().getExtras().getString("calle"));
        _id=(TextView) findViewById(R.id.idrestau);
        _id.setText( getIntent().getExtras().getString("id"));


     //   _id.setText( getIntent().getExtras().getString("id"));

        CrearMenuAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(Admi1.this,Registrar_Restaurant.class));
                Intent t=new Intent(Ventana_Menus_Admi.this,CrearMenu.class);
                t.putExtra("calle",calle.getText());
                t.putExtra("nombre",nombre.getText());
                t.putExtra("telefono",telefono.getText());
                t.putExtra("_id",_id.getText());

           //     t.putExtra("id",_id.getText());
                // t.putExtra("_id",_id);
                startActivity(t);
            }
        });
        VerMenuAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(Ventana_Menus_Admi.this,Ver_Menus_Admi.class);
              //  t.putExtra("_id",_id.getText());
                t.putExtra("_id",_id.getText());
                startActivity(t);

            }
        });



//ocultar navegacion kato
        getSupportActionBar().hide();

    }

}