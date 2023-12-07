package com.example.ecogardenapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ecogardenapp2.modelos.ArchivosRegistros;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    ArchivosRegistros crearArchivos = new ArchivosRegistros();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crearArchivos.crearArchivo(MainActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
