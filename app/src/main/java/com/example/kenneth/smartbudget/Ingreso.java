package com.example.kenneth.smartbudget;

/**
 * Created by Cristian on 31/05/17.
 */

public class Ingreso {

    String fecha;
    String nombre;
    String tipo_ingreso;
    String valor;

    public Ingreso() {
    }

    public Ingreso(String fecha, String nombre, String tipo_ingreso, String valor) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.tipo_ingreso = tipo_ingreso;
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_ingreso() {
        return tipo_ingreso;
    }

    public void setTipo_ingreso(String tipo_ingreso) {
        this.tipo_ingreso = tipo_ingreso;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
