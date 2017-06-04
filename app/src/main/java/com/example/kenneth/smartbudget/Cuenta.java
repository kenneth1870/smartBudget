package com.example.kenneth.smartbudget;

/**
 * Created by Cristian on 31/05/17.
 */

public class Cuenta {
    String ahorros;
    String gastos;
    String ingresos;
    String monto_actual;
    String nombre;

    public Cuenta() {
    }

    public Cuenta(String ahorros, String gastos, String ingresos, String monto_actual, String nombre) {
        this.ahorros = ahorros;
        this.gastos = gastos;
        this.ingresos = ingresos;
        this.monto_actual = monto_actual;
        this.nombre = nombre;

    }

    public String getAhorros() {
        return ahorros;
    }

    public void setAhorros(String ahorros) {
        this.ahorros = ahorros;
    }

    public String getGastos() {
        return gastos;
    }

    public void setGastos(String gastos) {
        this.gastos = gastos;
    }

    public String getIngresos() {
        return ingresos;
    }

    public void setIngresos(String ingresos) {
        this.ingresos = ingresos;
    }

    public String getMonto_actual() {
        return monto_actual;
    }

    public void setMonto_actual(String monto_actual) {
        this.monto_actual = monto_actual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
