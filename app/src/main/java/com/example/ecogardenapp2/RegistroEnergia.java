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
import com.example.ecogardenapp2.modelos.Energia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class RegistroEnergia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_energia);

        Button calcular = findViewById(R.id.calcularEnergia);
        Button guardar = findViewById(R.id.RegEn_btn_guardar);
        ImageButton back = findViewById(R.id.btn_back);
        ImageButton home = findViewById(R.id.btn_home);
        EditText kilovatios = findViewById(R.id.RegEn_eTxt_Kw);
        EditText horas = findViewById(R.id.RegEn_eTxt_horas);
        EditText precio = findViewById(R.id.RegEn_eTxt_valor);
        EditText fecha = findViewById(R.id.RegEn_eTxt_fecha);
        Intent actividadEnergia = new Intent(RegistroEnergia.this, RegistroEnergia.class);



        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent calcularActividad = new Intent(RegistroEnergia.this, EstadisticaEnergia.class);
                startActivity(calcularActividad);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(RegistroEnergia.this, Categorias.class);
                startActivity(backActividad);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeActividad = new Intent(RegistroEnergia.this, ZonaNavegacion.class);
                startActivity(homeActividad);
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!kilovatios.getText().toString().isEmpty() && !horas.getText().toString().isEmpty() && !precio.getText().toString().isEmpty() && !fecha.getText().toString().isEmpty()) {
                    String fechaBuscada = fecha.getText().toString();
                    boolean fechaExiste = verificarfecha(fechaBuscada);
                    if (fechaExiste) {
                        Toast.makeText(RegistroEnergia.this, "La fecha ya existe", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean datosGuardados = guardarDatos(kilovatios.getText().toString(), horas.getText().toString(), precio.getText().toString(), fecha.getText().toString());
                        if (datosGuardados) {
                            startActivity(actividadEnergia);
                        } else {
                            Toast.makeText(RegistroEnergia.this, "Error al guardar el archivo",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // si algunos campos están vacíos
                    Toast.makeText(RegistroEnergia.this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean verificarfecha(String fechaBuscada) {
        File file = new File(getFilesDir(), "energia.txt");
        fechaBuscada = fechaBuscada.toLowerCase();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String mes = linea.split(",")[3]; // Suponiendo que la fecha está en la cuarta columna separada por coma (,)
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
    public boolean guardarDatos(String kilovatios, String horas, String precio, String fecha) {
        File file = new File(getFilesDir(), "energia.txt");
        fecha = fecha.toLowerCase();
        try {
            // Verificar si el archivo existe
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            // Crear una instancia de Energia y escribir los datos en el archivo
            Energia energia = new Energia(Float.parseFloat(kilovatios), Float.parseFloat(horas), Float.parseFloat(precio), fecha);
            String linea = String.format(Locale.getDefault(), "%.2f,%.2f,%.2f,%s", energia.getKilovatios (), energia.getHoras (), energia.getPrecio(), energia.getFecha());
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
