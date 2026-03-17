
package ar.com.chavezdrive.medicar.controller;


import ar.com.chavezdrive.medicar.model.Especialidad;
import ar.com.chavezdrive.medicar.service.IEspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/especialidades")
public class EspecialidadViewController {

    @Autowired
    private IEspecialidadService especServ;

    // Muestra la lista y el formulario (vacío o para editar)
    @GetMapping("/gestion")
    public String gestionarEspecialidades(Model model) {
        // Si el modelo ya tiene una especialidad (por el método editar), no la sobrescribimos
        if (!model.containsAttribute("especialidad")) {
            model.addAttribute("especialidad", new Especialidad());
        }
        model.addAttribute("listaEspecialidades", especServ.getEspecialidades());
        return "especialidades/gestion"; 
    }

    // Procesa el guardado (Sirve para Crear y para Actualizar)
    @PostMapping("/guardar")
    public String guardarEspecialidad(@ModelAttribute("especialidad") Especialidad espec, RedirectAttributes msj) {
        especServ.saveEspecialidad(espec);
        msj.addFlashAttribute("exito", "Operación realizada con éxito.");
        return "redirect:/especialidades/gestion";
    }

    // Busca la especialidad y la devuelve a la misma página para cargar el formulario
    @GetMapping("/editar/{id}")
    public String mostrarFormEditar(@PathVariable Long id, Model model) {
        Especialidad espec = especServ.findEspecialidad(id);
        model.addAttribute("especialidad", espec);
        model.addAttribute("listaEspecialidades", especServ.getEspecialidades());
        return "especialidades/gestion";
    }

    // Elimina y redirige
    @GetMapping("/eliminar/{id}")
        public String eliminarEspecialidad(@PathVariable Long id, RedirectAttributes msj) {
        try {
            especServ.deleteEspecialidad(id);
            msj.addFlashAttribute("exito", "Especialidad eliminada correctamente");
        } catch (Exception e) {
            // Si hay un error de restricción (Foreign Key), entra aquí
            msj.addFlashAttribute("error", "No se puede eliminar: esta especialidad tiene profesionales asignados.");
        }
        return "redirect:/especialidades/gestion";
}
}