package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.katerin.androidproyectov6.adapter1.EsRestaurante;
import com.example.katerin.androidproyectov6.adapter1.ResAdapter1;
import com.example.katerin.androidproyectov6.adapterVRPAd.EsPADRestaurante;
import com.example.katerin.androidproyectov6.adapterVRPAd.ResPeAdAdapter;
import com.example.katerin.androidproyectov6.apiResfull.ResApi;
import com.example.katerin.androidproyectov6.apiResfull.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Ver_Restaurante_Pedidos_Admi extends AppCompatActivity implements onLoadData {
    ListView listares;
    String id;
    ImageButton atrasVR;
    TextView _idC;
    ArrayList<EsPADRestaurante> datos=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__restaurante__pedidos__admi);

        listares = findViewById(R.id.lisrestaurantPAD2);

        _idC=(TextView) findViewById(R.id.idADresP);
        _idC.setText( getIntent().getExtras().getString("_id"));

        Bundle intent = getIntent().getExtras();
        id = intent.getString("_id");

        ResApi api= new ResApi(this);
        //api.loadRes();
        api.loadRes(id);
    }

    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {

        for (int i = 0; i< data.length() ; i++) {
            //datos.add("item" + i);

            EsPADRestaurante item= new EsPADRestaurante();
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
        ResPeAdAdapter adapter=new ResPeAdAdapter(datos,Ver_Restaurante_Pedidos_Admi.this);
        // ArrayAdapter<String> adapter =new ArrayAdapter(this.getApplicationContext(),android.R.layout.simple_list_item_1,datos);
        listares.setAdapter(adapter);

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}