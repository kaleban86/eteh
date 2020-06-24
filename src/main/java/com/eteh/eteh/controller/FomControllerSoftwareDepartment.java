package com.eteh.eteh.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAnyAuthority('DEVELOPER')")
public class FomControllerSoftwareDepartment {
    @GetMapping("software")
    public String greeting(String name, Model model) {
        model.addAttribute("name", name);
        return "/software";

    }
}
