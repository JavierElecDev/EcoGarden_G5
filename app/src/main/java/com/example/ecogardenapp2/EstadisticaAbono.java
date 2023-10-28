package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EstadisticaAbono extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica_abono);

        Button categorias = findViewById(R.id.categorias);
        Button energia = findViewById(R.id.energia);

        categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent categoriasActividad = new Intent(EstadisticaAbono.this, Categorias.class);
                startActivity(categoriasActividad);

            }
        });

        energia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent energiaActividad = new Intent(EstadisticaAbono.this, EstadisticaEnergia.class);
                startActivity(energiaActividad);
            }
        });
    }
}
