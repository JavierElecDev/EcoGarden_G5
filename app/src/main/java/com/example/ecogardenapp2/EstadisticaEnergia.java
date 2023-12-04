package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ecogardenapp2.modelos.Agua;
import com.example.ecogardenapp2.modelos.Energia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EstadisticaEnergia extends AppCompatActivity {

    private TableLayout tableLayoutCant;
    private TableLayout tableLayoutCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica_energia);

        Button categorias = findViewById(R.id.categorias);
        Button crecimiento = findViewById(R.id.crecimiento);
        ImageButton home = findViewById(R.id.btn_home);
        ImageButton back = findViewById(R.id.btn_back);
        tableLayoutCant = findViewById(R.id.tab_estEn_CantEnergia);
        tableLayoutCost = findViewById(R.id.tab_estEn_CostEnergia);
        TextView canTotal = findViewById(R.id.txtV_EstEn_CantEnergia);
        TextView costTotal = findViewById(R.id.txtV_EstEn_CostEnergia);
        TextView fechaProxima = findViewById(R.id.txtV_EstEn_FechaEnergia);

        File energiaFile = new File(getFilesDir(), "energia.txt");
        List<Energia> listaEnergia = leerArchivoEnergia(energiaFile);

        addElementEnergia(listaEnergia);
        addPromedioEnergia(listaEnergia);
        addPrecioEnergia(listaEnergia);
        addPromedioCosto(listaEnergia);
        calcularTotalCantidadEnergia(listaEnergia);
        canTotal.setText(calcularTotalCantidadEnergia(listaEnergia) +" Kilovatios (Kw)");
        calcularTotalCostoEnergia(listaEnergia);
        costTotal.setText("$ " + calcularTotalCostoEnergia(listaEnergia));
        calcularNuevaFecha(listaEnergia);
        Date nuevaFecha = calcularNuevaFecha(listaEnergia);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String nuevaFechaFormateada = sdf.format(nuevaFecha);
        fechaProxima.setText("Recomendada: " +nuevaFechaFormateada);

        categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent categoriasActividad = new Intent(EstadisticaEnergia.this, Categorias.class);
                startActivity(categoriasActividad);

            }
        });

        crecimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent crecimientoActividad = new Intent(EstadisticaEnergia.this, EstadisticaCrecimiento.class);
                startActivity(crecimientoActividad);
            }
        });



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeActividad = new Intent(EstadisticaEnergia.this, ZonaNavegacion.class);
                startActivity(homeActividad);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(EstadisticaEnergia.this, RegistroEnergia.class);
                startActivity(backActividad);
            }
        });

    }
    private void addPromedioEnergia(List<Energia> energiaList){

        float promedioConsumoEnergia = calcularPromedioCantidadEnergia(energiaList);

        TableRow row = new TableRow(this);
        TextView cell1 = new TextView(this);
        cell1.setText("Promedio");
        cell1.setPadding(20, 10, 10, 20);
        cell1.setBackgroundResource(R.color.white);


        TextView cell2 = new TextView(this);
        cell2.setText(String.valueOf(promedioConsumoEnergia));
        cell2.setPadding(20, 10, 10, 20);
        cell2.setBackgroundResource(R.color.white);

        row.addView(cell1);
        row.addView(cell2);
        tableLayoutCant.addView(row);
    }

    private void addElementEnergia(List<Energia> energiaList){

        for (Energia item: energiaList) {
            TableRow row = new TableRow(this);
            TextView cell1 = new TextView(this);
            cell1.setText(item.getFecha());

            cell1.setPadding(10, 10, 10, 10);
            cell1.setBackgroundResource(R.color.white);

            TextView cell2 = new TextView(this);
            cell2.setText(String.valueOf(item.getKilovatios() * item.getHoras()));
            cell2.setPadding(10, 10, 10, 10);
            cell2.setBackgroundResource(R.color.white);

            row.addView(cell1);
            row.addView(cell2);
            tableLayoutCant.addView(row);
        }
    }

    private void addPromedioCosto (List<Energia> energiaList){

        float promedioPrecioEnergia = calcularPromedioPrecioEnergia(energiaList);

        TableRow row = new TableRow(this);
        TextView cell1 = new TextView(this);
        cell1.setText("Promedio");
        cell1.setPadding(20, 10, 10, 20);
        cell1.setBackgroundResource(R.color.white);


        TextView cell2 = new TextView(this);
        cell2.setText(String.valueOf(promedioPrecioEnergia));
        cell2.setPadding(20, 10, 10, 20);
        cell2.setBackgroundResource(R.color.white);

        row.addView(cell1);
        row.addView(cell2);
        tableLayoutCost.addView(row);
    }
    private void addPrecioEnergia(List<Energia> energiaList){


        for (Energia item: energiaList) {
            TableRow row = new TableRow(this);
            TextView cell1 = new TextView(this);
            cell1.setText(item.getFecha());

            cell1.setPadding(10, 10, 10, 10);
            cell1.setBackgroundResource(R.color.white);

            TextView cell2 = new TextView(this);
            cell2.setText(String.valueOf((item.getPrecio())));
            cell2.setPadding(10, 10, 10, 10);
            cell2.setBackgroundResource(R.color.white);

            row.addView(cell1);
            row.addView(cell2);
            tableLayoutCost.addView(row);
        }
    }
    private static List<Energia> leerArchivoEnergia(File archivo) {
        List<Energia> listaEnergia = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                float kilovatios = Float.parseFloat(datos[0]);
                float horas = Float.parseFloat(datos[1]);
                float precio = Float.parseFloat(datos[2]);
                String fecha = datos[3];


                Energia energia = new Energia(kilovatios, horas, precio, fecha);
                listaEnergia.add(energia);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaEnergia;
    }

    private static Date calcularNuevaFecha(List<Energia> listaEnergia) {
        String ultimaFechaString = obtenerUltimaFecha(listaEnergia);

        if (ultimaFechaString != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date ultimaFecha = sdf.parse(ultimaFechaString);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(ultimaFecha);
                calendar.add(Calendar.DAY_OF_YEAR, 7);

                return calendar.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String obtenerUltimaFecha(List<Energia> listaEnergia) {
        String ultimaFecha = null;

        for (Energia energia : listaEnergia) {
            if (energia.getFecha() != null && !energia.getFecha().isEmpty()) {
                ultimaFecha = energia.getFecha();
            }
        }
        return ultimaFecha;
    }

    private float calcularPromedioCantidadEnergia(List<Energia> energiaList) {
        float sum = 0;
        for (Energia item : energiaList) {
            sum += item.getKilovatios() * item.getHoras();
        }
        return sum / energiaList.size();
    }

    private float calcularPromedioPrecioEnergia(List<Energia> energiaList) {
        float sum = 0;
        for (Energia item : energiaList) {
            sum += item.getPrecio();
        }
        return sum / energiaList.size();
    }

    private float calcularTotalCantidadEnergia(List<Energia> energiaList) {
        float sum = 0;
        for (Energia item : energiaList) {
            sum += item.getKilovatios() * item.getHoras();
        }
        return sum;
    }

    private float calcularTotalCostoEnergia(List<Energia> energiaList) {
        float sum = 0;
        for (Energia item : energiaList) {
            sum += item.getPrecio() * item.getKilovatios() * item.getHoras();
        }
        return sum ;
    }
}

