package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ecogardenapp2.clasesLogReg.DatosRegistro;

public class TerminosCondiciones extends AppCompatActivity {

    private Button Registrarse;
    private CheckBox terminos, condiciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminos_condiciones);
        Registrarse = (Button) findViewById(R.id.reg_btnRegistrarse);
        terminos = (CheckBox) findViewById(R.id.checkTerminos);
        condiciones = (CheckBox) findViewById(R.id.checkCondiciones);

        Intent obtenerDatos = getIntent();
        DatosRegistro datosTemporales = (DatosRegistro) obtenerDatos.getSerializableExtra("datosTemporales");


        ImageButton back = findViewById(R.id.btn_back);
        Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(terminos.isChecked() && condiciones.isChecked()){
                    datosTemporales.enviarDatosDeRegistro(TerminosCondiciones.this);
                }else if(terminos.isChecked()){
                    Toast.makeText(TerminosCondiciones.this, "Debes aceptar las condiciones", Toast.LENGTH_LONG).show();
                }else if(condiciones.isChecked()){
                    Toast.makeText(TerminosCondiciones.this, "Debes aceptar los terminos", Toast.LENGTH_LONG).show();
                }

               /* Intent continuarActividad = new Intent(TerminosCondiciones.this, Registro3Usuario.class);
                startActivity(continuarActividad);*/

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
