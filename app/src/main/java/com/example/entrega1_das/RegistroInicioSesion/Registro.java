package com.example.entrega1_das.RegistroInicioSesion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entrega1_das.DataBase.miBD;
import com.example.entrega1_das.Principal.ClaseDialogoFecha;
import com.example.entrega1_das.Principal.MenuPrincipal;
import com.example.entrega1_das.R;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        EditText nombre = (EditText) findViewById(R.id.tpNombre);
        EditText apellidos = (EditText) findViewById(R.id.tpApellidos);
        EditText username = (EditText) findViewById(R.id.tpUsername);
        EditText contrasena = (EditText) findViewById(R.id.tPassword);
        EditText mostrarC = (EditText) findViewById(R.id.tMostrarCumple);
        Button bRegistro = (Button) findViewById(R.id.bRegistrarse);

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

        bRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase bd = miBD.getInstance(getBaseContext()).getWritableDatabase();

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
                } else if (n.length()>0 & a.length()>0 & u.length()>0 & p.length()>0 & d.length()>0){
                    Log.i("aaaa","Aqui llega");
                    // Ahora compruebo que si los campos introducidos son validos
                    Cursor c = bd.rawQuery("SELECT COUNT(*) FROM Usuarios WHERE Usuario=u",null); // Buscar si ya existe ese nombre de usuario
                    int num = c.getInt(0);
                    c.close();
                    if (num!=0) {
                        int tiempo = Toast.LENGTH_SHORT;
                        Toast aviso = Toast.makeText(getApplicationContext(), "Username ya registrado", tiempo);
                        aviso.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        aviso.show();
                    } else if (p.length()<=4) {
                        int tiempo = Toast.LENGTH_SHORT;
                        Toast aviso = Toast.makeText(getApplicationContext(), "Contraseña demasiado corta", tiempo);
                        aviso.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        aviso.show();
                    } else {
                        // Campos validos, se registra el usuario
                        bd.execSQL("INSERT INTO Usuarios (Usuario,Password,Nombre,Apellidos,Cumpleanos) VALUES (u,p,n,a,d)");
                        bd.close();
                        Intent mp = new Intent(getBaseContext(), MenuPrincipal.class);
                        startActivity(mp);
                    }
                }
            }
        });
    }

    private void showDatePickerDialog(final EditText editText) {
        ClaseDialogoFecha dialogoCumpleanos = new ClaseDialogoFecha(editText);
        dialogoCumpleanos.show(getSupportFragmentManager(),"cumple");
    }

}