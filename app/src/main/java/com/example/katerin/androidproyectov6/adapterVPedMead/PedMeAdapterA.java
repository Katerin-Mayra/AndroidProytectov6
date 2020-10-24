package com.example.katerin.androidproyectov6.adapterVPedMead;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.katerin.androidproyectov6.InfoPedidos;
import com.example.katerin.androidproyectov6.R;
import com.example.katerin.androidproyectov6.Registrar_Factura;
import com.example.katerin.androidproyectov6.adapter4P.PedAdapter;
import com.example.katerin.androidproyectov6.adapter5PA.EsPedidoA;

import java.util.ArrayList;

public class PedMeAdapterA extends BaseAdapter {

    public ArrayList<EsPedidoMenAd> LISTPEDIDOSAP;
    public Context context;
    PedAdapter.editarRest listem;

    public interface editarRest{
        void EdRest (String data);

    }

    public PedMeAdapterA(ArrayList<EsPedidoMenAd> data, Context context) {
        LISTPEDIDOSAP = data;
        this.context = context;
        // this.listem=listem;
    }
    @Override
    public int getCount() {
        return LISTPEDIDOSAP.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTPEDIDOSAP.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listpedidoadmi,null);

            final TextView pago_total = convertView.findViewById (R.id.pago_totalVP);
            pago_total.setText(LISTPEDIDOSAP.get(position).getPago_total());

            final TextView lugar_envio = convertView.findViewById (R.id.lugar_envioVP);
            lugar_envio.setText(LISTPEDIDOSAP.get(position).getLugar_envio());

            final TextView cantidad = convertView.findViewById (R.id.cantidadVP);
            cantidad.setText(LISTPEDIDOSAP.get(position).getCantidad());

            final TextView precios = convertView.findViewById (R.id.preciosVP);
            precios.setText(LISTPEDIDOSAP.get(position).getPrecios());

            final TextView estado = convertView.findViewById (R.id.idestadoad);
            estado.setText(LISTPEDIDOSAP.get(position).getEstado());

            final TextView cliente = convertView.findViewById (R.id.idclientep);
            cliente.setText(LISTPEDIDOSAP.get(position).getCliente());

            final TextView correo = convertView.findViewById (R.id.correofac);
            correo.setText(LISTPEDIDOSAP.get(position).getCorreo());

            final TextView correoC = convertView.findViewById (R.id.correoC);
            correoC.setText(LISTPEDIDOSAP.get(position).getCorreoC());

            final String id;

            //Button eliminar = convertView.findViewById(R.id.eliminarVP);
            Button info = convertView.findViewById(R.id.infoVPA);
            Button factura = convertView.findViewById(R.id.clientebut);
            id = this.LISTPEDIDOSAP.get(position).getId();

            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InfoPedidos.class);

                    intent.putExtra("lugar_envio",lugar_envio.getText());
                    //  intent.putExtra("precios",precios.getText());
                    intent.putExtra("cantidad",cantidad.getText());
                    // intent.putExtra("pago_total",pago_total.getText());
                    intent.putExtra("id",id);
                    context.startActivity(intent);
/*
                String EDRESTAURANTE=nombre.getText()+"/"+telefono.getText()+"/"+calle.getText()+"/"+id;
                listem.EdRest(EDRESTAURANTE);*/

                }
            });

            factura.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Registrar_Factura.class);

                    intent.putExtra("lugar_envio",lugar_envio.getText());
                    intent.putExtra("precios",precios.getText());
                    intent.putExtra("cantidad",cantidad.getText());
                    intent.putExtra("pago_total",pago_total.getText());
                    intent.putExtra("cliente",cliente.getText());
                    intent.putExtra("correo",correo.getText());
                    intent.putExtra("correoC",correoC.getText());
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
