package com.example.vuelos.utils;

import java.io.Serializable;

public class Vuelos implements Serializable {

    String salida,imagen,fsalida,hsalida,regreso,imagen2,fregreso,hregreso;

    public Vuelos(String salida, String imagen, String fsalida, String hsalida, String regreso, String imagen2, String fregreso, String hregreso) {
        this.salida = salida;
        this.imagen = imagen;
        this.fsalida = fsalida;
        this.hsalida = hsalida;
        this.regreso = regreso;
        this.imagen2 = imagen2;
        this.fregreso = fregreso;
        this.hregreso = hregreso;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFsalida() {
        return fsalida;
    }

    public void setFsalida(String fsalida) {
        this.fsalida = fsalida;
    }

    public String getHsalida() {
        return hsalida;
    }

    public void setHsalida(String hsalida) {
        this.hsalida = hsalida;
    }

    public String getRegreso() {
        return regreso;
    }

    public void setRegreso(String regreso) {
        this.regreso = regreso;
    }

    public String getImagen2() {
        return imagen2;
    }

    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }

    public String getFregreso() {
        return fregreso;
    }

    public void setFregreso(String fregreso) {
        this.fregreso = fregreso;
    }

    public String getHregreso() {
        return hregreso;
    }

    public void setHregreso(String hregreso) {
        this.hregreso = hregreso;
    }

    @Override
    public String toString() {
        return "Vuelos{" +
                "salida='" + salida + '\'' +
                ", fsalida='" + fsalida + '\'' +
                ", hsalida='" + hsalida + '\'' +
                ", imagen2='" + imagen2 + '\'' +
                ", fregreso='" + fregreso + '\'' +
                '}';
    }
}
