
package ar.com.chavezdrive.medicar.repository;

import ar.com.chavezdrive.medicar.model.Turno;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    // métodos personalizados
    List<Turno> findByFecha(LocalDate fecha);
    
    // Retorna true si ya existe un turno para ese médico en ese momento
    boolean existsByFechaAndHoraAndProfesionalId(LocalDate fecha, LocalTime hora, Long profesionalId);
}
