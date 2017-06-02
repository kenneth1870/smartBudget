package com.example.kenneth.smartbudget;

import java.util.Date;

import static android.R.attr.id;

/**
 * Created by Cristian on 31/05/17.
 */

public class Ahorro {

    String fechaFinal;
    String fechaInicial;
    String id;
    String monto;
    String nombre;
    String periodo;

    public Ahorro(String fechaFinal, String fechaInicial, String id, String monto, String nombre, String periodo) {
        this.fechaFinal = fechaFinal;
        this.fechaInicial = fechaInicial;
        this.id = id;
        this.monto = monto;
        this.nombre = nombre;
        this.periodo = periodo;
    }

    public Ahorro() {
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "Ahorro{" +
                "fechaFinal='" + fechaFinal + '\'' +
                ", fechaInicial='" + fechaInicial + '\'' +
                ", id='" + id + '\'' +
                ", monto='" + monto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", periodo='" + periodo + '\'' +
                '}';
    }
}
