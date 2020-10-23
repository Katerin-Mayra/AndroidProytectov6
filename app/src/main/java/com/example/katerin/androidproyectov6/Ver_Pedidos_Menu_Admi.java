package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.katerin.androidproyectov6.adapter5PA.EsPedidoA;
import com.example.katerin.androidproyectov6.adapter5PA.PedAdapterA;
import com.example.katerin.androidproyectov6.adapterVPedMead.EsPedidoMenAd;
import com.example.katerin.androidproyectov6.adapterVPedMead.PedMeAdapterA;
import com.example.katerin.androidproyectov6.apiResfull.PedApi;
import com.example.katerin.androidproyectov6.apiResfull.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Ver_Pedidos_Menu_Admi extends AppCompatActivity implements onLoadData {
    ListView listares;
    ImageButton atrasVPC;
    TextView _id;
    ArrayList<EsPedidoMenAd> datos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__pedidos__menu__admi);

        atrasVPC = findViewById(R.id.atrasVPC);
        atrasVPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ver_Pedidos_Menu_Admi.this,Cliente1.class));

            }
        });
        _id=(TextView) findViewById(R.id.idPediMe);
        _id.setText( getIntent().getExtras().getString("id"));

        listares = findViewById(R.id.lisrestaurantAdmiP);
        PedApi api= new PedApi(this);
        api.loadPed();
    }

    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {

        for (int i = 0; i< data.length() ; i++) {
            //datos.add("item" + i);

            EsPedidoMenAd item= new EsPedidoMenAd();
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
                if(data.getJSONObject(i).has("menus")){
                    item.setMenus(data.getJSONObject(i).getString("menus"));
                }else{
                    item.setMenus("");
                }

                item.setId(data.getJSONObject(i).getString("_id"));




                datos.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        PedMeAdapterA adapter=new PedMeAdapterA(datos,Ver_Pedidos_Menu_Admi.this);
        // ArrayAdapter<String> adapter =new ArrayAdapter(this.getApplicationContext(),android.R.layout.simple_list_item_1,datos);
        listares.setAdapter(adapter);

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}