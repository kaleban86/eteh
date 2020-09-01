package com.eteh.eteh.service;


import com.eteh.eteh.models.Appeal;
import com.eteh.eteh.repository.AppealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

@Service
public class AppealService {

    private final AppealRepository appealRepository;

    private final DataSource dataSource;

    private final MailSender mailSender;


    @Autowired
    public AppealService(AppealRepository appealRepository, DataSource dataSource, MailSender mailSender) {
        this.appealRepository = appealRepository;
        this.dataSource = dataSource;
        this.mailSender = mailSender;
    }

    public void deleteById(Long id) {
        appealRepository.deleteById(id);
    }

    public Appeal saveAppeal(Appeal appeal) {

        return appealRepository.save(appeal);
    }

    public Appeal findById(Long id) {


        return appealRepository.getOne(id);
    }



}

