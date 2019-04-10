package ar.gob.buenosaires.buenosairespatrimonio;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class MenuGuia extends AppCompatActivity {

    ListView list;
    AdapterListaPatrimonios adapter;
    public ArrayList<Method_Patrimonios> patrimonios_array = new ArrayList<>();

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_guia);
        setListData();
        list = findViewById(R.id.ListaPatrimonios);
        adapter = new AdapterListaPatrimonios(this, patrimonios_array, getResources());
        list.setAdapter(adapter);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Objects.requireNonNull(notificationManager).cancel(1);


//        webView = (WebView) findViewById(R.id.WebPat);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("http://www.buenosaires.gob.ar/cultura/patrimonio");

    }

    // ListView
    private void setListData() {
        String[] array = getResources().getStringArray(R.array.Lista_Patrimonios);
        for (int i = 0; i < array.length; i++) {
            final Method_Patrimonios sched = new Method_Patrimonios();
            String[] Separado = array[i].split("/;/"); //"&quot;&quot" = "";""
            sched.setNumero(Separado[0]);
            sched.setTipo(Separado[1]);
            sched.setCategoria(Separado[2]);
            sched.setDenominacion(Separado[3]);
            sched.setLocalizacion(Separado[4]);
            sched.setBarrio(Separado[5]);
            sched.setDireccion_Mapa(Separado[6]);
            sched.setContenido(Separado[7]);
            sched.setSientesis(Separado[8]);
            sched.setAnios(Separado[9]);
            sched.setEpoca(Separado[10]);
            sched.setAutor(Separado[11]);
            sched.setFuncion_Original(Separado[12]);
            sched.setFuncion_Actual(Separado[13]);
            sched.setProteccion_Nacional(Separado[14]);
            sched.setProteccion_Municipal(Separado[15]);
            sched.setFecha_de_Carga(Separado[16]);
            sched.setImagenes(Separado[17]);
            sched.setCoordenadasPat(Separado[18]);
            patrimonios_array.add(sched);
        }
    }

    public void onItemClick(int mPosition)
    {
        Method_Patrimonios tempValues = (Method_Patrimonios) patrimonios_array.get(mPosition);
        Intent myInt = new Intent(this, FichaGuia.class);
        myInt.putExtra("Patrimonio_Numero", tempValues.getNumero().toString());
        myInt.putExtra("Patrimonio_Tipo", tempValues.getTipo().toString());
        myInt.putExtra("Patrimonio_Categoria", tempValues.getCategoria().toString());
        myInt.putExtra("Patrimonio_Denominacion", tempValues.getDenominacion().toString());
        myInt.putExtra("Patrimonio_Localizacion", tempValues.getLocalizacion().toString());
        myInt.putExtra("Patrimonio_Barrio", tempValues.getBarrio().toString());
        myInt.putExtra("Patrimonio_Direccion_Mapa", tempValues.getDireccion_Mapa().toString());
        myInt.putExtra("Patrimonio_Contenido", tempValues.getContenido().toString());
        myInt.putExtra("Patrimonio_Sintesis", tempValues.getSientesis().toString());
        myInt.putExtra("Patrimonio_Anios", tempValues.getAnios().toString());
        myInt.putExtra("Patrimonio_Epoca", tempValues.getEpoca().toString());
        myInt.putExtra("Patrimonio_Autor", tempValues.getAutor().toString());
        myInt.putExtra("Patrimonio_Funcion_Original", tempValues.getFuncion_Original().toString());
        myInt.putExtra("Patrimonio_Funcion_Actual", tempValues.getFuncion_Actual().toString());
        myInt.putExtra("Patrimonio_Proteccion_Nacional", tempValues.getProteccion_Nacional().toString());
        myInt.putExtra("Patrimonio_Proteccion_Municipal", tempValues.getProteccion_Municipal().toString());
        myInt.putExtra("Patrimonio_Fecha_de_Carga", tempValues.getFecha_de_Carga().toString());
        myInt.putExtra("Patrimonio_Imagenes", tempValues.getImagenes().toString());
        myInt.putExtra("Patrimonio_Coordenadas", tempValues.getCoordenadasPat().toString());
        startActivity(myInt);
    }
}
