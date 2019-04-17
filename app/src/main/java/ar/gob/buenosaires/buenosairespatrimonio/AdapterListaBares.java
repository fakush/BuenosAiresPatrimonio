package ar.gob.buenosaires.buenosairespatrimonio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterListaBares extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Method_Bares> listabares = null;
    private ArrayList<Method_Bares> arraylist;

    public AdapterListaBares (Context context, List<Method_Bares> listabares) {
        mContext = context;
        this.listabares = listabares;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Method_Bares>();
        this.arraylist.addAll(listabares);
    }

    public class ViewHolder {

        TextView Nombre;
        TextView Direccion;
        TextView Barrio;
        TextView Telefono;
        TextView Descripcion;
        ImageView Foto;
        TextView Coordenadas;
    }

    @Override
    public int getCount() {
        return listabares.size();
    }

    @Override
    public Method_Bares getItem(int position) {
        return listabares.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_bares, null);
            // Locate the TextViews in listview_item.xml
            holder.Nombre = (TextView) view.findViewById(R.id.Titulo_LWbares);
            holder.Direccion = (TextView) view.findViewById(R.id.Direccion_LWbares);
            holder.Barrio = (TextView) view.findViewById(R.id.Barrio_LWbares);
            // Locate the ImageView in listview_item.xml
            holder.Foto = (ImageView) view.findViewById(R.id.FotoBar);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.Nombre.setText(listabares.get(position).getNombre());
        holder.Direccion.setText(listabares.get(position).getDireccion());
        holder.Barrio.setText(listabares.get(position).getBarrio());
        // Set the results into ImageView
        String ContImagen = listabares.get(position).getFoto().toLowerCase();
        FirebaseStorage storage  = FirebaseStorage.getInstance("gs://bsaspatrimonio.appspot.com");
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("bares-thumbs/"+ContImagen+"_1.png");
        GlideApp.with(mContext)
                .load(pathReference)
                .error(R.drawable.loading_thumb)
                .thumbnail(GlideApp.with(mContext).load(R.drawable.cargado_thumb))
                .diskCacheStrategy(DiskCacheStrategy.ALL) //using to load into cache then second time it will load fast.
                .override(75, 75)
                .into(holder.Foto);

//        holder.Foto.setText(listabares.get(position).getFoto());
        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, FichaBar.class);
                // Pass all data rank
                intent.putExtra("Nombre",(listabares.get(position).getNombre()));
                intent.putExtra("Bar_Nombre", (listabares.get(position).getNombre()));
                intent.putExtra("Bar_Direccion", (listabares.get(position).getDireccion()));
                intent.putExtra("Bar_Barrio", (listabares.get(position).getBarrio()));
                intent.putExtra("Bar_Telefono", (listabares.get(position).getTelefono()));
                intent.putExtra("Bar_Descripcion", (listabares.get(position).getDescripcion()));
                intent.putExtra("Bar_Foto", (listabares.get(position).getFoto()));
                intent.putExtra("Bar_Coordenadas", (listabares.get(position).getCoordenadas()));
                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        listabares.clear();
        if (charText.length() == 0) {
            listabares.addAll(arraylist);
        } else {
            for (Method_Bares wp : arraylist) {
                if (wp.getNombre().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    listabares.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}