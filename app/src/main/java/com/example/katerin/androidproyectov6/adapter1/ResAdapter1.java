package com.example.katerin.androidproyectov6.adapter1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.katerin.androidproyectov6.EditarRestaurant;
import com.example.katerin.androidproyectov6.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ResAdapter1 extends BaseAdapter {
     public ArrayList<EsRestaurante> LISTRESTAURANT;
     public Context context;
     editarRest listem;

     public interface editarRest{
         void EdRest (String data);

     }

    public ResAdapter1(ArrayList<EsRestaurante> data, Context context){
        LISTRESTAURANT=data;
        this.context=context;
       // this.listem=listem;


    }

    @Override
    public int getCount() {

        return LISTRESTAURANT.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTRESTAURANT.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listrest1,null);

            final TextView nombre = convertView.findViewById (R.id.nombreVM);
            nombre.setText(LISTRESTAURANT.get(position).getNombre());

            final TextView calle = convertView.findViewById (R.id.descripcionVM);
            calle.setText(LISTRESTAURANT.get(position).getCalle());

            final TextView telefono = convertView.findViewById (R.id.precioVM);
            telefono.setText(LISTRESTAURANT.get(position).getTelefono());

            ImageView image = convertView.findViewById (R.id.imagenVM);
            if(LISTRESTAURANT.get(position).getImagen().equals("No IMAGE")){
                Glide.with(context)
                        .load("https://image.freepik.com/vector-gratis/plantilla-fondo-menu-restaurante_23-2147490036.jpg")
                        .centerCrop()
                        .into(image);
            }else{
                Glide.with(context)
                        .load(LISTRESTAURANT.get(position).getImagen())
                        .centerCrop()
                        .into(image);
            }

            final String id;

            Button eliminar = convertView.findViewById(R.id.eliminarVM);
            Button editar = convertView.findViewById(R.id.editarVM);
            id = this.LISTRESTAURANT.get(position).getId();
            eliminar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                   // Toast.makeText(this,"eliminado",Toast.LENGTH_SHORT).show();
/*
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    // Add the buttons
                    builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {


                            // User clicked OK button
                            deleteMenu(id);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
                    builder.setTitle("Esta seguro de eliminar el menu");


                    AlertDialog dialog = builder.create();*/
                    //dialog.show();
                    Toast.makeText(context, "eliminado el restaurante id ="+id, Toast.LENGTH_LONG).show();
                    deleteMenu(id);


                }



            });
            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent = new Intent(context, EditarRestaurant.class);

                intent.putExtra("nombre",nombre.getText());
                intent.putExtra("telefono",telefono.getText());
                intent.putExtra("calle",calle.getText());
                intent.putExtra("id",id);
                context.startActivity(intent);
/*
                String EDRESTAURANTE=nombre.getText()+"/"+telefono.getText()+"/"+calle.getText()+"/"+id;
                listem.EdRest(EDRESTAURANTE);*/

                }
            });
        }

        return convertView;


    }

    private void deleteMenu(final String id) {


        AsyncHttpClient client = new AsyncHttpClient();

        client.delete("http://192.168.100.180:8000/api/1.0/restaurante" + "?id=" + id, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String message = response.getString("msn");
                    if (message != null) {

                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                        // loadComponents();
                                /*BorrarMenuAdapter adapter = new BorrarMenuAdapter(view);
                                        adapter.notifyDataSetChanged();9*/


                    } else {
                        Toast.makeText(context, "Error al borrar", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }




        });
    }


}
