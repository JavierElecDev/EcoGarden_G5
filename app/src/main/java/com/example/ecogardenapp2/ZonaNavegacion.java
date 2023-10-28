package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ZonaNavegacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_navegacion);

        Button crearHuerto = findViewById(R.id.crearHuerto);
        Button irHuerto = findViewById(R.id.huertoSeleccionado);
        ImageButton back = findViewById(R.id.btn_back);

        crearHuerto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent crearHuertoActividad = new Intent(ZonaNavegacion.this, CrearHuerto.class);
                startActivity(crearHuertoActividad);
            }
        });


        irHuerto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent irHuertoAcitividad = new Intent(ZonaNavegacion.this, Categorias.class);
                startActivity(irHuertoAcitividad);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(ZonaNavegacion.this, Login.class);
                startActivity(backActividad);
            }
        });
    }
}


