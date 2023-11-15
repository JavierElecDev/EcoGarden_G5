package com.example.ecogardenapp2.clasesLogReg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import java.util.regex.Pattern;

public class ComprobacionesLogReg{

    private String Correo, Contrasena;
    private Context Activity;
    //Declaramos una expersion regulas
    private static final Pattern reg_expresion = Pattern.compile(
            "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"
    );

    /*Compañeras usamos el contructuro de la case para recibir el contexto,
     * donde se va a usar el alert dialog. Att: Javier
     */
    public ComprobacionesLogReg(Context ventana){
        this.Activity = ventana;
    }

    //Metodo que comprueba que los campos no esten vacios
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

    public void VerficaCorreo(String CorreoE){
        this.Correo = CorreoE;
        if(!reg_expresion.matcher(Correo).matches()){
            AlertDialog.Builder alertaCorreo = new AlertDialog.Builder(Activity);
            alertaCorreo.setTitle("Atencion Correo NO Valido");
            alertaCorreo.setMessage("Verifica el correo electronico!!!");
            alertaCorreo.setCancelable(false);
            alertaCorreo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = alertaCorreo.create();
            alertDialog.show();
        }else{
            Toast.makeText(Activity, "Comprobcion Exitosa...", Toast.LENGTH_LONG).show();
        }
    }
}
