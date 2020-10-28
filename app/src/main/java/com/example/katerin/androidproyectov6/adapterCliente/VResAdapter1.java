package com.example.katerin.androidproyectov6.adapterCliente;

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
import com.example.katerin.androidproyectov6.Info_RestauranteCA;
import com.example.katerin.androidproyectov6.R;
import com.example.katerin.androidproyectov6.Ver_Restaurante_Cliente;
import com.example.katerin.androidproyectov6.Ver_menus;
import com.example.katerin.androidproyectov6.Ver_menus_cliente;
import com.example.katerin.androidproyectov6.adapter1.EsRestaurante;
import com.example.katerin.androidproyectov6.adapter1.ResAdapter1;
import com.example.katerin.androidproyectov6.adapter3.EsCliente;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class VResAdapter1 extends BaseAdapter {
    TextView tipo,_idc;

    public ArrayList<EsVRestaurante> LISTRESTAURANTCLI;
    public Context context;
   // EsVRestaurante.editarRest listem;



    public interface editarRest{
        void EdRest (String data);

    }
    public VResAdapter1(ArrayList<EsVRestaurante> data, Context context){
        LISTRESTAURANTCLI=data;
        this.context=context;
        // this.listem=listem;


    }





    @Override
    public int getCount() {
        return LISTRESTAURANTCLI.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTRESTAURANTCLI.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listrescliente,null);

            final TextView nombre = convertView.findViewById (R.id.nombreVM);
            nombre.setText(LISTRESTAURANTCLI.get(position).getNombre());

            final TextView calle = convertView.findViewById (R.id.descripcionVM);
            calle.setText(LISTRESTAURANTCLI.get(position).getCalle());

            final TextView telefono = convertView.findViewById (R.id.precioVM);
            telefono.setText(LISTRESTAURANTCLI.get(position).getTelefono());

            final TextView propietario = convertView.findViewById (R.id.propietarioVM);
            propietario.setText(LISTRESTAURANTCLI.get(position).getPropietario());

            ImageView image = convertView.findViewById (R.id.imagenVM);
            if(LISTRESTAURANTCLI.get(position).getImagen().equals("No IMAGE")){
                Glide.with(context)
                        .load("https://image.freepik.com/vector-gratis/plantilla-fondo-menu-restaurante_23-2147490036.jpg")
                        .centerCrop()
                        .into(image);
            }else{
                Glide.with(context)
                        .load(LISTRESTAURANTCLI.get(position).getImagen())
                        .centerCrop()
                        .into(image);
            }

            final String id;

            Button menus = convertView.findViewById(R.id.ver_menuLRC);
            Button infoRes = convertView.findViewById(R.id.ver_infoLRC);
            id = this.LISTRESTAURANTCLI.get(position).getId();

            menus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Ver_menus_cliente.class);

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
            infoRes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Info_RestauranteCA.class);

                    intent.putExtra("nombre",nombre.getText());
                    intent.putExtra("telefono",telefono.getText());
                    intent.putExtra("calle",calle.getText());
                    intent.putExtra("propietario",propietario.getText());

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
