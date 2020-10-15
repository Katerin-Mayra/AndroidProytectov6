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

public class VerMenuAdapter extends BaseAdapter {
    Context context;
    List<Menus> menus;

    public VerMenuAdapter(Context context, ArrayList<Menus> menus) {
        this.context = context;
        this.menus = menus;
    }
    @Override
    public int getCount() {
        return this.menus.size();
    }

    @Override
    public Object getItem(int position) {
        return this.menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(this.menus.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflate = (LayoutInflater) this.context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate (R.layout.vermenu, null);
        }
        TextView nombre = convertView.findViewById (R.id.vernombreproducto);
        TextView descripcion = convertView.findViewById (R.id.verdescripcionproducto);
        TextView precio = convertView.findViewById(R.id.verprecioproducto);
        ImageView image =  convertView.findViewById (R.id.verimgproducto);
        Glide.with (this.context).load (this.menus.get(position).getId()).into (image);
        nombre.setText (this.menus.get (position).getNombre ());
        descripcion.setText (this.menus.get (position).getDescripcion());
        precio.setText(this.menus.get(position).getPrecio());

        return convertView;
    }
}
