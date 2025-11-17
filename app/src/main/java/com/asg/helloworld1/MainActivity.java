package com.asg.helloworld1;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    MaterialButton ejemplBotton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectarConMySQL();;

    }

    private void connectarConMySQL() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection conex = ConexBBDD1.getConnection();

                    if (conex != null) {
                        Log.d(TAG, "¡Conexión a la base de datos exitosa!");

                        Statement statement = conex.createStatement();

                        //Probamos escritura en bbdd
                        String insertPrueba = "INSERT INTO alumnos (nombre, apellido) VALUES ('Juan', 'Perez')";

                        int celdasAfectadas = statement.executeUpdate(insertPrueba);
                        Log.d(TAG, "INSERT correcto. Filas afectadas: " + celdasAfectadas); // != 1 es error.

                        //Probamos lectura
                        ResultSet resultSet = statement.executeQuery("SELECT nombre, apellido FROM alumnos");

                        if (resultSet.next()) {
                            String nombre = resultSet.getString("nombre");
                            String apellido = resultSet.getString("apellido");
                            Log.d(TAG, "Lectura correcta - Alumno: " + nombre + " " + apellido);
                        } else {
                            Log.d(TAG, "No se encontraron registros en la tabla 'alumnos'.");
                        }

                        //CERRAR RECURSOS
                        resultSet.close();
                        statement.close();
                        conex.close();

                    } else {
                        Log.e(TAG, "Fallo al conectar a la base de datos.");
                    }

                } catch (SQLException e) {
                    Log.e(TAG, "Error de SQL: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
