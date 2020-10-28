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
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Editar_Menu1 extends AppCompatActivity {
    TextView nombre1,precio1,detalle1;
    String nombreme,preciome,detalleme,id;
    Button guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__menu1);

        nombre1 = findViewById(R.id.namemenuED);
        precio1 = findViewById(R.id.preciomenuED);
        detalle1 = findViewById(R.id.detallemenuED);
        informacion();
        guardar = findViewById(R.id.guardarmenuED);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sedData();
            }
        });

        //ocultar navegacion kato
        getSupportActionBar().hide();
    }
    public void informacion() {
        Bundle intent = getIntent().getExtras();

        nombreme = intent.getString("nombre");
        preciome = intent.getString("precio");
        detalleme = intent.getString("descripcion");
        id = intent.getString("id");

        nombre1.setText(nombreme);
        precio1.setText(preciome);
        detalle1.setText(detalleme);
    }
    public void sedData() {
        TextView nombre4 = findViewById(R.id.namemenuED);
        TextView precio4 = findViewById(R.id.preciomenuED);
        TextView detalle4 = findViewById(R.id.detallemenuED);

        AsyncHttpClient client = new AsyncHttpClient();
        //client.addHeader("authorization", Data.TOKEN);


        RequestParams params = new RequestParams();

        params.put("nombre", nombre4.getText().toString());
        params.put("precio", precio4.getText().toString());
        params.put("descripcion", detalle4.getText().toString());
        // Toast.makeText(getApplicationContext(),Data.REGISTER_RESTORANT+"/"+Data.ID_RESTORANT,Toast.LENGTH_LONG).show();
        client.patch(Data.REGISTER_MENUS+"?id="+id ,params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");

                try {
                    String res=response.getString("msn");
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                    Intent pruebaED=new Intent(Editar_Menu1.this,Ver_menus.class);
                    startActivity(pruebaED);
                } catch (JSONException e) {

                    e.printStackTrace();
                }



            }


        });
    }
}