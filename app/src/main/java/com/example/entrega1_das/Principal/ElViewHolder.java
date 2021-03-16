package com.example.entrega1_das.Principal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entrega1_das.R;

public class ElViewHolder extends RecyclerView.ViewHolder {
    public TextView txt;
    public ImageView img;
    public boolean[] seleccion;

    public ElViewHolder (@NonNull View itemView) {
        super(itemView);
        txt=itemView.findViewById(R.id.texto);
        img=itemView.findViewById(R.id.imagen);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seleccion[getAdapterPosition()]==true){ // Se comprueba si el usuario ha seleccionado dicha pelicula
                    seleccion[getAdapterPosition()]=false;
                    // Lo que debería hacer
                } else {
                    seleccion[getAdapterPosition()]=true; // La posicion del elemento seleccionado
                    //
                }
            }
        });
    }
}
