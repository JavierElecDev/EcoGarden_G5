package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FotoPlanta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_planta);

        Button guardarCambios = findViewById(R.id.fotoPlanta);
        ImageButton back = findViewById(R.id.btn_back);
        ImageButton home = findViewById(R.id.btn_home);

        guardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent guardarCambiosActividad = new Intent(FotoPlanta.this, EstadisticaCrecimiento.class);
                startActivity(guardarCambiosActividad);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(FotoPlanta.this, RegistroCrecimiento.class);
                startActivity(backActividad);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeActividad = new Intent(FotoPlanta.this, ZonaNavegacion.class);
                startActivity(homeActividad);
            }
        });
    }
}
