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

public class MainActivity extends AppCompatActivity {

    ListView list;
    AdapterMainActivity adapter;
    public ArrayList<Methods_Arrays> items_menu = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        setListData();
        list = findViewById(R.id.MenuPrincipal);
        adapter = new AdapterMainActivity(this, items_menu, getResources());
        list.setAdapter(adapter);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Objects.requireNonNull(notificationManager).cancel(1);


    }

    // ListView
    private void setListData() {
        String[] array = getResources().getStringArray(R.array.items_menu);
        for (int i = 0; i < array.length; i++) {
            final Methods_Arrays sched = new Methods_Arrays();
            String[] Separado = array[i].split("; ");
            sched.setTitulo(Separado[0]);
            sched.setDescripcion(Separado[1]);
            sched.setIcono(Separado[2]);
            sched.setActivity(Separado[3]);
            items_menu.add(sched);
        }
    }

    public void onItemClick(int mPosition)
    {
        Methods_Arrays tempValues = items_menu.get(mPosition);
        String activity = "ar.gob.buenosaires.buenosairespatrimonio." + tempValues.getActivity();
        Intent myInt = new Intent();

        myInt.setClassName("ar.gob.buenosaires.buenosairespatrimonio" ,activity);
        myInt.putExtra("titulo", tempValues.getTitulo());
        myInt.putExtra("descripcion", tempValues.getDescripcion());
        myInt.putExtra("icono", tempValues.getIcono());
        myInt.putExtra("activity", tempValues.getActivity());
        startActivity(myInt);
    }
}
