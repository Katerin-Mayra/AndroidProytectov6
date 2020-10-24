package com.example.katerin.androidproyectov6.adapterVMMAIN;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.katerin.androidproyectov6.Crear_Pedido;
import com.example.katerin.androidproyectov6.Info_MenusC;
import com.example.katerin.androidproyectov6.R;
import com.example.katerin.androidproyectov6.adapterClienteMenu.EsMenuC;

import java.util.ArrayList;

public class CMenuAdapterMain extends BaseAdapter {

    public ArrayList<EsMenuMain> LISTMENUMAIN;
    public Context context;

    public CMenuAdapterMain(ArrayList<EsMenuMain> data, Context context) {
        LISTMENUMAIN = data;
        this.context = context;
        // this.listem=listem;


    }

    @Override
    public int getCount() {
        return LISTMENUMAIN.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTMENUMAIN.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listmenumain, null);

            final TextView nombre = convertView.findViewById(R.id.nombreVMC);
            nombre.setText(LISTMENUMAIN.get(position).getNombre());

            final TextView descripcion = convertView.findViewById(R.id.descripcionVMC);
            descripcion.setText(LISTMENUMAIN.get(position).getDescripcion());

            final TextView precio = convertView.findViewById(R.id.precioVMC);
            precio.setText(LISTMENUMAIN.get(position).getPrecio());

            final ImageView foto = convertView.findViewById(R.id.imagenVMC);

            if (LISTMENUMAIN.get(position).getFoto().equals("No IMAGE")) {
                Glide.with(context)
                        .load("https://image.freepik.com/vector-gratis/plantilla-fondo-menu-restaurante_23-2147490036.jpg")
                        .centerCrop()
                        .into(foto);
            } else {
                Glide.with(context)
                        .load(LISTMENUMAIN.get(position).getFoto())
                        .centerCrop()
                        .into(foto);
            }

            final String id;

            Button info = convertView.findViewById(R.id.infoVMC);

            id = this.LISTMENUMAIN.get(position).getId();
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



        }





        return convertView;
    }
}
