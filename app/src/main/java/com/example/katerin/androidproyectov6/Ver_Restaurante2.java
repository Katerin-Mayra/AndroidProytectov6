package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import collects1.ResAdapter;
import collects1.Restaurants;
import cz.msebera.android.httpclient.Header;

public class Ver_Restaurante2 extends AppCompatActivity {
    ListView listares;
    ArrayList<Restaurants> restaurants=new ArrayList<Restaurants>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__restaurante2);
        listares = findViewById(R.id.lisrestaurant);
        loadComponents();
    }

    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://192.168.100.180:8000/api/1.0/restaurante",  new JsonHttpResponseHandler(){
/*
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    JSONArray data = response.getJSONArray("")
                    for (int i =0 ; i < data.length(); i++) {
                        Restaurants res =new Restaurants();
                        JSONObject object = data.getJSONObject(i);
                        res.setNombre(object.getString("nombre"));
                        res.setTelefono(object.getInt("telefono"));
                        res.setCalle(object.getString("calle"));
                        //menus.setFoto(object.getString("foto"));
                        restaurants.add(res);
                    }
                    ResAdapter adapter =  new ResAdapter(Ver_Restaurante2.this,restaurants);
                    listares.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
*/
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONArray data = response.getJSONArray("result");
                    for (int i =0 ; i < data.length(); i++) {
                        Restaurants res =new Restaurants();
                        JSONObject object = data.getJSONObject(i);
                        res.setNombre(object.getString("name"));
                        res.setTelefono(object.getInt("phone"));
                        res.setCalle(object.getString("street"));
                        res.setImagen(object.getString("picture"));
                        //menus.setFoto(object.getString("foto"));
                        restaurants.add(res);
                    }
                    ResAdapter adapter =  new ResAdapter(Ver_Restaurante2.this,restaurants);
                    listares.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });

    }
}