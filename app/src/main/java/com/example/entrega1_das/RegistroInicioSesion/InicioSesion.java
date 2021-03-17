package com.example.entrega1_das.RegistroInicioSesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.entrega1_das.Principal.MenuPrincipal;
import com.example.entrega1_das.R;

public class InicioSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        Button bIniS = (Button) findViewById(R.id.bIniSes);
        Button bRegis = (Button) findViewById(R.id.bNTC);

        bIniS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean correcto = false;

                if (correcto) {
                    Intent is = new Intent(getBaseContext(), MenuPrincipal.class);
                    startActivity(is);
                } else {

                }
            }
        });

        bRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent re = new Intent (getBaseContext(), Registro.class);
                startActivity(re);
            }
        });
    }
}