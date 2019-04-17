package ar.gob.buenosaires.buenosairespatrimonio;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class MenuBares extends AppCompatActivity {

    ListView list;
    AdapterListaBares adapter;
    public ArrayList<Method_Bares> bares_array = new ArrayList<Method_Bares>();
    EditText editsearch;

//    String[] Nombre;
//    String[] Direccion;
//    String[] Barrio;
//    String[] Telefono;
//    String[] Descripcion;
//    String[] Foto;
//    String[] Coordenadas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_menu_bares);
        setListData();
        list = findViewById(R.id.ListaBares);

        adapter = new AdapterListaBares(this, bares_array);
        list.setAdapter(adapter);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Objects.requireNonNull(notificationManager).cancel(1);


        editsearch = (EditText) findViewById(R.id.SearchBar);

        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });

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
//
//    public void onItemClick(int mPosition)
//    {
//        Method_Bares tempValues = (Method_Bares) bares_array.get(mPosition);
//        Intent myInt = new Intent(this, FichaBar.class);
//        myInt.putExtra("Bar_Nombre", tempValues.getNombre().toString());
//        myInt.putExtra("Bar_Direccion", tempValues.getDireccion().toString());
//        myInt.putExtra("Bar_Barrio", tempValues.getBarrio().toString());
//        myInt.putExtra("Bar_Telefono", tempValues.getTelefono().toString());
//        myInt.putExtra("Bar_Descripcion", tempValues.getDescripcion().toString());
//        myInt.putExtra("Bar_Foto", tempValues.getFoto().toString());
//        myInt.putExtra("Bar_Coordenadas", tempValues.getCoordenadas().toString());
//        startActivity(myInt);
//    }
}
