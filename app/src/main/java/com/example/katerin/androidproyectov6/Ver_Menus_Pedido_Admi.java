package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.katerin.androidproyectov6.adapterAdmiMenu.AMenuAdapter;
import com.example.katerin.androidproyectov6.adapterAdmiMenu.EsMenuA;
import com.example.katerin.androidproyectov6.adapterClienteMenu.CMenuAdapter;
import com.example.katerin.androidproyectov6.adapterClienteMenu.EsMenuC;
import com.example.katerin.androidproyectov6.adapterVMPAd.EsPADMenus;
import com.example.katerin.androidproyectov6.adapterVMPAd.MenPeAdAdapter;
import com.example.katerin.androidproyectov6.apiResfull.VMenusAdmi;
import com.example.katerin.androidproyectov6.apiResfull.VMenusCliente;
import com.example.katerin.androidproyectov6.apiResfull.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Ver_Menus_Pedido_Admi extends AppCompatActivity implements onLoadData {
    ListView listares;
    ImageButton atrasVMC1;
    TextView _id;
    String id;

    ArrayList<EsPADMenus> datos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__menus__pedido__admi);

        atrasVMC1 = findViewById(R.id.atrasVMC1);
        atrasVMC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ver_Menus_Pedido_Admi.this,Ver_Restaurante_Cliente.class));

            }
        });

        listares = findViewById(R.id.listamenuADPVMP);

        _id=(TextView) findViewById(R.id.idrestauVMCP);
        _id.setText( getIntent().getExtras().getString("id"));

        Bundle intent = getIntent().getExtras();
        id = intent.getString("id");

        VMenusCliente api= new VMenusCliente(this);
        api.loadMenu(id);


    }

    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {
        for (int i = 0; i< data.length() ; i++) {
            //datos.add("item" + i);

            EsPADMenus item= new EsPADMenus();
            try {
                if(data.getJSONObject(i).has("nombre")){
                    item.setNombre(data.getJSONObject(i).getString("nombre"));
                }else{
                    item.setNombre("");
                }
                if(data.getJSONObject(i).has("precio")){
                    item.setPrecio(data.getJSONObject(i).getString("precio"));
                }else{
                    item.setPrecio("");
                }
                if(data.getJSONObject(i).has("descripcion")){
                    item.setDescripcion(data.getJSONObject(i).getString("descripcion"));
                }else{
                    item.setDescripcion("");
                }
                if(data.getJSONObject(i).has("foto")){
                    item.setFoto(data.getJSONObject(i).getString("foto"));
                }else{
                    item.setFoto("");
                }

                item.setId(data.getJSONObject(i).getString("_id"));




                datos.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        MenPeAdAdapter adapter=new MenPeAdAdapter(datos,Ver_Menus_Pedido_Admi.this);
        // ArrayAdapter<String> adapter =new ArrayAdapter(this.getApplicationContext(),android.R.layout.simple_list_item_1,datos);
        listares.setAdapter(adapter);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}