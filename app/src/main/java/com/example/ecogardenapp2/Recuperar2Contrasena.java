package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Recuperar2Contrasena extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar2_contrasena);

        Button aceptar = findViewById(R.id.nuevaContrasena);
        ImageButton back = findViewById(R.id.btn_back);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent aceptarAcitividad = new Intent(Recuperar2Contrasena.this, ZonaNavegacion.class);
                startActivity(aceptarAcitividad);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(Recuperar2Contrasena.this, RecuperarContrasena.class);
                startActivity(backActividad);
            }
        });
    }
}
