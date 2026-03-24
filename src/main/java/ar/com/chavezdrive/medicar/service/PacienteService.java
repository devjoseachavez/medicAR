package ar.com.chavezdrive.medicar.service;

import ar.com.chavezdrive.medicar.model.Paciente;
import ar.com.chavezdrive.medicar.repository.IPacienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    private IPacienteRepository pacRepo;

    @Override
    public List<Paciente> getPacientes() {
        return pacRepo.findAll();
    }

    @Override
    public void savePaciente(Paciente pac) {
        pacRepo.save(pac);
    }

    @Override
    public void deletePaciente(Long id) {
        pacRepo.deleteById(id);
    }

    @Override
    public Paciente findPaciente(Long id) {
        return pacRepo.findById(id).orElse(null);
    }

    @Override
    public void editPaciente(Paciente pac) {
        // Al usar save(), JPA detecta el ID existente y actualiza los campos
        this.savePaciente(pac);
    }
}