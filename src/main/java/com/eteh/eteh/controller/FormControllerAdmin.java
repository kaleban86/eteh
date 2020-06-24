package com.eteh.eteh.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@PreAuthorize("hasAnyAuthority('ADMIN ','SUPER_ADMIN')  ")

public class FormControllerAdmin {

    @GetMapping("/admin")
    public String admin(String name, Model model) {
        model.addAttribute("admin", name);


        return "/admin";
    }



}
