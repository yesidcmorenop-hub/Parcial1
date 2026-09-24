package org.example.parcial1.model;

public class PlanPersonalizado extends PlanEntrenamiento{
    private int cantidadSeciones;
    private String especialidad;
    private String objetivosCliente;

    public PlanPersonalizado(int cantidadSeciones, String especialidad, String objetivosCliente) {
        this.cantidadSeciones = cantidadSeciones;
        this.especialidad = especialidad;
        this.objetivosCliente = objetivosCliente;
    }

    public int getCantidadSeciones() {
        return cantidadSeciones;
    }

    public void setCantidadSeciones(int cantidadSeciones) {
        this.cantidadSeciones = cantidadSeciones;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getObjetivosCliente() {
        return objetivosCliente;
    }

    public void setObjetivosCliente(String objetivosCliente) {
        this.objetivosCliente = objetivosCliente;
    }
}
