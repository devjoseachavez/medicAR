
package service;

import ar.com.chavezdrive.medicar.model.Turno;
import ar.com.chavezdrive.medicar.repository.TurnoRepository;
import ar.com.chavezdrive.medicar.service.TurnoService;
import exceptions.TurnoOcupadoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TurnoServiceTest {

    @Mock
    private TurnoRepository turnoRepository; // Simulamos el repo

    @InjectMocks
    private TurnoService turnoService; // Inyectamos el repo simulado en el servicio

    @Test
    public void cuandoTurnoEstaOcupado_entoncesLanzaExcepcion() {
        // GIVEN: Configuramos el mock para que diga que el turno está ocupado
        Turno turno = new Turno();
        turno.setFecha(LocalDate.now());
        turno.setHora(LocalTime.of(10, 0));
        // Simulamos un profesional con ID 1
        ar.com.chavezdrive.medicar.model.Profesional prof = new ar.com.chavezdrive.medicar.model.Profesional();
        prof.setId(1L);
        turno.setProfesional(prof);

        when(turnoRepository.existsByFechaAndHoraAndProfesionalId(any(), any(), anyLong()))
            .thenReturn(true);

        // WHEN & THEN: Verificamos que al intentar guardar, lance la excepción
        assertThrows(TurnoOcupadoException.class, () -> {
            turnoService.saveTurno(turno);
        });
        
        // Verificamos que el método save NUNCA se llamó
        verify(turnoRepository, never()).save(any());
    }
}
