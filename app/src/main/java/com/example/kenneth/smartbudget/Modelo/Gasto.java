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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gasto gasto = (Gasto) o;

        if (valor_gasto != null ? !valor_gasto.equals(gasto.valor_gasto) : gasto.valor_gasto != null)
            return false;
        if (nombre_gasto != null ? !nombre_gasto.equals(gasto.nombre_gasto) : gasto.nombre_gasto != null)
            return false;
        if (tipo_gasto != null ? !tipo_gasto.equals(gasto.tipo_gasto) : gasto.tipo_gasto != null)
            return false;
        return ubicacion != null ? ubicacion.equals(gasto.ubicacion) : gasto.ubicacion == null;

    }

    @Override
    public int hashCode() {
        int result = valor_gasto != null ? valor_gasto.hashCode() : 0;
        result = 31 * result + (nombre_gasto != null ? nombre_gasto.hashCode() : 0);
        result = 31 * result + (tipo_gasto != null ? tipo_gasto.hashCode() : 0);
        result = 31 * result + (ubicacion != null ? ubicacion.hashCode() : 0);
        return result;
    }
}
