
package ar.com.chavezdrive.medicar.repository;

import ar.com.chavezdrive.medicar.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
}

