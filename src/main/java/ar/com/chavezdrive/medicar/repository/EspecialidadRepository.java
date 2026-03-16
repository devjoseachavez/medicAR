
package ar.com.chavezdrive.medicar.repository;

import ar.com.chavezdrive.medicar.model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
}
