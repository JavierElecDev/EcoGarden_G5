package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecogardenapp2.modelos.Abono;
import com.example.ecogardenapp2.modelos.Agua;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class EstadisticaAbono extends AppCompatActivity {

    private TableLayout tableLayoutCant;
    private TableLayout tableLayoutCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica_abono);

        Button categorias = findViewById(R.id.categorias);
        Button agua = findViewById(R.id.agua);
        ImageButton home = findViewById(R.id.btn_home);
        ImageButton back = findViewById(R.id.btn_back);
        tableLayoutCant = findViewById(R.id.tab_estAb_CantAbono);
        tableLayoutCost = findViewById(R.id.tab_estAb_CostAbono);
        TextView canTotal = findViewById(R.id.txtV_EstAb_CantAbono);
        TextView costTotal = findViewById(R.id.txtV_EstAb_CostAbono);
        TextView fechaProxima = findViewById(R.id.txtV_EstAb_FechaAbono);

        try{
            File abonoFile = new File(getFilesDir(), "abono.txt");

            List<Abono> listaAbono = leerArchivoAbono(abonoFile);

            addElementAbono(listaAbono);
            addPromedioAbono(listaAbono);
            addPrecioAbono(listaAbono);
            addPromedioCosto(listaAbono);
            calcularTotalCantidadAbono(listaAbono);
            canTotal.setText(calcularTotalCantidadAbono(listaAbono) +" kilogramos");
            calcularTotalCostoAbono(listaAbono);
            costTotal.setText("$ " + calcularTotalCostoAbono(listaAbono));
            calcularNuevaFecha(listaAbono);
            Date nuevaFecha = calcularNuevaFecha(listaAbono);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String nuevaFechaFormateada = sdf.format(nuevaFecha);
            fechaProxima.setText("Recomendada: " +nuevaFechaFormateada);



            categorias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent categoriasActividad = new Intent(EstadisticaAbono.this, Categorias.class);
                    startActivity(categoriasActividad);

                }
            });

            agua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent aguaActividad = new Intent(EstadisticaAbono.this, EstadisticaAgua.class);
                    startActivity(aguaActividad);
                }
            });


            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent homeActividad = new Intent(EstadisticaAbono.this, ZonaNavegacion.class);
                    startActivity(homeActividad);
                }
            });

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent backActividad = new Intent(EstadisticaAbono.this, RegistroAbono.class);
                    startActivity(backActividad);
                }
            });
        }catch (Exception er){
            Toast.makeText(EstadisticaAbono.this, "Error en ejecucion", Toast.LENGTH_SHORT).show();
            Log.e("TAGJAVIER","Error en ejecucion" + er);
        }
    }

    private void addPromedioAbono(List<Abono> abonoList){

        float promedioConsumoAbono = calcularPromedioCantidadAbono(abonoList);

        TableRow row = new TableRow(this);
        TextView cell1 = new TextView(this);
        cell1.setText("Promedio");
        cell1.setPadding(20, 10, 10, 20);
        cell1.setBackgroundResource(R.color.white);


        TextView cell2 = new TextView(this);
        cell2.setText(String.valueOf(promedioConsumoAbono));
        cell2.setPadding(20, 10, 10, 20);
        cell2.setBackgroundResource(R.color.white);

        row.addView(cell1);
        row.addView(cell2);
        tableLayoutCant.addView(row);
    }
    private void addElementAbono(List<Abono> abonoList){

        for (Abono item: abonoList) {
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

    private void addPromedioCosto (List<Abono> abonoList){

        float promedioPrecioAbono = calcularPromedioPrecioAbono(abonoList);

        TableRow row = new TableRow(this);
        TextView cell1 = new TextView(this);
        cell1.setText("Promedio");
        cell1.setPadding(20, 10, 10, 20);
        cell1.setBackgroundResource(R.color.white);


        TextView cell2 = new TextView(this);
        cell2.setText(String.valueOf(promedioPrecioAbono));
        cell2.setPadding(20, 10, 10, 20);
        cell2.setBackgroundResource(R.color.white);

        row.addView(cell1);
        row.addView(cell2);
        tableLayoutCost.addView(row);
    }
    private void addPrecioAbono(List<Abono> abonoList){


        for (Abono item: abonoList) {
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
    private static List<Abono> leerArchivoAbono(File archivo) {
        List<Abono> listaAbono = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                float cantidad = Float.parseFloat(datos[0]);
                float precio = Float.parseFloat(datos[1]);
                String fecha = datos[2];


                Abono abono = new Abono(cantidad, precio, fecha);
                listaAbono.add(abono);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaAbono;
    }
    private static Date calcularNuevaFecha(List<Abono> listaAbono) {
        String ultimaFechaString = obtenerUltimaFecha(listaAbono);

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

    private static String obtenerUltimaFecha(List<Abono> listaAbono) {
        String ultimaFecha = null;

        for (Abono abono : listaAbono) {
            if (abono.getFecha() != null && !abono.getFecha().isEmpty()) {
                ultimaFecha = abono.getFecha();
            }
        }
        return ultimaFecha;
    }

    private float calcularPromedioCantidadAbono(List<Abono> abonoList) {
        float sum = 0;
        for (Abono item : abonoList) {
            sum += item.getCantidad();
        }
        return sum / abonoList.size();
    }

    private float calcularPromedioPrecioAbono(List<Abono> abonoList) {
        float sum = 0;
        for (Abono item : abonoList) {
            sum += item.getPrecio();
        }
        return sum / abonoList.size();
    }

    private float calcularTotalCantidadAbono(List<Abono> abonoList) {
        float sum = 0;
        for (Abono item : abonoList) {
            sum += item.getCantidad();
        }
        return sum;
    }

    private float calcularTotalCostoAbono(List<Abono> abonoList) {
        float sum = 0;
        for (Abono item : abonoList) {
            sum += item.getPrecio() * item.getCantidad();
        }
        return sum ;
    }

}