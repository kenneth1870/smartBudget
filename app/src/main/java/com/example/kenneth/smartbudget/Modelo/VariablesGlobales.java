package com.example.kenneth.smartbudget.Modelo;

/**
 * Created by Antonio on 03/06/2017.
 */

public class VariablesGlobales {

    private String mitexto="Hola";

    private static VariablesGlobales instance = null;

    protected VariablesGlobales() {}
    public static VariablesGlobales getInstance() {
        if(instance == null) {instance = new VariablesGlobales(); }
        return instance;
    }
    public String getMitexto() {
        return mitexto;
    }
    public void setMitexto(String mitexto) {
        this.mitexto = mitexto;
    }

}
