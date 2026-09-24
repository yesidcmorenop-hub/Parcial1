package org.example.parcial1.factory;

import org.example.parcial1.model.Estado;
import org.example.parcial1.model.PlanBasico;
import org.example.parcial1.model.PlanEntrenamiento;

public class FactoryPLanBasico implements FactoryPlan {

    @Override
    public PlanEntrenamiento crearPlan(String nombre, int codigo, String descripcion, int duracionMeses, double valorMensual, Estado estado) {
        return new PlanBasico(nombre, codigo, descripcion, duracionMeses, valorMensual, estado);
    }
}
