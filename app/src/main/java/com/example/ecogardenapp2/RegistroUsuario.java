package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RegistroUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        Button continuar = findViewById(R.id.registroContinuar);
        ImageButton back = findViewById(R.id.btn_back);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent continuarActividad = new Intent(RegistroUsuario.this, Registro2Usuario.class);
                startActivity(continuarActividad);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(RegistroUsuario.this, Login.class);
                startActivity(backActividad);
            }
        });
    }
}