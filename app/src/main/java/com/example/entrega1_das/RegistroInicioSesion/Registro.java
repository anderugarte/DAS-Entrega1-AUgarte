package com.example.entrega1_das.RegistroInicioSesion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
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
                boolean correcto = false;

                String n = nombre.getText().toString();
                String a = apellidos.getText().toString();
                String u = username.getText().toString();
                String p = contrasena.getText().toString();
                //Date d = cumple.getText();

                if (correcto){
                    bd.execSQL("INSERT INTO Usuarios (Usuario,Password,Nombre,Apellidos,Cumpleanos) VALUES (u,p,n,a,d)");
                    bd.close();
                } else {
                    int tiempo= Toast.LENGTH_SHORT;
                    Toast aviso = Toast.makeText(getApplicationContext(), "Campos incorrectos", tiempo);
                    aviso.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                    aviso.show();
                }

            }
        });
    }

    private void showDatePickerDialog(final EditText editText) {
        ClaseDialogoFecha dialogoCumpleanos = new ClaseDialogoFecha(editText);
        dialogoCumpleanos.show(getSupportFragmentManager(),"cumple");
    }

}