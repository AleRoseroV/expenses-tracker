package com.example.expenses_tracker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        // Asegúrate de que el nombre de la vista coincide con el archivo de vista real
        return "login"; // Este debe ser el nombre del archivo de vista (login.jsp o login.html)
    }

}
