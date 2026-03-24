package ar.com.chavezdrive.medicar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.chavezdrive.medicar.model.Turno;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
