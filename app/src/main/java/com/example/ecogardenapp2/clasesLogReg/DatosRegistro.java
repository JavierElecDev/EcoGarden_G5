package com.example.ecogardenapp2.clasesLogReg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ecogardenapp2.CrearHuerto;
import com.example.ecogardenapp2.Login;
import com.example.ecogardenapp2.ZonaNavegacion;
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
    private long telefono;
    public boolean exito;

    public void almacenarDatos(String nombresTemp, String apellidosTemp, String ciudadTemp,
                               String direccionTemp, String correoETemp, String passTemp,
                               long telefonoTemp){
        this.nombres = nombresTemp;
        this.apellidos = apellidosTemp;
        this.ciudad = ciudadTemp;
        this.direccion = direccionTemp;
        this.correoElectronico = correoETemp;
        this.password = passTemp;
        this.telefono = telefonoTemp;
    }

    public void mensajeRegistro(Context TerminosCondiciones){

        AlertDialog.Builder RegistroCompleto = new AlertDialog.Builder(TerminosCondiciones);
        RegistroCompleto.setMessage("El Registro se ha completado.").setCancelable(false)
                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        Intent regresoLogin = new Intent(TerminosCondiciones, Login.class);
                        TerminosCondiciones.startActivity(regresoLogin);
                    }
                });
        AlertDialog titulo = RegistroCompleto.create();
        titulo.setTitle("Felicidades Registro completo.");
        titulo.show();
    }

    //Metodo para registrar los usuarios en firebase
    public void registrarUsuario(Context TerminosCondiciones){
        //obtenemos la instancia a firebase
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        //este objeto nos ayuda a crear el usuario con los parametro de correo y contraseña
        mAuth.createUserWithEmailAndPassword(correoElectronico, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    //se sobre escribe el metodo para comprobar que la tarea se llevo acabo
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //si la tarea de conexion es correcta guardamos los datos en un map
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

                            /*
                             * chicas este objeto accede a los metodos de la clase firebase store y auth,
                             * por lo que seteamos los datos mediante le mapa y obtenemos el id,
                             * que son los parametros quese recibe en la base de datos.
                             */
                            mFirestore.collection("user").document(id).set(map)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            //averigurar por que no puedo usar toak o alert aqui!!!
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(TerminosCondiciones, "Error no se pudo crear el usuario", Toast.LENGTH_LONG).show();
                                        }
                                    });
                        } else {// si hay algun error de conexion o no se puede crear el usuario
                            AlertDialog.Builder FalloConexion = new AlertDialog.Builder(TerminosCondiciones);
                            FalloConexion.setMessage("Error Fallo Conexion Base de Datos." +
                                            "Error General comunicate con soporpete").setCancelable(false)
                                    .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                            AlertDialog titulo = FalloConexion.create();
                            titulo.setTitle("Erorr de conexion.");
                            titulo.show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TerminosCondiciones,
                        "Error de conexión, no se puede crear el Registro", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //metodo para iniciar sesión
    public void iniciarSersion(String correo, String password, Context TerminosCondiciones){
        mAuth = FirebaseAuth.getInstance();
        try {
            mAuth.signInWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        Intent CreaHuerto = new Intent(TerminosCondiciones, ZonaNavegacion.class);
                        TerminosCondiciones.startActivity(CreaHuerto);
                    }else{
                        Toast.makeText(TerminosCondiciones,
                                "Error en conexion intenta de nuevo!!",Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    AlertDialog.Builder FalloConexion = new AlertDialog.Builder(TerminosCondiciones);
                    FalloConexion.setMessage("Error Fallo Conexion Base de Datos." +
                                    "Error General comunicate con soporte").setCancelable(false)
                            .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog titulo = FalloConexion.create();
                    titulo.setTitle("Erorr de conexion.");
                    titulo.show();
                }
            });
        }catch (Exception e) {
            // por fi falla buscamos el error
            Log.e("Login", "Error al iniciar sesion:", e);
            Toast.makeText(TerminosCondiciones, "Error inesperado al iniciar sesión. Por favor, intente nuevamente.", Toast.LENGTH_LONG).show();
        }
    }
}