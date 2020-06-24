package com.eteh.eteh.models;


import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Long getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(Long numberOrder) {
        this.numberOrder = numberOrder;
    }

    public Date getDateShipment() {
        return dateShipment;
    }

    public void setDateShipment(Date dateShipment) {
        this.dateShipment = dateShipment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getListResponsiblePersons() {
        return listResponsiblePersons;
    }

    public void setListResponsiblePersons(String listResponsiblePersons) {
        this.listResponsiblePersons = listResponsiblePersons;
    }

    public String getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(String equipmentList) {
        this.equipmentList = equipmentList;
    }

    public Production() {

    }

    public Production( String organization, Long numberOrder, Date dateShipment, Date creationDate, String listResponsiblePersons, String equipmentList) {

        this.organization = organization;
        this.numberOrder = numberOrder;
        this.dateShipment = dateShipment;
        this.creationDate = creationDate;
        this.listResponsiblePersons = listResponsiblePersons;
        this.equipmentList = equipmentList;
    }

    private String organization;
    private Long  numberOrder;
    private Date dateShipment;
    private Date creationDate;
    private String listResponsiblePersons;
    private String equipmentList;


    @Override
    public String toString() {
        return "Production{" +
                "id=" + id +
                ", organization='" + organization + '\'' +
                ", numberOrder=" + numberOrder +
                ", dateShipment=" + dateShipment +
                ", creationDate=" + creationDate +
                ", listResponsiblePersons='" + listResponsiblePersons + '\'' +
                ", equipmentList='" + equipmentList + '\'' +
                '}';
    }
}


