package ar.gob.buenosaires.buenosairespatrimonio;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FichaBar extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String TituloMarker;
    private LatLng Coordenadas;

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
        String TxtCoordenadas = intent.getExtras().getString("Coordenadas");


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

        GlideApp.with(FichaBar.this)
                .load(pathReference)
                .error(R.drawable.loading_header)
                .thumbnail(GlideApp.with(FichaBar.this).load(R.drawable.loading_header))
                .diskCacheStrategy(DiskCacheStrategy.ALL) //using to load into cache then second time it will load fast.
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .into(Foto);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        TituloMarker = TxtNombre;
        String[] latlong =  TxtCoordenadas.split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);
        Coordenadas = new LatLng(latitude, longitude);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng BoundSW = new LatLng(-34.704639, -58.531333);
        LatLng BoundNE = new LatLng(-34.534139,-58.337543);
        LatLngBounds BoundBaires = new LatLngBounds(BoundSW ,BoundNE);

        LatLng baires = Coordenadas;
        mMap.addMarker(new MarkerOptions().position(baires).title(TituloMarker));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(baires, 15));
        mMap.setLatLngBoundsForCameraTarget(BoundBaires);

    }

}
