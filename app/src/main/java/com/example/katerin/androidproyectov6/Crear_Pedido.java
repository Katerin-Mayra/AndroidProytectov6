package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Crear_Pedido extends AppCompatActivity {
    Button aceptar,foto;
    TextView nombre;
    TextView precio;
    TextView descripcion,idm;
    TextView _id;

    EditText lugar_envio,cantidad;
    ImageView imagen;

    ListView listcrear;
    ArrayList<EsMenu> list_data = new ArrayList<> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__pedido);
       // foto = findViewById (R.id.tomarfotoCM);
        lugar_envio = findViewById(R.id.lugar_envioCP);
        cantidad = findViewById(R.id.cantidadCP);
      //  descripcion = findViewById(R.id.descripcionCM);
        aceptar = findViewById (R.id.aceptarCPC);
        // imagen = findViewById (R.id.fotomenu);


        nombre=(TextView) findViewById(R.id.nombreCPC);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        precio=(TextView) findViewById(R.id.precioCPC);
        precio.setText( getIntent().getExtras().getString("precio"));
        descripcion=(TextView) findViewById(R.id.descripcionCPC);
        descripcion.setText( getIntent().getExtras().getString("descripcion"));
        idm=(TextView) findViewById(R.id.idmenuP);
        idm.setText( getIntent().getExtras().getString("id"));


        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sedData();
            }
        });

        //ocultar navegacion kato
        getSupportActionBar().hide();
    }

    public void sedData(){

        final TextView nombre1  = findViewById(R.id.nombreCPC);
        final TextView precio1  = findViewById(R.id.precioCPC);
        final TextView descripcion1  = findViewById(R.id.descripcionCPC);
        final TextView idm1  = findViewById(R.id.idmenuP);

        final EditText lugar_envio  = findViewById(R.id.lugar_envioCP);
        final EditText cantidad  = findViewById(R.id.cantidadCP);

        //final EditText descripcion = findViewById(R.id.descripcionCM);
        //final ImageView image = findViewById (R.id.fotomenu);

        if (lugar_envio.getText().toString().equals("") || cantidad.getText().toString().equals("") ){
            Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
            return;
        }

        AsyncHttpClient client = new AsyncHttpClient();
        //client.addHeader("authorization", Data.TOKEN);


        RequestParams params = new RequestParams();


        params.put("lugar_envio", lugar_envio.getText().toString());
        params.put("cantidad", cantidad.getText().toString());

        params.put("nombre", nombre1.getText().toString());
        params.put("precios", precio1.getText().toString());
        params.put("descripcion", descripcion1.getText().toString());
        params.put("menus", idm1.getText().toString());
      //  params.put("descripcion",descripcion.getText().toString());

        client .post(Data.REGISTER_ORDEN, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");



                AlertDialog alertDialog = new AlertDialog.Builder(Crear_Pedido.this).create();
                try {

                    String res=response.getString("msn");
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();

                   /* String id = response.getString("id");
                    Data.ID_RESTORANT = id;

                    String msn = response.getString("msn");
                    Toast.makeText(getApplicationContext(),msn,Toast.LENGTH_LONG).show();

                    alertDialog.setTitle("RESPONSE SERVER");
                    alertDialog.setMessage(msn);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {/*
                                    Intent camera = new Intent(Registrar_Restaurant.this, FotoRestaurant.class);
                                    Registrar_Restaurant.this.startActivity(camera);*/
                                   /* dialog.dismiss();

                                }
                            });
                    alertDialog.show();*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });
    }
}