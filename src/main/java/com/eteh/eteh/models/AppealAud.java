package com.eteh.eteh.models;

import java.util.Date;

public class AppealAud {

    private Date dataCreation;

    private Date dataAnswer;

    private Date  dataChange;

    private int rev;




    private String briefDescription;
    private int userId;



    private String footing;
    private String text;
    private String executor;
    private String controller;
    private String status;
    private String surname;
    private String lastName;
    private String nameCompany;
    private String tel;
    private String address;
    private String emailAddress;
    private String fileName;



    private String authorUpdate;


    public Date getDataCreation() {
        return dataCreation;
    }

    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
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

    public String getFooting() {
        return footing;
    }

    public void setFooting(String footing) {
        this.footing = footing;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
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

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
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

    public String getAuthorUpdate() {
        return authorUpdate;
    }

    public void setAuthorUpdate(String authorUpdate) {
        this.authorUpdate = authorUpdate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
