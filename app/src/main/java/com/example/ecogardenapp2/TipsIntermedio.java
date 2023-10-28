package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TipsIntermedio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_intermedio);

        Button tusLogros = findViewById(R.id.logros);
        ImageButton back = findViewById(R.id.btn_back);

        tusLogros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent tusLogrosActividad = new Intent(TipsIntermedio.this, CompartirLogros.class);
                startActivity(tusLogrosActividad);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(TipsIntermedio.this, TipsHome.class);
                startActivity(backActividad);
            }
        });

    }
}
