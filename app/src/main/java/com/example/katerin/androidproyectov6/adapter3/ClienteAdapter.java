package com.example.katerin.androidproyectov6.adapter3;

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
import com.example.katerin.androidproyectov6.Editar_Menu1;
import com.example.katerin.androidproyectov6.R;
import com.example.katerin.androidproyectov6.adapter2.EsMenu;

import java.util.ArrayList;

public class ClienteAdapter extends BaseAdapter {

    public ArrayList<EsCliente> LISTCLIENT;
    public Context context;

    public ClienteAdapter(ArrayList<EsCliente> data, Context context) {
        LISTCLIENT = data;
        this.context = context;
        // this.listem=listem;


    }

    @Override
    public int getCount() {
        return LISTCLIENT.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTCLIENT.get(position);
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
            nombre.setText(LISTCLIENT.get(position).getNombre());

            final TextView email = convertView.findViewById(R.id.descripcionVM);
            email.setText(LISTCLIENT.get(position).getEmail());

            final TextView telefono = convertView.findViewById(R.id.precioVM);
            telefono.setText(LISTCLIENT.get(position).getTelefono());

            final String id;

            Button eliminar = convertView.findViewById(R.id.eliminarVM);
            Button editar = convertView.findViewById(R.id.editarVM);
            id = this.LISTCLIENT.get(position).getId();
            /*
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

            /*    }
            });*/

        }





        return convertView;
    }
}
