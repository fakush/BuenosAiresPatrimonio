package ar.gob.buenosaires.buenosairespatrimonio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FichaBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_bar);

        Intent intent = getIntent();
        String TxtNombre = intent.getExtras().getString("Nombre");
        String TxtDireccion = intent.getExtras().getString("Direccion");
        String TxtBarrio = intent.getExtras().getString("Barrio");
        String TxtTelefono = intent.getExtras().getString("Telefono");
        String TxtDescripcion = intent.getExtras().getString("Descripcion");
        String TxtFoto = intent.getExtras().getString("Foto");

        TextView Nombre = (TextView) findViewById(R.id.Bar_Nombre);
        TextView Direccion = (TextView) findViewById(R.id.BarDireccion);
        TextView Barrio = (TextView) findViewById(R.id.BarBarrio);
        TextView Telefono = (TextView) findViewById(R.id.BarTelefono);
        TextView Descripcion = (TextView) findViewById(R.id.BarDescripcion);
        ImageView Foto = (ImageView) findViewById(R.id.FotoBar);

        Nombre.setText(TxtNombre.toString());
        Direccion.setText(TxtDireccion.toString());
        Barrio.setText(TxtBarrio.toString());
        Telefono.setText(TxtTelefono.toString());
        Descripcion.setText(TxtDescripcion.toString());
    }
}
