package org.example.parcial1.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente extends Persona{
    private String correo;
    private int edad;
    private LocalDate fechaIngreso;
    private ArrayList<Planes> listPlanes;
    private ArrayList<Inscripcion> listInscripciones;


    public Cliente(String nombre, int documento, int telefono, String correo, int edad, LocalDate fechaIngreso) {
        super(nombre, documento, telefono);
        this.correo = correo;
        this.edad = edad;
        this.fechaIngreso = fechaIngreso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
