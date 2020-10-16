package com.example.katerin.androidproyectov6.adapter2;

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

public class MenuAdapter extends BaseAdapter {
    public ArrayList<EsMenu> LISTMENU;
    public Context context;

    public MenuAdapter(ArrayList<EsMenu> data, Context context) {
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



        }
        return convertView;
    }
}