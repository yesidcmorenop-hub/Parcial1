package org.example.parcial1.model;


import org.example.parcial1.model.Entrenador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    public class EntrenadorTest {

        @Test
        public void testCrearEntrenadorYGetters() {
            Entrenador entrenador = new Entrenador("Laura Gómez", 123456, 300123456, "Crossfit", 15000.0);

            assertEquals("Laura Gómez", entrenador.getNombre());
            assertEquals(123456, entrenador.getDocumento());
            assertEquals(300123456, entrenador.getTelefono());
            assertEquals("Crossfit", entrenador.getEspecialidad());
            assertEquals(15000.0, entrenador.getTarifa(), 0.01);
        }

        @Test
        public void testSettersEntrenador() {
            Entrenador entrenador = new Entrenador("Sin Nombre", 0, 0, "General", 0.0);

            entrenador.setEspecialidad("Pilates");
            entrenador.setTarifa(25000.0);

            assertEquals("Pilates", entrenador.getEspecialidad(), "La especialidad debió actualizarse a Pilates.");
            assertEquals(25000.0, entrenador.getTarifa(), 0.01, "La tarifa debió actualizarse a 25000.0.");
        }
    }
