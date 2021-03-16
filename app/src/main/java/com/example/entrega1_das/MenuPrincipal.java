package com.example.entrega1_das;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MenuPrincipal extends AppCompatActivity {

    RecyclerView rv = (RecyclerView) findViewById(R.id.lPelis);

    int[] imagenes= { };
    String[] titulos={ };
    ElAdaptadorRecycler eladaptador = new ElAdaptadorRecycler(imagenes,titulos);
    rv.setAdapter(eladaptador);

    LinearLayoutManager elLayoutLineal= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
    rv.setLayoutManager(elLayoutLineal);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }


}