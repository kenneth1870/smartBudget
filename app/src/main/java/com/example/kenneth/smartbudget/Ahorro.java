package com.example.kenneth.smartbudget;

import java.util.Date;

import static android.R.attr.id;

/**
 * Created by Cristian on 31/05/17.
 */

public class Ahorro {

    String nombre;
    String objetivo;
    String fechaFinal;
    String montoInicial;
    String tipoMoneda;
    int id;


    public Ahorro() {
    }

    public Ahorro(String nombre, String objetivo, String fechaFinal, String montoInicial, String tipoMoneda, int id) {
        this.nombre = nombre;
        this.objetivo = objetivo;
        this.fechaFinal = fechaFinal;
        this.montoInicial = montoInicial;
        this.tipoMoneda = tipoMoneda;
        this.id=id;
    }

    public String getNombre() {return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getObjetivo() { return objetivo; }

    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public String getFechaFinal() { return fechaFinal; }

    public void setFechaFinal(String fechaFinal) { this.fechaFinal = fechaFinal; }

    public String getMontoInicial() { return montoInicial;  }

    public void setMontoInicial(String montoInicial) {this.montoInicial = montoInicial; }

    public String getTipoMoneda() { return tipoMoneda; }

    public void setTipoMoneda(String tipoMoneda) { this.tipoMoneda = tipoMoneda;  }

    public int getId() { return id; }

    public void setId(int id) { this.id = id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ahorro ahorro = (Ahorro) o;

        if (!nombre.equals(ahorro.nombre)) return false;
        if (!objetivo.equals(ahorro.objetivo)) return false;
        if (!fechaFinal.equals(ahorro.fechaFinal)) return false;
        if (!montoInicial.equals(ahorro.montoInicial)) return false;
        return tipoMoneda.equals(ahorro.tipoMoneda);

    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + objetivo.hashCode();
        result = 31 * result + fechaFinal.hashCode();
        result = 31 * result + montoInicial.hashCode();
        result = 31 * result + tipoMoneda.hashCode();
        return result;
    }
}
