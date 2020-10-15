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

import collects1.ResAdapter;
import collects1.Restaurants;
import cz.msebera.android.httpclient.Header;

public class Lugar extends AppCompatActivity {
    ListView list1;
    ImageButton atras3;

    ArrayList<Restaurants> list_data1 = new ArrayList<Restaurants> ();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar);
        atras3 = findViewById(R.id.atrs2);
        list1= this.findViewById (R.id.restaurants);
        atras3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lugar.this,Cliente1.class));
                finish();
            }
        });
        loadComponents();
        list1.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Restaurants item = list_data1.get (position);


                Intent i = new Intent(Lugar.this, Menu.class);


                startActivity(i);


            }
        });
    }

    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://192.168.1.108:7777/api/v1.0/restaurant",  new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {

                try {

                    for (int i =0 ; i < jsonArray.length(); i++) {
                        Restaurants restaurants = new Restaurants();
                        JSONObject object = jsonArray.getJSONObject(i);
                        restaurants.setId(object.getString("id"));
                        restaurants.setNombre(object.getString("nombre"));
                        list_data1.add(restaurants);
                    }
                    ResAdapter adapter =  new ResAdapter(Lugar.this,list_data1);
                    list1.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });




    }

}