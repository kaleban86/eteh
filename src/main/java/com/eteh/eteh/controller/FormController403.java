package com.eteh.eteh.controller;


import com.eteh.eteh.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController403 {

    final
    MailSender mailSender;

    public FormController403(MailSender mailSender) {
        this.mailSender = mailSender;
    }




    @RequestMapping("/403")
    public String error403() {
        return "/403";



    }


}
