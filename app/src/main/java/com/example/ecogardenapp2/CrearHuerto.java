package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecogardenapp2.firebasedatabase.huertosData;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


public class CrearHuerto extends AppCompatActivity {

    private TextView nombreHuerto, descripcionHuerto;
    private Spinner tamanoHuerto, tipoDeHuertos;
    private huertosData registroDatos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_huerto);
        registroDatos = new huertosData();
        tamanoHuerto = (Spinner) findViewById(R.id.creaHue_spinner_tam);
        tipoDeHuertos = (Spinner) findViewById(R.id.creaHue_spinner_tipo);
        nombreHuerto = (TextView) findViewById(R.id.creaHue_et_nombreHue);
        descripcionHuerto = (TextView) findViewById(R.id.creaHue_descripcion);

        tamanoHuerto.setAdapter(registroDatos.adaptadorTamano(CrearHuerto.this));
        tipoDeHuertos.setAdapter(registroDatos.adaptadorHuertos(CrearHuerto.this));

        Button crearHuerto1 = (Button) findViewById(R.id.crearHuerto1);
        ImageButton back = findViewById(R.id.btn_back);


        crearHuerto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] datos = {nombreHuerto.getText().toString().trim(),
                        tamanoHuerto.getSelectedItem().toString(), tipoDeHuertos.getSelectedItem().toString(),
                        descripcionHuerto.getText().toString()};

                registroDatos.crearHuerto(datos, CrearHuerto.this);
                if(registroDatos.isCrecionCompleta()){
                    registroDatos.mensajeExitoCreacion(CrearHuerto.this);
                    tamanoHuerto.setSelection(0);
                    tipoDeHuertos.setSelection(0);
                    nombreHuerto.setText("");
                    descripcionHuerto.setText("");
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(CrearHuerto.this, ZonaNavegacion.class);
                startActivity(backActividad);
            }
        });
    }
}
