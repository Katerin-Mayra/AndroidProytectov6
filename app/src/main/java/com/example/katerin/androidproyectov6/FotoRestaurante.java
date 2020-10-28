package com.example.katerin.androidproyectov6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katerin.androidproyectov6.utils.BitmapStruct;
import com.example.katerin.androidproyectov6.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class FotoRestaurante extends AppCompatActivity {
    private  final int CODE = 100;
    private  final int CODE_PERMISSIONS = 101;
    private ImageView IMG;
    private ImageButton btn;
    private Button SEND;
    private BitmapStruct DATAIMAGE;
    String idCC;
    public  TextView _idC;
  //  TextView _idC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_restaurante);

        _idC=(TextView) findViewById(R.id.idclienteCtres);
        _idC.setText( getIntent().getExtras().getString("_id"));

        btn = findViewById(R.id.tomarfoto);

        SEND = findViewById(R.id.registrar);
        IMG = findViewById(R.id.imgrestaurant);

        btn.setVisibility(View.INVISIBLE);
        if (reviewPermissions()) {
            btn.setVisibility(View.VISIBLE);

        }
        SEND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DATAIMAGE != null) {
                    AsyncHttpClient client = new AsyncHttpClient();
                    File img = new File(DATAIMAGE.path);

                   // client.addHeader("authorization", Data.TOKEN);
                    RequestParams params = new RequestParams();
                    try {
                        params.put("file", img);

                        client.post(Data.REGISTER_IMG, params, new JsonHttpResponseHandler(){
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                Toast.makeText(FotoRestaurante.this, "EXITO", Toast.LENGTH_LONG).show();

                                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
                                try {

                                    int resp = response.getInt("resp");
                                    Toast.makeText(FotoRestaurante.this, "respuesta:"+resp, Toast.LENGTH_SHORT).show();
                                    if(resp==200){
                                        JSONObject json=response.getJSONObject("updateimg");

                                        //String token = response.getString("token");

                                        String idI=json.getString("_id");

                                        //Data.TOKEN="Data "+token;
                                        Data.ID_User=idI;

                                            Intent intent =new Intent(FotoRestaurante.this, Registrar_Restaurant.class);
                                            intent.putExtra("_idI",idI);
                                            startActivity(intent);
                                            finish();


                                        //Toast.makeText(login.this, "Login correctamente: "+ token, Toast.LENGTH_SHORT).show();
                                        Toast.makeText(FotoRestaurante.this, "registro correctamente: ", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(FotoRestaurante.this, "error de envio", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(FotoRestaurante.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            }

                        });

                    } catch(FileNotFoundException e) {}
                }
              //  startActivity(new Intent(FotoRestaurante.this,Info_Restaurante.class));
             //  startActivity(new Intent(FotoRestaurante.this,Info_Restaurante.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                FotoRestaurante.this.startActivityForResult(camera, CODE);

            }
        });

    }

    private boolean reviewPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        if (this.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        requestPermissions(new String [] {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_PERMISSIONS);
        return false;
    }
    private BitmapStruct saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String path = directory.getAbsolutePath() + "/profile.jpg";
        BitmapStruct p = new BitmapStruct();
        p.img = BitmapFactory.decodeFile(path);
        p.path = path;
        return p;
        //return directory.getAbsolutePath();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (CODE_PERMISSIONS == requestCode) {
            if (permissions.length == 3) {
                btn.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE) {
            Bitmap img = (Bitmap)data.getExtras().get("data");
            DATAIMAGE = saveToInternalStorage(img);
            IMG.setImageBitmap(DATAIMAGE.img);

        }
    }





}