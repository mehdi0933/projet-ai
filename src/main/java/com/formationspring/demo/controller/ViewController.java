package com.formationspring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/connexion")
    public String connexion() {
        return "connexion"; // src/main/resources/templates/connexion.html
    }

    @GetMapping("/inscription")
    public String inscription() {
        return "inscription"; // src/main/resources/templates/inscription.html
    }

    @GetMapping("/ai")
    public String ai() {
        return "ai"; // src/main/resources/templates/ai.html
    }

    @GetMapping("/historique")
    public String historique() {
        return "historique"; // src/main/resources/templates/historique.html
    }
}
