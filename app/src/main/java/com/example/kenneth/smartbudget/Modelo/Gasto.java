package com.example.kenneth.smartbudget.Modelo;

/**
 * Created by Antonio on 31/05/2017.
 */

public class Gasto {
    String fecha_gasto;
    int id;
    String nombre_gasto;
    String ubicacion;
    int valor_gasto;

    public Gasto() {
    }

    public Gasto(String fecha_gasto, int id, String nombre_gasto, String ubicacion, int valor_gasto) {
        this.fecha_gasto = fecha_gasto;
        this.id = id;
        this.nombre_gasto = nombre_gasto;
        this.ubicacion = ubicacion;
        this.valor_gasto = valor_gasto;
    }

    public String getFecha_gasto() {
        return fecha_gasto;
    }

    public void setFecha_gasto(String fecha_gasto) {
        this.fecha_gasto = fecha_gasto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_gasto() {
        return nombre_gasto;
    }

    public void setNombre_gasto(String nombre_gasto) {
        this.nombre_gasto = nombre_gasto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getValor_gasto() {
        return valor_gasto;
    }

    public void setValor_gasto(int valor_gasto) {
        this.valor_gasto = valor_gasto;
    }

    @Override
    public String toString() {
        return "Gasto{" +
                "fecha_gasto='" + fecha_gasto + '\'' +
                ", id=" + id +
                ", nombre_gasto='" + nombre_gasto + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", valor_gasto=" + valor_gasto +
                '}';
    }
}
