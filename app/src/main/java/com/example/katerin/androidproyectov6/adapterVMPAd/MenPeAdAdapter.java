package com.example.katerin.androidproyectov6.adapterVMPAd;

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
import com.example.katerin.androidproyectov6.InfoPedidos;
import com.example.katerin.androidproyectov6.Info_MenusC;
import com.example.katerin.androidproyectov6.R;
import com.example.katerin.androidproyectov6.Ver_Pedidos_Admi;
import com.example.katerin.androidproyectov6.Ver_Pedidos_Menu_Admi;
import com.example.katerin.androidproyectov6.adapterAdmiMenu.EsMenuA;

import java.util.ArrayList;

public class MenPeAdAdapter extends BaseAdapter {
    public ArrayList<EsPADMenus> LISTMENUAdmiP;
    public Context context;

    public MenPeAdAdapter(ArrayList<EsPADMenus> data, Context context) {
        LISTMENUAdmiP = data;
        this.context = context;
        // this.listem=listem;


    }

    @Override
    public int getCount() {
        return LISTMENUAdmiP.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTMENUAdmiP.get(position);
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
            nombre.setText(LISTMENUAdmiP.get(position).getNombre());

            final TextView descripcion = convertView.findViewById(R.id.descripcionVMC);
            descripcion.setText(LISTMENUAdmiP.get(position).getDescripcion());

            final TextView precio = convertView.findViewById(R.id.precioVMC);
            precio.setText(LISTMENUAdmiP.get(position).getPrecio());

            final ImageView foto = convertView.findViewById(R.id.imagenVMC);

            if (LISTMENUAdmiP.get(position).getFoto().equals("No IMAGE")) {
                Glide.with(context)
                        .load("https://image.freepik.com/vector-gratis/plantilla-fondo-menu-restaurante_23-2147490036.jpg")
                        .centerCrop()
                        .into(foto);
            } else {
                Glide.with(context)
                        .load(LISTMENUAdmiP.get(position).getFoto())
                        .centerCrop()
                        .into(foto);
            }

            final String id;

            Button info = convertView.findViewById(R.id.infoVMC);
            Button pedido = convertView.findViewById(R.id.pedidoVMC);
            id = this.LISTMENUAdmiP.get(position).getId();
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
                    Intent intent = new Intent(context, Ver_Pedidos_Menu_Admi.class);

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
