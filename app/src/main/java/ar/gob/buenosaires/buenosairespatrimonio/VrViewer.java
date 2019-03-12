package ar.gob.buenosaires.buenosairespatrimonio;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;

public class VrViewer extends AppCompatActivity {

    private VrPanoramaView mVRPanoramaView;
    private String VR;
    private Integer VrType;
    private Integer panoInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_viewer);

        Intent intent = getIntent();
        String TxtTitulo = intent.getExtras().getString("titulo");
        String TxtDireccion = intent.getExtras().getString("Direccion");
//        String TxtIcono = intent.getExtras().getString("icono");
        String TxtInfo = intent.getExtras().getString("info");
        String TxtVr = intent.getExtras().getString("vr");
        Integer IntVrType = intent.getExtras().getInt("vrtype");

        TextView Titulo = (TextView)findViewById(R.id.TituloMirador);
        Titulo.setText(TxtTitulo.toString());

        TextView Info = (TextView)findViewById(R.id.InfoView);
        Info.setText(TxtInfo.toString());

        VR = TxtVr.toString();

        VrType = IntVrType;

        mVRPanoramaView = (VrPanoramaView) findViewById(R.id.vrPanoramaView);

        setVrType();

        loadPhotoSphere();

        Toast.makeText(VrViewer.this,
                "Mové el celular a la altura de tus ojos, para ver la vista del mirador.", Toast.LENGTH_LONG).show();

    }

    private void setVrType() {
        if (VrType == 0) {
            panoInput = VrPanoramaView.Options.TYPE_MONO;
        } else {
            panoInput = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
        }

    }

    private void loadPhotoSphere() {
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        InputStream inputStream = null;

        AssetManager assetManager = getAssets();
        try {
            inputStream = assetManager.open(VR);
            options.inputType = panoInput; //Cambiar acá deacuerdo al tipo de foto VR. Cambiarlo via función.
            mVRPanoramaView.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        mVRPanoramaView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mVRPanoramaView.resumeRendering();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVRPanoramaView.shutdown();

    }
}

