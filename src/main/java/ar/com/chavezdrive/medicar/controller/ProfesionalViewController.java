package ar.com.chavezdrive.medicar.controller;

import ar.com.chavezdrive.medicar.model.Especialidad;
import ar.com.chavezdrive.medicar.model.Profesional;
import ar.com.chavezdrive.medicar.service.IEspecialidadService;
import ar.com.chavezdrive.medicar.service.IProfesionalService;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profesionales")
public class ProfesionalViewController {

    @Autowired
    private IProfesionalService profServ;

    @Autowired
    private IEspecialidadService especServ; // Inyectamos el servicio de especialidades

    @GetMapping("/gestion")
public String gestionarProfesionales(Model model) {
    // 1. La lista de médicos para la tabla
    model.addAttribute("listaProfesionales", profServ.getProfesionales());
    
    // 2. La lista de especialidades para llenar el SELECT (¡MUY IMPORTANTE!)
    // Revisa que el nombre coincida con el th:each del HTML
    model.addAttribute("todasLasEspecialidades", especServ.getEspecialidades());
    
    // 3. El objeto vacío para el formulario
    if (!model.containsAttribute("profesional")) {
        model.addAttribute("profesional", new Profesional());
    }
    return "profesionales/gestion";
}

    @PostMapping("/guardar")
public String guardarProfesional(@ModelAttribute("profesional") Profesional prof, RedirectAttributes msj) {
    try {
        // Log para ver qué llega del HTML
        System.out.println("Intentando guardar: " + prof.getNombre() + " " + prof.getApellido());
        System.out.println("DNI: " + prof.getDni());
        
        profServ.saveProfesional(prof);
        msj.addFlashAttribute("exito", "Profesional guardado con éxito");
    } catch (Exception e) {
        // Esto te dirá en la consola si falta un campo obligatorio
        System.err.println("ERROR AL GUARDAR: " + e.getMessage());
        msj.addFlashAttribute("error", "No se pudo guardar: verifique los datos obligatorios.");
    }
    return "redirect:/profesionales/gestion";
}
    
    // ... métodos de editar y eliminar similares a especialidades
    @GetMapping("/editar/{id}")
    public String editarProfesional(@PathVariable Long id, Model model) {
        Profesional p = profServ.findProfesional(id);
        model.addAttribute("profesional", p);
        model.addAttribute("listaProfesionales", profServ.getProfesionales());
        model.addAttribute("todasLasEspecialidades", especServ.getEspecialidades());
        return "profesionales/gestion";
    }

    @GetMapping("/eliminar/{id}")
public String eliminarProfesional(@PathVariable Long id, RedirectAttributes msj) {
    try {
        profServ.deleteProfesional(id);
        msj.addFlashAttribute("exito", "Profesional eliminado correctamente");
    } catch (Exception e) {
        // Esto imprimirá el error real en la consola de NetBeans
        e.printStackTrace(); 
        msj.addFlashAttribute("error", "Error al eliminar: el profesional tiene registros vinculados (especialidades o turnos).");
    }
    return "redirect:/profesionales/gestion";
}

@GetMapping("/especialidades-por-profesional/{id}")
@ResponseBody
public Set<Especialidad> getEspecialidades(@PathVariable Long id) {
    Profesional prof = profServ.findProfesional(id);
    return (prof != null) ? prof.getEspecialidades() : new HashSet<>();
}
}
