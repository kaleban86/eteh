package com.eteh.eteh.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormControllerHome {
    @GetMapping("/")
    public String home(String name, Model model) {

        model.addAttribute("home", name);

        return "redirect:/hello";
    }
}
