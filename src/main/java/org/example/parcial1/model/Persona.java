package org.example.parcial1.model;

public abstract class Persona {
    private String nombre;
    private int documento;
    private int telefono;

    public Persona(String nombre, int documento, int telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

}
