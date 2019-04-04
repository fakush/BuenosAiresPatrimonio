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

public class MenuBares extends AppCompatActivity {

    ListView list;
    AdapterListaBares adapter;
    public ArrayList<Method_Bares> bares_array = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_menu_bares);
        setListData();
        list = findViewById(R.id.ListaBares);
        adapter = new AdapterListaBares(this, bares_array, getResources());
        list.setAdapter(adapter);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Objects.requireNonNull(notificationManager).cancel(1);

    }

    // ListView
    private void setListData() {
        String[] array = getResources().getStringArray(R.array.Bares_notables);
        for (int i = 0; i < array.length; i++) {
            final Method_Bares sched = new Method_Bares();
            String[] Separado = array[i].split("; ");
            sched.setNombre(Separado[0]);
            sched.setDireccion(Separado[1]);
            sched.setBarrio(Separado[2]);
            sched.setTelefono(Separado[3]);
            sched.setDescripcion(Separado[4]);
            sched.setFoto(Separado[5]);
            sched.setCoordenadas(Separado[6]);
            bares_array.add(sched);
        }
    }

    public void onItemClick(int mPosition)
    {
        Method_Bares tempValues = (Method_Bares) bares_array.get(mPosition);
        Intent myInt = new Intent(this, FichaBar.class);
        myInt.putExtra("Nombre", tempValues.getNombre().toString());
        myInt.putExtra("Direccion", tempValues.getDireccion().toString());
        myInt.putExtra("Barrio", tempValues.getBarrio().toString());
        myInt.putExtra("Telefono", tempValues.getTelefono().toString());
        myInt.putExtra("Descripcion", tempValues.getDescripcion().toString());
        myInt.putExtra("Foto", tempValues.getFoto().toString());
        myInt.putExtra("Coordenadas", tempValues.getCoordenadas().toString());
        startActivity(myInt);
    }
}
