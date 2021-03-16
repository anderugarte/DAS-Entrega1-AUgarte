package com.example.entrega1_das;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        RecyclerView rv = (RecyclerView) findViewById(R.id.lPelis);

        int[] imagenes = {R.drawable.origen};
        String[] titulos = {"Origen"};

        ElAdaptadorRecycler elAdaptador = new ElAdaptadorRecycler(titulos,imagenes);
        rv.setAdapter(elAdaptador);

        LinearLayoutManager elLayoutLineal= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(elLayoutLineal);
    }

}