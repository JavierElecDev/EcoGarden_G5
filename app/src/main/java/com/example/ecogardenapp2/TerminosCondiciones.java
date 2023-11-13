package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TerminosCondiciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminos_condiciones);

        Button continuar = findViewById(R.id.reg_btnRegistrarse);
        ImageButton back = findViewById(R.id.btn_back);continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent continuarActividad = new Intent(TerminosCondiciones.this, Registro3Usuario.class);
                startActivity(continuarActividad);

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(TerminosCondiciones.this, RegistroUsuario.class);
                startActivity(backActividad);
            }
        });
    }
}
