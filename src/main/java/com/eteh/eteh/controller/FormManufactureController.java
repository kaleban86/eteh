package com.eteh.eteh.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormManufactureController {

    @GetMapping("/manufacture")
    public String greeting(String name, Model model) {
        model.addAttribute("name", name);
        return "manufacture";

    }
}
