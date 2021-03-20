package com.example.entrega1_das.Principal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        EditText tituloP = findViewById(R.id.etNotifi);

        Button bAnadirNotifi = (Button) findViewById(R.id.bAN);
        bAnadirNotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tituloPelicula = tituloP.getText().toString();
                if (tituloPelicula.length()==0) {
                    int tiempo= Toast.LENGTH_SHORT;
                    Toast aviso = Toast.makeText(getApplicationContext(), "Título de película vacío", tiempo);
                    aviso.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                    aviso.show();
                } else if (tituloPelicula.length()>0) {
                    guardarNotificacion(tituloPelicula);
                }
            }
        });

    }

    private void guardarNotificacion(String tituloPelicula) {
        // Notificacion local
        // A traves de esta opcion, el usuario sera capaz de crear una notificacion para poder recordar que pelicula desea ver
        // Resulta util si el usuario utiliza la aplicacion para consultar que pelicula puede ver en un par de horas, a la noche...
        NotificationCompat.Builder elBuilder = new NotificationCompat.Builder(getBaseContext(), "01");
        elBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logofilmometry))
                .setSmallIcon(android.R.drawable.ic_popup_reminder)
                .setContentTitle("Recordatorio para ver:")
                .setContentText(tituloPelicula)
                .setColor(Color.RED)
                .setVibrate(new long[]{0, 1000, 500, 1000})
                .setAutoCancel(true);

        NotificationManager elManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel elCanal = new NotificationChannel("01", "CanalNotifis01", NotificationManager.IMPORTANCE_DEFAULT);
            elManager.createNotificationChannel(elCanal);
            elCanal.setDescription("Canal encargado de las notificaciones de peliculas por ver");
            elCanal.enableLights(true);
            elCanal.setLightColor(Color.RED);
            elCanal.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            elCanal.enableVibration(true);
        }
        elManager.notify(1, elBuilder.build());
    }
}