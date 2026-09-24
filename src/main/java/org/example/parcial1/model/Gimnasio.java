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
    private static Gimnasio instance;

    /**
     * constructor de gimnasio
     * @param nombre de gimnasio
     * @param nit de gimnasio
     * @param telefono de gimnasio
     * @param correo de gimnasio
     * @param paginaWeb de gimnasio
     */
    public Gimnasio(String nombre, int nit, int telefono, String correo, String paginaWeb) {
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
        this.correo = correo;
        this.paginaWeb = paginaWeb;
        this.listCliente = new ArrayList<>();
        this.listInscripcion = new ArrayList<>();
        this.listPlanEntrenamiento = new ArrayList<>();
        this.listEntrenador = new ArrayList<>();
    }

    /**
     * creacion de la instancia unica de Gimnasio
     * @return instancia
     */
    public static Gimnasio getInstance() {
        if (instance == null) {
            instance=new Gimnasio("Gimnacio Pa Flacos",10101001,
                    222,"Gimnacio@","gimnacio.com");
        }
        return instance;
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

    public ArrayList<Cliente> getListCliente() {
        return listCliente;
    }

    public void setListCliente(ArrayList<Cliente> listCliente) {
        this.listCliente = listCliente;
    }

    public ArrayList<Inscripcion> getListInscripcion() {
        return listInscripcion;
    }

    public void setListInscripcion(ArrayList<Inscripcion> listInscripcion) {
        this.listInscripcion = listInscripcion;
    }

    public ArrayList<PlanEntrenamiento> getListPlanEntrenamiento() {
        return listPlanEntrenamiento;
    }

    public void setListPlanEntrenamiento(ArrayList<PlanEntrenamiento> listPlanEntrenamiento) {
        this.listPlanEntrenamiento = listPlanEntrenamiento;
    }

    public ArrayList<Entrenador> getListEntrenador() {
        return listEntrenador;
    }

    public void setListEntrenador(ArrayList<Entrenador> listEntrenador) {
        this.listEntrenador = listEntrenador;
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

    /**
     * metodo para registrar a un cliente
     * @param cliente
     */
    public void registrarCliente(Cliente cliente) {
        if (cliente!= null && !this.listCliente.contains(cliente)) {
            this.listCliente.add(cliente);
        }

    }

    /**
     * metodo para registrar a un entrenador
     * @param entrenador
     */
    public void registrarEntrenador(Entrenador entrenador) {
        if (entrenador != null && !this.listEntrenador.contains(entrenador)) {
            this.listEntrenador.add(entrenador);
        }

    }

    /**
     * metodo para registrar un plan
     * @param plan
     */
    public void registrarPlan(PlanEntrenamiento plan) {
        if (plan != null && !this.listPlanEntrenamiento.contains(plan)) {
            this.listPlanEntrenamiento.add(plan);
        }
    }

    /**
     * metodo para registrar una inscripcion
     * @param inscripcion
     */
    public void registrarInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null && !this.listInscripcion.contains(inscripcion)) {
            this.listInscripcion.add(inscripcion);
        }
    }
}
