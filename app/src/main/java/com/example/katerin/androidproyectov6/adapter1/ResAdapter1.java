package com.example.katerin.androidproyectov6.adapter1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.katerin.androidproyectov6.R;

import java.util.ArrayList;

public class ResAdapter1 extends BaseAdapter {
     public ArrayList<EsRestaurante> LISTRESTAURANT;
     public Context context;

    public ResAdapter1(ArrayList<EsRestaurante> data, Context context){
        LISTRESTAURANT=data;
        this.context=context;

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

            TextView nombre = convertView.findViewById (R.id.nombrestaurant2);
            nombre.setText(LISTRESTAURANT.get(position).getNombre());

            TextView calle = convertView.findViewById (R.id.callerestaurant2);
            calle.setText(LISTRESTAURANT.get(position).getCalle());

            TextView telefono = convertView.findViewById (R.id.telefonorestaurant2);
            telefono.setText(LISTRESTAURANT.get(position).getTelefono());

            ImageView image = convertView.findViewById (R.id.imagerestaurant2);
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
        }

        return convertView;
    }


}
