package com.eteh.eteh.service;


import com.eteh.eteh.models.Blog;
import com.eteh.eteh.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService  {

    final
    BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public void deleteById(Long id){
        blogRepository.deleteById(id);
    }

    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }


    public Blog findById(Long id){
        return blogRepository.getOne(id);
    }
}
