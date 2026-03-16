
package ar.com.chavezdrive.medicar.service;

import ar.com.chavezdrive.medicar.model.Paciente;
import java.util.List;


public interface IPacienteService {
    public List<Paciente> getPacientes();
    public void savePaciente(Paciente pac);
    public void deletePaciente(Long id);
    public Paciente findPaciente(Long id);
    public void editPaciente(Paciente pac);
}
