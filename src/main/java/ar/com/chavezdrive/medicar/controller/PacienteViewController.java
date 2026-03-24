package ar.com.chavezdrive.medicar.controller;

import ar.com.chavezdrive.medicar.model.Paciente;
import ar.com.chavezdrive.medicar.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pacientes")
public class PacienteViewController {

    @Autowired
    private IPacienteService pacServ;

    @GetMapping("/gestion")
    public String gestionarPacientes(Model model) {
        model.addAttribute("listaPacientes", pacServ.getPacientes());
        
        if (!model.containsAttribute("paciente")) {
            model.addAttribute("paciente", new Paciente());
        }
        return "pacientes/gestion";
    }

    @PostMapping("/guardar")
    public String guardarPaciente(@ModelAttribute("paciente") Paciente pac, RedirectAttributes msj) {
        try {
            pacServ.savePaciente(pac);
            msj.addFlashAttribute("exito", "Paciente guardado correctamente.");
        } catch (Exception e) {
            msj.addFlashAttribute("error", "Error al guardar el paciente: " + e.getMessage());
        }
        return "redirect:/pacientes/gestion";
    }

    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Paciente p = pacServ.findPaciente(id);
        model.addAttribute("paciente", p);
        model.addAttribute("listaPacientes", pacServ.getPacientes());
        return "pacientes/gestion";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable Long id, RedirectAttributes msj) {
        try {
            pacServ.deletePaciente(id);
            msj.addFlashAttribute("exito", "Paciente eliminado con éxito.");
        } catch (Exception e) {
            msj.addFlashAttribute("error", "No se puede eliminar: el paciente tiene turnos o registros asociados.");
        }
        return "redirect:/pacientes/gestion";
    }
}
