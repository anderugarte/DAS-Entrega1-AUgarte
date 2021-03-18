package com.example.entrega1_das.RegistroInicioSesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.entrega1_das.Principal.MenuPrincipal;
import com.example.entrega1_das.R;

public class InicioSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        EditText tUsername = (EditText) findViewById(R.id.tpNombreUsu);
        EditText tPassword = (EditText) findViewById(R.id.tpPass);
        Button bIniS = (Button) findViewById(R.id.bIniSes);
        Button bRegis = (Button) findViewById(R.id.bNTC);

        bIniS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean correcto = false;
                String user = tUsername.getText().toString();
                String pass = tPassword.getText().toString();


                if (correcto) {
                    Intent is = new Intent(getBaseContext(), MenuPrincipal.class);
                    startActivity(is);
                } else {
                    int tiempo= Toast.LENGTH_SHORT;
                    Toast aviso = Toast.makeText(getApplicationContext(), "Campos Incorrectos", tiempo);
                    aviso.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                    aviso.show();
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