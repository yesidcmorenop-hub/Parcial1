package org.example.parcial1.factory;

public enum TipoPlan {
    BASICO("Plan Básico"),
    PREMIUM("Plan Premium"),
    PERSONALIZADO("Plan Personalizado");

    private final String nombreMostrar;

    TipoPlan(String nombreMostrar) {
        this.nombreMostrar = nombreMostrar;
    }

    public String getNombreMostrar() {
        return nombreMostrar;
    }

    @Override
    public String toString() {
        return nombreMostrar;
    }
}
