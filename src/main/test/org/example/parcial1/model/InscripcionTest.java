package org.example.parcial1.model;

import org.example.parcial1.model.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class InscripcionTest {

    @Test
    public void testCalcularValorTotalConServiciosAdicionales() {

        Cliente cliente = new Cliente("Ana", 1010, 3001234, "ana@mail.com", 25, LocalDate.now());


        PlanBasico plan = new PlanBasico("Plan Estándar", 101, "Acceso a máquinas", 1, 50000.0, Estado.ACTIVO);

        Inscripcion inscripcion = new Inscripcion(LocalDate.now(), cliente, plan);

        ServicioAdicional servicio1 = new ServicioAdicional(
                TipoServicio.VALORACION_FISICA, "1234", "Locker", "Uso de locker por mes", 10000.0, true
        );

        ServicioAdicional servicio2 = new ServicioAdicional(
                TipoServicio.CLASES_ESPECIALES, "15665", "Toalla", "Servicio de toalla limpia", 15000.0, true
        );

        inscripcion.agregarServicioAdicional(servicio1);
        inscripcion.agregarServicioAdicional(servicio2);

        double totalEsperado = 75000.0;
        assertEquals(totalEsperado, inscripcion.calcularValorTotal(), 0.01, "El cálculo del valor total con servicios adicionales es incorrecto.");
    }

    @Test
    public void testEstaEnPeriodoValido() {

        LocalDate fechaInscripcion = LocalDate.of(2024, 5, 15);
        Inscripcion inscripcion = new Inscripcion(fechaInscripcion, null, null);

        LocalDate fechaInicio = LocalDate.of(2024, 5, 1);
        LocalDate fechaFin = LocalDate.of(2024, 5, 31);

        assertTrue(inscripcion.estaEnPeriodo(fechaInicio, fechaFin), "La fecha 15 de Mayo debería estar dentro del rango del mes de Mayo.");
    }

    @Test
    public void testEstaEnPeriodoFueraDeRango() {

        LocalDate fechaInscripcion = LocalDate.of(2024, 6, 10);
        Inscripcion inscripcion = new Inscripcion(fechaInscripcion, null, null);

        LocalDate fechaInicio = LocalDate.of(2024, 5, 1);
        LocalDate fechaFin = LocalDate.of(2024, 5, 31);

        assertFalse(inscripcion.estaEnPeriodo(fechaInicio, fechaFin), "La fecha 10 de Junio NO debería estar en el rango de Mayo.");
    }
}
