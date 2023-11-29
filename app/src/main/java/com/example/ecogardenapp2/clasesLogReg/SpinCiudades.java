package com.example.ecogardenapp2.clasesLogReg;

import android.content.Context;
import android.widget.ArrayAdapter;


public class SpinCiudades {
   //Creamos el array con las ciudades
   public static String[] ciudades = {"Bogotá", "Medellín", "Cali", "Barranquilla", "Cartagena",
            "Bucaramanga", "Pereira", "Ibagué", "Manizales", "Villavicencio"};

   /*
    * chicas para poder enviar los datos desde un array o una lista,
    * a un spinner debemos usar un adaptador, se debe obtener el contexto,
    * indicar el tipo de adaptador, e indicar la forma en que se muestra.
    * En este caso el método es de tipo "ArrayAdapter", y retorna el objeto "adapter".
    */
   public ArrayAdapter<String> adaptadorCiudades(Context activity){
      ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
              android.R.layout.simple_spinner_item, ciudades);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      return adapter;
   }

}
