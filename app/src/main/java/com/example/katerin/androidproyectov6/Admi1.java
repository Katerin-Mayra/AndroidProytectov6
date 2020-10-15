package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katerin.androidproyectov6.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Admi1 extends AppCompatActivity {
    Button crearres,veres;
    Button edicuenta1;
    Button elicuenta1,controlar;
    TextView nombre;
    TextView ci;
    TextView telefono;
    TextView email;
    TextView tipo;
    static String em="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admi1);
        elicuenta1 = findViewById(R.id.elicuenta1);
        crearres =  findViewById(R.id.crestaurant);
        veres =  findViewById(R.id.verestaurant);
        edicuenta1 = findViewById(R.id.edicuenta1);
        controlar = findViewById(R.id.irpedidos);
        nombre=(TextView) findViewById(R.id.nombre2);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        ci=(TextView) findViewById(R.id.ci2);
        ci.setText( getIntent().getExtras().getString("ci"));
        telefono=(TextView) findViewById(R.id.telefono2);
        telefono.setText( getIntent().getExtras().getString("telefono"));
        email=(TextView) findViewById(R.id.correo2);
        email.setText( getIntent().getExtras().getString("email"));
        tipo=(TextView) findViewById(R.id.tipo);
        tipo.setText( getIntent().getExtras().getString("tipo"));


/*
        controlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admi.this,InfoPedidos.class));
            }
        });*/

        crearres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admi1.this,Registrar_Restaurant.class));

            }
        });
        veres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admi1.this,Ver_Restaurante1.class));

            }
        });


        edicuenta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(Admi1.this,Editar_Admin1.class);
                t.putExtra("email",email.getText());
                t.putExtra("nombre",nombre.getText());
                t.putExtra("ci", ci.getText());
                t.putExtra("telefono",telefono.getText());
                t.putExtra("tipo",tipo.getText());
                startActivity(t);

                /*Bundle b=getIntent().getExtras();
                Toast.makeText(getApplicationContext(),b.getString("nombreMod")+"",Toast.LENGTH_LONG).show();*/

            }
        });


        elicuenta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sedData();
                startActivity(new Intent(Admi1.this,MainActivity.class));

            }
        });



    }

    private void sedData() {
        final AsyncHttpClient client = new AsyncHttpClient();
        client.delete(Data.REGISTER_delete+"/"+Data.ID_User, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String message = response.getString("message");
                    if (message != null){
                        Toast.makeText(Admi1.this,message, Toast.LENGTH_LONG).show();


                    }else   {
                        Toast.makeText(Admi1.this,"Error al borrar", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}