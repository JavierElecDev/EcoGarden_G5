package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ecogardenapp2.clasesLogReg.ComprobacionesLogReg;

public class Login extends AppCompatActivity {

    private Button Comenzar;
    private TextView OlvidoContrasena, Registro;
    private EditText CorreoElectronico, Contrsena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Comenzar = (Button) findViewById(R.id.log_comenzar);
        OlvidoContrasena = (TextView) findViewById(R.id.log_txtOlvidoContraseña);
        Registro = findViewById(R.id.log_txtRegistro);
        CorreoElectronico = (EditText) findViewById(R.id.log_et_correo);
        Contrsena = (EditText) findViewById(R.id.log_et_contrasena);

        Comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Correo = CorreoElectronico.getText().toString();
                String Pass = Contrsena.getText().toString();
                ComprobacionesLogReg Comprobar = new ComprobacionesLogReg(Login.this);
                Comprobar.ComprobarEntradas(Correo,Pass);

                /*
                Intent intent = new Intent(Login.this, ZonaNavegacion.class);
                startActivity(intent);
                 */

            }
        });


        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registroActivity = new Intent(Login.this, RegistroUsuario.class);
                startActivity(registroActivity);

            }
        });


        OlvidoContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contrasenaActivity = new Intent(Login.this, RecuperarContrasena.class);
                startActivity(contrasenaActivity);

            }
        });

    }
}