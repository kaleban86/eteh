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


        return "/login";
    }



}
