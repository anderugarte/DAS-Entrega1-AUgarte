package com.example.entrega1_das;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ElAdaptadorRecycler extends RecyclerView.Adapter<ElViewHolder> {
    private String[] titulos;
    private int[] imagenes;
    private static boolean[] seleccionado; // Array de booleanos para indicar qué elemento se ha seleccionado

    public ElAdaptadorRecycler (String[] titulosPelis, int[] imagenesPelis) {
        titulos=titulosPelis;
        imagenes=imagenesPelis;
        seleccionado=new boolean[titulosPelis.length]; //Inicializar a false (nada elegido) un array de tantas posiciones como elementos se muestran
    }

    @NonNull
    @Override
    public ElViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elLayoutDeCadaItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,null);
        ElViewHolder evh = new ElViewHolder(elLayoutDeCadaItem);
        evh.seleccion=seleccionado; // Pasar el atributo del adaptador al atributo del ViewHolder
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ElViewHolder holder, int position) {
        holder.txt.setText(titulos[position]);
        holder.img.setImageResource(imagenes[position]);
    }

    @Override
    public int getItemCount() {
        return titulos.length;
    }

}
