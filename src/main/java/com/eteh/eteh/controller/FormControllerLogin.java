package com.eteh.eteh.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;
import javax.validation.constraints.NotNull;

@Controller
public class FormControllerLogin {

    @NotNull
    @RequestMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "/login";
    }


    @GetMapping("/activate")
    public String activate(String name, Model model){
        model.addAttribute("name", name);

        return "/login";
    }
}
