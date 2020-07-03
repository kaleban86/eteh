package com.eteh.eteh.service;

import com.eteh.eteh.repository.AppealStatusRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppealStatus {


    private final AppealStatusRepo appealStatusRepo;

    public AppealStatus(AppealStatusRepo appealStatusRepo) {
        this.appealStatusRepo = appealStatusRepo;
    }

    public List<com.eteh.eteh.models.AppealStatus> findAll() {




        return appealStatusRepo.findAll();
    }



}
