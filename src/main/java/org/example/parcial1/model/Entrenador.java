package org.example.parcial1.model;

public class Entrenador extends Persona {
    private String especialidad;
    private double tarifa;

    public Entrenador(String nombre, int documento, int telefono, String especialidad, double tarifa) {
        super(nombre, documento, telefono);
        this.especialidad = especialidad;
        this.tarifa = tarifa;
    }
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "-- Especialidad: " + especialidad;
    }
}
