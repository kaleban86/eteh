package com.eteh.eteh.controller;

import com.eteh.eteh.models.Production;
import com.eteh.eteh.models.Role;
import com.eteh.eteh.models.User;
import com.eteh.eteh.repository.ProductionRepository;
import com.eteh.eteh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collections;
import java.util.Map;

@Controller
public class FormController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    ProductionRepository productionRepository;

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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "/login";
    }


    @GetMapping("/home")
    public String home(String name, Model model) {

        model.addAttribute("home", name);

        return "home";
    }



    @GetMapping("/admin")
    public String admin(String name, Model model) {

        model.addAttribute("admin", name);

        return name;
    }


    @GetMapping("/registration")
    public String registration(String name,Model model) {

        model.addAttribute("name",name);
        return "/registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepository.findAllByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "/registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }



}