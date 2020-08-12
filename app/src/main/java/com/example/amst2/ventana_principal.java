package com.example.amst2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ventana_principal extends AppCompatActivity {
    private  String consultaonsulta = "https://tareaautonoma4.000webhostapp.com//getImage.php?id=1";
    private  String consulta_lista = "https://tareaautonoma4.000webhostapp.com/lista_imagenes.php";
    ImageView imageView;
    TextView textView,lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_principal);
        textView = (TextView) findViewById(R.id.textView4);
        lista = (TextView) findViewById(R.id.lista_imagenes);
        imageView = (ImageView) findViewById(R.id.imageView);
        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("direccion");
        ArrayList<String> lista = new ArrayList();
        String[] arr_tex = text.split("\n");
        textView.setText(text);
        consulta_imagen(this,consultaonsulta);
        consultar_lista(consulta_lista);
    }
    public void consulta_imagen(ventana_principal view, String url){
        String[] resultado = null;

        try {
            String[] datos = new String[]{
                    "call",
                    url
            };
            AsyncImageQuery async = new AsyncImageQuery();
            resultado = async.execute(datos).get();

            byte[] decodedString = Base64.decode(resultado[0], Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedByte);


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
public void consultar_lista(String url){
    String[] resultado = null;
    try {
        String[] datos = new String[]{
                "call",
                url
        };
        AsyncImageQuery async = new AsyncImageQuery();
        resultado = async.execute(datos).get();
        lista.setText(resultado[0]);


    } catch (ExecutionException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
}