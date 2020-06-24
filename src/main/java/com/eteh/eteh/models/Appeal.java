package com.eteh.eteh.models;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDateTime;

@Audited
@Entity
public class Appeal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @CreationTimestamp
    @CreatedDate
    private Date dataCreation;


    private Date dataAnswer;
    @UpdateTimestamp
    @CreatedDate
    private LocalDateTime dataChange;


    private String fileName;



    private String fileName2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")


    @CreatedBy
    @LastModifiedBy
    private User author;

    private String authorUpdate;


    @Size(max = 75)
    private String briefDescription;
    private String footing, text, executor, controller, status, surname, lastName, nameCompany;


    private String tel;

    private String address, emailAddress;


    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public Appeal() {
    }

    public Appeal(User user, String briefDescription, String footing, String text, String executor, String controller, String status,
                  String surname, String lastName, java.sql.Date dataAnswer, java.sql.Date dataCreation,
                  String nameCompany, String tel, String address, String emailAddress, String authorUpdate) {

        this.briefDescription = briefDescription;
        this.footing = footing;
        this.text = text;
        this.executor = executor;
        this.controller = controller;
        this.status = status;
        this.surname = surname;
        this.lastName = lastName;
        this.dataCreation = dataCreation;
        this.dataAnswer = dataAnswer;
        this.nameCompany = nameCompany;
        this.tel = tel;
        this.address = address;
        this.emailAddress = emailAddress;
        this.author = user;
        this.authorUpdate = authorUpdate;


    }

    public LocalDateTime getDataChange() {
        return dataChange;
    }

    public void setDataChange(LocalDateTime dataChange) {
        this.dataChange = dataChange;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getbriefDescription() {
        return briefDescription;
    }

    public void setbriefDescription(String briefDescription) {
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAuthorUpdate() {
        return authorUpdate;
    }

    public void setAuthorUpdate(String authorUpdate) {
        this.authorUpdate = authorUpdate;
    }
    public String getFileName2() {
        return fileName2;
    }

    public void setFileName2(String fileName2) {
        this.fileName2 = fileName2;
    }
    @Override
    public String toString() {
        return "Appeal{" +
                "id=" + id +
                ", dataCreation=" + dataCreation +
                ", dataAnswer=" + dataAnswer +
                ", briefDescription='" + briefDescription + '\'' +
                ", footing='" + footing + '\'' +
                ", text='" + text + '\'' +
                ", executor='" + executor + '\'' +
                ", controller='" + controller + '\'' +
                ", status='" + status + '\'' +
                ", surname='" + surname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nameCompany='" + nameCompany + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
