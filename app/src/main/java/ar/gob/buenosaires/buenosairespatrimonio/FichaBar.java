package ar.gob.buenosaires.buenosairespatrimonio;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.Target;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FichaBar extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

//        Glide.with(context.getApplicationContext())
//                .load(Your Path)   //passing your url to load image.
//                .override(18, 18)  //just set override like this
//                .error(R.drawable.placeholder)
//                .listener(glide_callback)
//                .animate(R.anim.rotate_backward)
//                .centerCrop()
//                .into(image.score);

        GlideApp.with(FichaBar.this).load(pathReference).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(Foto);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng baires = new LatLng(-34.606972, -58.418639);
        mMap.addMarker(new MarkerOptions().position(baires).title("12 de Octubre"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(baires, 15));
    }

}
