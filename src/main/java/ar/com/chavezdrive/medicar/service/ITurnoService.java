
package ar.com.chavezdrive.medicar.service;

import ar.com.chavezdrive.medicar.model.Turno;
import java.util.List;

public interface ITurnoService {
    public List<Turno> getTurnos();
    public void saveTurno(Turno tur);
    public void deleteTurno(Long id);
    public Turno findTurno(Long id);
    public void editTurno(Turno tur);
}
