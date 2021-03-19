package com.example.entrega1_das.RegistroInicioSesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.entrega1_das.DataBase.miBD;
import com.example.entrega1_das.Principal.MenuPrincipal;
import com.example.entrega1_das.R;

public class InicioSesion extends AppCompatActivity {

    protected static int usuario;
    protected int contrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        EditText tUsername = (EditText) findViewById(R.id.tpNombreUsu);
        EditText tPassword = (EditText) findViewById(R.id.tpPass);
        Button bIniS = (Button) findViewById(R.id.bIniSes);
        Button bRegis = (Button) findViewById(R.id.bNTC);

        bIniS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Se recogen los campos rellenados por el usuario
                String user = tUsername.getText().toString();
                String pass = tPassword.getText().toString();

                // Primero, se comprueba si existen campos vacios
                if (user.length()==0 || pass.length()==0) {
                    if (user.length()==0) { // Campo username vacio
                        int tiempo= Toast.LENGTH_SHORT;
                        Toast aviso = Toast.makeText(getApplicationContext(), "Nombre de usuario vacío", tiempo);
                        aviso.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                        aviso.show();
                    } else { // Campos contrasena vacia
                        int tiempo= Toast.LENGTH_SHORT;
                        Toast aviso = Toast.makeText(getApplicationContext(), "Contraseña vacía", tiempo);
                        aviso.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                        aviso.show();
                    }
                } else if (user.length()>0 && pass.length()>0) { // No existe campos vacios, se comprobara si los campos introducidos por el usuario son correctos

                    // Con el fin de devolver informacion mas concreta al usuario, se especificara cual de los campos introducidos por el usuario es incorrecto
                    // Es posible que el usuario existente pero que la contraseña este mal --> Se le indicara
                    // Por otro lado, es posible que el propio username no exista --> Se le indicara
                    // No tiene sentido comprobar si existe la contraseña si no existe ningun usuario con el username introducido por el usuario

                    // Soy consciente que desde el punto de vista de la Seguridad, el programa resulta mas inseguro si le confirmamos al usuario que existe dicho username
                    // Sin embargo, considero para este trabajo mas util para las pruebas por ejemplo saber si el username introducido existe o no
                    SQLiteDatabase bd = miBD.getInstance(getBaseContext()).getWritableDatabase();
                    boolean existeU = false; // Se empleara para comprobar si existe el username
                    boolean existeUyP = false; // Se empleara para comprobar si, una vez que sabemos que existe el username, la contraseña es correcta o no

                    // Se comprueba si existe algun usuario con ese username
                    String[] campos1 = new String[] {"Usuario"};
                    String[] argumentos1 = new String[] {user};
                    Cursor c = bd.query("Usuarios",campos1,"Usuario=?",argumentos1,null,null,null); // Buscar si existe un usuario registrado con ese username en la BD
                    int cod1=0; // Se utilizara para comprobar si ya existe un usuario registrado con ese username
                    while (c.moveToNext()) {
                        String usu = c.getString(0);
                        cod1++;
                        existeU = true; // Ha encontrado un usuario, se establece el booleano a TRUE
                    }
                    c.close();

                    // Si se ha encontrado el username, comprobamos si la contraseña es correcta o no
                    if (existeU) {
                        String[] campos2 = new String[] {"Password"};
                        String[] argumentos2 = new String[] {pass};
                        // Falta anadir el username !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        Cursor cu = bd.query("Usuarios",campos2,"Password=?",argumentos2,null,null,null); // Buscar si existe un usuario registrado con ese username y contrasena en la BD
                        int cod2=0; // Se utilizara para comprobar si ya existe un usuario registrado con ese username y contrasena
                        while (cu.moveToNext()) {
                            String pas = cu.getString(0);
                            cod2++;
                            existeUyP = true; // La contrasena es correcta, se establece el booleano a TRUE
                        }
                        cu.close();
                    }

                    // Se crealiza la comprobacion final en base a los valores de los booleanos
                    if (existeUyP) { // Si existeUyP=TRUE, se ha encontrado un usuario con dichas credenciales y se inicia sesion
                        Intent is = new Intent(getBaseContext(), MenuPrincipal.class);
                        startActivity(is);
                    } else if (existeU) { // Se ha encontrado un usuario con dicho username pero la contraseña es incorrecta
                        int tiempo= Toast.LENGTH_SHORT;
                        Toast aviso = Toast.makeText(getApplicationContext(), "Contraseña Incorrecta", tiempo);
                        aviso.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                        aviso.show();
                    } else { // No existe ningun usuario con dicho username
                        int tiempo= Toast.LENGTH_SHORT;
                        Toast aviso = Toast.makeText(getApplicationContext(), "No existe ningún usuario con ese username", tiempo);
                        aviso.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                        aviso.show();
                    }
                }
            }
        });

        bRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent re = new Intent (getBaseContext(), Registro.class);
                startActivity(re);
            }
        });
    }
}