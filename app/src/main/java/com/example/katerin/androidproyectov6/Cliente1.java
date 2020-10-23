package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katerin.androidproyectov6.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Cliente1 extends AppCompatActivity {
    Button pedido,carrito,eliminar,editar;
    TextView nombre;
    TextView ci;
    TextView telefono;
    TextView email;
    TextView _id;
    TextView tipo;
    ImageButton atrasVC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente1);
        pedido =  findViewById(R.id.reapedido);
        carrito = findViewById(R.id.vercarrito);
        eliminar = findViewById(R.id.elicuenta);
        editar = findViewById(R.id.edicuenta);
        _id = findViewById(R.id.idC);


        atrasVC = findViewById(R.id.atrasVC);
        atrasVC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cliente1.this,MainActivity.class));

            }
        });

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

        _id=(TextView) findViewById(R.id.idC);
        _id.setText( getIntent().getExtras().getString("_id"));



        pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(Cliente1.this,Ver_Restaurante_Cliente.class);
                t.putExtra("_id",_id.getText());
                startActivity(t);
            }
        });

        carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent t=new Intent(Cliente1.this,Ver_Pedido.class);
                t.putExtra("_id",_id.getText());
                startActivity(t);
            }
        });


        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(Cliente1.this,Editar_Cliente.class);
                t.putExtra("email",email.getText());
                t.putExtra("nombre",nombre.getText());
                t.putExtra("ci", ci.getText());
                t.putExtra("telefono",telefono.getText());
                t.putExtra("tipo",tipo.getText());
                t.putExtra("_id",_id.getText());
                startActivity(t);
                /*Bundle b=getIntent().getExtras();
                Toast.makeText(getApplicationContext(),b.getString("nombreMod")+"",Toast.LENGTH_LONG).show();*/

            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sedData();
                startActivity(new Intent(Cliente1.this,login.class));


            }
        });

    }

    private void sedData() {
        final AsyncHttpClient client = new AsyncHttpClient();
        client.delete(Data.REGISTER_CLIENTE+"/"+Data.ID_User, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String message = response.getString("message");
                    if (message != null){
                        Toast.makeText(Cliente1.this,message,Toast.LENGTH_LONG).show();


                    }else   {
                        Toast.makeText(Cliente1.this,"Error al borrar",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }




}