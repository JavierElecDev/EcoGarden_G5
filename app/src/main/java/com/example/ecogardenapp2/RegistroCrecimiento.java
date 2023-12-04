package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecogardenapp2.modelos.Agua;
import com.example.ecogardenapp2.modelos.Crecimiento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RegistroCrecimiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_crecimiento);

        Button calcular = findViewById(R.id.calcularCrecimiento);
        Button guardar = findViewById(R.id.RegCr_btn_guardar);
        ImageButton back = findViewById(R.id.btn_back);
        ImageButton home = findViewById(R.id.btn_home);
        ImageButton fotoFinal = findViewById(R.id.RegCr_imgBtn_FotoF);
        EditText altura = findViewById(R.id.RegCr_eTxt_centimetrosF);
        EditText fecha = findViewById(R.id.RegCr_eTxt_FechaF);
        TextView alturaAnterior = findViewById(R.id.RegCr_txtV_centimetrosAnte);
        TextView fechaAnterior = findViewById(R.id.RegCr_txtV_FechaAnterior);
        Intent actividadCrecimiento = new Intent(RegistroCrecimiento.this, RegistroCrecimiento.class);

        File crecimientoFile = new File(getFilesDir(), "crecimiento.txt");
        List<Crecimiento> listaCrecimiento = leerArchivoCrecimiento(crecimientoFile);

        obtenerUltimaAltura(listaCrecimiento);
        alturaAnterior.setText(obtenerUltimaAltura(listaCrecimiento) + " cm");
        obtenerUltimaFecha(listaCrecimiento);
        fechaAnterior.setText(obtenerUltimaFecha(listaCrecimiento));


        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent calcularActividad = new Intent(RegistroCrecimiento.this, EstadisticaCrecimiento.class);
                startActivity(calcularActividad);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(RegistroCrecimiento.this, Categorias.class);
                startActivity(backActividad);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeActividad = new Intent(RegistroCrecimiento.this, ZonaNavegacion.class);
                startActivity(homeActividad);
            }
        });

        fotoFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent fotoFinalActivity = new Intent(RegistroCrecimiento.this, FotoPlanta.class);
                startActivity(fotoFinalActivity);

            }

        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!altura.getText().toString().isEmpty() && !fecha.getText().toString().isEmpty()) {
                    String fechaBuscada = fecha.getText().toString();
                    boolean fechaExiste = verificarfecha(fechaBuscada);
                    if (fechaExiste) {
                        Toast.makeText(RegistroCrecimiento.this, "La fecha ya existe", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean datosGuardados = guardarDatos(altura.getText().toString(), fecha.getText().toString());
                        if (datosGuardados) {
                            // Se queda en la misma actividad
                            startActivity(actividadCrecimiento);
                        } else {
                            Toast.makeText(RegistroCrecimiento.this, "Error al guardar el archivo",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Algunos campos están vacíos
                    Toast.makeText(RegistroCrecimiento.this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean verificarfecha(String fechaBuscada) {
        File file = new File(getFilesDir(), "crecimiento.txt");
        fechaBuscada = fechaBuscada.toLowerCase();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String fecha = linea.split(",")[1]; // Suponiendo que la fecha está en la segunda columna separada por coma (,)
                if (fecha.equalsIgnoreCase(fechaBuscada)) {
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
    public boolean guardarDatos(String altura, String fecha) {
        File file = new File(getFilesDir(), "crecimiento.txt");
        fecha = fecha.toLowerCase();
        try {
            // Verificar si el archivo existe
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            // Crear una instancia de Agua y escribir los datos en el archivo
            Crecimiento crecimiento = new Crecimiento(Float.parseFloat(altura), fecha);
            String linea = String.format(Locale.getDefault(), "%.2f,%s", crecimiento.getAltura (), crecimiento.getFecha());
            bufferedWriter.write(linea);
            bufferedWriter.newLine();
            bufferedWriter.close();
            return true; // Los datos se guardaron correctamente
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Error al guardar los datos
    }

    private static List<Crecimiento> leerArchivoCrecimiento(File archivo) {
        List<Crecimiento> listaCrecimiento = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                float altura = Float.parseFloat(datos[0]);
                String fecha = datos[1];
                Crecimiento crecimiento = new Crecimiento(altura, fecha);
                listaCrecimiento.add(crecimiento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaCrecimiento;
    }
    private static String obtenerUltimaFecha(List<Crecimiento> listaCrecimiento) {
        String ultimaFecha = null;

        for (Crecimiento crecimiento : listaCrecimiento) {
            if (crecimiento.getFecha() != null && !crecimiento.getFecha().isEmpty()) {
                ultimaFecha = crecimiento.getFecha();
            }
        }
        return ultimaFecha;
    }

    private static String obtenerUltimaAltura(List<Crecimiento> listaCrecimiento) {
        String ultimaAltura = null;

        for (Crecimiento crecimiento : listaCrecimiento) {
            if (crecimiento.getAltura() != 0) {
                ultimaAltura = String.valueOf(crecimiento.getAltura());
            }
        }
        return ultimaAltura;
    }

}

