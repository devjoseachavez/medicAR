package ar.com.chavezdrive.medicar.service;

import ar.com.chavezdrive.medicar.model.Turno;
import ar.com.chavezdrive.medicar.repository.ITurnoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnoService implements ITurnoService {

    @Autowired
    private ITurnoRepository turRepo;

    @Override
    public List<Turno> getTurnos() {
        return turRepo.findAll();
    }

    @Override
    public void saveTurno(Turno tur) {
        turRepo.save(tur);
    }

    @Override
    public void deleteTurno(Long id) {
        turRepo.deleteById(id);
    }

    @Override
    public Turno findTurno(Long id) {
        return turRepo.findById(id).orElse(null);
    }

    @Override
    public void editTurno(Turno tur) {
        // JPA detecta el ID y realiza el UPDATE automáticamente
        this.saveTurno(tur);
    }
}