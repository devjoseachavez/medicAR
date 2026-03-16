
package ar.com.chavezdrive.medicar.service;

import ar.com.chavezdrive.medicar.model.Especialidad;
import java.util.List;


public interface IEspecialidadService {
    public List<Especialidad> getEspecialidades();
    public void saveEspecialidad(Especialidad espec);
    public void deleteEspecialidad(Long id);
    public Especialidad findEspecialidad(Long id);
    public void editEspecialidad(Especialidad espec);
}
