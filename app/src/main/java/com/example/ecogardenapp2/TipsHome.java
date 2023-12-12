package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class TipsHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_home);

        Button principiante = findViewById(R.id.principiante);
        Button intermedio = findViewById(R.id.intermedio);
        Button avanzado = findViewById(R.id.avanzado);
        ImageButton home = findViewById(R.id.btn_home);
        ImageButton back = findViewById(R.id.btn_back);

        principiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent principianteActividad = new Intent(TipsHome.this, TipsPrincipiante.class);
                startActivity(principianteActividad);

            }
        });

        intermedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intermedioActividad = new Intent(TipsHome.this, TipsIntermedio.class);
                startActivity(intermedioActividad);
            }
        });

        avanzado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent avanzadoActividad = new Intent(TipsHome.this, TipsAvanzado.class);
                startActivity(avanzadoActividad);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeActividad = new Intent(TipsHome.this, ZonaNavegacion.class);
                startActivity(homeActividad);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(TipsHome.this, Categorias.class);
                startActivity(backActividad);
            }
        });

    }
}
