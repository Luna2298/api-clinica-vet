package com.clinicaveterinaria.ejercicio2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "API Clínica Veterinaria funcionando correctamente 🚀";
    }
}
