package com.eteh.eteh.service;

import com.eteh.eteh.models.ColorStatusId;
import com.eteh.eteh.repository.ColorIdRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorId {


   private final ColorIdRepo colorIdRepo;


    public ColorId(ColorIdRepo colorIdRepo) {
        this.colorIdRepo = colorIdRepo;
    }


    public List<ColorStatusId> findAll(){


        return colorIdRepo.findAll();
    }

}
