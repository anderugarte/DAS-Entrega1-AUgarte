package com.example.entrega1_das.Principal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.entrega1_das.DataBase.miBD;
import com.example.entrega1_das.R;
import com.example.entrega1_das.RegistroInicioSesion.MainActivity;

public class MenuPrincipal extends AppCompatActivity {

    String usernameUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        RecyclerView rv = (RecyclerView) findViewById(R.id.lPelis);

        // Se asignan las imagenes y titulos de las peliculas del RecyclerView
        int[] imagenes = {R.drawable.dosmildoce,R.drawable.fury,R.drawable.guerramundialz,R.drawable.interestellar,R.drawable.origen,R.drawable.projectx,R.drawable.shutterisland};
        String[] titulos = {"2012","Fury (Corazones de Acero)","Guerra Mundial Z","Interestellar","Origen","Project X","Shutter Island"};

        ElAdaptadorRecycler elAdaptador = new ElAdaptadorRecycler(titulos,imagenes);
        rv.setAdapter(elAdaptador);

        LinearLayoutManager elLayoutLineal= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(elLayoutLineal);

        // Recibimos el nombre de usuario del usuario que ha iniciado sesion o se ha registrado
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            usernameUsuario = extras.getString("username");
        }

        // Cerrar sesion
        Button bCerrar = (Button) findViewById(R.id.bCS);
        bCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cs = new Intent(getBaseContext(), MainActivity.class);
                startActivity(cs);
            }
        });

        // Eliminar cuenta
        Button bElimC = (Button) findViewById(R.id.bEC);
        bElimC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Se genera un dialogo para preguntar si el usuario esta seguro de eliminar su cuenta
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipal.this);
                builder.setTitle("Eliminar Cuenta");
                builder.setMessage("¿Está seguro de eliminar su cuenta?");

                builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Eliminar cuenta
                        SQLiteDatabase bd = miBD.getInstance(getBaseContext()).getWritableDatabase();
                        String condition = "Usuario='"+usernameUsuario+"'";
                        bd.delete("Usuarios",condition,null);
                        bd.close();
                        dialogInterface.cancel();
                        Intent ec = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(ec);
                        finish();
                    }
                });
                builder.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });
    }
}