package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    Button ir_ventana_zonavegacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ir_ventana_zonavegacion = (Button) findViewById(R.id.log_comenzar);

        ir_ventana_zonavegacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ZonaNavegacion.class);
                startActivity(intent);
            }
        });

        TextView registro = findViewById(R.id.log_txtRegistro);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registroActivity = new Intent(Login.this, RegistroUsuario.class);
                startActivity(registroActivity);

            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView contrasena = findViewById(R.id.log_txtOlvidoContraseña);
        contrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contrasenaActivity = new Intent(Login.this, RecuperarContrasena.class);
                startActivity(contrasenaActivity);

            }
        });

    }
}