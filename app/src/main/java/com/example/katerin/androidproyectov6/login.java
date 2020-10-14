package com.example.katerin.androidproyectov6;

import android.content.Intent;
import android.os.Bundle;

import com.example.katerin.androidproyectov6.utils.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class login extends AppCompatActivity {
    Button btn_login;
    Button registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        btn_login = findViewById (R.id.login);
        /*
        registrarse = findViewById(R.id.registrarse);
        registrarse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,RegistrarUsuario.class));
                finish();
            }

        });
*/
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendData();

            }

        });
    }

    private void sendData() {
        TextView correo  = findViewById(R.id.correoL);
        TextView password  = findViewById(R.id.passwordL);
        if (correo.getText().toString().equals(" ")  || password.getText().toString().equals("")){
            Toast.makeText(this,"Es necesario llenar los dos campos",Toast.LENGTH_SHORT).show();
            return;

        }
        AsyncHttpClient login = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.add("email", correo.getText().toString());
        params.add("password", password.getText().toString());
        login.post(Data.REGISTER_LOGIN, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");
                AlertDialog alertDialog = new AlertDialog.Builder(login.this).create();
                try {

                    int resp = response.getInt("resp");
                    Toast.makeText(login.this, "respuesta:"+resp, Toast.LENGTH_SHORT).show();
                    if(resp==200){
                        JSONObject json=response.getJSONObject("dato");

                        //String token = response.getString("token");
                        String tipo= json.getString("tipo");
                        String nombre=json.getString("nombre");
                        String ci=json.getString("ci");
                        String telefono=json.getString("telefono");
                        String correo=json.getString("email");
                        String id=json.getString("_id");

                        //Data.TOKEN="Data "+token;
                        Data.ID_User=id;
                        Data.Tipo=tipo;
                        if(tipo.compareTo("Administrador")==0) {
                            Intent intent =new Intent(login.this, Admi1.class);
                            intent.putExtra("email",correo);
                            intent.putExtra("nombre",nombre);
                            intent.putExtra("ci",ci);
                            intent.putExtra("telefono",telefono);
                            intent.putExtra("tipo",tipo);
                            startActivity(intent);
                            finish();
                        }/*else{
                            Intent intent =new Intent(login.this, Cliente.class);
                            intent.putExtra("email",correo);
                            intent.putExtra("nombre",nombre);
                            intent.putExtra("ci",ci);
                            intent.putExtra("telefono",telefono);
                            intent.putExtra("tipo",tipo);
                            startActivity(intent);
                            finish();
                        }*/



                        //Toast.makeText(login.this, "Login correctamente: "+ token, Toast.LENGTH_SHORT).show();
                        Toast.makeText(login.this, "Login correctamente: ", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(login.this, "error de logueo", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(login.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }


        });
    }

}
