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

        CorreoElectronico.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    CorreoElectronico.setHint(null);
                }else if(!b && CorreoElectronico.getText().toString().isEmpty()){
                    CorreoElectronico.setHint("Ingresa el Correo Electronico.");
                }
            }
        });

        Contrsena.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    Contrsena.setHint(null);
                }else if(!b && Contrsena.getText().toString().isEmpty()){
                    Contrsena.setHint("Ingresa la contraseña");
                }
            }
        });

        Comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Correo = CorreoElectronico.getText().toString().trim();
                String Pass = Contrsena.getText().toString();
                ComprobacionesLogReg Comprobar = new ComprobacionesLogReg(Login.this);
                Comprobar.ComprobarEntradas(Correo,Pass);
                Comprobar.VerficaCorreo(Correo);
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