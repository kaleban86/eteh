package com.eteh.eteh.models;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
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
//@OptimisticLocking(type = OptimisticLockType.DIRTY)
public class Appeal  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @CreationTimestamp
    @CreatedDate
    private Date dataCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_color")
    private ColorStatusId color;



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
    private String  text,surname, lastName;

    private Long  controller, authorUpdate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer nameCompany;
    @ManyToOne(fetch = FetchType.LAZY)
    private User executor;

    private Long footing;



    @ManyToOne(fetch = FetchType.LAZY)
    private AppealStatus status;

    private String tel;

   

    private String address, emailAddress;




    public Appeal(User user, String briefDescription, Long footing,
                  String text, User executor, Long controller, AppealStatus status,
                  String surname, String lastName, Date dataAnswer, Date dataCreation,
                  Customer nameCompany, String address, String tel, String emailAddress,
                  Long authorUpdate, ColorStatusId color) {


        this.briefDescription= briefDescription;
        this.footing=footing;
        this.text=text;
        this.executor=executor;
        this.controller=controller;
        this.status=status;
        this.surname=surname;
        this.lastName=lastName;
        this.dataAnswer=dataAnswer;
        this.dataCreation=dataCreation;
        this.nameCompany=nameCompany;
        this.address=address;
        this.tel= tel;
        this.emailAddress=emailAddress;
        this.authorUpdate=authorUpdate;
        this.authorUpdate = authorUpdate;
        this.author = user;
        this.color=color;



    }

    public Long getFooting() {
        return footing;
    }

    public Customer getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(Customer nameCompany) {
        this.nameCompany = nameCompany;
    }

    public void setFooting(Long footing) {
        this.footing = footing;
    }

    public ColorStatusId getColor() {
        return color;
    }

    public void setColor(ColorStatusId color) {
        this.color = color;
    }
    public Appeal() {
    }

    public AppealStatus getStatus() {
        return status;
    }


    public void setStatus(AppealStatus status) {
        this.status = status;
    }
    public String getBriefDescription() {
        return briefDescription;
    }


    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
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

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public Long getController() {
        return controller;
    }

    public void setController(Long controller) {
        this.controller = controller;
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
