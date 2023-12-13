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

public class TipsPrincipiante extends AppCompatActivity {
    private int [] imageIds = {R.drawable.regarplantas,R.drawable.abonoplantas, R.drawable.podarplantas};
    private String [] cardTitles = {"Riega \ntus plantas", "Abona \ntus plantas", "Poda \ntus plantas"};
    private String [] cardDescriptions = {
            "Recuerda que es muy importante proporcionar el riego adecuado a tus plantas. Procura usar agua libre de químicos",
            "Es muy importante que abones tus plantas, ése es su alimento. Ten presente disminuir o evitar el uso de abonos químicos.",
            "Recuerda siempre estar podando tus plantas, especialmente las partes más secas de ellas"
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
        setContentView(R.layout.activity_tips_principiante);

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

                Intent tusLogrosActividad = new Intent(TipsPrincipiante.this, CompartirLogros.class);
                startActivity(tusLogrosActividad);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(TipsPrincipiante.this, TipsHome.class);
                startActivity(backActividad);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeActividad = new Intent(TipsPrincipiante.this, ZonaNavegacion.class);
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