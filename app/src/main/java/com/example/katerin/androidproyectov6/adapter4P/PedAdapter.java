package com.example.katerin.androidproyectov6.adapter4P;

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
import com.example.katerin.androidproyectov6.EditarPedido;
import com.example.katerin.androidproyectov6.EditarRestaurant;
import com.example.katerin.androidproyectov6.R;
import com.example.katerin.androidproyectov6.adapter1.EsRestaurante;
import com.example.katerin.androidproyectov6.adapter1.ResAdapter1;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PedAdapter extends BaseAdapter {

    public ArrayList<EsPedido> LISTPEDIDOS;
    public Context context;
    PedAdapter.editarRest listem;

    public interface editarRest{
        void EdRest (String data);

    }

    public PedAdapter(ArrayList<EsPedido> data, Context context){
        LISTPEDIDOS=data;
        this.context=context;
        // this.listem=listem;


    }




    @Override
    public int getCount() {
        return LISTPEDIDOS.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTPEDIDOS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listpedido,null);

            final TextView pago_total = convertView.findViewById (R.id.pago_totalVP);
            pago_total.setText(LISTPEDIDOS.get(position).getPago_total());

            final TextView lugar_envio = convertView.findViewById (R.id.lugar_envioVP);
            lugar_envio.setText(LISTPEDIDOS.get(position).getLugar_envio());

            final TextView cantidad = convertView.findViewById (R.id.cantidadVP);
            cantidad.setText(LISTPEDIDOS.get(position).getCantidad());

            final TextView precios = convertView.findViewById (R.id.preciosVP);
            precios.setText(LISTPEDIDOS.get(position).getPrecios());

            final TextView nombre = convertView.findViewById (R.id.nombrePro);
            nombre.setText(LISTPEDIDOS.get(position).getNombre());

            final TextView estado = convertView.findViewById (R.id.idestadoVP);
            estado.setText(LISTPEDIDOS.get(position).getEstado());








            final String id;

            Button eliminar = convertView.findViewById(R.id.eliminarVP);
            Button editar = convertView.findViewById(R.id.editarVPC);
            id = this.LISTPEDIDOS.get(position).getId();
            eliminar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Toast.makeText(context, "eliminado el menu id ="+id, Toast.LENGTH_LONG).show();
                    deleteMenu(id);


                }



            });
            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EditarPedido.class);

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
        }

        return convertView;
    }
    private void deleteMenu(final String id) {


        AsyncHttpClient client = new AsyncHttpClient();

        client.delete("http://192.168.100.180:8000/api/1.0/orden" + "?id=" + id, new JsonHttpResponseHandler() {
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
