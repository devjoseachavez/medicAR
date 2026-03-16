
package ar.com.chavezdrive.medicar.service;

import ar.com.chavezdrive.medicar.model.Especialidad;
import ar.com.chavezdrive.medicar.repository.EspecialidadRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadService implements IEspecialidadService {

    @Autowired
    private EspecialidadRepository repo;

    @Override
    public List<Especialidad> getEspecialidades() {
        return repo.findAll();
    }

    @Override
    public void saveEspecialidad(Especialidad espec) {
        repo.save(espec);
    }

    @Override
    public void deleteEspecialidad(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Especialidad findEspecialidad(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void editEspecialidad(Especialidad espec) {
        this.saveEspecialidad(espec);
    }
}
