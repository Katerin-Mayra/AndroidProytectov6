package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katerin.androidproyectov6.adapter2.EsMenu;
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
    TextView _id;
    TextView _idM;
    TextView _idMenudimg;
    ListView listcrear;
    ArrayList<EsMenu> list_data = new ArrayList<> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_menu);
        //istcrear=findViewById(R.id.);
        foto = findViewById (R.id.tomarfotoMENU);
        producto = findViewById(R.id.productoCM);
        precio = findViewById(R.id.precioproductoCM);
        descripcion = findViewById(R.id.descripcionCM);
        aceptar = findViewById (R.id.aceptarCM);

        _id=(TextView) findViewById(R.id.idRestauM);
        _id.setText( getIntent().getExtras().getString("_id"));

        _idM=(TextView) findViewById(R.id.idimgdos);
        _idM.setText( getIntent().getExtras().getString("_idM"));

        _idMenudimg=(TextView) findViewById(R.id.idmenudeimg);
        _idMenudimg.setText( getIntent().getExtras().getString("_idC"));

       // imagen = findViewById (R.id.fotomenu);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sedData();
            }
        });


        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(CrearMenu.this,FotoRestauranteMenu.class);
                t.putExtra("_id",_id.getText());
                // t.putExtra("_id",_id);
                startActivity(t);
            }
        });



       // loadComponents();

        //ocultar navegacion kato
        getSupportActionBar().hide();

    }



    public void sedData(){
        final EditText nombre  = findViewById(R.id.productoCM);
        final EditText precio  = findViewById(R.id.precioproductoCM);
        final EditText descripcion = findViewById(R.id.descripcionCM);

        final TextView imagenes = findViewById(R.id.idimgdos);
        _idMenudimg=(TextView) findViewById(R.id.idmenudeimg);
        _idMenudimg.setText( getIntent().getExtras().getString("_idC"));


        _idM=(TextView) findViewById(R.id.idimgdos);
        _idM.setText( getIntent().getExtras().getString("_idM"));

        //final ImageView image = findViewById (R.id.fotomenu);

        if (imagenes.length() <= 0){
            Toast.makeText(this, "Debes ingresar una foto", Toast.LENGTH_SHORT).show();
            return;
        }

        if (nombre.length() <= 0){
            Toast.makeText(this, "Debes ingresar el nombre del producto", Toast.LENGTH_SHORT).show();
            return;
        }

        if (precio.length() <= 0){
            Toast.makeText(this, "Debes ingresar el precio del producto", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nombre.getText().toString().equals("") || precio.getText().toString().equals("") || descripcion.getText().toString().equals("")|| imagenes.getText().toString().equals("")){
            Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
            return;
        }



        AsyncHttpClient client = new AsyncHttpClient();
        //client.addHeader("authorization", Data.TOKEN);


        RequestParams params = new RequestParams();


        params.put("nombre", nombre.getText().toString());
        params.put("precio", precio.getText().toString());
        params.put("descripcion",descripcion.getText().toString());
        params.put("restaurante",_idMenudimg.getText().toString());
        params.put("foto",_idM.getText().toString());

        client .post(Data.REGISTER_MENUS, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");



                AlertDialog alertDialog = new AlertDialog.Builder(CrearMenu.this).create();
                try {





                    String id = response.getString("id");
                    Data.ID_RESTORANT = id;
                    String msn = response.getString("msn");
                    alertDialog.setTitle("RESPONSE SERVER");
                    alertDialog.setMessage(msn);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {/*
                                    Intent camera = new Intent(Registrar_Restaurant.this, FotoRestaurant.class);
                                    Registrar_Restaurant.this.startActivity(camera);*/
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                   /*
                    String id = response.getString("id");
                    int resp = response.getInt("resp");

                    if(resp==200){
                        String msn = response.getString("msn");

                        nombre.getText().clear();
                        precio.getText().clear();
                        descripcion.getText().clear();
                        //loadComponents();

                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });
    }/*
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

    }*/



}