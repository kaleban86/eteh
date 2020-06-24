package com.eteh.eteh.controller;


import com.eteh.eteh.models.Blog;
import com.eteh.eteh.models.User;
import com.eteh.eteh.repository.BlogRepository;
import com.eteh.eteh.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

/*
    @RequestMapping( value = "blog/{id}",method =  RequestMethod.POST)
    public String updateBlog(@PathVariable (value = "id") long id,@RequestParam String title,
                             @RequestParam String  text, Model model ){
        Blog blog = blogRepository.findById(id).orElseThrow(IllegalStateException::new);
        blog.setTitle(title);
        blog.setText(text);
        blogRepository.save(blog);

        return "redirect:/hello";
    }



    @PostMapping("blog/{id}/blog")
    public String blogSave(@PathVariable("id") Long id, Model model){
        Blog blog = blogRepository.findById(id).orElseThrow(IllegalStateException::new);

      blogRepository.save(blog);
        return "redirect:/hello";
    }




    @PostMapping("/blog/{id}/blog")
    public String updateBlog(@PathVariable (value = "id") long id,@RequestParam String title,
                             @RequestParam String  text, Model model ){
        Blog blog = blogRepository.findById(id).orElseThrow(IllegalStateException::new);
        blog.setTitle(title);
        blog.setText(text);
        blogRepository.save(blog);

        return "redirect:/hello";
    }

    @GetMapping("/blog/{id}")
    public String blogUpdate(@PathVariable(value = "id") long id, Model model){
        if (!blogRepository.existsById(id)){
            return "redirect:/hello";
        }

        Optional<Blog> blog =  blogRepository.findById(id);
        ArrayList<Blog> res = new ArrayList<>();
        blog.ifPresent(res::add);
        model.addAttribute("blogUpdate",blog);
        return "/blog-update";

    }

//    @PostMapping("blog/{id}")
//    public String updateBlog(@PathVariable (value = "id")  long id, @RequestParam String title,
//                            @RequestParam String  text, Blog blog){
//        blog.setTitle(title);
//        blog.setText(text);
//        blogService.saveBlog(blog);
//        return "redirect:/hello";
//    }



    @PostMapping("blog/{id}/blog")
    public String updateBlog(@PathVariable (value = "id") long id,@RequestParam String title,
                             @RequestParam String  text, Model model ){
       Blog blog = blogRepository.findById(id).orElseThrow(IllegalStateException::new);
       blog.setTitle(title);
       blog.setText(text);
       blogRepository.save(blog);

        return "redirect:/hello";
    }

*/


}
