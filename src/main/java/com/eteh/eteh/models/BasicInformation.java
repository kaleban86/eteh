package com.eteh.eteh.models;


import com.sun.javafx.beans.IDProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BasicInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String productType,
            designFeatures,
            modifications,
            serialNumber,
            systemSerialNumber;

    public BasicInformation() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getDesignFeatures() {
        return designFeatures;
    }

    public void setDesignFeatures(String designFeatures) {
        this.designFeatures = designFeatures;
    }

    public String getModifications() {
        return modifications;
    }

    public void setModifications(String modifications) {
        this.modifications = modifications;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSystemSerialNumber() {
        return systemSerialNumber;
    }

    public void setSystemSerialNumber(String systemSerialNumber) {
        this.systemSerialNumber = systemSerialNumber;
    }

    @Override
    public String toString() {
        return "BasicInformation{" +
                "id=" + id +
                ", productType='" + productType + '\'' +
                ", designFeatures='" + designFeatures + '\'' +
                ", modifications='" + modifications + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", systemSerialNumber='" + systemSerialNumber + '\'' +
                '}';
    }
}
