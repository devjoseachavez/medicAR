
package ar.com.chavezdrive.medicar.controller;

import ar.com.chavezdrive.medicar.model.Especialidad;
import ar.com.chavezdrive.medicar.service.IEspecialidadService;
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
@RequestMapping("/especialidades")
public class EspecialidadController {

    @Autowired
    private IEspecialidadService especServ;

    @GetMapping("/traer")
    public List<Especialidad> getEspecialidades() {
        return especServ.getEspecialidades();
    }

    @PostMapping("/crear")
    public String saveEspecialidad(@RequestBody Especialidad espec) {
        especServ.saveEspecialidad(espec);
        return "La especialidad se creó correctamente";
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteEspecialidad(@PathVariable Long id) {
        especServ.deleteEspecialidad(id);
        return "La especialidad fue eliminada";
    }

    @GetMapping("/traer/{id}")
    public Especialidad findEspecialidad(@PathVariable Long id) {
        return especServ.findEspecialidad(id);
    }

    @PutMapping("/editar")
    public Especialidad editEspecialidad(@RequestBody Especialidad espec) {
        especServ.editEspecialidad(espec);
        return especServ.findEspecialidad(espec.getId());
    }
}