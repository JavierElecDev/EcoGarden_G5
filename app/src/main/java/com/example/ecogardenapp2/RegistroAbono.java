package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ecogardenapp2.modelos.Abono;
import com.example.ecogardenapp2.modelos.Agua;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class RegistroAbono extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_abono);

        Button calcular = findViewById(R.id.calcularAbono);
        Button guardar = findViewById(R.id.RegAb_btn_guardar);
        ImageButton back = findViewById(R.id.btn_back);
        ImageButton home = findViewById(R.id.btn_home);
        EditText cantidad = findViewById(R.id.RegAb_eTxt_kilos);
        EditText precio = findViewById(R.id.RegAb_eTxt_valor);
        EditText fecha = findViewById(R.id.RegAb_eTxt_fecha);
        Intent actividadAbono = new Intent(RegistroAbono.this, RegistroAbono.class);


        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent calcularActividad = new Intent(RegistroAbono.this, EstadisticaAbono.class);
                startActivity(calcularActividad);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(RegistroAbono.this, Categorias.class);
                startActivity(backActividad);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeActividad = new Intent(RegistroAbono.this, ZonaNavegacion.class);
                startActivity(homeActividad);
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cantidad.getText().toString().isEmpty() && !precio.getText().toString().isEmpty() && !fecha.getText().toString().isEmpty()) {
                    String fechaBuscada = fecha.getText().toString();
                    boolean fechaExiste = verificarfecha(fechaBuscada);
                    if (fechaExiste) {
                        Toast.makeText(RegistroAbono.this, "La fecha ya existe", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean datosGuardados = guardarDatos(cantidad.getText().toString(), precio.getText().toString(), fecha.getText().toString());
                        if (datosGuardados) {
                            startActivity(actividadAbono);
                        } else {
                            Toast.makeText(RegistroAbono.this, "Error al guardar el archivo",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(RegistroAbono.this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean verificarfecha(String fechaBuscada) {
        File file = new File(getFilesDir(), "abono.txt");
        fechaBuscada = fechaBuscada.toLowerCase();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String mes = linea.split(",")[2];
                if (mes.equalsIgnoreCase(fechaBuscada)) {
                    bufferedReader.close();
                    return true; // La fecha existe
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // La fecha no existe
    }
    public boolean guardarDatos(String cantidad, String precio, String fecha) {
        File file = new File(getFilesDir(), "abono.txt");
        fecha = fecha.toLowerCase();
        try {
            // Verificar si el archivo existe
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            Abono abono = new Abono(Float.parseFloat(cantidad), Float.parseFloat(precio), fecha);
            String linea = String.format(Locale.getDefault(), "%.2f,%.2f,%s", abono.getCantidad (), abono.getPrecio(), abono.getFecha());
            bufferedWriter.write(linea);
            bufferedWriter.newLine();
            bufferedWriter.close();
            return true; // Los datos se guardaron correctamente
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Error al guardar los datos
    }
}