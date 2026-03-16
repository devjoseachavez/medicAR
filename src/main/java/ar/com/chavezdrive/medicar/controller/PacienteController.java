
package ar.com.chavezdrive.medicar.controller;

import ar.com.chavezdrive.medicar.model.Paciente;
import ar.com.chavezdrive.medicar.service.IPacienteService;
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
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacServ;

    @GetMapping("/traer")
    public List<Paciente> getPacientes() {
        return pacServ.getPacientes();
    }

    @PostMapping("/crear")
    public String savePaciente(@RequestBody Paciente pac) {
        pacServ.savePaciente(pac);
        return "El paciente fue creado correctamente";
    }

    @DeleteMapping("/borrar/{id}")
    public String deletePaciente(@PathVariable Long id) {
        pacServ.deletePaciente(id);
        return "El paciente fue eliminado correctamente";
    }

    @GetMapping("/traer/{id}")
    public Paciente findPaciente(@PathVariable Long id) {
        return pacServ.findPaciente(id);
    }

    @PutMapping("/editar")
    public Paciente editPaciente(@RequestBody Paciente pac) {
        pacServ.editPaciente(pac);
        return pacServ.findPaciente(pac.getId());
    }
}
