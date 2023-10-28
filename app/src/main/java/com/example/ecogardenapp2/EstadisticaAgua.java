package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EstadisticaAgua extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica_agua);

        Button categorias = findViewById(R.id.categorias);
        Button abono = findViewById(R.id.abono);

        categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent categoriasActividad = new Intent(EstadisticaAgua.this, Categorias.class);
                startActivity(categoriasActividad);

            }
        });

        abono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent abonoActividad = new Intent(EstadisticaAgua.this, EstadisticaAbono.class);
                startActivity(abonoActividad);
            }
        });
    }
}
