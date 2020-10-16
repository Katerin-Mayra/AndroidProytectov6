package collects1;

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
import java.util.List;

public class ResAdapter extends BaseAdapter {
    Context context;
    List<Restaurants> restaurants;


    public ResAdapter(Context context, ArrayList<Restaurants> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }

    ///////////////
    @Override
    public int getCount() {
        return this.restaurants.size();
    }

    @Override
    public Object getItem(int position) {
        return this.restaurants.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(this.restaurants.get(position).getId());

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null){
            LayoutInflater inflate = (LayoutInflater) this.context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate (R.layout.item_listrest1, null);
        }
        final TextView nombre = (TextView)view.findViewById (R.id.nombrestaurant);
        final TextView calle = (TextView)view.findViewById (R.id.descripcionVM);
        final TextView telefono = (TextView)view.findViewById (R.id.telefonorestaurant);

        ImageView image = (ImageView) view.findViewById (R.id.imagerestaurant);


        final String id;
        Glide.with(context).load("https://image.freepik.com/vector-gratis/plantilla-fondo-menu-restaurante_23-2147490036.jpg").into(image);
        //Glide.with(context).load("http://192.168.100.180:8000/public/avatars" + restaurants.get(position).getImagen()).into(image);
        nombre.setText (this.restaurants.get (position).getNombre ());
        calle.setText(this.restaurants.get(position).getCalle());
        telefono.setText(this.restaurants.get(position).getTelefono());
        id = this.restaurants.get(position).getId();

        return view;
    }
}
