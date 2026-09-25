package org.example.parcial1.factory;

import org.example.parcial1.model.Entrenador;
import org.example.parcial1.model.Estado;
import org.example.parcial1.model.PlanEntrenamiento;
import org.example.parcial1.model.PlanPersonalizado;

public class FactoryPLanPersonalizado implements FactoryPlan{

    private int cantidadSesiones;
    private String especialidad;
    private String objetivosCliente;
    private Entrenador entrenadorAsignado;

    public FactoryPLanPersonalizado() {}

    public FactoryPLanPersonalizado(int cantidadSesiones, String especialidad,
                                    String objetivosCliente, Entrenador entrenadorAsignado) {
        this.cantidadSesiones = cantidadSesiones;
        this.especialidad = especialidad;
        this.objetivosCliente = objetivosCliente;
        this.entrenadorAsignado = entrenadorAsignado;
    }

    @Override
    public PlanEntrenamiento crearPlan(String nombre, int codigo, String descripcion,
                                       int duracionMeses, double valorMensual, Estado estado) {
        return new PlanPersonalizado.Builder()
                .nombre(nombre)
                .codigo(codigo)
                .descripcion(descripcion)
                .duracionMeses(duracionMeses)
                .valorMensual(valorMensual)
                .estado(estado)
                .cantidadSesiones(this.cantidadSesiones)
                .especialidad(this.especialidad)
                .objetivosCliente(this.objetivosCliente)
                .entrenadorAsignado(this.entrenadorAsignado)
                .build();
    }
}
