package com.eteh.eteh.controller;

import com.eteh.eteh.models.User;
import com.eteh.eteh.repository.UserRepository;
import com.eteh.eteh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.PersistenceContext;
import java.util.Map;

@PersistenceContext
@Controller
public class FormControllerRegistration {


    private
    UserRepository userRepository;
    private UserService userService;
    @Lazy
    @Autowired
    public FormControllerRegistration(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String registration(String name, Model model) {

        model.addAttribute("name", name);
        return "/registration";
    }


    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model ){



        if (!userService.addUser(user)) {
            model.put("message", "User exists!");
            return "/registration";
        }







        return "redirect:/login";

    }


    @GetMapping("*/activate/{code}")
    public String activate(Model model, @PathVariable String code){

        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message1", "User successfully activated");
        } else {
            model.addAttribute("message2", "Activation code is not found!");
        }



        return "/login";
    }




}








