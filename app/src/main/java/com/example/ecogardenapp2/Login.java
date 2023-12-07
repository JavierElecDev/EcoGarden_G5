package com.example.ecogardenapp2;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ecogardenapp2.clasesLogReg.ComprobacionesLogReg;
import com.example.ecogardenapp2.clasesLogReg.DatosRegistro;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private Button Comenzar;
    private TextView OlvidoContrasena, Registro;
    private EditText CorreoElectronico, Contrsena;
    FirebaseAuth mAuth;
    String Correo, Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Comenzar = (Button) findViewById(R.id.log_comenzar);
        OlvidoContrasena = (TextView) findViewById(R.id.log_txtOlvidoContraseña);
        Registro = findViewById(R.id.log_txtRegistro);
        CorreoElectronico = (EditText) findViewById(R.id.log_et_correo);
        Contrsena = (EditText) findViewById(R.id.log_et_contrasena);

        mAuth = FirebaseAuth.getInstance();

        ComprobacionesLogReg Comprobar = new ComprobacionesLogReg(Login.this);
        DatosRegistro inicia = new DatosRegistro();

        //aplicamos un evento de escucha para que se muestre el hint si el campo esta viacio
        CorreoElectronico.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    CorreoElectronico.setHint(null);
                } else if (!b && CorreoElectronico.getText().toString().isEmpty()) {
                    CorreoElectronico.setHint("Ingresa el Correo Electronico.");
                }
            }
        });

        Contrsena.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    Contrsena.setHint(null);
                } else if (!b && Contrsena.getText().toString().isEmpty()) {
                    Contrsena.setHint("Ingresa la contraseña");
                }
            }
        });

        Comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Correo = CorreoElectronico.getText().toString().trim();
                Pass = Contrsena.getText().toString();

                boolean comprueba = Comprobar.ComprobarEntradas(Correo, Pass);
                boolean verifica = Comprobar.VerficaCorreo(Correo);

                if (comprueba && verifica) {
                    inicia.iniciarSersion(Correo, Pass,Login.this);
                }
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