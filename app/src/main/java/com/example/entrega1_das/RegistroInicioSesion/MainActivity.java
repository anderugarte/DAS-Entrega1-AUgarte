package com.example.entrega1_das.RegistroInicioSesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.entrega1_das.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bIS = findViewById(R.id.bIS);
        Button bR = findViewById(R.id.bR);

        Intent i = new Intent (this, Registro.class);
        startActivity(i);
    }
}