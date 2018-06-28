package com.example.kenneth.smartbudget.Modelo;

/**
 * Created by Antonio on 31/05/2017.
 */

public class Ingreso {
    String fecha;
    int id;
    String nombre;
    String tipo_ingreso;
    int valor;

    public Ingreso(String fecha, int id, String nombre, String tipo_ingreso, int valor) {
        this.fecha = fecha;
        this.id = id;
        this.nombre = nombre;
        this.tipo_ingreso = tipo_ingreso;
        this.valor = valor;
    }

    public Ingreso() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Ingreso{" +
                "fecha='" + fecha + '\'' +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo_ingreso='" + tipo_ingreso + '\'' +
                ", valor=" + valor +
                '}';
    }
}
