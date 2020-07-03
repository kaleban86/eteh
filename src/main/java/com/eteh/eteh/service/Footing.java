package com.eteh.eteh.service;

import com.eteh.eteh.repository.FootingRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Footing {

    private final FootingRepo footingRepo;

    public Footing(FootingRepo footingRepo) {
        this.footingRepo = footingRepo;
    }

    public List<com.eteh.eteh.models.Footing> findAll(){

        return footingRepo.findAll();
    }
}
