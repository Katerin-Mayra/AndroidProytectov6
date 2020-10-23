package com.example.katerin.androidproyectov6.adapterClienteMenu;

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
import com.example.katerin.androidproyectov6.Crear_Pedido;
import com.example.katerin.androidproyectov6.Editar_Menu1;
import com.example.katerin.androidproyectov6.Info_MenusC;
import com.example.katerin.androidproyectov6.R;
import com.example.katerin.androidproyectov6.Ver_menus_cliente;
import com.example.katerin.androidproyectov6.adapter2.EsMenu;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class CMenuAdapter extends BaseAdapter {


    public ArrayList<EsMenuC> LISTMENUCli;
    public Context context;

    public CMenuAdapter(ArrayList<EsMenuC> data, Context context) {
        LISTMENUCli = data;
        this.context = context;
        // this.listem=listem;


    }

    @Override
    public int getCount() {
        return LISTMENUCli.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTMENUCli.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listmenucliente, null);

            final TextView nombre = convertView.findViewById(R.id.nombreVMC);
            nombre.setText(LISTMENUCli.get(position).getNombre());

            final TextView descripcion = convertView.findViewById(R.id.descripcionVMC);
            descripcion.setText(LISTMENUCli.get(position).getDescripcion());

            final TextView precio = convertView.findViewById(R.id.precioVMC);
            precio.setText(LISTMENUCli.get(position).getPrecio());

            final ImageView foto = convertView.findViewById(R.id.imagenVMC);

            if (LISTMENUCli.get(position).getFoto().equals("No IMAGE")) {
                Glide.with(context)
                        .load("https://image.freepik.com/vector-gratis/plantilla-fondo-menu-restaurante_23-2147490036.jpg")
                        .centerCrop()
                        .into(foto);
            } else {
                Glide.with(context)
                        .load(LISTMENUCli.get(position).getFoto())
                        .centerCrop()
                        .into(foto);
            }

            final String id;

            Button info = convertView.findViewById(R.id.infoVMC);
            Button pedido = convertView.findViewById(R.id.pedidoVMC);
            id = this.LISTMENUCli.get(position).getId();
            info.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, Info_MenusC.class);

                    intent.putExtra("nombre",nombre.getText());
                    intent.putExtra("precio",precio.getText());
                    intent.putExtra("descripcion",descripcion.getText());
                    intent.putExtra("foto","No IMAGE");
                    intent.putExtra("id",id);
                    context.startActivity(intent);

                }
            });
            pedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Crear_Pedido.class);

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



}
