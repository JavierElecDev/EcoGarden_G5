package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Registro3Usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro3_usuario);

        Button registrarse = findViewById(R.id.aceptoTerminos);
        ImageButton back = findViewById(R.id.btn_back);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registrarseActividad = new Intent(Registro3Usuario.this, ZonaNavegacion.class);
                startActivity(registrarseActividad);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(Registro3Usuario.this, TerminosCondiciones.class);
                startActivity(backActividad);
            }
        });
    }
}
