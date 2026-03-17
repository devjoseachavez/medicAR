
package ar.com.chavezdrive.medicar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping("/")
    public String home() {
        return "index"; // Esto busca index.html en templates
    }
}
