package org.example.parcial1.model;

import java.util.ArrayList;
import java.util.List;

public abstract class PlanEntrenamiento {
    protected String nombre;
    protected int codigo;
    protected String descripcion;
    protected int duracionMeses;
    protected double valorMensual;
    protected Estado estado;
    protected List<Beneficio> listaBeneficio;


    public PlanEntrenamiento(String nombre, int codigo, String descripcion, int duracionMeses, double valorMensual, Estado estado) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = estado;
        this.listaBeneficio = new ArrayList<>();
    }





    public String getNombre() { return nombre; }
    public int getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public int getDuracionMeses() { return duracionMeses; }
    public double getValorMensual() { return valorMensual; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }



    public void agregarBeneficio(Beneficio beneficio) {
        if (beneficio != null) {
            this.listaBeneficio.add(beneficio);
        }
        }
    /**
     * Método para calcular el valor base del plan según su tipo.
     */
    public abstract double calcularValorBase();
}

