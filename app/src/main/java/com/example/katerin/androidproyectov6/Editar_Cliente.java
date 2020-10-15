package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

public class Editar_Cliente extends AppCompatActivity {
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__cliente);
        guardar = findViewById(R.id.guardar);
        final TextView nombre=(TextView) findViewById(R.id.nombre3);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        final TextView ci=(TextView) findViewById(R.id.ci3);
        ci.setText( getIntent().getExtras().getString("ci"));
        final TextView telefono=(TextView) findViewById(R.id.phone3);
        telefono.setText( getIntent().getExtras().getString("telefono"));
        final TextView email=(TextView) findViewById(R.id.correo3);
        email.setText( getIntent().getExtras().getString("email"));
        final TextView tipo=(TextView) findViewById(R.id.tipo);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sedData();



            }
        });
    }
    public void sedData() {
        TextView nombre3 = findViewById(R.id.nombre3);
        TextView ci3 = findViewById(R.id.ci3);
        TextView telefono3 = findViewById(R.id.phone3);
        TextView email3 = findViewById(R.id.correo3);


        AsyncHttpClient client = new AsyncHttpClient();
        //client.addHeader("authorization", Data.TOKEN);


        RequestParams params = new RequestParams();

        params.put("nombre", nombre3.getText().toString());
        params.put("ci", ci3.getText().toString());
        params.put("telefono", telefono3.getText().toString());
        params.put("email", email3.getText().toString());
        Toast.makeText(getApplicationContext(),Data.REGISTER_CLIENTE+"/"+Data.ID_User,Toast.LENGTH_LONG).show();
        client.put(Data.REGISTER_CLIENTE+"/"+Data.ID_User, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");


                AlertDialog alertDialog = new AlertDialog.Builder(Editar_Cliente.this).create();
                try {
                    int resp = response.getInt("resp");

                    if (resp == 200) {
                        String msn = response.getString("msn");
                        JSONObject json = response.getJSONObject("dato");
                        final String nombre3_resp = json.getString("nombre");
                        final String ci3_resp = json.getString("ci");
                        final String telefono3_resp = json.getString("telefono");
                        final String email3_resp = json.getString("email");

                        alertDialog.setTitle("Mensaje");
                        alertDialog.setMessage(msn);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Editar_Cliente.this, Cliente1.class);
                                intent.putExtra("nombre", nombre3_resp);
                                intent.putExtra("ci", ci3_resp);
                                intent.putExtra("telefono", telefono3_resp);
                                intent.putExtra("email", email3_resp);
                                intent.putExtra("tipo",Data.Tipo);


                                startActivity(intent);
                                finish();
                            }
                        });
                        alertDialog.show();
                    } else {
                        alertDialog.setTitle("Mensaje");
                        alertDialog.setMessage("Error al tratar de crear nuevo restaurant");
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