package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katerin.androidproyectov6.utils.Data;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class EditarRestaurant extends AppCompatActivity implements OnMapReadyCallback{

    private MapView map;
    private GoogleMap mMap;
    private Geocoder geocoder;
    private LatLng mainposition;
    private TextView street;
    private Button next;

    TextView nombre1,telefono1,calle1;
    String nombreres,telefonores,calleres,id;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_restaurant);

        nombre1 = findViewById(R.id.namerestorantED);
        telefono1 = findViewById(R.id.phonerestorantED);
        calle1 = findViewById(R.id.streetrestorantED);
        informacion();
        guardar = findViewById(R.id.guardarestorantED);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sedData();
            }
        });



        map = findViewById(R.id.MapView2);

        map.onCreate(savedInstanceState);
        map.onResume();
        MapsInitializer.initialize(this);
        map.getMapAsync((OnMapReadyCallback) this);
        geocoder = new Geocoder(getBaseContext(), Locale.getDefault());


//ocultar navegacion kato
        getSupportActionBar().hide();
    }

    public void informacion() {
        Bundle intent = getIntent().getExtras();

        nombreres = intent.getString("nombre");
        telefonores = intent.getString("telefono");
        calleres = intent.getString("calle");
        id = intent.getString("id");

        nombre1.setText(nombreres);
        telefono1.setText(telefonores);
        calle1.setText(calleres);
    }
    public void sedData() {
        TextView  nombre4 = findViewById(R.id.namerestorantED);
        TextView telefono4 = findViewById(R.id.phonerestorantED);
        TextView calle4 = findViewById(R.id.streetrestorantED);

        AsyncHttpClient client = new AsyncHttpClient();
        //client.addHeader("authorization", Data.TOKEN);


        RequestParams params = new RequestParams();

        params.put("name", nombre4.getText().toString());
        params.put("phone", telefono4.getText().toString());
        params.put("street", calle4.getText().toString());
       // Toast.makeText(getApplicationContext(),Data.REGISTER_RESTORANT+"/"+Data.ID_RESTORANT,Toast.LENGTH_LONG).show();
        client.patch(Data.REGISTER_RESTORANT+"?id="+id ,params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");

                try {
                    String res=response.getString("msn");
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                  /*  Intent  pruebaED=new Intent(EditarRestaurant.this,Admi1.class);
                    startActivity(pruebaED);*/
                } catch (JSONException e) {

                    e.printStackTrace();
                }



            }


        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //-19.5783329,-65.7563853
        LatLng potosi = new LatLng(-19.5783329, -65.7563853);
        mainposition = potosi;
        mMap.addMarker(new MarkerOptions().position(potosi).title("Lugar").zIndex(17).draggable(true));
        mMap.setMinZoomPreference(16);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(potosi));

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                mainposition = marker.getPosition();
                String street_string = getStreet(marker.getPosition().latitude, marker.getPosition().longitude);
                street.setText(street_string);
            }
        });
    }
    public String getStreet (Double lat, Double lon) {
        List<Address> address;
        String result = "";
        try {
            address = geocoder.getFromLocation(lat, lon, 1);
            result += address.get(0).getThoroughfare();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}