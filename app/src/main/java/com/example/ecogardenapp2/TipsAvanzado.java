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

public class TipsAvanzado extends AppCompatActivity {
    private int [] imageIds = {R.drawable.rotaplantas,R.drawable.compostajeplantas, R.drawable.aprendeplantas};
    private String [] cardTitles = {"Rota \ntus plantas", "Haz \ncompostaje", "Aprende \nde la experiencia"};
    private String [] cardDescriptions = {
            "Si tienes un jardín con varias especies de plantas, considera rotar sus ubicaciones cada temporada. Esto ayuda a prevenir enfermedades.",
            "Considera la posibilidad de compostar restos de cocina y material vegetal para crear tu propio compost, es una fuente excelente de nutrientes.",
            "No tengas miedo de cometer errores. Cada jardín es único, y aprenderás más sobre tus plantas a medida que te vuelvas más experimentado."
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
        setContentView(R.layout.activity_tips_avanzado);

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

                Intent tusLogrosActividad = new Intent(TipsAvanzado.this, CompartirLogros.class);
                startActivity(tusLogrosActividad);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(TipsAvanzado.this, TipsHome.class);
                startActivity(backActividad);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeActividad = new Intent(TipsAvanzado.this, ZonaNavegacion.class);
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