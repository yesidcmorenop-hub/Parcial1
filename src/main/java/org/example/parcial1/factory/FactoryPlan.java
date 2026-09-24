package org.example.parcial1.factory;

import org.example.parcial1.model.*;

import java.util.List;

public interface FactoryPlan {
    PlanEntrenamiento crearPlan(String nombre, int codigo, String descripcion,
                                int duracionMeses, double valorMensual, Estado estado);
}