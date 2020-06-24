package com.eteh.eteh.controller;

import com.eteh.eteh.models.Customer;
import com.eteh.eteh.models.Production;
import com.eteh.eteh.repository.CustomerRepository;
import com.eteh.eteh.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
public class FormControllerProductionorder {




    private
    ProductionRepository productionRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public FormControllerProductionorder(ProductionRepository productionRepository, CustomerRepository customerRepository) {
        this.productionRepository = productionRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/productionorder")
    public String greeting(String name, Model model) {
        model.addAttribute("name", name);
        return "productionorder";

    }

    @GetMapping("/order/add")

    public String add(String name, Model model){
        model.addAttribute("name",name);

        return "/order/add";
    }

    @PostMapping("/productionorder/order/add")
    public String orderAdd(@RequestParam String organization,
                           @RequestParam Long numberOrder,
                           @RequestParam Date dateShipment,
                           @RequestParam Date creationDate,
                           @RequestParam String listResponsiblePersons,
                           @RequestParam(required = false) String quipmentList ){

        Production production = new Production(organization,numberOrder,dateShipment,creationDate,
                listResponsiblePersons
                ,quipmentList);
        productionRepository.save(production);

        return "redirect:/home";

    }



















}