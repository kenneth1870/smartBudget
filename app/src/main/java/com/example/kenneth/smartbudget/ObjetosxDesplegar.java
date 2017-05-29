package com.example.kenneth.smartbudget;

public class ObjetosxDesplegar {


    private String atributo01;
    private String atributo02;
    private String NumDibujo;

    public ObjetosxDesplegar(String atributo01, String atributo02, String NumDibujo) {
        super();
        this.atributo01 = atributo01;
        this.atributo02 = atributo02;
        this.NumDibujo = NumDibujo;
    }

    public String getAtributo01() {
        return atributo01;
    }

    public String getAtributo02() {
        return atributo02;
    }

    public String getNumDibujo() {
        return NumDibujo;
    }

    public void setAtributo01(String atributo01) {
        this.atributo01 = atributo01;
    }
}
