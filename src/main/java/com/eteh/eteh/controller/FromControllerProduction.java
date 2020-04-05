package com.eteh.eteh.controller;


import com.eteh.eteh.models.Production;
import com.eteh.eteh.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class FromControllerProduction {



    @GetMapping("/equipment")
    public String greeting(String name, Model model) {
        model.addAttribute("name", name);
        return "/equipment";
    }



}
