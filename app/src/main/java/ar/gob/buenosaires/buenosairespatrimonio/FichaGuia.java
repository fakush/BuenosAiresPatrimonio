package ar.gob.buenosaires.buenosairespatrimonio;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class FichaGuia extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String TituloMarker;
    private LatLng Coordenadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_ficha_guia);

        Intent intent = getIntent();
        String TxtNumero = intent.getExtras().getString("Numero");
        String TxtTipo = intent.getExtras().getString("Tipo");
        String TxtCategoria = intent.getExtras().getString("Categoria");
        String TxtDenominacion = intent.getExtras().getString("Denominacion");
        String TxtLocalizacion = intent.getExtras().getString("Localizacion");
        String TxtBarrio = intent.getExtras().getString("Barrio");
        String TxtDireccion_Mapa = intent.getExtras().getString("Direccion_Mapa");
        String TxtContenido = intent.getExtras().getString("Contenido");
        String TxtSintesis = intent.getExtras().getString("Sintesis");
        String TxtAnios = intent.getExtras().getString("Anios");
        String TxtEpoca = intent.getExtras().getString("Epoca");
        String TxtAutor = intent.getExtras().getString("Autor");
        String TxtFuncion_Original = intent.getExtras().getString("Funcion_Original");
        String TxtFuncion_Actual = intent.getExtras().getString("Funcion_Actual");
        String TxtProteccion_Nacional = intent.getExtras().getString("Proteccion_Nacional");
        String TxtProteccion_Municipal = intent.getExtras().getString("Proteccion_Municipal");
        String TxtFecha_de_Carga = intent.getExtras().getString("Fecha_de_Carga");
        String TxtImagenes = intent.getExtras().getString("Imagenes");

        TextView Nombre = findViewById(R.id.Patrimonio_Nombre);
        TextView Direccion = findViewById(R.id.Patrimonio_Direccion);
        TextView Barrio = findViewById(R.id.Patrimonio_Barrio);
        TextView Telefono = findViewById(R.id.Patrimonio_Telefono);
        TextView Descripcion = findViewById(R.id.Patrimonio_Descripcion);
        ImageView Foto = findViewById(R.id.Patrimonio_Foto);

        Nombre.setText(TxtNombre);
        Direccion.setText(TxtDireccion);
        Barrio.setText(TxtBarrio);
        Telefono.setText(TxtTelefono);
        Descripcion.setText(TxtDescripcion);

        FirebaseStorage storage  = FirebaseStorage.getInstance("gs://bsaspatrimonio.appspot.com");
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("patrimonio-headers/"+urlFoto);

        GlideApp.with(FichaGuia.this)
                .load(pathReference)
                .error(R.drawable.loading_header)
                .thumbnail(GlideApp.with(FichaGuia.this).load(R.drawable.loading_header))
                .diskCacheStrategy(DiskCacheStrategy.ALL) //using to load into cache then second time it will load fast.
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .into(Foto);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView2);
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
