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

    private GoogleMap mMapaGuia;
    private String PatrimonioMarker;
    private LatLng CoordenadasPat, CoordsDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_ficha_guia);

        Intent intent = getIntent();
        String TxtNumero = intent.getExtras().getString("Patrimonio_Numero");
        String TxtTipo = intent.getExtras().getString("Patrimonio_Tipo");
        String TxtCategoria = intent.getExtras().getString("Patrimonio_Categoria");
        String TxtDenominacionPat = intent.getExtras().getString("Patrimonio_Denominacion");
        String TxtLocalizacion = intent.getExtras().getString("Patrimonio_Localizacion");
        String TxtBarrio = intent.getExtras().getString("Patrimonio_Barrio");
        String TxtDireccion_Mapa = intent.getExtras().getString("Patrimonio_Direccion_Mapa");
        String TxtContenido = intent.getExtras().getString("Patrimonio_Contenido");
        String TxtSintesis = intent.getExtras().getString("Patrimonio_Sintesis");
        String TxtAnios = intent.getExtras().getString("Patrimonio_Anios");
        String TxtEpoca = intent.getExtras().getString("Patrimonio_Epoca");
        String TxtAutor = intent.getExtras().getString("Patrimonio_Autor");
        String TxtFuncion_Original = intent.getExtras().getString("Patrimonio_Funcion_Original");
        String TxtFuncion_Actual = intent.getExtras().getString("Patrimonio_Funcion_Actual");
        String TxtProteccion_Nacional = intent.getExtras().getString("Patrimonio_Proteccion_Nacional");
        String TxtProteccion_Municipal = intent.getExtras().getString("Patrimonio_Proteccion_Municipal");
        String TxtFecha_de_Carga = intent.getExtras().getString("Patrimonio_Fecha_de_Carga");
        String TxtImagenes = intent.getExtras().getString("Patrimonio_Imagenes");
        String TxtCoordenadasPat = intent.getExtras().getString("Patrimonio_Coordenadas");

        TextView Tipo = findViewById(R.id.Ficha_Patrimonio_Tipo);
        TextView Categoria = findViewById(R.id.Ficha_Patrimonio_Categoria);
        TextView Denominacion = findViewById(R.id.Ficha_Patrimonio_Denominacion);
        ImageView Foto = findViewById(R.id.FotoFichaPatrimonio);
        TextView Sistesis = findViewById(R.id.Ficha_Patrimonio_Sintesis);
        TextView Contenido = findViewById(R.id.Ficha_Patrimonio_Contenido);
        TextView Anios = findViewById(R.id.Ficha_Patrimonio_anios);
        TextView Autor = findViewById(R.id.Ficha_Patrimonio_Autor);
        TextView FO = findViewById(R.id.Ficha_Patrimonio_FO);
        TextView FA = findViewById(R.id.Ficha_Patrimonio_FA);
        TextView PN = findViewById(R.id.Ficha_Patrimonio_PN);
        TextView PM = findViewById(R.id.Ficha_Patrimonio_PM);
        TextView FechaDeCarga = findViewById(R.id.Ficha_Patrimonio_FechadeCarga);

        Tipo.setText(TxtTipo);
        Categoria.setText(TxtCategoria);
        Denominacion.setText(TxtDenominacionPat);
        Sistesis.setText(TxtSintesis);
        Contenido.setText(TxtContenido);
        Anios.setText(TxtAnios);
        Autor.setText(TxtAutor);
        FO.setText(TxtFuncion_Original);
        FA.setText(TxtFuncion_Actual);
        PN.setText(TxtProteccion_Nacional);
        PM.setText(TxtProteccion_Municipal);
        FechaDeCarga.setText(TxtFecha_de_Carga);

        FirebaseStorage storage  = FirebaseStorage.getInstance("gs://bsaspatrimonio.appspot.com");
        StorageReference storageRef = storage.getReference();
        String urlFoto = TxtImagenes+"-2.png";
        StorageReference pathReference = storageRef.child("patrimonio-headers/"+urlFoto);

        GlideApp.with(FichaGuia.this)
                .load(pathReference)
                .error(R.drawable.loading_header)
                .thumbnail(GlideApp.with(FichaGuia.this).load(R.drawable.loading_header))
                .diskCacheStrategy(DiskCacheStrategy.ALL) //using to load into cache then second time it will load fast.
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .into(Foto);

        SupportMapFragment mapFragmentPat = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.MapGuia);
        mapFragmentPat.getMapAsync(this);

        PatrimonioMarker = TxtDenominacionPat;
        String[] latlongPat =  TxtCoordenadasPat.split(",");
        double latitudePat = Double.parseDouble(latlongPat[0]);
        double longitudePat = Double.parseDouble(latlongPat[1]);
        CoordenadasPat = new LatLng(latitudePat, longitudePat);
        CoordsDemo = new LatLng(-34.608409, -58.372164);
    }

    @Override
    public void onMapReady(GoogleMap GuiaMap) {
        mMapaGuia = GuiaMap;
        LatLng BoundSWPat = new LatLng(-34.704639, -58.531333);
        LatLng BoundNEPat = new LatLng(-34.534139,-58.337543);
        LatLngBounds BoundPatrimonioBaires = new LatLngBounds(BoundSWPat ,BoundNEPat);

        LatLng PatrimonioBaires = CoordenadasPat;
        mMapaGuia.addMarker(new MarkerOptions().position(PatrimonioBaires).title(PatrimonioMarker));
        mMapaGuia.moveCamera(CameraUpdateFactory.newLatLngZoom(PatrimonioBaires, 15));
        mMapaGuia.setLatLngBoundsForCameraTarget(BoundPatrimonioBaires);

    }
}
