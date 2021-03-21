package com.example.entrega1_das.RegistroInicioSesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.entrega1_das.DataBase.miBD;
import com.example.entrega1_das.Principal.ClaseDialogoFecha;
import com.example.entrega1_das.Principal.MenuPrincipal;
import com.example.entrega1_das.R;

import java.util.Locale;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // A traves de esta clase se gestionara el registro de un nuevo usuario
        EditText nombre = (EditText) findViewById(R.id.tpNombre);
        EditText apellidos = (EditText) findViewById(R.id.tpApellidos);
        EditText username = (EditText) findViewById(R.id.tpUsername);
        EditText contrasena = (EditText) findViewById(R.id.tPassword);
        EditText mostrarC = (EditText) findViewById(R.id.tMostrarCumple);
        Button bRegistro = (Button) findViewById(R.id.bRegistrarse);

        // Al pulsar este EditText, desplegaremos un dialogo donde se podra seleccionar la fecha de nacimientop del usuario
        mostrarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tMostrarCumple:
                        showDatePickerDialog(mostrarC);
                        break;
                }
            }
        });

        // Al pulsar este boton, se gestionara el registro del usuario
        bRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Obtenemos los campos introducidos por el usuario
                String n = nombre.getText().toString();
                String a = apellidos.getText().toString();
                String u = username.getText().toString();
                String p = contrasena.getText().toString();
                String d = mostrarC.getText().toString();

                // Compruebo si algun campo esta vacio
                if (n.length()==0 || a.length()==0 || u.length()==0 || p.length()==0 || d.length()==0) {
                    int tiempo= Toast.LENGTH_SHORT;
                    Toast aviso = Toast.makeText(getApplicationContext(), "Existen campos vacíos", tiempo);
                    aviso.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                    aviso.show();
                } else if (n.length()>0 && a.length()>0 && u.length()>0 && p.length()>0 && d.length()>0){
                    // Ahora compruebo si los campos introducidos son validos
                    SQLiteDatabase bd = miBD.getInstance(getBaseContext()).getWritableDatabase();
                    String[] campos = new String[] {"Usuario"};
                    String[] argumentos = new String[] {u};
                    Cursor c = bd.query("Usuarios",campos,"Usuario=?",argumentos,null,null,null); // Buscar si ya existe ese nombre de usuario
                    int num=0; // Se utilizara para comprobar si ya existe un usuario registrado con ese username
                    while (c.moveToNext()) {
                        String usu = c.getString(0);
                        num++;
                    }
                    c.close();
                    if (num!=0) { // Si obtenemos != 0, significa que ya existe un usuario registrado con ese username
                        int tiempo = Toast.LENGTH_SHORT;
                        Toast aviso = Toast.makeText(getApplicationContext(), "Username ya registrado", tiempo);
                        aviso.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        aviso.show();
                    } else if (p.length()<=4) { // La contrasena debe de tener mas de 4 caracteres
                        int tiempo = Toast.LENGTH_SHORT;
                        Toast aviso = Toast.makeText(getApplicationContext(), "Contraseña demasiado corta", tiempo);
                        aviso.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        aviso.show();
                    } else {
                        // Campos validos, se registra el usuario
                        ContentValues nuevo = new ContentValues();
                        nuevo.put("Usuario", u);
                        nuevo.put("Password", p);
                        nuevo.put("Nombre", n);
                        nuevo.put("Apellidos", a);
                        nuevo.put("Cumpleanos", d);
                        bd.insert("Usuarios", null, nuevo);
                        bd.close();
                        Intent mp = new Intent(getBaseContext(), MenuPrincipal.class);
                        mp.putExtra("username",u);
                        startActivity(mp);
                        finish();
                    }
                }
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