package ar.gob.buenosaires.buenosairespatrimonio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.Target;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
        String urlFoto = intent.getExtras().getString("Foto")+"_2.png";


        TextView Nombre = findViewById(R.id.Bar_Nombre);
        TextView Direccion = findViewById(R.id.BarDireccion);
        TextView Barrio = findViewById(R.id.BarBarrio);
        TextView Telefono = findViewById(R.id.BarTelefono);
        TextView Descripcion = findViewById(R.id.BarDescripcion);
        ImageView Foto = findViewById(R.id.Bar_Foto);

        Nombre.setText(TxtNombre);
        Direccion.setText(TxtDireccion);
        Barrio.setText(TxtBarrio);
        Telefono.setText(TxtTelefono);
        Descripcion.setText(TxtDescripcion);

        FirebaseStorage storage  = FirebaseStorage.getInstance("gs://bsaspatrimonio.appspot.com");
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("bares-headers/"+urlFoto);



        GlideApp.with(FichaBar.this).load(pathReference).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(Foto);

    }
}
