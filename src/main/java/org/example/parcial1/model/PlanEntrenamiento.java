package org.example.parcial1.model;

public abstract class PlanEntrenamiento {
    protected String nombre;
    protected int codigo;
    protected String descripcion;
    protected int duracionMeses;
    protected double valorMensual;
    protected Estado estado;
    /**
     * Método para calcular el valor base del plan según su tipo.
     */
    public abstract double calcularValorBase();
}
