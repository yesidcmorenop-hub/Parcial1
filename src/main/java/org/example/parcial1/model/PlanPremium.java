package org.example.parcial1.model;

public class PlanPremium extends PlanEntrenamiento {

    public PlanPremium(String nombre, int codigo, String descripcion, int duracionMeses, double valorMensual, Estado estado) {
        super(nombre, codigo, descripcion, duracionMeses, valorMensual, estado);
    }

    @Override
    public double calcularValorBase() {
        double totalSinDescuento = this.duracionMeses * this.valorMensual;
        return totalSinDescuento * 0.90;
    }

    @Override
    public String toString() {
        return "Plan Premium: "+nombre + " ($" + calcularValorBase() + ")";
    }
}
