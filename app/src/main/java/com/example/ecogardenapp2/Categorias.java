package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Categorias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        Button abono = findViewById(R.id.cateAbono);
        Button agua = findViewById(R.id.cateAgua);
        Button energia = findViewById(R.id.cateEnergia);
        Button crecimiento = findViewById(R.id.cateCrecimiento);
        Button estadisticas = findViewById(R.id.cateEstadisticas);
        ImageButton back = findViewById(R.id.btn_back);

        abono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent abonoActividad = new Intent(Categorias.this, RegistroAbono.class);
                startActivity(abonoActividad);

            }
        });

        agua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent aguaActividad = new Intent(Categorias.this, RegistroAgua.class);
                startActivity(aguaActividad);

            }
        });

        energia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent energiaActividad = new Intent(Categorias.this, RegistroEnergia.class);
                startActivity(energiaActividad);

            }
        });

        crecimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent crecimientoActividad = new Intent(Categorias.this, RegistroCrecimiento.class);
                startActivity(crecimientoActividad);

            }
        });

        estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent estadisticasActividad = new Intent(Categorias.this, EstadisticaAgua.class);
                startActivity(estadisticasActividad);

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(Categorias.this, ZonaNavegacion.class);
                startActivity(backActividad);
            }
        });
    }
}
