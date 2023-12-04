package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ecogardenapp2.modelos.Agua;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class RegistroAgua extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_agua);

        Button calcular = findViewById(R.id.calcularAgua);
        Button guardar = findViewById(R.id.RegAg_btn_guardar);
        ImageButton back = findViewById(R.id.btn_back);
        ImageButton home = findViewById(R.id.btn_home);
        EditText cantidad = findViewById(R.id.RegAg_eTxt_litros);
        EditText precio = findViewById(R.id.RegAg_eTxt_valor);
        EditText fecha = findViewById(R.id.RegAg_eTxt_fecha);
        Intent actividadAgua = new Intent(RegistroAgua.this, RegistroAgua.class);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent calcularActividad = new Intent(RegistroAgua.this, EstadisticaAgua.class);
                startActivity(calcularActividad);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(RegistroAgua.this, Categorias.class);
                startActivity(backActividad);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeActividad = new Intent(RegistroAgua.this, ZonaNavegacion.class);
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
                        Toast.makeText(RegistroAgua.this, "La fecha ya existe", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean datosGuardados = guardarDatos(cantidad.getText().toString(), precio.getText().toString(), fecha.getText().toString());
                        if (datosGuardados) {
                            // Se queda en la misma actividad
                            startActivity(actividadAgua);
                        } else {
                            Toast.makeText(RegistroAgua.this, "Error al guardar el archivo",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Algunos campos están vacíos
                    Toast.makeText(RegistroAgua.this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean verificarfecha(String fechaBuscada) {
        File file = new File(getFilesDir(), "agua.txt");
        fechaBuscada = fechaBuscada.toLowerCase();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String mes = linea.split(",")[2]; // Suponiendo que la fecha está en la tercera columna separada por coma (,)
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
        File file = new File(getFilesDir(), "agua.txt");
        fecha = fecha.toLowerCase();
        try {
            // Verificar si el archivo existe
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            // Crear una instancia de Agua y escribir los datos en el archivo
            Agua agua = new Agua(Float.parseFloat(cantidad), Float.parseFloat(precio), fecha);
            String linea = String.format(Locale.getDefault(), "%.2f,%.2f,%s", agua.getCantidad (), agua.getPrecio(), agua.getFecha());
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



