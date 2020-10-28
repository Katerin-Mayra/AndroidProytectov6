package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.katerin.androidproyectov6.adapter1.EsRestaurante;
import com.example.katerin.androidproyectov6.adapter1.ResAdapter1;
import com.example.katerin.androidproyectov6.adapterCliente.EsVRestaurante;
import com.example.katerin.androidproyectov6.adapterCliente.VResAdapter1;
import com.example.katerin.androidproyectov6.apiResfull.ResApi;
import com.example.katerin.androidproyectov6.apiResfull.VResApi;
import com.example.katerin.androidproyectov6.apiResfull.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Ver_Restaurante_Cliente extends AppCompatActivity implements onLoadData {

    ListView listares;
    ImageButton atrasVR;
    String id;
    TextView tipo,_idc;
    ArrayList<EsVRestaurante> datos=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__restaurante__cliente);

        _idc=(TextView) findViewById(R.id.idCliVeRestMP);
        _idc.setText( getIntent().getExtras().getString("_id"));

        listares = findViewById(R.id.lisrestaurantcliente2);
        VResApi api= new VResApi(this);
        api.loadRes();

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

            EsVRestaurante item= new EsVRestaurante();
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
                if(data.getJSONObject(i).has("property")){
                    item.setPropietario(data.getJSONObject(i).getString("property"));
                }else{
                    item.setImagen("");
                }





                item.setId(data.getJSONObject(i).getString("_id"));




                datos.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        VResAdapter1 adapter=new VResAdapter1(datos,Ver_Restaurante_Cliente.this);
        // ArrayAdapter<String> adapter =new ArrayAdapter(this.getApplicationContext(),android.R.layout.simple_list_item_1,datos);
        listares.setAdapter(adapter);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}