package org.example.parcial1.model;

import java.util.ArrayList;

public class Gimnasio {
    private String nombre;
    private int nit;
    private int telefono;
    private String correo;
    private String paginaWeb;
    private ArrayList<Cliente> listCliente;
    private ArrayList<Inscripcion>listInscripcion;
    private ArrayList<PlanEntrenamiento>listPlanEntrenamiento;
    private ArrayList<Entrenador>listEntrenador;
    public Gimnasio(String nombre, int nit, int telefono, String correo, String paginaWeb) {
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
        this.correo = correo;
        this.paginaWeb = paginaWeb;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }
}
