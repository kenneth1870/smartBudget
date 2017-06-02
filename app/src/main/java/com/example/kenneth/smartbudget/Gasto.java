package com.example.kenneth.smartbudget;

/**
 * Created by Cristian on 31/05/17.
 */

public class Gasto {
    String fechas;
    String nombre;
    String ubicacion;
    String valor;

    public Gasto() {
    }

    public Gasto(String fechas, String nombre, String ubicacion, String valor) {
        this.fechas = fechas;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.valor = valor;
    }

    public String getFechas() {
        return fechas;
    }

    public void setFechas(String fechas) {
        this.fechas = fechas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
