package ar.com.chavezdrive.medicar.controller;

import ar.com.chavezdrive.medicar.model.Turno;
import ar.com.chavezdrive.medicar.service.IEspecialidadService;
import ar.com.chavezdrive.medicar.service.IPacienteService;
import ar.com.chavezdrive.medicar.service.IProfesionalService;
import ar.com.chavezdrive.medicar.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/turnos")
public class TurnoViewController {

    @Autowired
    private ITurnoService turServ;

    @Autowired
    private IPacienteService pacServ;

    @Autowired
    private IProfesionalService profServ;

    @Autowired
    private IEspecialidadService espServ;

    // Listado Principal y Formulario Vacío
    @GetMapping("/gestion")
    public String gestionarTurnos(Model model) {
        // 1. Cargamos la lista de turnos agendados
        model.addAttribute("listaTurnos", turServ.getTurnos());
        
        // 2. Cargamos las listas para que los <select> tengan opciones
        model.addAttribute("listaPacientes", pacServ.getPacientes());
        model.addAttribute("listaProfesionales", profServ.getProfesionales());
        model.addAttribute("listaEspecialidades", espServ.getEspecialidades());
        
        // 3. Preparamos el objeto para el formulario (si no viene de un redirect de error)
        if (!model.containsAttribute("turno")) {
            model.addAttribute("turno", new Turno());
        }
        return "turnos/gestion";
    }

    // Guardar o Actualizar
    @PostMapping("/guardar")
    public String guardarTurno(@ModelAttribute("turno") Turno tur, RedirectAttributes msj) {
        try {
            // Verificación básica: ¿Se seleccionaron los objetos?
            if (tur.getPaciente() == null || tur.getProfesional() == null) {
                msj.addFlashAttribute("error", "Debe seleccionar un paciente y un profesional.");
                return "redirect:/turnos/gestion";
            }
            
            turServ.saveTurno(tur);
            msj.addFlashAttribute("exito", "El turno se ha agendado con éxito.");
        } catch (Exception e) {
            msj.addFlashAttribute("error", "Error al procesar el turno: " + e.getMessage());
        }
        return "redirect:/turnos/gestion";
    }

    // Cargar datos en el formulario para editar
    @GetMapping("/editar/{id}")
    public String editarTurno(@PathVariable Long id, Model model) {
        Turno t = turServ.findTurno(id);
        
        if (t != null) {
            model.addAttribute("turno", t);
            // Al editar, necesitamos recargar todo el contexto de la página
            model.addAttribute("listaTurnos", turServ.getTurnos());
            model.addAttribute("listaPacientes", pacServ.getPacientes());
            model.addAttribute("listaProfesionales", profServ.getProfesionales());
            model.addAttribute("listaEspecialidades", espServ.getEspecialidades());
            return "turnos/gestion";
        }
        
        return "redirect:/turnos/gestion";
    }

    // Eliminar / Cancelar Turno
    @GetMapping("/eliminar/{id}")
    public String eliminarTurno(@PathVariable Long id, RedirectAttributes msj) {
        try {
            turServ.deleteTurno(id);
            msj.addFlashAttribute("exito", "El turno ha sido cancelado correctamente.");
        } catch (Exception e) {
            msj.addFlashAttribute("error", "No se pudo cancelar el turno: " + e.getMessage());
        }
        return "redirect:/turnos/gestion";
    }
}
