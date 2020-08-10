package com.example.amst2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private  String consultaonsulta = "https://tareaautonoma4.000webhostapp.com/ingreso.php";
    private TextView consulta;
    private EditText quary,quary2;
    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        consulta = (TextView) findViewById(R.id.textView);
        quary = (EditText) findViewById(R.id.query);
        quary2 = (EditText) findViewById(R.id.query2);

    }
    public void consulta(View view){
        String[] resultado = null;
        String post = "Select * from Cliente where `Cliente`.`id` = '"+quary.getText().toString()+"' AND `Cliente`.`contrasena` = '"+quary2.getText().toString()+"'";
        try {
            String[] datos = new String[]{
                    "query",
                    consultaonsulta,
                    post
            };
            AsyncQuery async = new AsyncQuery();
            resultado = async.execute(datos).get();

                    Intent i = new Intent(this, MenuPrincipal.class );
                    i.putExtra("direccion", resultado[0]);
                    startActivity(i);



        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}