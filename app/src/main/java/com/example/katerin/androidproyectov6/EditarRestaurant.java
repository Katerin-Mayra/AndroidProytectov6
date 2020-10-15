package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class EditarRestaurant extends AppCompatActivity {
    TextView nombre1,telefono1,calle1;
    String nombreres,telefonores,calleres;
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



    }

    public void informacion() {
        Intent intent = getIntent();

        nombreres = intent.getStringExtra("nombre");
        telefonores = intent.getStringExtra("telefono");
        calleres = intent.getStringExtra("calle");

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

        params.put("nombre", nombre4.getText().toString());
        params.put("telefono", telefono4.getText().toString());
        params.put("calle", calle4.getText().toString());
        Toast.makeText(getApplicationContext(),Data.REGISTER_RESTORANT+"/"+Data.ID_RESTORANT,Toast.LENGTH_LONG).show();
        client.put(Data.REGISTER_RESTORANT+"/"+Data.ID_RESTORANT, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");


                AlertDialog alertDialog = new AlertDialog.Builder(EditarRestaurant.this).create();
                try {
                    int resp = response.getInt("resp");

                    if (resp == 200) {
                        String msn = response.getString("msn");
                        JSONObject json = response.getJSONObject("dato");
                        final String nombre4_resp = json.getString("nombre");
                        final String telefono4_resp = json.getString("telefono");
                        final String calle4_resp = json.getString("calle");


                        alertDialog.setTitle("Mensaje");
                        alertDialog.setMessage(msn);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(EditarRestaurant.this, Ver_Restaurante1.class);
                                intent.putExtra("nombre", nombre4_resp);
                                intent.putExtra("telefono", telefono4_resp);
                                intent.putExtra("email", calle4_resp);

                                startActivity(intent);

                            }
                        });
                        alertDialog.show();
                    } else {
                        alertDialog.setTitle("Mensaje");
                        alertDialog.setMessage("Error al editar los datos");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alertDialog.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }


        });
    }



}