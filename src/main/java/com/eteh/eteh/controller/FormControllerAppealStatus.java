package com.eteh.eteh.controller;


import com.eteh.eteh.models.AppealStatus;
import com.eteh.eteh.repository.AppealStatusRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormControllerAppealStatus {

    private final AppealStatusRepo appealStatusRepo;

    public FormControllerAppealStatus(AppealStatusRepo appealStatusRepo) {
        this.appealStatusRepo = appealStatusRepo;
    }

    @GetMapping("/appeal-status")
    public String String(){

        return"/appeal-status";
    }


    @RequestMapping(value = "/appeal-status/add", method = {RequestMethod.POST})
    public String appealStatusAdd(@RequestParam String status,
                                  @RequestParam Long idStatus,
                                  @RequestParam String color) {


        AppealStatus appealStatus = new AppealStatus(status, idStatus,color);
        appealStatusRepo.save(appealStatus);



        return "redirect:/appealHome";
    }



}
