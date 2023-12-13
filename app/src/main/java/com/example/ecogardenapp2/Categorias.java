package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Categorias extends AppCompatActivity {

    private ImageView imgEstadistica, imgAgua, imgAbono, imgEnergia, imgCrecimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        imgAbono = (ImageView)findViewById(R.id.cate_imgAbono);
        imgAgua = (ImageView) findViewById(R.id.cate_imgAgua);
        imgCrecimiento = (ImageView)findViewById(R.id.cate_imgCresimiento);
        imgEnergia = (ImageView) findViewById(R.id.cate_imgEnergia);
        imgEstadistica = (ImageView) findViewById(R.id.cate_imgEstadisticas);

        Button abono = findViewById(R.id.cateAbono);
        Button agua = findViewById(R.id.cateAgua);
        Button energia = findViewById(R.id.cateEnergia);
        Button crecimiento = findViewById(R.id.cateCrecimiento);
        Button estadisticas = findViewById(R.id.cateEstadisticas);
        ImageButton back = findViewById(R.id.btn_back);


        imgAbono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent abonoActividad = new Intent(Categorias.this, RegistroAbono.class);
                startActivity(abonoActividad);

            }
        });


        abono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent abonoActividad = new Intent(Categorias.this, RegistroAbono.class);
                startActivity(abonoActividad);

            }
        });

        imgAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent aguaActividad = new Intent(Categorias.this, RegistroAgua.class);
                startActivity(aguaActividad);

            }
        });

        imgEnergia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent energiaActividad = new Intent(Categorias.this, RegistroEnergia.class);
                startActivity(energiaActividad);

            }
        });

        imgCrecimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent crecimientoActividad = new Intent(Categorias.this, RegistroCrecimiento.class);
                startActivity(crecimientoActividad);

            }
        });

        imgEstadistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent estadisticasActividad = new Intent(Categorias.this, EstadisticaAgua.class);
                startActivity(estadisticasActividad);

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
