package com.example.kenneth.smartbudget;

/**
 * Created by kenneth on 27/5/17.
 */

public class User {
    String id;
    String name;
    String email;
    String photo_url;
    String lista_cuentas;

    public User() { }

    public User(String id, String name, String email, String photo_url, String lista_cuentas) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photo_url = photo_url;
        this.lista_cuentas = lista_cuentas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getLista_cuentas() {return lista_cuentas; }

    public void setLista_cuentas(String lista_cuentas) {this.lista_cuentas = lista_cuentas;}
}