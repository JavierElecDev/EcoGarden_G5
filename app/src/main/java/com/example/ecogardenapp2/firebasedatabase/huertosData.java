package com.example.ecogardenapp2.firebasedatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ecogardenapp2.Login;
import com.example.ecogardenapp2.ZonaNavegacion;
import com.example.ecogardenapp2.clasesLogReg.DatosRegistro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class huertosData {


    public static String[] tamanos = {"Indica el tamaño", "Muy pequeño","Pequeño", "Mediano", "Grandre", "Muy Grande"};
    public static String[] tipoDeHuertos = {"Que tipo de huerto es?", "Huerto doméstico","Huerto Colectivo",
            "Huerto escolar", "Huerto de ocio", "Huerto de producción", "Huerto educativo", "Huerto terapéutico",
    "Huerto ecológico", "Huerto hidropónico", "Huerto urbano vertical", "Huerto urbano flotante"};

    private boolean crecionCompleta;

    //Chicas aca se crea una instancia ya que esta clase obtine el id de firebase para el usuario que se registro
    private DatosRegistro UserID = new DatosRegistro();

    //adaptadores para mostrar las opciones en el spinner
    public ArrayAdapter<String> adaptadorTamano(Context activity){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item, tamanos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public ArrayAdapter<String> adaptadorHuertos(Context activity){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item, tipoDeHuertos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    //Chicas este metodo contine la instancia a fire base para crear los huertos en la BD
    public boolean crearHuerto(String[] datosNuevoHuerto, Context ventana){
        FirebaseDatabase baseHuertos = FirebaseDatabase.getInstance();
        DatabaseReference huertosRef = baseHuertos.getReference("HuertosUsuarios");
        String nombre = datosNuevoHuerto[0];
        String tamano = datosNuevoHuerto[1];
        String tipo = datosNuevoHuerto[2];
        String descripcion = datosNuevoHuerto[3];
        String Id_usuario = UserID.getUserID();

        huertosRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            /*metodo de escucha para los dato que se enviaran,
             * para entendelo revisar la documentacion
             */
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // se instancia la clase Huerto que recibe y genera los datos mendiente un objeto
                Huerto nuevoHuerto = new Huerto(nombre, tamano, tipo, descripcion,Id_usuario);

                huertosRef.push().setValue(nuevoHuerto);
                crecionCompleta = true;
                mensajeExitoCreacion(ventana);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ventana, "Error de conexion, no se puedo crear el huerto", Toast.LENGTH_LONG).show();
                crecionCompleta = false;
            }
        });
        return crecionCompleta;
    }

    public void mensajeExitoCreacion(Context ventana){
        AlertDialog.Builder huertoCreado = new AlertDialog.Builder(ventana);
        huertoCreado.setMessage("El Huerto se ha creado que deseas hacer?:.").setCancelable(false)
                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        Intent irZonanavegacion = new Intent(ventana, ZonaNavegacion.class);
                        ventana.startActivity(irZonanavegacion);
                    }
                })
                .setNegativeButton("Crear otro", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog titulo = huertoCreado.create();
        titulo.setTitle("Huerto Registrado..");
        titulo.show();
    }

}
