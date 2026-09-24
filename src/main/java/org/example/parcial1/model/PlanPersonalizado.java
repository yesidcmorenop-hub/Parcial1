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

    // Atributo para asociar el entrenador al plan personalizado

    private Entrenador entrenadorAsignado;

    public Entrenador getEntrenadorAsignado() {
        return entrenadorAsignado;
    }

    public void setEntrenadorAsignado(Entrenador entrenadorAsignado) {
        this.entrenadorAsignado = entrenadorAsignado;
    }

    @Override
    public double calcularValorBase() {
        double valorPlan = this.duracionMeses * this.valorMensual;
        double costoSesiones = 0.0;
        if (this.entrenadorAsignado != null) {
            costoSesiones = this.cantidadSeciones * this.entrenadorAsignado.getTarifa();
        }
        return valorPlan + costoSesiones;
    }
}
