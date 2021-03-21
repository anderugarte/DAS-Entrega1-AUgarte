package com.example.entrega1_das.Principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.entrega1_das.DataBase.miBD;
import com.example.entrega1_das.R;

import java.util.Locale;

public class Modificar_Datos_Personales extends AppCompatActivity {

    String usernameUsuarioBD;
    String nom;
    String ape;
    String pas;
    String cum;
    String usu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar__datos__personales);

        // A traves de esta clase se gestionara la modificacion de los datos personales de un usuario
        EditText nombreBD = (EditText) findViewById(R.id.tpNombreBD);
        EditText apellidosBD = (EditText) findViewById(R.id.tpApellidosBD);
        TextView usernameBD = (TextView) findViewById(R.id.tpUsername2);
        EditText contrasenaBD = (EditText) findViewById(R.id.tpPasswordBD);
        EditText mostrarCBD = (EditText) findViewById(R.id.tpMostrarCumpleBD);
        Button bRegistroBD = (Button) findViewById(R.id.bMDatosBD);
        Button bCancelarBD = (Button) findViewById(R.id.bCancelarBD);

        // Al pulsar este EditText, desplegaremos un dialogo donde se podra seleccionar la fecha de nacimientop del usuario
        mostrarCBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tpMostrarCumpleBD:
                        showDatePickerDialog(mostrarCBD);
                        break;
                }
            }
        });

        // Recibimos el nombre de usuario del usuario que desea modificar sus datos personales
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            usernameUsuarioBD = extras.getString("username");
        }

        SQLiteDatabase bd = miBD.getInstance(getBaseContext()).getWritableDatabase();

        // Recogemos los datos del usuario
        String[] campos1 = new String[] {"Usuario","Password","Nombre","Apellidos","Cumpleanos"};
        String[] argumentos1 = new String[] {usernameUsuarioBD};
        Cursor c = bd.query("Usuarios",campos1,"Usuario=?",argumentos1,null,null,null); // Buscar el usuario registrado con ese username en la BD
        // Recogemos de la BD la informacion del usuario
        while (c.moveToNext()) {
            usu = c.getString(0);
            pas = c.getString(1);
            nom = c.getString(2);
            ape = c.getString(3);
            cum = c.getString(4);
        }
        c.close();
        bd.close();

        // Escribimos los datos del usuario
        nombreBD.setText(nom);
        apellidosBD.setText(ape);
        usernameBD.setText(usernameUsuarioBD);
        contrasenaBD.setText(pas);
        mostrarCBD.setText(cum);

        bRegistroBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Modificación de los datos introducidos por el usuario
                // El username no se puede modificar
                SQLiteDatabase bdd = miBD.getInstance(getBaseContext()).getWritableDatabase();
                ContentValues nuevo = new ContentValues();
                nuevo.put("Usuario", usernameUsuarioBD);
                nuevo.put("Password", contrasenaBD.getText().toString());
                nuevo.put("Nombre", nombreBD.getText().toString());
                nuevo.put("Apellidos", apellidosBD.getText().toString());
                nuevo.put("Cumpleanos", mostrarCBD.getText().toString());
                String cond = "Usuario='"+usernameUsuarioBD+"'";
                bdd.update("Usuarios",nuevo,cond,null);
                bdd.close();
                Intent mp = new Intent(getBaseContext(), MenuPrincipal.class);
                mp.putExtra("username",usernameUsuarioBD);
                startActivity(mp);
                finish();
            }
        });

        bCancelarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mp = new Intent(getBaseContext(), MenuPrincipal.class);
                mp.putExtra("username",usernameUsuarioBD);
                startActivity(mp);
                finish();
            }
        });

    }

    // Este metodo nos ayuda a desplegar el dialogo para la seleccion de la fecha de nacimiento y nos permite enviar
    // el EditText por parametro para una vez obtenida la fecha poder realizar un .setText("fecha") en el
    private void showDatePickerDialog(final EditText editText) {
        ClaseDialogoFecha dialogoCumpleanos = new ClaseDialogoFecha(editText);
        dialogoCumpleanos.show(getSupportFragmentManager(),"cumple");
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