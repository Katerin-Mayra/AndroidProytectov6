package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katerin.androidproyectov6.utils.Data;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class Registrar_Restaurant extends AppCompatActivity {
    private MapView map;
    private GoogleMap mMap;
    private Geocoder geocoder;
    private TextView street;
    private Button next;
    private Button imgbutton;
    private LatLng mainposition;
    TextView _id;
    TextView _idC;


    TextView _idI;
    ImageButton atrasRRA;
    //TextView _idCdos;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__restaurant);

        atrasRRA = findViewById(R.id.atrasRRA);
        atrasRRA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registrar_Restaurant.this,Admi1.class));

            }
        });


        final TextView _idCdos=(TextView) findViewById(R.id.idclientedos);
        _idCdos.setText( getIntent().getExtras().getString("_id"));
/*
        TextView _id=(TextView) findViewById(R.id.idcliente);
        _id.setText( getIntent().getExtras().getString("_id"));
*/


/*
        map = findViewById(R.id.MapView1);

        map.onCreate(savedInstanceState);
        map.onResume();
        MapsInitializer.initialize(this);
        map.getMapAsync((OnMapReadyCallback) this);
        geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
*/

        imgbutton = findViewById(R.id.imgbutton);
        imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                final TextView _idCdos=(TextView) findViewById(R.id.idclientedos);
                _idCdos.setText( getIntent().getExtras().getString("_id"));
*/
               // startActivity(new Intent(Registrar_Restaurant.this,FotoRestaurante.class));
                Intent t=new Intent(Registrar_Restaurant.this,FotoRestaurante.class);
               t.putExtra("_id",_idCdos.getText());
                // t.putExtra("_id",_id);
                startActivity(t);

            }
        });

        _idI=(TextView) findViewById(R.id.imgid);
        _idI.setText( getIntent().getExtras().getString("_idI"));
        street = findViewById(R.id.streetrestorant);
        next = findViewById(R.id.crear);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registrar();

               sendData();
            }
        });

        //ocultar navegacion kato
        getSupportActionBar().hide();
    }



    private void Registrar() {
        TextView name = findViewById(R.id.namerestorant);
        TextView nit = findViewById(R.id.nit);
        TextView street = findViewById(R.id.streetrestorant);
        TextView property = findViewById(R.id.propietario);
        TextView phone = findViewById(R.id.phonerestorant);
        TextView picture = findViewById(R.id.imgid);

        if (name.length() == 0){
            Toast.makeText(this, "Debes ingresar un nombre", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nit.length() == 0){
            Toast.makeText(this, "Debes ingresar tu CI", Toast.LENGTH_SHORT).show();
            return;
        }

        if (street.length() == 0){
            Toast.makeText(this, "Debes ingresar tu telefono", Toast.LENGTH_SHORT).show();
            return;
        }

        if (property.length() == 0){
            Toast.makeText(this, "Debes ingresar un correo", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone.length() <= 0){
            Toast.makeText(this, "Debes ingresar minimammente 8 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (name.length()!=0 && nit.length()!=0 && street.length()!=0 && phone.length()!=0  && property.length()!=7 ) {
            Toast.makeText(this, "Se Registro Correctamente", Toast.LENGTH_SHORT).show();
            startActivity (new Intent(Registrar_Restaurant.this, MainActivity.class));


        }
    }

    private void sendData() {

        TextView name = findViewById(R.id.namerestorant);
        TextView nit = findViewById(R.id.nit);
        TextView street = findViewById(R.id.streetrestorant);
        TextView property = findViewById(R.id.propietario);
        TextView phone = findViewById(R.id.phonerestorant);
        TextView picture = findViewById(R.id.imgid);

        TextView _id=(TextView) findViewById(R.id.idcliente);
        _id.setText( getIntent().getExtras().getString("_id"));

        AsyncHttpClient client = new AsyncHttpClient();
        //client.addHeader("authorization", Data.TOKEN);

        RequestParams params = new RequestParams();
        params.add("name", name.getText().toString());
        params.add("nit", nit.getText().toString());
        params.add("property", property.getText().toString());
        params.add("street", street.getText().toString());

        params.add("phone", phone.getText().toString());
        params.add("cliente", _id.getText().toString());
        params.add("picture", picture.getText().toString());


         /*   Bundle intent = getIntent().getExtras();
            _id = intent.getString("_id");*/

       // params.add("cliente", _id);
       ///// params.add("lat", String.valueOf(""));
        //params.add("lon", String.valueOf(""));

        client.post(Data.REGISTER_RESTORANT, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
              //  AlertDialog alertDialog = new AlertDialog.Builder(Registrar_Restaurant.this).create();
                try {
                    String res=response.getString("msn");
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                   /* String id = response.getString("id");
                    Data.ID_RESTORANT = id;
                    String msn = response.getString("msn");*/

                /*    alertDialog.setTitle("RESPONSE SERVER");
                    alertDialog.setMessage(msn);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {*/
                                    /*
                                    Intent camera = new Intent(Registrar_Restaurant.this, FotoRestaurant.class);
                                    Registrar_Restaurant.this.startActivity(camera);*/
                }
                                  /*  dialog.dismiss();
                                }
                            });
                    alertDialog.show();*/
                   /* Intent  pruebaED=new Intent(Registrar_Restaurant.this,Admi1.class);
                    startActivity(pruebaED);*/
                 catch (JSONException e) {
                    e.printStackTrace();
                }


                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
            }
        });

    }
}




