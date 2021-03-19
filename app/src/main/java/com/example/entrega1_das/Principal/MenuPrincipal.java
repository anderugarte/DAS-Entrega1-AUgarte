package com.example.entrega1_das.Principal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.entrega1_das.R;
import com.example.entrega1_das.RegistroInicioSesion.MainActivity;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        RecyclerView rv = (RecyclerView) findViewById(R.id.lPelis);

        int[] imagenes = {R.drawable.dosmildoce,R.drawable.fury,R.drawable.guerramundialz,R.drawable.interestellar,R.drawable.origen,R.drawable.projectx,R.drawable.shutterisland};
        String[] titulos = {"2012","Fury (Corazones de Acero)","Guerra Mundial Z","Interestellar","Origen","Project X","Shutter Island"};

        ElAdaptadorRecycler elAdaptador = new ElAdaptadorRecycler(titulos,imagenes);
        rv.setAdapter(elAdaptador);

        LinearLayoutManager elLayoutLineal= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(elLayoutLineal);

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
                // Aquí haré un dialogo de si está seguro que desea eliminar su cuenta
            }
        });
    }
}