package org.example.parcial1.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente extends Persona {
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
        this.listPlanEntrenamiento = new ArrayList<>();
        this.listInscripcion = new ArrayList<>();
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

    public ArrayList<PlanEntrenamiento> getListPlanEntrenamiento() {
        return listPlanEntrenamiento;
    }

    public ArrayList<Inscripcion> getListInscripcion() {
        return listInscripcion;
    }

    /**
     *  Metodo que verifica si el numero de teléfono del cliente es un numero perfecto.
     *
     */
    public boolean telefonoPerfecto() {
        long num = this.telefono;
        if (num <= 1) {
            return false;
        }
        long sumaDivisores = 0;
        for (long i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                sumaDivisores += i;
            }
        }
        return sumaDivisores == num;
    }
}
