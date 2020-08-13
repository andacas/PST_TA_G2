package com.example.amst2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Informacion extends AppCompatActivity {
    private  String info_usuario;
    TextView nombre,apellido,correo,celular,genero,id_persona;
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
        id_persona.setText(arr_text[0]);
        nombre.setText(arr_text[1]);
        apellido.setText(arr_text[2]);
        correo.setText(arr_text[3]);
        celular.setText(arr_text[4]);
        genero.setText(arr_text[5]);
    }
    public void menu_principal(View view){
        Intent i = new Intent(this, ventana_principal.class );
        i.putExtra("direccion", info_usuario);
        startActivity(i);

    }
}