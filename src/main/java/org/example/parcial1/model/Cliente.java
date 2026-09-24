package org.example.parcial1.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente extends Persona{
    private String correo;
    private int edad;
    private LocalDate fechaIngreso;
    private ArrayList<PlanEntrenamiento> listPlanEntrenamiento;
    private ArrayList<Inscripcion> listInscripcion;


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
    /**
     *  Metodo que verifica si el numero de teléfono del cliente es un numero perfecto.
     *
     */
    public boolean telefonoPerfecto() {
        if (this.telefono <= 1) {
            return false;
        }
        int sumaDivisores = 0;
        for (int i = 1; i <= this.telefono / 2; i++) {
            if (this.telefono % i == 0) {
                sumaDivisores += i;
            }
        }
        return sumaDivisores == this.telefono;
    }
}
