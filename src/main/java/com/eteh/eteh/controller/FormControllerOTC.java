package com.eteh.eteh.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormControllerOTC {
    @GetMapping("/otc")
    public String greeting(String name, Model model) {
        model.addAttribute("name", name);
        return "/otc";

    }
}
