package com.example.amst2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ventana_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_principal);
        TextView textView = (TextView) findViewById(R.id.textView4);
        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("direccion");
        // ArrayList<String> lista = new ArrayList();
        //String[] arr_tex = text.split("\n");
        textView.setText(text);

    }
}