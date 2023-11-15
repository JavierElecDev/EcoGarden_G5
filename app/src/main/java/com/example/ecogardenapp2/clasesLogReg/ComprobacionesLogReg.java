package com.example.ecogardenapp2.clasesLogReg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.ecogardenapp2.Login;

public class ComprobacionesLogReg{

    private String Correo, Contrasena;
    private Context Activity;

    /*Compañeras usamos el contructuro de la case para recibir el contexto,
     * donde se va a usar el alert dialog. Att: Javier
     */
    public ComprobacionesLogReg(Context ventana){
        this.Activity = ventana;
    }

    public void ComprobarEntradas(String CorreoE, String ContrasenaU){

        this.Correo = CorreoE;
        this.Contrasena = ContrasenaU;

        if(Correo.isEmpty() || Contrasena.isEmpty()){
            /* Chicas para pdoer usar un alert Dialog desde una clase externa,
             * Debemos pasar el contexto que seria la actividad donde sedispara el,
             * alertDialog, en este caso recibimos el contexto desde la activity "Login.class".
             * Att: javier.
             */
            AlertDialog.Builder alertaCamposVacios = new AlertDialog.Builder(Activity);
            alertaCamposVacios.setTitle("Atencion Campos vacios");
            alertaCamposVacios.setMessage("Debes ingresar los datos en los campos!!!");
            alertaCamposVacios.setCancelable(false);
            alertaCamposVacios.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = alertaCamposVacios.create();
            alertDialog.show();
        }
    }
}
