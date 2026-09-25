package org.example.parcial1.model;

public class PlanBasico extends PlanEntrenamiento {

    public PlanBasico(String nombre, int codigo, String descripcion, int duracionMeses, double valorMensual, Estado estado) {
        super(nombre, codigo, descripcion, duracionMeses, valorMensual, estado);
    }

    @Override
    public double calcularValorBase() {
        return this.duracionMeses * this.valorMensual;
    }

    @Override
    public String toString() {
        return "Plan Basico: "+nombre + " ($" + calcularValorBase() + ")";
    }
}
