package org.example.parcial1.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Inscripcion {
    private LocalDate fechaInscripcion;
    private Cliente cliente;
    private PlanEntrenamiento planEntrenamiento;
    private ArrayList<ServicioAdicional> listServiciosAdicionales;
    /**
     * Calcula el costo total final considerando el tipo de plan y los servicios adicionales agregados.
     */
    public double calcularValorTotal() {
        double total = 0.0;
        if (this.planEntrenamiento != null) {
            total += this.planEntrenamiento.calcularValorBase();
        }
        if (this.listServiciosAdicionales != null) {
            for (ServicioAdicional servicio : this.listServiciosAdicionales) {
                total += servicio.getPrecio();
            }
        }

        return total;
    }

    /**
     * Determina si la inscripción se realizó dentro del periodo consultado.
     */
    public boolean estaEnPeriodo(LocalDate fechaInicio, LocalDate fechaFin) {
        if (this.fechaInscripcion == null) {
            return false;
        }
        return !this.fechaInscripcion.isBefore(fechaInicio) && !this.fechaInscripcion.isAfter(fechaFin);
    }
}
