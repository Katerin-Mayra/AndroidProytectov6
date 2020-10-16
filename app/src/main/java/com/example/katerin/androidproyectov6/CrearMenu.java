package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.katerin.androidproyectov6.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import collects1.BorrarMenuAdapter;
import collects1.MenuAdapter;
import collects1.Menus;
import collects1.VerMenuAdapter;
import cz.msebera.android.httpclient.Header;

public class CrearMenu extends AppCompatActivity {
    Button aceptar,foto;

    EditText producto,precio,descripcion;
    ImageView imagen;

    ListView listcrear;
    ArrayList<Menus> list_data = new ArrayList<Menus> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_menu);
        //istcrear=findViewById(R.id.);
        foto = findViewById (R.id.tomarfoto);
        producto = findViewById(R.id.producto);
        precio = findViewById(R.id.precioproducto);
        descripcion = findViewById(R.id.descripcion);
        aceptar = findViewById (R.id.aceptar);
        imagen = findViewById (R.id.fotomenu);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sedData();
            }
        });


        loadComponents();

    }



    public void sedData(){
        final EditText nombre  = findViewById(R.id.producto);
        final EditText precio  = findViewById(R.id.precioproducto);
        final EditText descripcion = findViewById(R.id.descripcion);
        final ImageView image = findViewById (R.id.fotomenu);

        if (nombre.getText().toString().equals("") || precio.getText().toString().equals("") || descripcion.getText().toString().equals("")){
            Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
            return;
        }



        AsyncHttpClient client = new AsyncHttpClient();
        //client.addHeader("authorization", Data.TOKEN);


        RequestParams params = new RequestParams();


        params.put("nombre", nombre.getText().toString());
        params.put("precio", precio.getText().toString());
        params.put("descripcion",descripcion.getText().toString());

        client .post(Data.REGISTER_MENUS, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");



                AlertDialog alertDialog = new AlertDialog.Builder(CrearMenu.this).create();
                try {
                    String id = response.getString("id");
                    int resp = response.getInt("resp");

                    if(resp==200){
                        String msn = response.getString("msn");

                        nombre.getText().clear();
                        precio.getText().clear();
                        descripcion.getText().clear();
                        loadComponents();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });
    }
    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://192.168.100.180:8000/api/1.0/menus",  new JsonHttpResponseHandler(){
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
                        // menus.setFoto(object.getString("foto"));


                        list_data.add(menus);
                    }
                    MenuAdapter adapter =  new MenuAdapter(CrearMenu.this,list_data);
                    listcrear.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });

    }
}