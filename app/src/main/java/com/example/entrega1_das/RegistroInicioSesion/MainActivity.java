package com.example.entrega1_das.RegistroInicioSesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.entrega1_das.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Actividad principal de la aplicacion
        Button bIS = (Button) findViewById(R.id.bIS);
        Button bR = (Button) findViewById(R.id.bR);

        // A traves de este boton se accedera a la interfaz para iniciar sesion
        bIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is = new Intent (getBaseContext(), InicioSesion.class);
                startActivity(is);
            }
        });

        // A traves de este boton se accedera a la interfaz para registrarse
        bR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent (getBaseContext(), Registro.class);
                startActivity(r);
            }
        });

        // No coviene realizar finish() ya que es posible que el usuario desee volver a esta interfaz

    }

    // Gestiona el cambio de idioma
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cI) {
            Configuration configuration = getBaseContext().getResources().getConfiguration();
            String l = configuration.getLocales().toString();
            if (l.equals("[es_ES]")) {
                Locale nuevaloc = new Locale("en");
                Locale.setDefault(nuevaloc);
                configuration.setLocale(nuevaloc);
                configuration.setLayoutDirection(nuevaloc);
                Context context = getBaseContext().createConfigurationContext(configuration);
                getBaseContext().getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
                finish();
                startActivity(getIntent());
            } else {
                Locale nuevaloc = new Locale("es","GB");
                Locale.setDefault(nuevaloc);
                configuration.setLocale(nuevaloc);
                configuration.setLayoutDirection(nuevaloc);
                Context context = getBaseContext().createConfigurationContext(configuration);
                getBaseContext().getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
                finish();
                startActivity(getIntent());
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}