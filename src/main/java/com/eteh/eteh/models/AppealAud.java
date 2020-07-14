package com.eteh.eteh.models;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
@Service
public class AppealAud {


    private Date dataCreation;

    private Date dataAnswer;

    private Date  dataChange;

    private int rev;




    private String briefDescription;
    private int userId;






   

    private String footing;
    private String text;
    private Long executor;
    private Long controller;



    private String status;
    private String name,firstName;

    private String surname;
    private String lastName;
    private Long nameCompany;
    private String tel;
    private String address;
    private String emailAddress;
    private String fileName;
    private Long authorUpdate;


    public Date getDataCreation() {
        return dataCreation;
    }

    public void setDataCreation(Date dataCreation) {
        this.dataCreation = dataCreation;
    }

    public Date getDataAnswer() {
        return dataAnswer;
    }

    public void setDataAnswer(Date dataAnswer) {
        this.dataAnswer = dataAnswer;
    }

    public Date getDataChange() {
        return dataChange;
    }

    public void setDataChange(java.sql.Date dataChange) {
        this.dataChange = dataChange;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }
    public Long getExecutor() {
        return executor;
    }

    public void setExecutor(Long executor) {
        this.executor = executor;
    }

    public Long getController() {
        return controller;
    }

    public void setController(Long controller) {
        this.controller = controller;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDataChange(Date dataChange) {
        this.dataChange = dataChange;
    }

    public String getFooting() {
        return footing;
    }

    public void setFooting(String footing) {
        this.footing = footing;
    }




    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(Long nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getTel() {
        return tel;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    public Long getAuthorUpdate() {
        return authorUpdate;
    }

    public void setAuthorUpdate(Long authorUpdate) {
        this.authorUpdate = authorUpdate;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
