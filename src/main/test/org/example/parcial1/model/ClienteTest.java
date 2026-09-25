
package org.example.parcial1.model;

import org.example.parcial1.model.Cliente;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

    public class ClienteTest {

        @Test
        public void testTelefonoPerfectoValido() {
            Cliente cliente = new Cliente("Carlos", 1010, 28, "carlos@mail.com", 25, LocalDate.now());
            boolean esPerfecto = cliente.telefonoPerfecto();
            assertTrue(esPerfecto, "El numero de telefono 28 deberia ser identificado como perfecto.");
        }

        @Test
        public void testTelefonoPerfectoInvalido() {
            Cliente cliente = new Cliente("Ana", 2020, 3124567, "ana@mail.com", 30, LocalDate.now());

            boolean esPerfecto = cliente.telefonoPerfecto();

            assertFalse(esPerfecto, "El numero de telefono 3124567 NO deberia ser perfecto.");
        }
    }

