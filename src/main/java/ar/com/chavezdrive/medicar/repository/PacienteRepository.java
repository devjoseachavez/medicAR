
package ar.com.chavezdrive.medicar.repository;

import ar.com.chavezdrive.medicar.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
