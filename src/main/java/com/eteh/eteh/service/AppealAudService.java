package com.eteh.eteh.service;

import com.eteh.eteh.models.Appeal;
import com.eteh.eteh.models.User;
import com.eteh.eteh.repository.AppealRepository;
import com.eteh.eteh.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppealAudService {


    private final AppealRepository appealRepository;
    private final UserRepository userRepository;


    public AppealAudService(AppealRepository appealRepository, UserRepository userRepository) {
        this.appealRepository = appealRepository;
        this.userRepository = userRepository;
    }



    public Appeal findById(Long id){




        return appealRepository.getOne(id);
    }




    public List<Appeal> findAll() {




        return appealRepository.findAll();
    }



}
