package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RecuperarContrasena extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        Button restablecer = findViewById(R.id.restablecerContra);
        ImageButton back = findViewById(R.id.btn_back);

        restablecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent restablecerAcitividad = new Intent(RecuperarContrasena.this, Recuperar2Contrasena.class);
                startActivity(restablecerAcitividad);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(RecuperarContrasena.this, Login.class);
                startActivity(backActividad);
            }
        });
    }
}
