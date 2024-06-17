package com.hack.hack.model;

import javax.xml.crypto.Data;
import java.util.Date;

public class Supervisor {

    // Supervidor atributos: idSupervisor, correo, contrasena
    private int idSupervisor;
    private String correo;
    private String contrasena;

    // constructor
    public Supervisor(int idSupervisor, String correo, String contrasena) {
        this.idSupervisor = idSupervisor;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    // getters y setters
    public int getIdSupervisor() {
        return idSupervisor;
    }
    public void setIdSupervisor(int idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // toString
    @Override
    public String toString() {
        return "Supervisor{" +
                "idSupervisor=" + idSupervisor +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
