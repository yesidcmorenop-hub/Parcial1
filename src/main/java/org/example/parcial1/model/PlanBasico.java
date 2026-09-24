package org.example.parcial1.model;

public class PlanBasico extends PlanEntrenamiento{
    @Override
    public double calcularValorBase() {
        return this.duracionMeses * this.valorMensual;
    }
}
