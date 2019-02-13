package ar.gob.buenosaires.buenosairespatrimonio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterMenuBares extends BaseAdapter {

    Activity activity;
    ArrayList data;
    LayoutInflater inflater;
    public Resources res;
    Methods_Arrays tempValues=null;
    int i=0;
    public TextView txtTitulo;
    public TextView txtDescripcion;
    public ImageView imbIcono;

    @Override
    public boolean areAllItemsEnabled() {
        return super.areAllItemsEnabled();
    }

    public AdapterMenuBares(Activity LocalActivity, ArrayList MyArrayListOfObjects, Resources LocalResources) {

        activity = LocalActivity;
        data     = MyArrayListOfObjects;
        res      = LocalResources;

        /***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;

        // Este condicional es por la implementación del Pattern.
        if(convertView==null) {
            vi = inflater.inflate(R.layout.item_listview, null);
        }

        txtTitulo = (TextView) vi.findViewById(R.id.lw_Titulo);
        txtDescripcion = (TextView) vi.findViewById(R.id.lw_Descripcion);
        imbIcono = (ImageView) vi.findViewById(R.id.lw_icono);

        tempValues = null;
        tempValues = (Methods_Arrays) data.get(position);

        txtTitulo.setText(tempValues.getTitulo());
        txtDescripcion.setText(tempValues.getDescripcion());
        String Icon = tempValues.getIcono().toLowerCase();
        imbIcono.setImageResource(activity.getResources().getIdentifier(Icon, "drawable", activity.getPackageName())
        );
        vi.setOnClickListener(new MainClickListener(position));
        return vi;
    }

    public class MainClickListener implements View.OnClickListener {
        private int mPosition;

        MainClickListener(int position){mPosition = position;}
        @Override
        public void onClick(View view) {
            MenuBares sct = (MenuBares) activity;
            sct.onItemClick(mPosition);
        }
    }
}