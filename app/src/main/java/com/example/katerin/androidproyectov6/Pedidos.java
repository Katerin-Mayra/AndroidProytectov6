package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Pedidos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
         /* for (int i = 0; i < 100; i++) {
                item p1 = new item();
                p1.id = i;
                p1.nombre = "Titulo" + i;
                p1.description = "Descripcion" + i;
                p1.url = "image" + i;
                list_data1.add (p1);
            }
            listAdapter adapter = new listAdapter(this, list_data1);
            list = this.findViewById (R.id.pedidos);
            list.setAdapter (adapter);


            list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                @SuppressLint("ResourceType")
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    final item item2 = list_data1.get (position);


                    Intent i = new Intent(Pedidos.this, InfoPedidos.class);
                    i.putExtra ("Titulo", item2.getNombre());
                    i.putExtra ("Descripcion", item2.getDescription());
                    i.putExtra ("image",item2.getUrl());
                    startActivity(i);
                }
            });*/
    }
}