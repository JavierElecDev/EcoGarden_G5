package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class TipsIntermedio extends AppCompatActivity {

    private int [] imageIds = {R.drawable.conoceplantas,R.drawable.ubicaplantas, R.drawable.plagasplantas};
    private String [] cardTitles = {"Conoce tus plantas", "Ubica bien tus plantas", "Cuidalas de plagas"};
    private String [] cardDescriptions = {
            "Investiga las necesidades específicas de cada tipo de planta que tienes en tu jardín. Esto incluye requisitos de luz, agua, suelo y temperatura.",
            "Coloca las plantas en áreas que se ajusten a sus necesidades de luz, porque algunas prefieren luz solar directa y otras lugares sombreados.",
            "Recuerda siempre estar atento a signos de plagas y enfermedades y tratar cualquier problema tan pronto como lo identifiques."
    };
    private int currentIndex = 0;
    private ImageView imageView;
    private MaterialTextView cardTitle;
    private MaterialTextView cardDescription;
    private MaterialButton botonNext;
    private MaterialButton botonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_intermedio);

        Button tusLogros = findViewById(R.id.logros);
        ImageButton back = findViewById(R.id.btn_back);
        ImageButton home = findViewById(R.id.btn_home);

        imageView = findViewById(R.id.image_card);
        cardTitle = findViewById(R.id.card_title);
        cardDescription = findViewById(R.id.card_SUBtitle);
        botonNext = findViewById(R.id.btn_siguiente);
        botonBack = findViewById(R.id.btn_atras);

        setterInitialValues();

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

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeActividad = new Intent(TipsIntermedio.this, ZonaNavegacion.class);
                startActivity(homeActividad);
            }
        });

        botonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionesBotonSiguiente();
            }
        });
        botonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionesBotonAtras();
            }
        });
    }

    private void accionesBotonAtras() {
        currentIndex--;
        if(currentIndex < 0)
        {
            currentIndex = imageIds.length - 1;
        }
        setterInitialValues();
    }

    protected void accionesBotonSiguiente() {
        currentIndex++;
        if(currentIndex >= imageIds.length)
        {
            currentIndex = 0;
        }
        setterInitialValues();
    }

    protected void setterInitialValues() {
        imageView.setImageResource(imageIds[currentIndex]);
        cardTitle.setText(cardTitles[currentIndex]);
        cardDescription.setText(cardDescriptions[currentIndex]);
    }
}