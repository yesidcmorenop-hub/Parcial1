package org.example.parcial1.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Gimnasio {
    private String nombre;
    private int nit;
    private int telefono;
    private String correo;
    private String paginaWeb;
    private ArrayList<Cliente> listCliente;
    private ArrayList<Inscripcion>listInscripcion;
    private ArrayList<PlanEntrenamiento>listPlanEntrenamiento;
    private ArrayList<Entrenador>listEntrenador;
    public Gimnasio(String nombre, int nit, int telefono, String correo, String paginaWeb) {
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
        this.correo = correo;
        this.paginaWeb = paginaWeb;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    /**
     * Busca un cliente mediante su número de teléfono.
     */
    public Cliente buscarClientePorTelefono(int telefonoBusqueda) {
        for (Cliente cliente : this.listCliente) {
            if (cliente.getTelefono() == telefonoBusqueda) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Recorre las inscripciones y acumula los ingresos dentro de un período determinado.
     */
    public double calcularIngresosPeriodo(LocalDate fechaInicio, LocalDate fechaFin) {
        double ingresosTotales = 0.0;
        for (Inscripcion inscripcion : this.listInscripcion) {
            if (inscripcion.estaEnPeriodo(fechaInicio, fechaFin)) {
                ingresosTotales += inscripcion.calcularValorTotal();
            }
        }
        return ingresosTotales;
    }

    /**
     * Asigna un entrenador a un plan personalizado.
     */
    public boolean asignarEntrenador(Entrenador entrenador, PlanPersonalizado planPersonalizado) {
        if (entrenador != null && planPersonalizado != null) {
            planPersonalizado.setEntrenadorAsignado(entrenador);
            return true;
        }
        return false;
    }

    public void registrarCliente(Cliente cliente) {
        if (this.listCliente == null) this.listCliente = new ArrayList<>();
        this.listCliente.add(cliente);
    }

    public void registrarEntrenador(Entrenador entrenador) {
        if (this.listEntrenador == null) this.listEntrenador = new ArrayList<>();
        this.listEntrenador.add(entrenador);
    }

    public void registrarPlan(PlanEntrenamiento plan) {
        if (this.listPlanEntrenamiento == null) this.listPlanEntrenamiento = new ArrayList<>();
        this.listPlanEntrenamiento.add(plan);
    }

    public void registrarInscripcion(Inscripcion inscripcion) {
        if (this.listInscripcion == null) this.listInscripcion = new ArrayList<>();
        this.listInscripcion.add(inscripcion);
    }
}
