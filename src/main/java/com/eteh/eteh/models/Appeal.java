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



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @CreatedBy
    @LastModifiedBy
    private User author;




    @Size(max = 75)
    private String briefDescription;
    private String surname;

    @Size(max = 9000)
    private String  text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorUpdate")
    private User   authorUpdate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer nameCompany;
    @ManyToOne(fetch = FetchType.LAZY)
    private User executor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "footing_id")
    private Footing footing;
    @ManyToOne(fetch = FetchType.LAZY)
   private User controller;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppealStatus status;

    private String tel;

   

    private String address, emailAddress;




    public Appeal(User user, String briefDescription, Footing footing,
                  String text, User executor, User controller, AppealStatus status,
                  String surname, Date dataAnswer, Date dataCreation,
                  Customer nameCompany, String address, String tel, String emailAddress,
                  User authorUpdate, ColorStatusId color) {


        this.briefDescription= briefDescription;
        this.footing=footing;
        this.text=text;
        this.executor=executor;
        this.controller=controller;
        this.status=status;
        this.surname=surname;

        this.dataAnswer=dataAnswer;
        this.dataCreation=dataCreation;
        this.nameCompany=nameCompany;
        this.address=address;
        this.tel= tel;
        this.emailAddress=emailAddress;
        this.author = user;
        this.color=color;
        this.authorUpdate=authorUpdate;



    }



    public Customer getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(Customer nameCompany) {
        this.nameCompany = nameCompany;
    }

    public Footing getFooting() {
        return footing;
    }

    public void setFooting(Footing footing) {
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

    public User getController() {
        return controller;
    }

    public void setController(User controller) {
        this.controller = controller;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }



    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public User getAuthorUpdate() {
        return authorUpdate;
    }

    public void setAuthorUpdate(User authorUpdate) {
        this.authorUpdate = authorUpdate;
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
                ", nameCompany='" + nameCompany + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
