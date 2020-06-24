package com.eteh.eteh.controller;


import com.eteh.eteh.models.Customer;
import com.eteh.eteh.models.Production;
import com.eteh.eteh.models.User;
import com.eteh.eteh.repository.CustomerRepository;
import com.eteh.eteh.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class FromControllerCustomer {

   private
    CustomerRepository customerRepository;
   private
    CostumerService costumerService;


    @Autowired
    public FromControllerCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customer")
    public String greeting( Model model) {
        Iterable<Customer> customersList = customerRepository.findAll();
        model.addAttribute("customerList",customersList);
        return "/customer";
    }

    @GetMapping("/customeradd")
    public String customerAdd(String name, Model model) {
        model.addAttribute("name", name);
        return "/customeradd";
    }


    @PostMapping("/customeradd/add")
    public String orderAdd(@RequestParam String nameCompany,
                           @RequestParam String fullNameCompany,
                           @RequestParam String legalAddress,
                           @RequestParam String actualAddress,
                           @RequestParam Long inn,
                           @RequestParam Long ogrnip,
                           @RequestParam Long okpo,
                           @RequestParam String email,
                           @RequestParam String site,
                           @RequestParam String phone){

        Customer customer = new Customer(nameCompany,fullNameCompany,
                legalAddress,actualAddress,inn,ogrnip,okpo,email,site,phone);
        customerRepository.save(customer);

        return "redirect:/customer";

    }



    @GetMapping("/list-customer")
    public String findAll(Model model){
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "/list-customer";
    }



}
