package com.example.katerin.androidproyectov6.adapter2;

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
import com.example.katerin.androidproyectov6.EditarMenu;
import com.example.katerin.androidproyectov6.EditarRestaurant;
import com.example.katerin.androidproyectov6.Editar_Menu1;
import com.example.katerin.androidproyectov6.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MenuAdapter1 extends BaseAdapter {

    public ArrayList<EsMenu> LISTMENU;
    public Context context;

    public MenuAdapter1(ArrayList<EsMenu> data, Context context) {
        LISTMENU = data;
        this.context = context;
        // this.listem=listem;


    }

    @Override
    public int getCount() {
        return LISTMENU.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTMENU.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listmenu, null);

            final TextView nombre = convertView.findViewById(R.id.nombreVM);
            nombre.setText(LISTMENU.get(position).getNombre());

            final TextView descripcion = convertView.findViewById(R.id.descripcionVM);
            descripcion.setText(LISTMENU.get(position).getDescripcion());

            final TextView precio = convertView.findViewById(R.id.precioVM);
            precio.setText(LISTMENU.get(position).getPrecio());

            ImageView foto = convertView.findViewById(R.id.imagenVM);
            if (LISTMENU.get(position).getFoto().equals("No IMAGE")) {
                Glide.with(context)
                        .load("https://image.freepik.com/vector-gratis/plantilla-fondo-menu-restaurante_23-2147490036.jpg")
                        .centerCrop()
                        .into(foto);
            } else {
                Glide.with(context)
                        .load(LISTMENU.get(position).getFoto())
                        .centerCrop()
                        .into(foto);
            }

            final String id;

            Button eliminar = convertView.findViewById(R.id.eliminarVM);
            Button editar = convertView.findViewById(R.id.editarVM);
            id = this.LISTMENU.get(position).getId();
            eliminar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Toast.makeText(context, "eliminado el restaurante id ="+id, Toast.LENGTH_LONG).show();
                    deleteMenu(id);
                }
            });
            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Editar_Menu1.class);

                    intent.putExtra("nombre",nombre.getText());
                    intent.putExtra("precio",precio.getText());
                    intent.putExtra("descripcion",descripcion.getText());
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

        client.delete("http://192.168.100.180:8000/api/1.0/menus" + "?id=" + id, new JsonHttpResponseHandler() {
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




