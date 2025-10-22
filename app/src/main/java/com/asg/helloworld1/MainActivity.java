package com.asg.helloworld1;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectarConMySQL();
    }

    private void connectarConMySQL() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection conex = ConexBBDD.getConnection();

                    if (conex != null) {
                        Log.d(TAG, "¡Conexión a la base de datos exitosa!");

                        Statement statement = conex.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT nombre, apellido FROM alumnos");

                        // Usamos un if para asegurarnos de que hay al menos un resultado
                        if (resultSet.next()) {
                            String nombre = resultSet.getString("nombre");
                            String apellido = resultSet.getString("apellido");
                            Log.d(TAG, "Alumno: " + nombre + " " + apellido);
                        } else {
                            Log.d(TAG, "No se encontraron registros en la tabla 'alumnos'.");
                        }

                        //Cerrar los recursos
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
