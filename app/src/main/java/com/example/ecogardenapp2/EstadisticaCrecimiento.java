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
import com.example.ecogardenapp2.modelos.Crecimiento;

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

public class EstadisticaCrecimiento extends AppCompatActivity {

    private TableLayout tableLayoutCant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica_crecimiento);

        Button categorias = findViewById(R.id.categorias);
        Button tips = findViewById(R.id.tips);
        ImageButton home = findViewById(R.id.btn_home);
        ImageButton back = findViewById(R.id.btn_back);
        tableLayoutCant = findViewById(R.id.tab_estCr_CantCreci);
        TextView canTotal = findViewById(R.id.txtV_EstCr_CantCreci);
        TextView fechaProxima = findViewById(R.id.txtV_EstCr_FechaCreci);

        File crecimientoFile = new File(getFilesDir(), "crecimiento.txt");
        List<Crecimiento> listaCrecimiento = leerArchivoCrecimiento(crecimientoFile);

        addElementCrecimiento(listaCrecimiento);
        addPromedioCrecimiento(listaCrecimiento);
        calcularTotalCantidadCrecimiento(listaCrecimiento);
        canTotal.setText(calcularTotalCantidadCrecimiento(listaCrecimiento) +" cm");
        calcularNuevaFecha(listaCrecimiento);
        Date nuevaFecha = calcularNuevaFecha(listaCrecimiento);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String nuevaFechaFormateada = sdf.format(nuevaFecha);
        fechaProxima.setText("Recomendada: " +nuevaFechaFormateada);

        categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent categoriasActividad = new Intent(EstadisticaCrecimiento.this, Categorias.class);
                startActivity(categoriasActividad);
            }
        });

        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent tipsActividad = new Intent(EstadisticaCrecimiento.this, TipsHome.class);
                startActivity(tipsActividad);
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeActividad = new Intent(EstadisticaCrecimiento.this, ZonaNavegacion.class);
                startActivity(homeActividad);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(EstadisticaCrecimiento.this, RegistroCrecimiento.class);
                startActivity(backActividad);
            }
        });
    }

    private void addPromedioCrecimiento(List<Crecimiento> crecimientoList){

        float promedioCrecimiento = calcularPromedioCrecimiento (crecimientoList);

        TableRow row = new TableRow(this);
        TextView cell1 = new TextView(this);
        cell1.setText("Promedio");
        cell1.setPadding(20, 10, 10, 20);
        cell1.setBackgroundResource(R.color.white);


        TextView cell2 = new TextView(this);
        cell2.setText(String.valueOf(promedioCrecimiento));
        cell2.setPadding(20, 10, 10, 20);
        cell2.setBackgroundResource(R.color.white);

        row.addView(cell1);
        row.addView(cell2);
        tableLayoutCant.addView(row);
    }
    private void addElementCrecimiento(List<Crecimiento> crecimientoList){

        for (Crecimiento item: crecimientoList) {
            TableRow row = new TableRow(this);
            TextView cell1 = new TextView(this);
            cell1.setText(item.getFecha());

            cell1.setPadding(10, 10, 10, 10);
            cell1.setBackgroundResource(R.color.white);

            TextView cell2 = new TextView(this);
            cell2.setText(String.valueOf((item.getAltura())));
            cell2.setPadding(10, 10, 10, 10);
            cell2.setBackgroundResource(R.color.white);

            row.addView(cell1);
            row.addView(cell2);
            tableLayoutCant.addView(row);
        }
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

    private static Date calcularNuevaFecha(List<Crecimiento> listaCrecimiento) {
        String ultimaFechaString = obtenerUltimaFecha(listaCrecimiento);

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

    private static String obtenerUltimaFecha(List<Crecimiento> listaCrecimiento) {
        String ultimaFecha = null;

        for (Crecimiento crecimiento : listaCrecimiento) {
            if (crecimiento.getFecha() != null && !crecimiento.getFecha().isEmpty()) {
                ultimaFecha = crecimiento.getFecha();
            }
        }
        return ultimaFecha;
    }


    private float calcularPromedioCrecimiento(List<Crecimiento> crecimientoList) {
        float sum = 0;
        for (Crecimiento item : crecimientoList) {
            sum += item.getAltura();
        }
        return sum / crecimientoList.size();
    }
    private float calcularTotalCantidadCrecimiento(List<Crecimiento> crecimientoList) {
        float sum = 0;
        for (Crecimiento item : crecimientoList) {
            sum += item.getAltura();
        }
        return sum;
    }
}
