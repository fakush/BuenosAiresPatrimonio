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

public class AdapterListaBares extends BaseAdapter {

    Activity activity;
    ArrayList data;
    LayoutInflater inflater;
    public Resources res;
    Method_Bares tempValues=null;
    int i=0;
    public TextView txtNombre;
    public TextView txtDireccion;
    public TextView txtBarrio;
    public ImageView imbFoto;

    @Override
    public boolean areAllItemsEnabled() {
        return super.areAllItemsEnabled();
    }

    public AdapterListaBares(Activity LocalActivity, ArrayList MyArrayListOfObjects, Resources LocalResources) {

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
            vi = inflater.inflate(R.layout.item_bares, null);
        }

        txtNombre = (TextView) vi.findViewById(R.id.Titulo_LWbares);
        txtDireccion = (TextView) vi.findViewById(R.id.Direccion_LWbares);
        txtBarrio = (TextView)  vi.findViewById(R.id.Barrio_LWbares);
        imbFoto = (ImageView) vi.findViewById(R.id.FotoBar);

        tempValues = null;
        tempValues = (Method_Bares) data.get(position);

        txtNombre.setText(tempValues.getNombre());
        txtDireccion.setText(tempValues.getDireccion());
        txtBarrio.setText(tempValues.getBarrio());
//        String Icon = tempValues.getFoto().toLowerCase();
//        imbFoto.setImageResource(activity.getResources().getIdentifier(Icon, "drawable", activity.getPackageName())
//        );
        vi.setOnClickListener(new AdapterListaBares.MainClickListener(position));
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