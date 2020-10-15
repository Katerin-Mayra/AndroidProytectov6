package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class InfoPedidos extends AppCompatActivity {
    ImageView image1;
    TextView title2,descripcion1;
    String titulo,descripcion,img;
    RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pedidos);
        informacion();
        group = findViewById(R.id.group);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.enviado){
                    Toast.makeText(getApplicationContext(), "enviado", Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.proceso){
                    Toast.makeText(getApplicationContext(), "proceso", Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.espera){
                    Toast.makeText(getApplicationContext(), "espera", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void informacion(){
        Intent intent = getIntent();
        titulo = intent.getStringExtra("Titulo");
        descripcion = intent.getStringExtra("Descripcion");
        img = intent.getStringExtra("image");
        title2.setText(titulo);
        descripcion1.setText(descripcion);
        image1.setImageURI(Uri.parse(img));
    }



}