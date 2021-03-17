package com.example.entrega1_das.RegistroInicioSesion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.entrega1_das.DataBase.miBD;
import com.example.entrega1_das.Principal.ClaseDialogoFecha;
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
        Button bCumple = (Button) findViewById(R.id.bCumpleanos);
        Button bRegistro = (Button) findViewById(R.id.bRegistrarse);

        bCumple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogoCumple = new ClaseDialogoFecha();
                dialogoCumple.show(getSupportFragmentManager(),"cumple");
            }
        });

        bRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase bd = miBD.getInstance(getBaseContext()).getWritableDatabase();

                String n = nombre.getText().toString();
                String a = apellidos.getText().toString();
                String u = username.getText().toString();
                String p = contrasena.getText().toString();
                //Date d = cumple.getText();

                bd.execSQL("INSERT INTO Usuarios (Usuario,Password,Nombre,Apellidos,Cumpleanos) VALUES (u,p,n,a,d)");
                bd.close();

            }
        });
    }
}