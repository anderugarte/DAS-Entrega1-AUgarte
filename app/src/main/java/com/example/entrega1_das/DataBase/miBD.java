package com.example.entrega1_das.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class miBD extends SQLiteOpenHelper {

    private static miBD mInstance = null;

    private static final String DATABASE_NAME = "FilmometryDB";
    private static final int DATABASE_VERSION = 1;

    private Context mContext;

    public static miBD getInstance(@Nullable Context ctx) {
        if (mInstance == null) {mInstance = new miBD(ctx.getApplicationContext());}
        return mInstance;
    }

    private miBD (Context ctx) {
        super(ctx,DATABASE_NAME,null,DATABASE_VERSION);
        mContext=ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Crear la tabla de Usuarios
        sqLiteDatabase.execSQL("CREATE TABLE Usuarios ('Usuario' PRIMARY KEY " + "NOT NULL, 'Password' PASSWORD(255), 'Nombre' VARCHAR(255), 'Apellidos' VARCHAR(255)," + "'Cumpleanos' VARCHAR(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
