package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RegistroCrecimiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_crecimiento);

        Button calcular = findViewById(R.id.calcularCrecimiento);
        ImageButton back = findViewById(R.id.btn_back);
        ImageButton home = findViewById(R.id.btn_home);
        ImageButton fotoInicial = findViewById(R.id.btn_fotoI);
        ImageButton fotoFinal = findViewById(R.id.btn_fotoF);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent calcularActividad = new Intent(RegistroCrecimiento.this, EstadisticaCrecimiento.class);
                startActivity(calcularActividad);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(RegistroCrecimiento.this, Categorias.class);
                startActivity(backActividad);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeActividad = new Intent(RegistroCrecimiento.this, ZonaNavegacion.class);
                startActivity(homeActividad);
            }
        });

        fotoInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent fotoInicialActivity = new Intent(RegistroCrecimiento.this, FotoPlanta.class);
                startActivity(fotoInicialActivity);

            }
        });

        fotoFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent fotoFinalActivity = new Intent(RegistroCrecimiento.this, FotoPlanta.class);
                startActivity(fotoFinalActivity);

            }

        });

    }
}

