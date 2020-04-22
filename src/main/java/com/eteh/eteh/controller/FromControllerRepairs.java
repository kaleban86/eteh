package com.eteh.eteh.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FromControllerRepairs {


    @GetMapping("/repairs")
    private String repairs(String name, Model model){
        model.addAttribute("name",name);

        return "/repairs";
    }

    @GetMapping("/repairsadd")
    public String repairsadd(String name, Model model){
        model.addAttribute("name" ,name);

        return "/repairsadd";
    }



}
