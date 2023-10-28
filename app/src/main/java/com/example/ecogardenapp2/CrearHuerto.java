package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CrearHuerto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_huerto);

        Button crearHuerto1 = (Button) findViewById(R.id.crearHuerto1);
        ImageButton back = findViewById(R.id.btn_back);


        crearHuerto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crearHuerto1Activity = new Intent(CrearHuerto.this, Categorias.class);
                startActivity(crearHuerto1Activity);


                                    }
                });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent backActividad = new Intent(CrearHuerto.this, ZonaNavegacion.class);
                        startActivity(backActividad);
                    }
                });
            }
        }
