package org.example.parcial1.model;

import java.util.ArrayList;
import java.util.List;

public class PlanPersonalizado extends PlanEntrenamiento {
    private int cantidadSesiones;
    private String especialidad;
    private String objetivosCliente;
    private Entrenador entrenadorAsignado;

    public PlanPersonalizado(Builder builder) {
        super(builder.nombre, builder.codigo, builder.descripcion,
                builder.duracionMeses, builder.valorMensual, builder.estado);
        this.cantidadSesiones = builder.cantidadSesiones;
        this.especialidad = builder.especialidad;
        this.objetivosCliente = builder.objetivosCliente;
        this.entrenadorAsignado = builder.entrenadorAsignado;
    }

    public int getCantidadSesiones() { return cantidadSesiones; }
    public void setCantidadSesiones(int cantidadSesiones) { this.cantidadSesiones = cantidadSesiones; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getObjetivosCliente() { return objetivosCliente; }
    public void setObjetivosCliente(String objetivosCliente) { this.objetivosCliente = objetivosCliente; }
    public Entrenador getEntrenadorAsignado() { return entrenadorAsignado; }
    public void setEntrenadorAsignado(Entrenador entrenadorAsignado) { this.entrenadorAsignado = entrenadorAsignado; }

    @Override
    public double calcularValorBase() {
        double valorPlan = this.duracionMeses * this.valorMensual;
        double costoSesiones = 0.0;
        if (this.entrenadorAsignado != null) {
            costoSesiones = this.cantidadSesiones * this.entrenadorAsignado.getTarifa();
        }
        return valorPlan + costoSesiones;
    }

    public static class Builder {
        private String nombre;
        private int codigo;
        private String descripcion;
        private int duracionMeses;
        private double valorMensual;
        private Estado estado;
        private int cantidadSesiones;
        private String especialidad;
        private String objetivosCliente;
        private Entrenador entrenadorAsignado;

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder codigo(int codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder duracionMeses(int duracionMeses) {
            this.duracionMeses = duracionMeses;
            return this;
        }

        public Builder valorMensual(double valorMensual) {
            this.valorMensual = valorMensual;
            return this;
        }

        public Builder estado(Estado estado) {
            this.estado = estado;
            return this;
        }

        public Builder cantidadSesiones(int cantidadSesiones) {
            this.cantidadSesiones = cantidadSesiones;
            return this;
        }

        public Builder especialidad(String especialidad) {
            this.especialidad = especialidad;
            return this;
        }

        public Builder objetivosCliente(String objetivosCliente) {
            this.objetivosCliente = objetivosCliente;
            return this;
        }

        public Builder entrenadorAsignado(Entrenador entrenadorAsignado) {
            this.entrenadorAsignado = entrenadorAsignado;
            return this;
        }

        public PlanPersonalizado build() {
            return new PlanPersonalizado(this);
        }
    }
}
