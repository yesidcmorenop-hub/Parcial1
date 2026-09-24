package org.example.parcial1.model;

public class PlanPremium extends PlanEntrenamiento{
    @Override
    public double calcularValorBase() {
        // Ejemplo de regla de negocio: Aplica un 10% de descuento por ser Premium
        double totalSinDescuento = this.duracionMeses * this.valorMensual;
        return totalSinDescuento * 0.90;
    }
}
