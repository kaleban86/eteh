package com.eteh.eteh.controller;

import com.eteh.eteh.models.User;
import com.eteh.eteh.models.UserProfileModels;
import com.eteh.eteh.repository.BlogRepository;
import com.eteh.eteh.repository.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FormControllerHello {

    private
    BlogRepository blogRepository;
    private UserProfileRepo userProfileRepo;

    @Autowired
    public FormControllerHello(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping("/hello")
    public String orderAdd(String name, Model model, @AuthenticationPrincipal User user){

        model.addAttribute("appeal" , blogRepository.findAll());
        Long id = user.getId();



        return "/hello";
    }


}
