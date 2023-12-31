package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ecogardenapp2.clasesLogReg.ComprobacionesLogReg;
import com.example.ecogardenapp2.clasesLogReg.DatosRegistro;
import com.example.ecogardenapp2.clasesLogReg.SpinCiudades;

public class RegistroUsuario extends AppCompatActivity {

    private EditText Nombres, Apellidos, Direccion, Telefono, CorreoE;
    private EditText valCorreoE, Password, valPassword;
    private Spinner ciudades;
    private Button continuarRegistro;
    private String nombres, apellidos, direccion, telefono, correoElec;
    private String validaCorreoE, password, validaPassword;
    private boolean comprobacionPaso1, comprobacionPaso2;
    SpinCiudades ciudad = new SpinCiudades();

    // Chicas se instanciar un objeto para obtener el contexto y llamar los alert dialog
    ComprobacionesLogReg comprobar = new ComprobacionesLogReg(RegistroUsuario.this);

    //Se instancia un objeto de la clase "DatosRegistro" para guardar datos
    DatosRegistro datosTemporales = new DatosRegistro();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        Nombres = (EditText) findViewById(R.id.reg_nombre);
        Apellidos = (EditText) findViewById(R.id.reg_Apellidos);
        Direccion = (EditText) findViewById(R.id.reg_direccion);
        Telefono = (EditText) findViewById(R.id.reg_telefono);
        CorreoE = (EditText) findViewById(R.id.reg_correoE);
        valCorreoE = (EditText) findViewById(R.id.reg_conf_correoE);
        Password = (EditText) findViewById(R.id.reg_contrasena);
        valPassword = (EditText) findViewById(R.id.reg_conf_contrasena);
        continuarRegistro = (Button) findViewById(R.id.registroContinuar);
        ciudades = (Spinner) findViewById(R.id.reg_spinner_ciudad);
        ciudades.setAdapter(ciudad.adaptadorCiudades(RegistroUsuario.this));

        ImageButton back = findViewById(R.id.btn_back);

        continuarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombres = Nombres.getText().toString().trim();
                apellidos = Apellidos.getText().toString().trim();
                direccion = Direccion.getText().toString().trim();
                telefono = Telefono.getText().toString().trim();
                correoElec = CorreoE.getText().toString().trim();
                validaCorreoE = valCorreoE.getText().toString().trim();
                password = Password.getText().toString();
                validaPassword = valPassword.getText().toString();


                comprobacionPaso1 = comprobar.comprobarCamposRegistro(nombres,apellidos,direccion,
                        telefono,correoElec, validaCorreoE,password,validaPassword);

                if(comprobacionPaso1){
                    comprobacionPaso2 = comprobar.comprobarDatosDeLogueo(correoElec,validaCorreoE,password, validaPassword);
                };

                if(comprobacionPaso2){
                    Toast.makeText(RegistroUsuario.this, "Comprobcion Correcta puede continuar...", Toast.LENGTH_LONG).show();
                    long telefonocontacto = Long.parseLong(telefono);
                    String ciudadIngresada = String.valueOf(ciudades.getSelectedItem());

                    datosTemporales.almacenarDatos(nombres,apellidos,ciudadIngresada,direccion,
                            correoElec, password, telefonocontacto);

                Intent continuarRegistro = new Intent(RegistroUsuario.this, TerminosCondiciones.class);
                /*
                 *el metodo "putExtra" lo usamos para enviar esta instancia del objeto a la nueva actividad,
                 * y poder obtener los datos desde esa actividad.
                 */
                continuarRegistro.putExtra("datosTemporales", datosTemporales);
                startActivity(continuarRegistro);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(RegistroUsuario.this, Login.class);
                startActivity(backActividad);
            }
        });
    }
}