package com.example.ecogardenapp2.modelos;

public class Energia {
    private float kilovatios;
    private float horas;
    private float precio;
    private String fecha;

    public Energia (float kilovatios, float horas, float precio, String fecha) {
        this.kilovatios = kilovatios;
        this.horas = horas;
        this.precio = precio;
        this.fecha = fecha;
    }

    public float getKilovatios() {
        return kilovatios;
    }
    public void setKilovatios(float kilovatios) {
        this.kilovatios = kilovatios;
    }

    public float getHoras() {
        return horas;
    }
    public void setHoras(float horas) {
        this.horas = horas;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}