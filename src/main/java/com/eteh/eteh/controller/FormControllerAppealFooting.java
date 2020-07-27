package com.eteh.eteh.controller;

import com.eteh.eteh.models.AppealStatus;
import com.eteh.eteh.models.Footing;
import com.eteh.eteh.repository.FootingRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormControllerAppealFooting {


    private final FootingRepo footingRepo;

    public FormControllerAppealFooting(FootingRepo footingRepo) {
        this.footingRepo = footingRepo;
    }

    @GetMapping("appeal-footing")
    public String appealFootingAdd(){

        return "appeal-footing";
    }


    @RequestMapping(value = "/appeal-footing/add", method = {RequestMethod.POST})
    public String appealStatusAdd(@RequestParam String footing,
                                  @RequestParam Long footingId) {


        Footing footing1 = new Footing(footing,footingId);
        footingRepo.save(footing1);



        return "redirect:/appealHome";
    }
}
