package ar.com.chavezdrive.medicar; // Corregido

import ar.com.chavezdrive.medicar.model.Especialidad;
import ar.com.chavezdrive.medicar.model.Paciente;
import ar.com.chavezdrive.medicar.model.Profesional;
import ar.com.chavezdrive.medicar.model.Turno;
import ar.com.chavezdrive.medicar.repository.EspecialidadRepository;
import ar.com.chavezdrive.medicar.repository.PacienteRepository;
import ar.com.chavezdrive.medicar.repository.ProfesionalRepository;
import ar.com.chavezdrive.medicar.service.TurnoService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set; // Importante
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

// Asegúrate que MedicarApplication sea el nombre real de tu clase principal
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestFlujoCompletoReservaTurno {

    @Autowired
    private TurnoService turnoServ;
    @Autowired
    private PacienteRepository pacRepo;
    @Autowired
    private ProfesionalRepository profRepo;
    @Autowired
    private EspecialidadRepository especRepo;

    @Test
    public void testFlujoCompletoReservaTurno() {
        // 1. Crear Especialidad
        Especialidad esp = new Especialidad();
        esp.setNombre("Odontología " + System.currentTimeMillis()); // Nombre único para evitar duplicados
        esp = especRepo.save(esp);

        // 2. Crear Profesional
        Profesional prof = new Profesional();
        prof.setNombre("Maria");
        prof.setApellido("Buslaiman");
        prof.setDni("25456" + (int)(Math.random()*1000)); // DNI aleatorio para que no falle al repetir el test
        prof.setUsuario("maria_" + System.currentTimeMillis());
        prof.setPassword("1234");
        prof.setEspecialidades(Set.of(esp)); // Corregido el Set
        prof = profRepo.save(prof);

        // 3. Crear Paciente
        Paciente pac = new Paciente();
        pac.setNombre("Luis");
        pac.setApellido("Vera");
        pac.setDni("29321" + (int)(Math.random()*1000));
        pac.setUsuario("lvera_" + System.currentTimeMillis());
        pac.setPassword("1234");
        pac = pacRepo.save(pac);

        // 4. Intentar guardar Turno
        Turno tur = new Turno();
        tur.setFecha(LocalDate.now().plusDays(1));
        tur.setHora(LocalTime.of(15, 0));
        tur.setPaciente(pac);
        tur.setProfesional(prof);
        
        assertDoesNotThrow(() -> turnoServ.saveTurno(tur));

        // 5. Verificar persistencia
        List<Turno> turnos = turnoServ.getTurnos();
        boolean encontrado = false;
        for (Turno t : turnos) {
            if (t.getProfesional().getId().equals(prof.getId())) {
                encontrado = true;
                break;
            }
        }
        
        assertTrue(encontrado, "El turno debería estar persistido en la base de datos");
    }
}