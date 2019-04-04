package ar.gob.buenosaires.buenosairespatrimonio;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class MenuMapas extends AppCompatActivity {

    ListView list;
    AdapterMenuMapas adapter;
    public ArrayList<Methods_Arrays> mapas_array = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_menu_mapas);
        setListData();
        list = findViewById(R.id.ListaMapas);
        adapter = new AdapterMenuMapas(this, mapas_array, getResources());
        list.setAdapter(adapter);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Objects.requireNonNull(notificationManager).cancel(1);


    }

    // ListView
    private void setListData() {
        String[] personajes_array = getResources().getStringArray(R.array.mapas_array);
        for (int i = 0; i < personajes_array.length; i++) {
            final Methods_Arrays sched = new Methods_Arrays();
            String[] Separado = personajes_array[i].split("; ");
            sched.setAnio(Separado[0]);
            sched.setTitulo(Separado[1]);
            sched.setIcono(Separado[2]);
            sched.setDescripcion(Separado[3]);
            sched.setDirInternet(Separado[4]);
            sched.setBool(Integer.parseInt(Separado[5]));
            sched.setZoomMax(Integer.parseInt(Separado[6]));
            sched.setZoomMin(Integer.parseInt(Separado[7]));
            sched.setLimiteN(Double.parseDouble(Separado[8]));
            sched.setLimiteE(Double.parseDouble(Separado[9]));
            sched.setLimiteS(Double.parseDouble(Separado[10]));
            sched.setLimiteO(Double.parseDouble(Separado[11]));
            mapas_array.add(sched);
        }
    }

    public void onItemClick(int mPosition) {
        Methods_Arrays tempValues = (Methods_Arrays) mapas_array.get(mPosition);
        Intent myInt = new Intent(this, Tiled_GPS_BuenosAires.class);
        myInt.putExtra("anio", tempValues.getAnio());
        myInt.putExtra("Titulo", tempValues.getTitulo());
        myInt.putExtra("icono", tempValues.getIcono());
        myInt.putExtra("descripcion", tempValues.getDescripcion());
        myInt.putExtra("DirInternet", tempValues.getDirInternet());
        myInt.putExtra("vrbool", tempValues.getBool());
        myInt.putExtra("zmax", tempValues.getZoomMax());
        myInt.putExtra("zmin", tempValues.getZoomMin());
        myInt.putExtra("limiteN", tempValues.getLimiteN());
        myInt.putExtra("limiteE", tempValues.getLimiteE());
        myInt.putExtra("limiteS", tempValues.getLimiteS());
        myInt.putExtra("limiteO", tempValues.getLimiteO());

        startActivity(myInt);
    }
}
