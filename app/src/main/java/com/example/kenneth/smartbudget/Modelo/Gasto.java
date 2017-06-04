package com.example.kenneth.smartbudget.Modelo;

/**
 * Created by Antonio on 31/05/2017.
 */

public class Gasto {


    String valor_gasto;
    String nombre_gasto;
    String tipo_gasto;
    String ubicacion;



    public Gasto() {
    }

    public Gasto(String valor_gasto, String nombre_gasto, String tipo_gasto, String ubicacion) {
        this.valor_gasto = valor_gasto;
        this.nombre_gasto = nombre_gasto;
        this.tipo_gasto = tipo_gasto;
        this.ubicacion = ubicacion;
    }

    public String getValor_gasto() {
        return valor_gasto;
    }

    public void setValor_gasto(String valor_gasto) {
        this.valor_gasto = valor_gasto;
    }

    public String getNombre_gasto() {
        return nombre_gasto;
    }

    public void setNombre_gasto(String nombre_gasto) {
        this.nombre_gasto = nombre_gasto;
    }

    public String getTipo_gasto() {
        return tipo_gasto;
    }

    public void setTipo_gasto(String tipo_gasto) {
        this.tipo_gasto = tipo_gasto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
