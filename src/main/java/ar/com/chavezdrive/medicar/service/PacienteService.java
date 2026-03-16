
package ar.com.chavezdrive.medicar.service;

import ar.com.chavezdrive.medicar.model.Paciente;
import ar.com.chavezdrive.medicar.repository.PacienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    private PacienteRepository repo;

    @Override
    public List<Paciente> getPacientes() {
        return repo.findAll();
    }

    @Override
    public void savePaciente(Paciente pac) {
        repo.save(pac);
    }

    @Override
    public void deletePaciente(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Paciente findPaciente(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void editPaciente(Paciente pac) {
        //  save() funciona como "crear" si el ID es nulo
        // y como "actualizar" si el ID ya existe 
        this.savePaciente(pac);
    }
}