package com.asg.helloworld1;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton btn_Cliente = findViewById(R.id.BotonCliente);

        btn_Cliente.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
            startActivity(intent);
        });
    }
}
