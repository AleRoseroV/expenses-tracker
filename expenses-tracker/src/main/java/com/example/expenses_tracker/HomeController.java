package com.example.expenses_tracker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // Asegúrate de que existe src/main/resources/templates/home.html
    }
}
