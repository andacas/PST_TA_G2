package com.example.amst2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Categorias extends AppCompatActivity {
    private  String consulta_lista = "https://tareaautonoma4.000webhostapp.com/lista_imagenes.php";
    private String consulta_libros = "https://tareaautonoma4.000webhostapp.com/lista_libros.php";
    private  String consultar_generos = "https://tareaautonoma4.000webhostapp.com/lista_generos.php";
    private  String info_usuario = "";
    Map<String,String> diccionario_lista = new HashMap<String,String>();
    Map<String,String> diccionario_genero = new HashMap<String,String>();
    ArrayList<libro> lista_libro_por_genero = new ArrayList<>();
    ArrayList<libro> momentaneo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("direccion");

        info_usuario = text;
        consultar_lista_imagenes(consulta_lista);
        crear_diccionarios_de_libros(consulta_libros);
        crear_dicionario_generos(consultar_generos);
        init();
    }

    public void consultar_lista_imagenes(String url){
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
                diccionario_lista.put(valores[0],valores[1]);
            }



        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void crear_diccionarios_de_libros(String url ){
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
                libro l = new libro(valores[0],valores[1],valores[2],valores[3],valores[4],valores[5],diccionario_lista.get(valores[0]));
                lista_libro_por_genero.add(l);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        for (final String i :diccionario_genero.keySet()) {
            TableRow tbrow = new TableRow(this);
            Button t1v = new Button(this);
            t1v.setText(diccionario_genero.get(i));

            t1v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    por_categoria(diccionario_genero.get(i));
                }


            });


            tbrow.addView(t1v);
            stk.addView(tbrow);
        }

    }
    public void informacion_personal(View view){
        Intent i = new Intent(this, Informacion.class );
        i.putExtra("direccion", info_usuario);
        startActivity(i);
    }
    public void por_categoria(String cat){
       momentaneo =  new ArrayList();
       for(libro i : lista_libro_por_genero){
           if(diccionario_genero.get(i.genero) == cat){
               momentaneo.add(i);
           }
       }
       lista_libro_por_genero = momentaneo;
       init2();
    }

    public void init2() {
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        stk.removeAllViews();
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Portada ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Informacion ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        stk.addView(tbrow0);
        for (final libro i :lista_libro_por_genero) {
            TableRow tbrow = new TableRow(this);
            ImageView t1v = new ImageView(this);
            i.consulta_imagen(i.url,t1v);
            tbrow.setPadding(20,20,20,20);
            t1v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final LinearLayout anotherLayout = new LinearLayout(view.getContext());
                    LinearLayout.LayoutParams linearLayoutParams =
                            new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT);
                    linearLayoutParams.gravity =Gravity.CENTER;
                    anotherLayout.setGravity(Gravity.CENTER);
                    anotherLayout.setBackgroundColor(Color.WHITE);
                    anotherLayout.setOrientation(LinearLayout.VERTICAL);
                    TextView texto_dinamico = new TextView(view.getContext());
                    texto_dinamico.setText(i.titulo+"\n");
                    texto_dinamico.setGravity(Gravity.CENTER);
                    TextView resumen = new TextView(view.getContext());
                    resumen.setText(i.sinopsis);
                    Button anotherButton = new Button(view.getContext());

                    anotherButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            anotherLayout.removeAllViews();
                            anotherLayout.setBackgroundColor(Color.alpha(0));
                        }
                    });

                    anotherButton.setText("regresar");


                    anotherLayout.addView(texto_dinamico);
                    anotherLayout.addView(resumen);
                    anotherLayout.addView(anotherButton);
                    addContentView(anotherLayout, linearLayoutParams);
                }

                public String get_libro(){
                    String lib = i.autor;
                    return lib;
                }
            });


            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText("Titulo: "+i.titulo+"\nAutor: "+i.autor+"\n"+"Editorial: "+i.editorial);
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            stk.addView(tbrow);
        }

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
    public void menu_principal(View view){
        Intent i = new Intent(this, ventana_principal.class );
        i.putExtra("direccion", info_usuario);
        startActivity(i);

    }
}