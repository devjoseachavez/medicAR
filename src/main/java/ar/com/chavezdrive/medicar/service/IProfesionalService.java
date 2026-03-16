
package ar.com.chavezdrive.medicar.service;

import ar.com.chavezdrive.medicar.model.Profesional;
import java.util.List;


public interface IProfesionalService {
    public List<Profesional> getProfesionales();
    public void saveProfesional(Profesional prof);
    public void deleteProfesional(Long id);
    public Profesional findProfesional(Long id);
    public void editProfesional(Profesional prof);
}
