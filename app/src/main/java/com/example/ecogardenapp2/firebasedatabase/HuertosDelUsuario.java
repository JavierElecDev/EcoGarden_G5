package com.example.ecogardenapp2.firebasedatabase;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ecogardenapp2.clasesLogReg.DatosRegistro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HuertosDelUsuario {
    private DatosRegistro idDelUsuario = new DatosRegistro();
    //Recupero el id del usuario que incia sesion
    private String UserID = idDelUsuario.getUserID().toString();
    //creo una lista para recuperar los nombres de los huertos
    private List<String> huertosDelUsuario = new ArrayList<>();

    public void llamarHuertos(Context ventana){
        //obtenemos la instancia a la base de datos, estoes importante siempre chicas
        FirebaseDatabase baseHuertos = FirebaseDatabase.getInstance();
        //HuertosUsuarios es la referencia a la base de datos
        DatabaseReference huertosUsuario = baseHuertos.getReference("HuertosUsuarios");
        //leno el spiner para que tenga una leyenda incial
        huertosDelUsuario.add("Selecciona tu Huerto.");

        //instnacia de firebase que usa un metodo de escucha
        huertosUsuario.addListenerForSingleValueEvent(new ValueEventListener() {

            /*el metodo onDatachage no deb estar vacion, DataSnapshop hace referencia a la base de datos,
             * nos permite el acceso a los datos mediante el objeto snapshot.
             *
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //recorremos la base de datos
                for(DataSnapshot huertos : snapshot.getChildren()){
                    String usuarioID = huertos.child("usuarioID").getValue(String.class);

                    // Compara el usuario usando el metodo equals() y verifica que no sea nulo
                    if (usuarioID != null && usuarioID.equals(UserID)){
                        //llenamos el array list
                        String nombreHuerto = huertos.child("nombre").getValue(String.class);
                        huertosDelUsuario.add(nombreHuerto);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ventana, "Error al traer la informacion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //el adaptador para el spinner que muestra los huertos
    public ArrayAdapter<String> nombreDeLosHuertos(Context activity){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item, huertosDelUsuario);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }
}
