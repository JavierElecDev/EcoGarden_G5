package com.example.ecogardenapp2.firebasedatabase;

public class Huerto {
    private String nombre;
    private String tamano;
    private String tipo;
    private String descripcion;
    private String usuarioID;

    public Huerto() {

    }

    public Huerto(String nombre, String tamano, String tipo, String descripcion, String IDUser) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.usuarioID = IDUser;
    }

    //Getter y seeter que se requieren para que firebase pueda serializar
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }
}