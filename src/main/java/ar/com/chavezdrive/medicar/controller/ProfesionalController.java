
package ar.com.chavezdrive.medicar.controller;

import ar.com.chavezdrive.medicar.model.Profesional;
import ar.com.chavezdrive.medicar.service.IProfesionalService;
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
@RequestMapping("/profesionales")
public class ProfesionalController {

    @Autowired
    private IProfesionalService profServ;

    @GetMapping("/traer")
    public List<Profesional> getProfesionales() {
        return profServ.getProfesionales();
    }

    @PostMapping("/crear")
    public String saveProfesional(@RequestBody Profesional prof) {
        profServ.saveProfesional(prof);
        return "El profesional fue creado correctamente";
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteProfesional(@PathVariable Long id) {
        profServ.deleteProfesional(id);
        return "El profesional fue eliminado correctamente";
    }

    @GetMapping("/traer/{id}")
    public Profesional findProfesional(@PathVariable Long id) {
        return profServ.findProfesional(id);
    }

    @PutMapping("/editar")
    public Profesional editProfesional(@RequestBody Profesional prof) {
        profServ.editProfesional(prof);
        return profServ.findProfesional(prof.getId());
    }
}
