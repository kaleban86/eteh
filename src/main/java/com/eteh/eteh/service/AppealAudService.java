package com.eteh.eteh.service;

import com.eteh.eteh.models.Appeal;
import com.eteh.eteh.repository.AppealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppealAudService {


    private final AppealRepository appealRepository;


    public AppealAudService(AppealRepository appealRepository) {
        this.appealRepository = appealRepository;
    }



    public Appeal findById(Long id){
        return appealRepository.getOne(id);
    }

    public List<Appeal> findAll(){
        return appealRepository.findAll();
    }
}
