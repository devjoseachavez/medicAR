
package ar.com.chavezdrive.medicar.service;

import ar.com.chavezdrive.medicar.model.Turno;
import ar.com.chavezdrive.medicar.repository.TurnoRepository;
import exceptions.TurnoOcupadoException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnoService implements ITurnoService {

    @Autowired
    private TurnoRepository repo;

    @Override
    public List<Turno> getTurnos() {
        return repo.findAll();
    }

    @Override
    public void saveTurno(Turno tur) {
        
        // Validamos si el profesional ya tiene un turno ese día a esa hora
    boolean ocupado = repo.existsByFechaAndHoraAndProfesionalId(
                        tur.getFecha(), 
                        tur.getHora(), 
                        tur.getProfesional().getId());

    if (ocupado) {
        // Agrego excepción personalizada 
        throw new TurnoOcupadoException("El profesional ya tiene un turno en ese horario.");
    }

    repo.save(tur);
    }

    @Override
    public void deleteTurno(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Turno findTurno(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void editTurno(Turno tur) {
        this.saveTurno(tur);
    }
}
