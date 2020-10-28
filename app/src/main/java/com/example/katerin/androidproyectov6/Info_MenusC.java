package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Info_MenusC extends AppCompatActivity {
    Button crear,ver,edit,delete;
    ImageButton atrasIMVC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__menus_c);
/*
        crear = findViewById(R.id.crearmenuIR);
        ver = findViewById(R.id.vermenuIR);
        edit = findViewById(R.id.editarmenuIR);
        delete = findViewById(R.id.elimenuIR);
*/
        atrasIMVC = findViewById(R.id.atrasIMVC);
        atrasIMVC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Info_MenusC.this,Ver_menus_cliente.class));

            }
        });
        final TextView nombre=(TextView) findViewById(R.id.nombreIMVC);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        final TextView precio=(TextView) findViewById(R.id.precioIMVC);
        precio.setText( getIntent().getExtras().getString("precio"));
        final TextView descripcion=(TextView) findViewById(R.id.descripcionIMVC);
        descripcion.setText( getIntent().getExtras().getString("descripcion"));


/*
        final TextView propietario=(TextView) findViewById(R.id.propertyIRV);
        propietario.setText( getIntent().getExtras().getString("propietario"));
*/

        String _id_de_mi_restaurant=getIntent().getExtras().getString("_id");

        //ocultar navegacion kato
        getSupportActionBar().hide();
    }

}