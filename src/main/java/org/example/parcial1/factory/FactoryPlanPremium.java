package org.example.parcial1.factory;

import org.example.parcial1.model.Estado;
import org.example.parcial1.model.PlanEntrenamiento;
import org.example.parcial1.model.PlanPremium;

public class FactoryPlanPremium implements FactoryPlan{

    @Override
    public PlanEntrenamiento crearPlan(String nombre, int codigo, String descripcion, int duracionMeses, double valorMensual, Estado estado) {
        return new PlanPremium(nombre, codigo, descripcion, duracionMeses, valorMensual, estado);
    }
}
