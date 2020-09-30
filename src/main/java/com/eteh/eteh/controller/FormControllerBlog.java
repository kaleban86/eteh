package com.eteh.eteh.controller;


import com.eteh.eteh.models.Blog;
import com.eteh.eteh.repository.BlogRepository;
import com.eteh.eteh.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormControllerBlog {

    private
    BlogRepository blogRepository;

    final
    BlogService blogService;

    @Autowired
    public FormControllerBlog(BlogRepository blogRepository, BlogService blogService) {
        this.blogRepository = blogRepository;
        this.blogService = blogService;
    }


    @GetMapping("/blog-delete/{id}")
    public String deleteBlog(@PathVariable("id") Long id) {
        blogService.deleteById(id);
        return "redirect:/hello";
    }


    @GetMapping("/blog/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "/blog-update";
    }

    @PostMapping("/blog-update{id}")
    public String updateUser(Blog blog) {
        blogService.saveBlog(blog);
        return "redirect:/hello";
    }


}
