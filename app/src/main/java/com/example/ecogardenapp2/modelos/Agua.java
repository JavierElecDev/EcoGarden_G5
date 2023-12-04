package com.example.ecogardenapp2.modelos;

import java.time.LocalDate;
import java.util.Date;

public class Agua {
    private float cantidad;
    private float precio;
    private String fecha;

    public Agua(float cantidad, float precio, String fecha) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha = fecha;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}