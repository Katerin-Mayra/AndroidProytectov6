package com.example.katerin.androidproyectov6;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.katerin.androidproyectov6.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class Editar1 extends AppCompatActivity {
    ImageView image1;
    EditText title2,descripcion1,precio1;
    String titulo,descripcion,precio;
    String idmenu;
    Button aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar1);
        image1 = findViewById (R.id.fotomenu1);
        title2 = findViewById (R.id.producto1);
        precio1 = findViewById(R.id.precio1);
        descripcion1 = findViewById (R.id.descripcion1);
        aceptar = findViewById(R.id.aceptar1);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sedData();
                startActivity(new Intent(Editar1.this,EditarMenu.class));

            }
        });


        informacion();

        //ocultar navegacion kato
        getSupportActionBar().hide();
    }
    public void informacion(){
        Intent intent = getIntent();
        //idmenu = intent.getStringExtra("_id");
        titulo = intent.getStringExtra("nombre");
        descripcion = intent.getStringExtra("descripcion");
        precio = intent.getStringExtra("precio");

        title2.setText(titulo);
        descripcion1.setText(descripcion);
        precio1.setText(precio);

        //image1.setImageURI(Uri.parse(img));
    }
    public void sedData() {



        AsyncHttpClient client = new AsyncHttpClient();
        //client.addHeader("authorization", Data.TOKEN);


        RequestParams params = new RequestParams();

        params.put("nombre", title2.getText().toString());
        params.put("precio", precio1.getText().toString());
        params.put("descripcion", descripcion1.getText().toString());

        // Toast.makeText(getApplicationContext(),Data.REGISTER_CLIENTE+"/"+Data.ID_User,Toast.LENGTH_LONG).show();
        client.put(Data.REGISTER_MENUS+"/", params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");


                AlertDialog alertDialog = new AlertDialog.Builder(Editar1.this).create();
                try {
                    int resp = response.getInt("resp");





                    if (resp == 200) {
                        JSONObject json = response.getJSONObject("dato");
                        final String nombre_resp = json.getString("nombre");
                        final String precio_resp = json.getString("precio");
                        final String descripcion_resp = json.getString("descripcion");
                        String msn = response.getString("msn");


                        alertDialog.setTitle("Mensaje");
                        alertDialog.setMessage(msn);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Editar1.this, EliminarMenu.class);
                                intent.putExtra("nombre", nombre_resp);
                                intent.putExtra("ci", precio_resp);
                                intent.putExtra("telefono", descripcion_resp);

                                startActivity(intent);


                            }
                        });
                        alertDialog.show();
                    } else {
                        alertDialog.setTitle("Mensaje");
                        alertDialog.setMessage("Error al actualizar el menu");
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
    String mCurrentPhotoPath;
    @SuppressLint("NewApi")
    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = null;

        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String imageFileName = "BACKUP_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg", storageDir  );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    static final int REQUEST_TAKE_PHOTO = 1;

    public void tomarFoto (View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }



    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            image1.setImageBitmap(imageBitmap);
        }
    }

    public void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
}