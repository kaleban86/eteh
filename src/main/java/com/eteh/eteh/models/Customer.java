package com.eteh.eteh.models;


import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameCompany;
    private String fullNameCompany;
    private String legalAddress;
    private String actualAddress;
    private Long inn;
    private Long ogrnip;
    private Long okpo;
    private String email;
    private String site;
    private String phone;

    public Customer(String nameCompany, String fullNameCompany, String legalAddress, String actualAddress, Long inn, Long ogrnip, Long okpo, String email, String site, String phone) {
        this.nameCompany = nameCompany;
        this.fullNameCompany = fullNameCompany;
        this.legalAddress = legalAddress;
        this.actualAddress = actualAddress;
        this.inn = inn;
        this.ogrnip = ogrnip;
        this.okpo = okpo;
        this.email = email;
        this.site = site;
        this.phone = phone;
    }

    public Customer() {

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getFullNameCompany() {
        return fullNameCompany;
    }

    public void setFullNameCompany(String fullNameCompany) {
        this.fullNameCompany = fullNameCompany;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public void setActualAddress(String actualAddress) {
        this.actualAddress = actualAddress;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public Long getOgrnip() {
        return ogrnip;
    }

    public void setOgrnip(Long ogrnip) {
        this.ogrnip = ogrnip;
    }

    public Long getOkpo() {
        return okpo;
    }

    public void setOkpo(Long okpo) {
        this.okpo = okpo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
