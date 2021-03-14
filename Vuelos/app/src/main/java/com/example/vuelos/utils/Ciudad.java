package com.example.vuelos.utils;

import java.io.Serializable;

public class Ciudad implements Serializable {

    //int imagen;
    String nombre,fecha,hora,imagen;

    public Ciudad( String nombre,String imagen) {
        this.imagen = imagen;
        this.nombre = nombre;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
