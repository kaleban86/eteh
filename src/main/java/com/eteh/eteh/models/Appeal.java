package com.eteh.eteh.models;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.hibernate.type.ClassType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDateTime;

@Audited
@Entity
public class Appeal  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @CreationTimestamp
    @CreatedDate
    private Date dataCreation;



    private String color;



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




    @Size(max = 75)
    private String briefDescription;
    private String  text,surname, lastName, nameCompany;

    private Long executor, controller, authorUpdate,footing;



    private Long status;

    private String tel;

    private String address, emailAddress;




    public Appeal() {
    }



    public Appeal(User user, String briefDescription, Long footing, String text, Long executor, Long controller, Long status,
                  String surname, String lastName, java.sql.Date dataAnswer, java.sql.Date dataCreation,
                  String nameCompany, String tel, String address, String emailAddress, Long authorUpdate ,String color) {

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
        this.color = color;


    }
    public String getBriefDescription() {
        return briefDescription;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }
    public Long getFooting() {
        return footing;
    }

    public void setFooting(Long footing) {
        this.footing = footing;
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



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
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

    public Long getAuthorUpdate() {
        return authorUpdate;
    }

    public void setAuthorUpdate(Long authorUpdate) {
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
