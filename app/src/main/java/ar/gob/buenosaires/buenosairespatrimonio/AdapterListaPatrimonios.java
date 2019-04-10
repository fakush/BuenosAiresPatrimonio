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

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class AdapterListaPatrimonios extends BaseAdapter {

    Activity activity;
    ArrayList data;
    LayoutInflater inflater;
    public Resources res;
    Method_Patrimonios tempValues=null;
    int i=0;
    public TextView txtTipo;
    public TextView txtCategoría;
    public TextView txtDenominación;
    public ImageView imbIcono;



    @Override
    public boolean areAllItemsEnabled() {
        return super.areAllItemsEnabled();
    }

    public AdapterListaPatrimonios(Activity LocalActivity, ArrayList MyArrayListOfObjects, Resources LocalResources) {

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
            vi = inflater.inflate(R.layout.item_patrimonios, null);
        }

        txtTipo = (TextView) vi.findViewById(R.id.Tipo_LWpatrimonios);
        txtCategoría = (TextView) vi.findViewById(R.id.Categoria_LWpatrimonios);
        txtDenominación = (TextView)  vi.findViewById(R.id.Denominacion_LWpatrimonios);
        imbIcono = (ImageView) vi.findViewById(R.id.FotoPatrimonio);

        tempValues = null;
        tempValues = (Method_Patrimonios) data.get(position);

        txtTipo.setText(tempValues.getTipo());
        txtCategoría.setText(tempValues.getCategoria());
        txtDenominación.setText(tempValues.getDenominacion());
        String Icon = tempValues.getImagenes().toLowerCase();

        FirebaseStorage storage  = FirebaseStorage.getInstance("gs://bsaspatrimonio.appspot.com");
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("patrimonio-thumbs/"+Icon+"-1.png");

        GlideApp.with(activity)
                .load(pathReference)
                .error(R.drawable.loading_thumb)
                .thumbnail(GlideApp.with(activity).load(R.drawable.cargado_thumb))
                .diskCacheStrategy(DiskCacheStrategy.ALL) //using to load into cache then second time it will load fast.
                .override(75, 75)
                .into(imbIcono);

//        imbFoto.setImageResource(activity.getResources().getIdentifier(Icon, "drawable", activity.getPackageName())
//        );
        vi.setOnClickListener(new AdapterListaPatrimonios.MainClickListener(position));
        return vi;
    }

    public class MainClickListener implements View.OnClickListener {
        private int mPosition;

        MainClickListener(int position){mPosition = position;}
        @Override
        public void onClick(View view) {
            MenuGuia sct = (MenuGuia) activity;
            sct.onItemClick(mPosition);
        }
    }
}