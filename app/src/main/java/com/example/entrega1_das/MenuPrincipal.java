package com.example.entrega1_das;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MenuPrincipal extends AppCompatActivity {

    RecyclerView rv = (RecyclerView) findViewById(R.id.lPelis);

    miBD GestorDB = new miBD (this, "NombreBD", null, 1);
    SQLiteDatabase bd = GestorDB.getWritableDatabase();

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