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

public class MenuMiradores extends AppCompatActivity {

    ListView list;
    AdapterMenuMiradores adapter;
    public ArrayList<Methods_Arrays> miradores_array = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_menu_miradores);
        setListData();
        list = findViewById(R.id.ListaMiradores);
        adapter = new AdapterMenuMiradores(this, miradores_array, getResources());
        list.setAdapter(adapter);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Objects.requireNonNull(notificationManager).cancel(1);


    }

    // ListView
    private void setListData() {
        String[] personajes_array = getResources().getStringArray(R.array.miradores_array);
        for (int i = 0; i < personajes_array.length; i++) {
            final Methods_Arrays sched = new Methods_Arrays();
            String[] Separado = personajes_array[i].split("; ");
            sched.setTitulo(Separado[0]);
            sched.setDireccion(Separado[1]);
            sched.setIcono(Separado[2]);
            sched.setInfo(Separado[3]);
            sched.setVR(Separado[4]);
            sched.setVrType(Integer.parseInt(Separado[5]));
            miradores_array.add(sched);
        }
    }

    public void onItemClick(int mPosition)
    {
        Methods_Arrays tempValues = (Methods_Arrays) miradores_array.get(mPosition);
        Intent myInt = new Intent(this, VrViewer.class);
        myInt.putExtra("titulo", tempValues.getTitulo().toString());
        myInt.putExtra("Direccion", tempValues.getDireccion().toString());
        myInt.putExtra("icono", tempValues.getIcono().toString());
        myInt.putExtra("info", tempValues.getInfo().toString());
        myInt.putExtra("vr", tempValues.getVR().toString());
        myInt.putExtra("vrtype", tempValues.getVrType());
        startActivity(myInt);
    }
}
