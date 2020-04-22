package com.eteh.eteh.controller;


import com.eteh.eteh.models.Blog;
import com.eteh.eteh.models.Customer;
import com.eteh.eteh.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class FormControllerBlog {
    @Autowired
    BlogRepository blogRepository;
    @GetMapping("/blog")
    public String orderAdd(Model model){
       Iterable<Blog> blogs = blogRepository.findAll();
       model.addAttribute("blog",blogs);
        return "/blog";

    }



    @PostMapping("/blog")
    public String orderAdd(@RequestParam String text,@RequestParam String title){

        Blog blog = new Blog(text,title);
        blogRepository.save(blog);

        return "redirect:/";

    }


    @GetMapping("/blog/{id}")
    public String blogInfo(@PathVariable(value = "id") long id, Model model){

      Optional<Blog> blog =  blogRepository.findById(id);
        ArrayList<Blog> res = new ArrayList<>();
        blog.ifPresent(res::add);
        model.addAttribute("blog",blog);
        return "/blog";

    }


}
