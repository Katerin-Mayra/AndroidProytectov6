package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import collects1.MenuAdapter;
import collects1.Menus;
import collects1.StaticData;

public class Carrito extends AppCompatActivity {
    Button pedir;
    ListView list1;
    Bundle bundle ;
    MenuAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        pedir= findViewById (R.id.pedir);
        pedir.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent(Carrito.this,Pedidos.class));
                finish ();

            }
        });
        StaticData.LISTAPARCIAL.clear();
        //ocultar navegacion kato
        getSupportActionBar().hide();
    }
    @Override
    protected void onResume() {

        if (StaticData.LISTAPARCIAL == null) {
            StaticData.LISTAPARCIAL = new ArrayList<>();
        }
        bundle = getIntent().getExtras();
        Menus item2 = new Menus();
        item2.setNombre(bundle.getString("nombre"));
        item2.setPrecio(bundle.getInt("precio"));
        StaticData.LISTAPARCIAL.add(item2);
        list1 = findViewById(R.id.pedidos);
        adapter = new MenuAdapter(this, StaticData.LISTAPARCIAL);
        list1.setAdapter(adapter);

        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StaticData.LISTAPARCIAL.remove(position);
                adapter.notifyDataSetChanged();


            }
        });


        super.onResume();
    }
}