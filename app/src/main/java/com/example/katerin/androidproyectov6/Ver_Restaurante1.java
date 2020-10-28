package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.katerin.androidproyectov6.adapter1.EsRestaurante;
import com.example.katerin.androidproyectov6.adapter1.ResAdapter1;
import com.example.katerin.androidproyectov6.apiResfull.ResApi;
import com.example.katerin.androidproyectov6.apiResfull.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Ver_Restaurante1 extends AppCompatActivity implements onLoadData {

    ListView listares;
    String id;
    ImageButton atrasVR;
    ArrayList<EsRestaurante> datos=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__restaurante1);
        /*
        atrasVR = findViewById(R.id.imatras);
        atrasVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditarMenu.this,InfoRestaurant.class));

            }
        });*/
        listares = findViewById(R.id.lisrestaurant2);

        Bundle intent = getIntent().getExtras();
        id = intent.getString("_id");

        ResApi api= new ResApi(this);
        //api.loadRes();
        api.loadRes(id);
        /*
        for (int i = 0; i < 100; i++) {
            //datos.add("item" + i);

            EsRestaurante item= new EsRestaurante();
            item.setNombre("nombre"+ i);
            item.setTelefono("telefono" + i);
            item.setCalle("calle"+ i);
            item.setImagen("No IMAGE");
            datos.add(item);
        }
        ResAdapter1 adapter=new ResAdapter1(datos,this.getBaseContext());
       // ArrayAdapter<String> adapter =new ArrayAdapter(this.getApplicationContext(),android.R.layout.simple_list_item_1,datos);
        listares.setAdapter(adapter);*/

        //ocultar navegacion kato
        getSupportActionBar().hide();
    }

    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {
        for (int i = 0; i< data.length() ; i++) {
            //datos.add("item" + i);

            EsRestaurante item= new EsRestaurante();
            try {
                if(data.getJSONObject(i).has("name")){
                    item.setNombre(data.getJSONObject(i).getString("name"));
                }else{
                    item.setNombre("");
                }
                if(data.getJSONObject(i).has("phone")){
                    item.setTelefono(data.getJSONObject(i).getString("phone"));
                }else{
                    item.setTelefono("");
                }
                if(data.getJSONObject(i).has("street")){
                    item.setCalle(data.getJSONObject(i).getString("street"));
                }else{
                    item.setCalle("");
                }
                if(data.getJSONObject(i).has("picture")){
                    item.setImagen(data.getJSONObject(i).getString("picture"));
                }else{
                    item.setImagen("");
                }

                item.setId(data.getJSONObject(i).getString("_id"));




                datos.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        ResAdapter1 adapter=new ResAdapter1(datos,Ver_Restaurante1.this);
        // ArrayAdapter<String> adapter =new ArrayAdapter(this.getApplicationContext(),android.R.layout.simple_list_item_1,datos);
        listares.setAdapter(adapter);

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }



/*
    private void loadComponents() {

        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://192.168.100.180:8000/api/1.0/restaurante",  new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONArray data = response.getJSONArray("result");
                    for (int i =0 ; i < data.length(); i++) {
                        Restaurants res =new Restaurants();
                        JSONObject object = data.getJSONObject(i);
                        res.setNombre(object.getString("nombre"));
                        res.setTelefono(object.getInt("telefono"));
                        res.setCalle(object.getString("calle"));
                        //menus.setFoto(object.getString("foto"));
                        restaurants.add(res);
                    }
                    ResAdapter adapter =  new ResAdapter(Ver_Restaurante1.this,restaurants);
                    listares.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });

    }*/
}