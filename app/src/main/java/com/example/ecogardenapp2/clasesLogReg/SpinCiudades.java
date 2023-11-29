package com.example.ecogardenapp2.clasesLogReg;

import android.content.Context;
import android.widget.ArrayAdapter;


public class SpinCiudades {
   //Creamos el array con las ciudades
   public static String[] ciudades = {"Bogotá", "Medellín", "Cali", "Barranquilla", "Cartagena",
            "Bucaramanga", "Pereira", "Ibagué", "Manizales", "Villavicencio"};

   public ArrayAdapter<String> adaptadorCiudades(Context activity){
      ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
              android.R.layout.simple_spinner_item, ciudades);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      return adapter;
   }

}
