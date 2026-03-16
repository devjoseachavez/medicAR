
package ar.com.chavezdrive.medicar.controller;

import ar.com.chavezdrive.medicar.model.Turno;
import ar.com.chavezdrive.medicar.service.ITurnoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private ITurnoService turnoServ;

    @GetMapping("/traer")
    public List<Turno> getTurnos() {
        return turnoServ.getTurnos();
    }

    @PostMapping("/crear")
    public String saveTurno(@RequestBody Turno tur) {
        turnoServ.saveTurno(tur);
        return "El turno fue reservado con éxito";
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteTurno(@PathVariable Long id) {
        turnoServ.deleteTurno(id);
        return "El turno fue cancelado/eliminado";
    }

    @GetMapping("/traer/{id}")
    public Turno findTurno(@PathVariable Long id) {
        return turnoServ.findTurno(id);
    }

    @PutMapping("/editar")
    public Turno editTurno(@RequestBody Turno tur) {
        turnoServ.editTurno(tur);
        return turnoServ.findTurno(tur.getId());
    }
}
