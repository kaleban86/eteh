package com.eteh.eteh.controller;


import com.eteh.eteh.models.Blog;
import com.eteh.eteh.models.User;
import com.eteh.eteh.repository.BlogRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class FormControllerBlogAdd {

    private
    BlogRepository blogRepository;

    @Autowired
    public FormControllerBlogAdd(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }


    @GetMapping("/blogadd")
    public String orderAdd(String name, Model model){
        model.addAttribute("blog",name);
        return "/blogadd";
    }


    @PostMapping("/blog/add")
    public String orderAdd(
            @AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String title){

        Blog blog = new Blog(text,title,user);
        blogRepository.save(blog);

        return "redirect:/hello";

    }





}
