package com.eteh.eteh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FomControllerElectronicDesign {

    @GetMapping("/electronic")
    public String greeting(String name, Model model) {
        model.addAttribute("name", name);
        return "/electronic";

    }
}
