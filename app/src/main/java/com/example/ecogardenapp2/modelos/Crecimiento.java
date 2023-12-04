package com.example.ecogardenapp2.modelos;

import java.util.Date;

public class Crecimiento {
    private float altura;
    private String fecha;

    public Crecimiento (float altura, String fecha) {
        this.altura = altura;
        this.fecha = fecha;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        altura = altura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
