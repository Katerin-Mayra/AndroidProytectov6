package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.katerin.androidproyectov6.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import collects1.BorrarMenuAdapter;
import collects1.Menus;
import cz.msebera.android.httpclient.Header;

public class CrearMenu extends AppCompatActivity {
    Button aceptar,foto;

    EditText producto,precio,descripcion;
    ImageView imagen;

    ListView listcrear;
    ArrayList<Menus> list_data = new ArrayList<Menus> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_menu);
        //istcrear=findViewById(R.id.);
        foto = findViewById (R.id.tomarfoto);
        producto = findViewById(R.id.producto);
        precio = findViewById(R.id.precioproducto);
        descripcion = findViewById(R.id.descripcion);
        aceptar = findViewById (R.id.aceptar);
        imagen = findViewById (R.id.fotomenu);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sedData();
            }
        });

        /*if (ContextCompat.checkSelfPermission(CrearMenu.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(CrearMenu.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CrearMenu.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }*/
        loadComponents();

    }
     /* String mCurrentPhotoPath;
    @SuppressLint("NewApi")
    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = null;

        timeStamp = new SimpleDateFormat ("yyyyMMdd_HHmmss").format(new Date ());

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
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagen.setImageBitmap(imageBitmap);
        }
    }

    public void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }*/



    public void sedData(){
        final EditText nombre  = findViewById(R.id.producto);
        final EditText precio  = findViewById(R.id.precioproducto);
        final EditText descripcion = findViewById(R.id.descripcion);
        final ImageView image = findViewById (R.id.fotomenu);
        if (nombre.getText().toString().equals("") || precio.getText().toString().equals("") || descripcion.getText().toString().equals("")){
            Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
            return;
        }



        AsyncHttpClient client = new AsyncHttpClient();
        //client.addHeader("authorization", Data.TOKEN);


        RequestParams params = new RequestParams();


        params.put("nombre", nombre.getText().toString());
        params.put("precio", precio.getText().toString());
        params.put("descripcion",descripcion.getText().toString());

        client .post(Data.REGISTER_MENUS, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");



                AlertDialog alertDialog = new AlertDialog.Builder(CrearMenu.this).create();
                try {
                    String id = response.getString("id");
                    int resp = response.getInt("resp");




                    if(resp==200){
                        String msn = response.getString("msn");

                        nombre.getText().clear();
                        precio.getText().clear();
                        descripcion.getText().clear();
                        loadComponents();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });
    }
    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://192.168.1.102:7777/api/v1.0/menus",  new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONArray data = response.getJSONArray("result");
                    for (int i =0 ; i < data.length(); i++) {
                        Menus menus = new Menus();
                        JSONObject object = data.getJSONObject(i);
                        menus.setId(object.getString("_id"));
                        menus.setNombre(object.getString("nombre"));
                        menus.setDescripcion(object.getString("descripcion"));
                        menus.setPrecio(object.getInt("precio"));
                        // menus.setFoto(object.getString("foto"));


                        list_data.add(menus);
                    }
                    BorrarMenuAdapter adapter =  new BorrarMenuAdapter(CrearMenu.this,list_data);
                    listcrear.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });

    }
}