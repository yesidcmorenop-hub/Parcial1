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
    private ArrayList<Inscripcion> listInscripcion;
    private ArrayList<PlanEntrenamiento> listPlanEntrenamiento;
    private ArrayList<Entrenador> listEntrenador;
    private ArrayList<ServicioAdicional> listServiciosAdicionales;

    private static Gimnasio instance;

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
        this.listServiciosAdicionales = new ArrayList<>();
    }

    public static Gimnasio getInstance() {
        if (instance == null) {
            instance = new Gimnasio("SmartGym", 10101001, 628, "contacto@smartgym.com", "www.smartgym.com");
            instance.cargarDatosPrueba();
        }
        return instance;
    }

    private void cargarDatosPrueba() {
        // Instanciación usando el enum TipoServicio y tu constructor
        ServicioAdicional s1 = new ServicioAdicional(
                TipoServicio.VALORACION_FISICA, "SA01", "Valoración Física",
                "Evaluación de composición corporal", 30000.0, true
        );
        ServicioAdicional s2 = new ServicioAdicional(
                TipoServicio.ASESORIA_NUTRICIONAL, "SA02", "Asesoría Nutricional",
                "Plan alimenticio personalizado", 50000.0, true
        );
        ServicioAdicional s3 = new ServicioAdicional(
                TipoServicio.CLASES_ESPECIALES, "SA03", "Clase Especial Spin",
                "Acceso a masterclass de spinning", 20000.0, true
        );

        this.listServiciosAdicionales.add(s1);
        this.listServiciosAdicionales.add(s2);
        this.listServiciosAdicionales.add(s3);
    }

    // --- GETTERS Y SETTERS ---
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono; }

    public int getNit() { return nit; }
    public void setNit(int nit) { this.nit = nit; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPaginaWeb() { return paginaWeb; }
    public void setPaginaWeb(String paginaWeb) { this.paginaWeb = paginaWeb; }

    public ArrayList<Cliente> getListCliente() { return listCliente; }
    public void setListCliente(ArrayList<Cliente> listCliente) { this.listCliente = listCliente; }

    public ArrayList<Inscripcion> getListInscripcion() { return listInscripcion; }
    public void setListInscripcion(ArrayList<Inscripcion> listInscripcion) { this.listInscripcion = listInscripcion; }

    public ArrayList<PlanEntrenamiento> getListPlanEntrenamiento() { return listPlanEntrenamiento; }
    public void setListPlanEntrenamiento(ArrayList<PlanEntrenamiento> listPlanEntrenamiento) { this.listPlanEntrenamiento = listPlanEntrenamiento; }

    public ArrayList<Entrenador> getListEntrenador() { return listEntrenador; }
    public void setListEntrenador(ArrayList<Entrenador> listEntrenador) { this.listEntrenador = listEntrenador; }

    public ArrayList<ServicioAdicional> getListServiciosAdicionales() { return listServiciosAdicionales; }
    public void setListServiciosAdicionales(ArrayList<ServicioAdicional> listServiciosAdicionales) { this.listServiciosAdicionales = listServiciosAdicionales; }

    // --- MÉTODOS DE NEGOCIO ---
    public void registrarServicioAdicional(ServicioAdicional servicio) {
        if (servicio != null && !this.listServiciosAdicionales.contains(servicio)) {
            this.listServiciosAdicionales.add(servicio);
        }
    }

    public Cliente buscarClientePorDocumento(int documento) {
        for (Cliente c : this.listCliente) {
            if (c.getDocumento() == documento) {
                return c;
            }
        }
        return null;
    }

    public Cliente buscarClientePorTelefono(int telefonoBusqueda) {
        for (Cliente cliente : this.listCliente) {
            if (cliente.getTelefono() == telefonoBusqueda) {
                return cliente;
            }
        }
        return null;
    }

    public boolean registrarCliente(Cliente cliente) {
        if (cliente != null && buscarClientePorDocumento(cliente.getDocumento()) == null) {
            this.listCliente.add(cliente);
            return true;
        }
        return false;
    }

    public boolean esNumeroPerfecto(int numero) {
        if (numero <= 1) {
            return false;
        }
        int sumaDivisores = 0;
        for (int i = 1; i <= numero / 2; i++) {
            if (numero % i == 0) {
                sumaDivisores += i;
            }
        }
        return sumaDivisores == numero;
    }

    public double calcularIngresosPeriodo(LocalDate fechaInicio, LocalDate fechaFin) {
        double ingresosTotales = 0.0;
        for (Inscripcion inscripcion : this.listInscripcion) {
            if (inscripcion.estaEnPeriodo(fechaInicio, fechaFin)) {
                ingresosTotales += inscripcion.calcularValorTotal();
            }
        }
        return ingresosTotales;
    }

    public boolean asignarEntrenador(Entrenador entrenador, PlanPersonalizado planPersonalizado) {
        if (entrenador != null && planPersonalizado != null) {
            planPersonalizado.setEntrenadorAsignado(entrenador);
            return true;
        }
        return false;
    }

    public void registrarEntrenador(Entrenador entrenador) {
        if (entrenador != null && !this.listEntrenador.contains(entrenador)) {
            this.listEntrenador.add(entrenador);
        }
    }

    public void registrarPlan(PlanEntrenamiento plan) {
        if (plan != null && !this.listPlanEntrenamiento.contains(plan)) {
            this.listPlanEntrenamiento.add(plan);
        }
    }

    public void registrarInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null && !this.listInscripcion.contains(inscripcion)) {
            this.listInscripcion.add(inscripcion);
        }
    }
}