package com.eteh.eteh.models;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;


@Entity
public class Repairs  {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private Date dataReceipt,repairPeriod,repairOver,dateShipment;
    private String serialNumber,descriptionRepair,diagnosticsRepairs,replacingAppliances,executionWork;

    public Repairs() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataReceipt() {
        return dataReceipt;
    }

    public void setDataReceipt(Date dataReceipt) {
        this.dataReceipt = dataReceipt;
    }

    public Date getRepairPeriod() {
        return repairPeriod;
    }

    public void setRepairPeriod(Date repairPeriod) {
        this.repairPeriod = repairPeriod;
    }

    public Date getRepairOver() {
        return repairOver;
    }

    public void setRepairOver(Date repairOver) {
        this.repairOver = repairOver;
    }

    public Date getDateShipment() {
        return dateShipment;
    }

    public void setDateShipment(Date dateShipment) {
        this.dateShipment = dateShipment;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDescriptionRepair() {
        return descriptionRepair;
    }

    public void setDescriptionRepair(String descriptionRepair) {
        this.descriptionRepair = descriptionRepair;
    }

    public String getDiagnosticsRepairs() {
        return diagnosticsRepairs;
    }

    public void setDiagnosticsRepairs(String diagnosticsRepairs) {
        this.diagnosticsRepairs = diagnosticsRepairs;
    }

    public String getReplacingAppliances() {
        return replacingAppliances;
    }

    public void setReplacingAppliances(String replacingAppliances) {
        this.replacingAppliances = replacingAppliances;
    }

    public String getExecutionWork() {
        return executionWork;
    }

    public void setExecutionWork(String executionWork) {
        this.executionWork = executionWork;
    }
}
