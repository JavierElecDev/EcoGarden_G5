package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Registro2Usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2_usuario);

        Button continuar = findViewById(R.id.regContinuar);
        ImageButton back = findViewById(R.id.btn_back);continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent continuarActividad = new Intent(Registro2Usuario.this, Registro3Usuario.class);
                startActivity(continuarActividad);

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(Registro2Usuario.this, RegistroUsuario.class);
                startActivity(backActividad);
            }
        });
    }
}
