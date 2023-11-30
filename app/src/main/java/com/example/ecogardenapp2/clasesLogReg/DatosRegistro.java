package com.example.ecogardenapp2.clasesLogReg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.io.Serializable;

/*
 *  Implementamos la clase Serializable para poder enviar informacion entre activities
 */
public class DatosRegistro implements Serializable {

    private String nombres, apellidos, ciudad, direccion;
    private String correoElectronico, password;
    private int telefono;


    public void almacenarDatos(String nombresTemp, String apellidosTemp, String ciudadTemp,
                               String direccionTemp, String correoETemp, String passTemp,
                               int telefonoTemp){
        this.nombres = nombresTemp;
        this.apellidos = apellidosTemp;
        this.ciudad = ciudadTemp;
        this.direccion = direccionTemp;
        this.correoElectronico = correoETemp;
        this.password = passTemp;
        this.telefono = telefonoTemp;
    }

    public void enviarDatosDeRegistro(Context destinoVentana){

        AlertDialog.Builder alertDatosEnvio = new AlertDialog.Builder(destinoVentana);
        alertDatosEnvio.setTitle("Registro Completo");
        alertDatosEnvio.setMessage("Se enviaran los siguientes datos: "
        +"\n" + nombres + "\n" + apellidos + "\n" + ciudad + "\n" + direccion
                + "\n" + correoElectronico + "\n" + password  + "\n" + telefono);
        alertDatosEnvio.setCancelable(false);
        alertDatosEnvio.setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDatosEnvio.create();
        alertDialog.show();
    }
}
