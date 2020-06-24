package com.eteh.eteh.service;


import com.eteh.eteh.models.Appeal;
import com.eteh.eteh.models.Blog;
import com.eteh.eteh.models.User;
import com.eteh.eteh.repository.AppealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AppealService  {

    private final AppealRepository appealRepository;

    @Autowired
    public AppealService(AppealRepository appealRepository) {
        this.appealRepository = appealRepository;
    }
    public void deleteById(Long id){
        appealRepository.deleteById(id);
    }

    public Appeal saveAppeal(Appeal appeal) {

        return appealRepository.save(appeal);
    }

    public Appeal findById(Long id){
        return appealRepository.getOne(id);
    }



}
