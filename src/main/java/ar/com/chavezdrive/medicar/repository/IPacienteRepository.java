package ar.com.chavezdrive.medicar.repository;

import ar.com.chavezdrive.medicar.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
    // Aquí podrías agregar métodos de búsqueda personalizados en el futuro,
    // como buscar por DNI o por Obra Social.
}