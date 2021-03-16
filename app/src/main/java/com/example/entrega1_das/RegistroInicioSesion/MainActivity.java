package com.example.entrega1_das.RegistroInicioSesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.entrega1_das.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bIS = (Button) findViewById(R.id.bIS);
        Button bR = (Button) findViewById(R.id.bR);

        bIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is = new Intent (getBaseContext(), InicioSesion.class);
                startActivity(is);
            }
        });

        bR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent (getBaseContext(), Registro.class);
                startActivity(r);
            }
        });
    }
}