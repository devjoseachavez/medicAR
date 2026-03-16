
package ar.com.chavezdrive.medicar.service;

import ar.com.chavezdrive.medicar.model.Profesional;
import ar.com.chavezdrive.medicar.repository.ProfesionalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesionalService implements IProfesionalService {

    @Autowired
    private ProfesionalRepository repo;

    @Override
    public List<Profesional> getProfesionales() {
        return repo.findAll();
    }

    @Override
    public void saveProfesional(Profesional prof) {
        repo.save(prof);
    }

    @Override
    public void deleteProfesional(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Profesional findProfesional(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void editProfesional(Profesional prof) {
        this.saveProfesional(prof);
    }
}

