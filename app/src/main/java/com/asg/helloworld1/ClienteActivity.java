package com.asg.helloworld1;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteActivity extends AppCompatActivity {

    private static final String TAG = "ClienteActivity";

    private EditText etNombre;
    private EditText etApellido;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        // Enlazamos los componentes.
        etNombre = findViewById(R.id.editext_nombre);
        etApellido = findViewById(R.id.editext_apellido);
        btnAgregar = findViewById(R.id.botonAgregar);

        // actuador onClick
        btnAgregar.setOnClickListener(v -> {
            // Obtenemos el texto
            String nombre = etNombre.getText().toString().trim();
            String apellido = etApellido.getText().toString().trim();

            // utilizamos isEmpty para que no este vacio
            if (TextUtils.isEmpty(nombre)) {
                etNombre.setError("Este campo es obligatorio");
                return; // Detenemos ejecucion.
            }

            if (TextUtils.isEmpty(apellido)) {
                etApellido.setError("Este campo es obligatorio");
                return; // Detenemos ejecucion.
            }

            // llamamos a nuestra funcion insertar
            insertarCliente(nombre, apellido);

            // limpiamos
            etNombre.setText("");
            etApellido.setText("");
            
        });
    }

    //Creamos la funcion para insertar
    private void insertarCliente(String nombre, String apellido) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection conex = ConexBBDD.getConnection();

                    if (conex != null) {
                        Log.d(TAG, "¡Conexión a la base de datos exitosa!");

                        //Toast.makeText(ClienteActivity.this, "¡Conexión a la base de datos exitosa!", Toast.LENGTH_SHORT).show();
                        //runOnUiThread(() -> Toast.makeText(ClienteActivity.this, "¡Conexión a la base de datos exitosa!", Toast.LENGTH_SHORT).show());

                        // usamos PreparedStatement para seguridad y eficiencia
                        String sqlInsert = "INSERT INTO CLIENTE (nombre, apellido) VALUES (?, ?)";
                        PreparedStatement prepStatement = conex.prepareStatement(sqlInsert);
                        prepStatement.setString(1, nombre);
                        prepStatement.setString(2, apellido);

                        // comprobamos si se ha actualizado
                        int fAfectadas = prepStatement.executeUpdate();
                        Log.d(TAG, "INSERT correcto: " + fAfectadas);
                        runOnUiThread(() -> Toast.makeText(ClienteActivity.this, "Actualizada BBDD", Toast.LENGTH_SHORT).show());

                        // cerramos los recursos
                        prepStatement.close();
                        conex.close();

                    } else {
                        Log.e(TAG, "Fallo al conectar a la base de datos.");
                        //Toast.makeText(ClienteActivity.this, "Fallo al conectar la BBDD", Toast.LENGTH_SHORT).show();


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
