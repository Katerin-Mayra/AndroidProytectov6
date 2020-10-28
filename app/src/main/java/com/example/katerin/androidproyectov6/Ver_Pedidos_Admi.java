package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.katerin.androidproyectov6.adapter4P.EsPedido;
import com.example.katerin.androidproyectov6.adapter4P.PedAdapter;
import com.example.katerin.androidproyectov6.adapter5PA.EsPedidoA;
import com.example.katerin.androidproyectov6.adapter5PA.PedAdapterA;
import com.example.katerin.androidproyectov6.apiResfull.PedApi;
import com.example.katerin.androidproyectov6.apiResfull.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Ver_Pedidos_Admi extends AppCompatActivity implements onLoadData {
    ListView listares;
    ImageButton atrasVPC;
    ArrayList<EsPedidoA> datos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__pedidos__admi);

        atrasVPC = findViewById(R.id.atrasVPC);
        atrasVPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ver_Pedidos_Admi.this,Cliente1.class));

            }
        });

        listares = findViewById(R.id.lisrestaurantAdmi);
        PedApi api= new PedApi(this);
        api.loadPed();

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

            EsPedidoA item= new EsPedidoA();
            try {
                if(data.getJSONObject(i).has("lugar_envio")){
                    item.setLugar_envio(data.getJSONObject(i).getString("lugar_envio"));
                }else{
                    item.setLugar_envio("");
                }
                if(data.getJSONObject(i).has("precios")){
                    item.setPrecios(data.getJSONObject(i).getString("precios"));
                }else{
                    item.setPrecios("");
                }
                if(data.getJSONObject(i).has("cantidad")){
                    item.setCantidad(data.getJSONObject(i).getString("cantidad"));
                }else{
                    item.setCantidad("");
                }
                if(data.getJSONObject(i).has("pago_total")){
                    item.setPago_total(data.getJSONObject(i).getString("pago_total"));
                }else{
                    item.setPago_total("");
                }

                item.setId(data.getJSONObject(i).getString("_id"));




                datos.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        PedAdapterA adapter=new PedAdapterA(datos,Ver_Pedidos_Admi.this);
        // ArrayAdapter<String> adapter =new ArrayAdapter(this.getApplicationContext(),android.R.layout.simple_list_item_1,datos);
        listares.setAdapter(adapter);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}