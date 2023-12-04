package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ecogardenapp2.modelos.Agua;

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

public class EstadisticaAgua extends AppCompatActivity {
    private TableLayout tableLayoutCant;
    private TableLayout tableLayoutCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica_agua);

        Button categorias = findViewById(R.id.categorias);
        Button energia = findViewById(R.id.energia);
        ImageButton home = findViewById(R.id.btn_home);
        ImageButton back = findViewById(R.id.btn_back);
        tableLayoutCant = findViewById(R.id.tab_estAg_CantAgua);
        tableLayoutCost = findViewById(R.id.tab_estAg_CostAgua);
        TextView canTotal = findViewById(R.id.txtV_EstAg_CantAgua);
        TextView costTotal = findViewById(R.id.txtV_EstAg_CostAgua);
        TextView fechaProxima = findViewById(R.id.txtV_EstAg_FechaAgua);


        File aguaFile = new File(getFilesDir(), "agua.txt");
        List<Agua> listaAgua = leerArchivoAgua(aguaFile);

        addElementAgua(listaAgua);
        addPromedioAgua(listaAgua);
        addPrecioAgua(listaAgua);
        addPromedioCosto(listaAgua);
        calcularTotalCantidadAgua(listaAgua);
        canTotal.setText(calcularTotalCantidadAgua(listaAgua) +" litros");
        calcularTotalCostoAgua(listaAgua);
        costTotal.setText("$ " + calcularTotalCostoAgua(listaAgua));
        calcularNuevaFecha(listaAgua);
        Date nuevaFecha = calcularNuevaFecha(listaAgua);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String nuevaFechaFormateada = sdf.format(nuevaFecha);
        fechaProxima.setText("Recomendada: " +nuevaFechaFormateada);


        categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent categoriasActividad = new Intent(EstadisticaAgua.this, Categorias.class);
                startActivity(categoriasActividad);
            }
        });

        energia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent energiaActividad = new Intent(EstadisticaAgua.this, EstadisticaEnergia.class);
                startActivity(energiaActividad);
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeActividad = new Intent(EstadisticaAgua.this, ZonaNavegacion.class);
                startActivity(homeActividad);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(EstadisticaAgua.this, RegistroAgua.class);
                startActivity(backActividad);
            }
        });
    }
    private void addPromedioAgua(List<Agua> aguaList){

        float promedioConsumoAgua = calcularPromedioCantidadAgua(aguaList);

        TableRow row = new TableRow(this);
        TextView cell1 = new TextView(this);
        cell1.setText("Promedio");
        cell1.setPadding(20, 10, 10, 20);
        cell1.setBackgroundResource(R.color.white);


        TextView cell2 = new TextView(this);
        cell2.setText(String.valueOf(promedioConsumoAgua));
        cell2.setPadding(20, 10, 10, 20);
        cell2.setBackgroundResource(R.color.white);

        row.addView(cell1);
        row.addView(cell2);
        tableLayoutCant.addView(row);
    }
    private void addElementAgua(List<Agua> aguaList){

        for (Agua item: aguaList) {
            TableRow row = new TableRow(this);
            TextView cell1 = new TextView(this);
            cell1.setText(item.getFecha());

            cell1.setPadding(10, 10, 10, 10);
            cell1.setBackgroundResource(R.color.white);

            TextView cell2 = new TextView(this);
            cell2.setText(String.valueOf((item.getCantidad())));
            cell2.setPadding(10, 10, 10, 10);
            cell2.setBackgroundResource(R.color.white);

            row.addView(cell1);
            row.addView(cell2);
            tableLayoutCant.addView(row);
        }
    }

    private void addPromedioCosto (List<Agua> aguaList){

        float promedioPrecioAgua = calcularPromedioPrecioAgua(aguaList);

        TableRow row = new TableRow(this);
        TextView cell1 = new TextView(this);
        cell1.setText("Promedio");
        cell1.setPadding(20, 10, 10, 20);
        cell1.setBackgroundResource(R.color.white);


        TextView cell2 = new TextView(this);
        cell2.setText(String.valueOf(promedioPrecioAgua));
        cell2.setPadding(20, 10, 10, 20);
        cell2.setBackgroundResource(R.color.white);

        row.addView(cell1);
        row.addView(cell2);
        tableLayoutCost.addView(row);
    }
    private void addPrecioAgua(List<Agua> aguaList){


        for (Agua item: aguaList) {
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
    private static List<Agua> leerArchivoAgua(File archivo) {
        List<Agua> listaAgua = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                float cantidad = Float.parseFloat(datos[0]);
                float precio = Float.parseFloat(datos[1]);
                String fecha = datos[2];

                Agua agua = new Agua(cantidad, precio, fecha);
                listaAgua.add(agua);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaAgua;
    }
    private static Date calcularNuevaFecha(List<Agua> listaAgua) {
        String ultimaFechaString = obtenerUltimaFecha(listaAgua);

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

    private static String obtenerUltimaFecha(List<Agua> listaAgua) {
        String ultimaFecha = null;

        for (Agua agua : listaAgua) {
            if (agua.getFecha() != null && !agua.getFecha().isEmpty()) {
                ultimaFecha = agua.getFecha();
            }
        }
        return ultimaFecha;
    }

    private float calcularPromedioCantidadAgua(List<Agua> aguaList) {
        float sum = 0;
        for (Agua item : aguaList) {
            sum += item.getCantidad();
        }
        return sum / aguaList.size();
    }

    private float calcularPromedioPrecioAgua(List<Agua> aguaList) {
        float sum = 0;
        for (Agua item : aguaList) {
            sum += item.getPrecio();
        }
        return sum / aguaList.size();
    }

    private float calcularTotalCantidadAgua(List<Agua> aguaList) {
        float sum = 0;
        for (Agua item : aguaList) {
            sum += item.getCantidad();
        }
        return sum;
    }

    private float calcularTotalCostoAgua(List<Agua> aguaList) {
        float sum = 0;
        for (Agua item : aguaList) {
            sum += item.getPrecio() * item.getCantidad();
        }
        return sum ;
    }
}

