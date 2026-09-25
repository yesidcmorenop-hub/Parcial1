package org.example.parcial1.model;

import org.example.parcial1.factory.TipoPlan;

import java.util.List;

public enum Beneficio {
        ACCESO_MAQUINAS("Acceso a zona de pesas y cardio"),
        LOCKER_GRATIS("Uso de locker individual"),
        ZONAS_HUMEDAS("Acceso a sauna y turco"),
        CLASES_GRUPALES("Acceso ilimitado a clases grupales"),
        ENTRENADOR_PERSONAL("Sesiones con entrenador dedicado"),
        NUTRICIONISTA("Asesoría nutricional mensual");

        private final String descripcion;

        Beneficio(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        // Retorna la lista automática según el tipo de plan
        public static List<Beneficio> obtenerPorPlan(TipoPlan tipo) {
            switch (tipo) {
                case BASICO:
                    return List.of(ACCESO_MAQUINAS, LOCKER_GRATIS);
                case PREMIUM:
                    return List.of(ACCESO_MAQUINAS, LOCKER_GRATIS, ZONAS_HUMEDAS, CLASES_GRUPALES);
                case PERSONALIZADO:
                    return List.of(ACCESO_MAQUINAS, ENTRENADOR_PERSONAL, NUTRICIONISTA);
                default:
                    return List.of();
            }
        }
    }

