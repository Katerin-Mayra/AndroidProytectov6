package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.katerin.androidproyectov6.adapterCliente.EsVRestaurante;
import com.example.katerin.androidproyectov6.adapterCliente.VResAdapter1;
import com.example.katerin.androidproyectov6.adapterVRMAIN.EsVRestauranteMAIN;
import com.example.katerin.androidproyectov6.adapterVRMAIN.VResAdapterMAIN;
import com.example.katerin.androidproyectov6.apiResfull.VResApi;
import com.example.katerin.androidproyectov6.apiResfull.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Ver_Restaurantes_MAIN extends AppCompatActivity implements onLoadData {
    ListView listares;

    String id;
    TextView tipo,_idc;
    ArrayList<EsVRestauranteMAIN> datos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__restaurantes__m_a_i_n);

        listares = findViewById(R.id.lisrestaurantcliente2A);
        VResApi api= new VResApi(this);

        //ocultar navegacion kato
        getSupportActionBar().hide();
        api.loadRes();
    }


    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {
        for (int i = 0; i< data.length() ; i++) {
            //datos.add("item" + i);

            EsVRestauranteMAIN item= new EsVRestauranteMAIN();
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
        VResAdapterMAIN adapter=new VResAdapterMAIN(datos,Ver_Restaurantes_MAIN.this);
        // ArrayAdapter<String> adapter =new ArrayAdapter(this.getApplicationContext(),android.R.layout.simple_list_item_1,datos);
        listares.setAdapter(adapter);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}