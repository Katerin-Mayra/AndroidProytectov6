package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import collects1.Menus;
import collects1.VerMenuAdapter;
import cz.msebera.android.httpclient.Header;

public class EditarMenu extends AppCompatActivity {
    ListView list;
    ImageButton atras4;
    ArrayList<Menus> list_data = new ArrayList<Menus> ();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_menu);
        atras4 = findViewById(R.id.imatras);
        list = findViewById(R.id.listamenu1);
        atras4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditarMenu.this,Info_Restaurante.class));

            }
        });
        loadComponents();
        list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Menus menus = list_data.get(position);


                Intent i = new Intent(EditarMenu.this, Editar1.class);
                i.putExtra ("nombre", menus.nombre);
                i.putExtra ("descripcion", menus.descripcion);
                i.putExtra ("precio",menus.precio);
                //i.putExtra("_id", menus.id);

                startActivity(i);


            }
        });

        //ocultar navegacion kato
        getSupportActionBar().hide();
    }


    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://192.168.1.110:7777/api/v1.0/menus",  new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONArray data = response.getJSONArray("result");
                    for (int i =0 ; i < data.length(); i++) {
                        Menus menus = new Menus();
                        JSONObject object = data.getJSONObject(i);
                        menus.setId(object.getString("_id"));
                        menus.setNombre(object.getString("nombre"));
                        menus.setDescripcion(object.getString("descripcion"));
                        menus.setPrecio(object.getInt("precio"));
                        //menus.setFoto(object.getString("foto"));
                        list_data.add(menus);
                    }
                    VerMenuAdapter adapter =  new VerMenuAdapter(EditarMenu.this,list_data);
                    list.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });




    }

}