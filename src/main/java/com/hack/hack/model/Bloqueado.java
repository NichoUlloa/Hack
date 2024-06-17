package com.hack.hack.model;

public class Bloqueado {
    // Bloqueado atributos: correo
    private String correo;

    // constructor
    public Bloqueado(String correo) {
        this.correo = correo;
    }

    // getters y setters
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // toString
    @Override
    public String toString() {
        return "Bloqueados{" +
                "correo='" + correo + '\'' +
                '}';
    }
}
