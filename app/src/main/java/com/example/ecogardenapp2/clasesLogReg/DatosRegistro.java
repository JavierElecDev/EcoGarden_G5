package com.example.ecogardenapp2.clasesLogReg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*
 *  Implementamos la clase Serializable para poder enviar información entre activities
 */
public class DatosRegistro implements Serializable {
    /*Vamos a instanciar las objetos que nosayudaran a usar metodos de Firebase,
     *esto con el fin de realizar la conexion con la platsaforma Firebase de google.
     */
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
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
        alertDatosEnvio.setMessage("Se enviarán los siguientes datos: "
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

    //Metodo para registrar los usuarios en firebase
    public void registrarUsuario(final Context destinoVentana){
        //obtenemos la instancia
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(correoElectronico, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String id = mAuth.getCurrentUser().getUid();
                            Map<String, Object> map = new HashMap<>();
                            map.put("id", id);
                            map.put("nombres", nombres);
                            map.put("apellidos", apellidos);
                            map.put("ciudad", ciudad);
                            map.put("direccion", direccion);
                            map.put("email", correoElectronico);
                            map.put("password", password);
                            map.put("phone", telefono);

                            mFirestore.collection("user").document(id).set(map)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            AlertDialog.Builder RegistroCompleto = new AlertDialog.Builder(destinoVentana);
                                            RegistroCompleto.setMessage("El Registro se ha completado.").setCancelable(false)
                                                    .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            dialogInterface.cancel();
                                                        }
                                                    });
                                            AlertDialog titulo = RegistroCompleto.create();
                                            titulo.setTitle("Felicidades Registro completo.");
                                            titulo.show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(destinoVentana, "Error no se pudo crear el usuario", Toast.LENGTH_LONG).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(destinoVentana, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(destinoVentana,
                        "Error de conexión, no se puede crear el Registro", Toast.LENGTH_LONG).show();
            }
        });

    }
}