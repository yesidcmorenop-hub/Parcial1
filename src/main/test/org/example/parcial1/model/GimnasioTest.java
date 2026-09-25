package org.example.parcial1.model;

import org.example.parcial1.model.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class GimnasioTest {

    @Test
    public void testCalcularIngresosPorPeriodo() {

        Gimnasio gimnasio = Gimnasio.getInstance();
        gimnasio.getListInscripcion().clear();

        Cliente cliente = new Cliente("Carlos", 1010, 28, "carlos@mail.com", 25, LocalDate.now());
        PlanBasico plan = new PlanBasico("Plan Basico", 101, "Acceso gym", 1, 60000.0, Estado.ACTIVO);

        // Inscripción DENTRO del periodo (10 de Mayo - $60,000)
        Inscripcion inscripcionDentro = new Inscripcion(LocalDate.of(2024, 5, 10), cliente, plan);

        // Inscripción FUERA del periodo (10 de Enero - $60,000)
        Inscripcion inscripcionFuera = new Inscripcion(LocalDate.of(2024, 1, 10), cliente, plan);

        gimnasio.getListInscripcion().add(inscripcionDentro);
        gimnasio.getListInscripcion().add(inscripcionFuera);

        LocalDate fechaInicio = LocalDate.of(2024, 5, 1);
        LocalDate fechaFin = LocalDate.of(2024, 5, 31);

        double ingresosCalculados = 0.0;
        for (Inscripcion inscripcion : gimnasio.getListInscripcion()) {
            if (inscripcion.estaEnPeriodo(fechaInicio, fechaFin)) {
                ingresosCalculados += inscripcion.calcularValorTotal();
            }
        }

        // Assert: Solo debe sumar la de Mayo ($60,000)
        assertEquals(60000.0, ingresosCalculados, 0.01, "El cálculo de ingresos por periodo no filtró correctamente las fechas.");
    }
}