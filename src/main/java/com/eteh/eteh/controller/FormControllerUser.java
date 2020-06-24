package com.eteh.eteh.controller;


import com.eteh.eteh.models.User;
import com.eteh.eteh.repository.UserRepository;
import com.eteh.eteh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN','SUPER_ADMIN')")
public class FormControllerUser  {

    private  UserRepository userRepository;
    private  UserService userService;


    @Lazy
    @Autowired
    public FormControllerUser(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @GetMapping("/user")
    public String findAll(Model model){
        List<User> user = userService.findAll();
        model.addAttribute("user", user);
        return "/user";
    }
    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "/user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/user";
    }
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/user";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/user";
    }
}
