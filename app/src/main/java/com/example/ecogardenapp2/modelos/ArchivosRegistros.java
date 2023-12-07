package com.example.ecogardenapp2.modelos;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class ArchivosRegistros implements Serializable {
    private File abono, agua, energia, crecimiento;

   public void crearArchivo(Context ventana){
       agua = new File(ventana.getFilesDir(), "agua.txt");
       abono = new File(ventana.getFilesDir(), "abono.txt");
       energia = new File(ventana.getFilesDir(), "energia.txt");
       crecimiento = new File(ventana.getFilesDir(), "crecimiento.txt");

       if (!agua.exists()) {
           try {
               FileWriter wAgua = new FileWriter(agua);
               wAgua.write("00, 00, 00/00/0000");
               wAgua.flush();
               wAgua.close();
           }catch (Exception er){
               System.out.println("algo paso " + er);
           }
       }

       if (!abono.exists()) {
           try {FileWriter wAbono = new FileWriter(abono);
               wAbono.write("00, 00 , 00/00/0000");
               wAbono.flush();
               wAbono.close();
           }catch (Exception er){
               System.out.println("algo paso " + er);
           }
       }

       if (!energia.exists()) {
           try {
               FileWriter wEnergia = new FileWriter(energia);
               wEnergia.write("0000 ,0000 , 0000, 00/00/0000");
               wEnergia.flush();
               wEnergia.close();
           }catch (Exception er){
               System.out.println("algo paso " + er);
           }
       }

       if (!crecimiento.exists()) {
           try {
               FileWriter wCresimiento = new FileWriter(crecimiento);
               wCresimiento.write("00, 00/00/0000");
               wCresimiento.flush();
               wCresimiento.close();
           }catch (Exception er){
               System.out.println("algo paso " + er);
           }
       }

   }
}
