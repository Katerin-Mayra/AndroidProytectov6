package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Editar_Admin1 extends AppCompatActivity {
    Button guardar1;
String _id;
    ImageButton atrasEAD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__admin1);

        atrasEAD = findViewById(R.id.atrasEAD);
        atrasEAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Editar_Admin1.this,Admi1.class));

            }
        });

        final TextView nombre=(TextView) findViewById(R.id.nombre4);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        final TextView ci=(TextView) findViewById(R.id.ci4);
        ci.setText( getIntent().getExtras().getString("ci"));
        final TextView telefono=(TextView) findViewById(R.id.phone4);
        telefono.setText( getIntent().getExtras().getString("telefono"));
        final TextView email=(TextView) findViewById(R.id.correo4);
        email.setText( getIntent().getExtras().getString("email"));
        final TextView tipo=(TextView) findViewById(R.id.tipo);

        /*
        final TextView _id=(TextView) findViewById(R.id.idclient2);
        _id.setText( getIntent().getExtras().getString("_id"));*/

        guardar1 = findViewById(R.id.guardar1);
        guardar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sedData();

            }
        });
    }

    public void sedData() {
        TextView  nombre4 = findViewById(R.id.nombre4);
        TextView ci4 = findViewById(R.id.ci4);
        TextView telefono4 = findViewById(R.id.phone4);
        TextView email4 = findViewById(R.id.correo4);
       // TextView _id = findViewById(R.id.idclient2);
        Bundle intent = getIntent().getExtras();
        _id = intent.getString("_id");

        AsyncHttpClient client = new AsyncHttpClient();
        //client.addHeader("authorization", Data.TOKEN);


        RequestParams params = new RequestParams();

        params.put("nombre", nombre4.getText().toString());
        params.put("ci", ci4.getText().toString());
        params.put("telefono", telefono4.getText().toString());
        params.put("email", email4.getText().toString());

        //Toast.makeText(getApplicationContext(),Data.REGISTER_CLIENTE+"/"+Data.ID_User,Toast.LENGTH_LONG).show();
        client.patch(Data.REGISTER_CLIENTE+"?id="+_id, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");

                try {
                    String res=response.getString("msn");
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                    Intent  pruebaED=new Intent(Editar_Admin1.this,login.class);
                    startActivity(pruebaED);
                } catch (JSONException e) {

                    e.printStackTrace();
                }



              /*
                AlertDialog alertDialog = new AlertDialog.Builder(Editar_Admin1.this).create();
                try {
                    int resp = response.getInt("resp");

                    if (resp == 200) {
                        String msn = response.getString("msn");
                        JSONObject json = response.getJSONObject("dato");
                        final String nombre4_resp = json.getString("nombre");
                        final String ci4_resp = json.getString("ci");
                        final String telefono4_resp = json.getString("telefono");
                        final String email4_resp = json.getString("email");

                        alertDialog.setTitle("Mensaje");
                        alertDialog.setMessage(msn);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Editar_Admin1.this, Admi1.class);
                                intent.putExtra("nombre", nombre4_resp);
                                intent.putExtra("ci", ci4_resp);
                                intent.putExtra("telefono", telefono4_resp);
                                intent.putExtra("email", email4_resp);
                                intent.putExtra("tipo",Data.Tipo);


                                startActivity(intent);

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
                }*/

            }


        });
    }


}