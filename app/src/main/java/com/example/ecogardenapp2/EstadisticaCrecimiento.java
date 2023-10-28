package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EstadisticaCrecimiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica_crecimiento);

        Button categorias = findViewById(R.id.categorias);
        Button tips = findViewById(R.id.tips);

        categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent categoriasActividad = new Intent(EstadisticaCrecimiento.this, Categorias.class);
                startActivity(categoriasActividad);

            }
        });

        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent tipsActividad = new Intent(EstadisticaCrecimiento.this, TipsHome.class);
                startActivity(tipsActividad);
            }
        });
    }
}
