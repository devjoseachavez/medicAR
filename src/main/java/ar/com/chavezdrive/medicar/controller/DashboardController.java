package ar.com.chavezdrive.medicar.controller;

import ar.com.chavezdrive.medicar.service.IPacienteService;
import ar.com.chavezdrive.medicar.service.IProfesionalService;
import ar.com.chavezdrive.medicar.service.ITurnoService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired private IPacienteService pacServ;
    @Autowired private IProfesionalService profServ;
    @Autowired private ITurnoService turServ;

    @GetMapping("/")
    public String index(Model model) {
        // Estadísticas simples
        long totalPacientes = pacServ.getPacientes().size();
        long totalProfesionales = profServ.getProfesionales().size();
        
        // Contamos turnos de hoy
        long turnosHoy = turServ.getTurnos().stream()
                .filter(t -> t.getFecha().equals(LocalDate.now()))
                .count();

        model.addAttribute("cantPacientes", totalPacientes);
        model.addAttribute("cantProfesionales", totalProfesionales);
        model.addAttribute("cantTurnosHoy", turnosHoy);
        model.addAttribute("proximosTurnos", turServ.getTurnos()); // Para una tabla rápida

        return "index";
    }
}
