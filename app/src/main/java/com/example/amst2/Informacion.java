package com.example.amst2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Informacion extends AppCompatActivity {
    private  String info_usuario;
    TextView nombre,apellido,correo,celular,genero,id_persona;
    private String consulta_generos = "https://tareaautonoma4.000webhostapp.com/lista_generos.php";
    Map<String,String> diccionario_genero = new HashMap<String,String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("direccion");
        String[] arr_text = text.split("zzz");
        info_usuario = text;
        nombre = (TextView) findViewById(R.id.txt_nombres);
        apellido = (TextView) findViewById(R.id.txt_apellidos);
        correo = (TextView) findViewById(R.id.txt_correo);
        celular = (TextView) findViewById(R.id.txt_celular);
        genero = (TextView) findViewById(R.id.txt_genero);
        id_persona = (TextView) findViewById(R.id.txt_id);
        crear_dicionario_generos(consulta_generos);
        id_persona.setText(arr_text[0]);
        nombre.setText(arr_text[1]);
        apellido.setText(arr_text[2]);
        correo.setText(arr_text[3]);
        celular.setText(arr_text[4]);
        genero.setText(diccionario_genero.get(arr_text[5]));
    }
    public void menu_principal(View view){
        Intent i = new Intent(this, ventana_principal.class );
        i.putExtra("direccion", info_usuario);
        startActivity(i);

    }
    public void crear_dicionario_generos(String url){
        String[] resultado = null;
        try {
            String[] datos = new String[]{
                    "call",
                    url
            };
            AsyncImageQuery async = new AsyncImageQuery();
            resultado = async.execute(datos).get();


            String[] arr_tex = resultado[0].split("///");
            for(String lineas:arr_tex){
                String[] valores = lineas.split("~");
                diccionario_genero.put(valores[0],valores[1]);
            }



        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void menu_categorias(View view){
        Intent i = new Intent(this, Categorias.class );
        i.putExtra("direccion", info_usuario);
        startActivity(i);

    }
}