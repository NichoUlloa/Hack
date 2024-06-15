package com.hack.hack.model;

import java.util.Date;

public class Participante {

    // atributos: id, nombreCompleto, contrasena, correo, numeroContacto, Rut, fechaAsignada, codigoEntrada.
    private int idParticipante;
    private String nombreCompleto;
    private String contrasena;
    private String correo;
    private String numeroContacto;
    private String Rut;
    private Date fechaAsignada;
    private String codigoEntrada;

    // constructor
    public Participante(int idParticipante, String nombreCompleto, String contrasena, String correo, String numeroContacto, String Rut, Date fechaAsignada, String codigoEntrada) {
        this.idParticipante = idParticipante;
        this.nombreCompleto = nombreCompleto;
        this.contrasena = contrasena;
        this.correo = correo;
        this.numeroContacto = numeroContacto;
        this.Rut = Rut;
        this.fechaAsignada = fechaAsignada;
        this.codigoEntrada = codigoEntrada;
    }

    // getters y setters
    public int getIdParticipante() {
        return idParticipante;
    }
    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }
    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public String getRut() {
        return Rut;
    }
    public void setRut(String rut) {
        Rut = rut;
    }

    public Date getFechaAsignada() {
        return fechaAsignada;
    }
    public void setFechaAsignada(Date fechaAsignada) {
        this.fechaAsignada = fechaAsignada;
    }

    public String getCodigoEntrada() {
        return codigoEntrada;
    }
    public void setCodigoEntrada(String codigoEntrada) {
        this.codigoEntrada = codigoEntrada;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "idParticipante=" + idParticipante +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", correo='" + correo + '\'' +
                ", numeroContacto='" + numeroContacto + '\'' +
                ", Rut='" + Rut + '\'' +
                ", fechaAsignada=" + fechaAsignada +
                ", codigoEntrada='" + codigoEntrada + '\'' +
                '}';
    }
}
