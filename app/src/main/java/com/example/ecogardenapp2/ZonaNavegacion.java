package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.ecogardenapp2.firebasedatabase.HuertosDelUsuario;

import java.util.List;

public class ZonaNavegacion extends AppCompatActivity {

    private Button crearHuerto, irHuerto;
    private ImageView imdAddHuerto;
    private Spinner seleccionHuerto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_navegacion);

        crearHuerto = (Button) findViewById(R.id.zonaNav_crearHuerto);
        irHuerto = (Button) findViewById(R.id.huertoSeleccionado);
        imdAddHuerto = (ImageView) findViewById(R.id.zonaNav_imgAdd);
        seleccionHuerto = (Spinner)findViewById(R.id.zonaNav_spinner_slecHue);
        ImageButton back = findViewById(R.id.btn_back);
        HuertosDelUsuario llamadaHuertos = new HuertosDelUsuario();
        llamadaHuertos.llamarHuertos(ZonaNavegacion.this);
        seleccionHuerto.setAdapter(llamadaHuertos.nombreDeLosHuertos(ZonaNavegacion.this));


        crearHuerto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent crearHuertoActividad = new Intent(ZonaNavegacion.this, CrearHuerto.class);
                startActivity(crearHuertoActividad);
            }
        });

        imdAddHuerto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent crearHuertoActividad = new Intent(ZonaNavegacion.this, CrearHuerto.class);
                startActivity(crearHuertoActividad);
            }
        });


        irHuerto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irHuertoAcitividad = new Intent(ZonaNavegacion.this, Categorias.class);
                startActivity(irHuertoAcitividad);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(ZonaNavegacion.this, Login.class);
                startActivity(backActividad);
            }
        });
    }
}


