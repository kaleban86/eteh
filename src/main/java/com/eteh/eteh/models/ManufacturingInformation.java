package com.eteh.eteh.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class ManufacturingInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String softwareVersion,
            designFeatures
            ,controllerBoard,
            preampBoard,
            sensorSystem, weightPipe1,
            weightPipe2,sensorsL1,sensorsL2,
            pressureSensorNumber,
            checkStabilityWater,coss,balance,amplitude,shift,purity,psiMaximumError;

    public ManufacturingInformation() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getDesignFeatures() {
        return designFeatures;
    }

    public void setDesignFeatures(String designFeatures) {
        this.designFeatures = designFeatures;
    }

    public String getControllerBoard() {
        return controllerBoard;
    }

    public void setControllerBoard(String controllerBoard) {
        this.controllerBoard = controllerBoard;
    }

    public String getPreampBoard() {
        return preampBoard;
    }

    public void setPreampBoard(String preampBoard) {
        this.preampBoard = preampBoard;
    }

    public String getSensorSystem() {
        return sensorSystem;
    }

    public void setSensorSystem(String sensorSystem) {
        this.sensorSystem = sensorSystem;
    }

    public String getWeightPipe1() {
        return weightPipe1;
    }

    public void setWeightPipe1(String weightPipe1) {
        this.weightPipe1 = weightPipe1;
    }

    public String getWeightPipe2() {
        return weightPipe2;
    }

    public void setWeightPipe2(String weightPipe2) {
        this.weightPipe2 = weightPipe2;
    }

    public String getSensorsL1() {
        return sensorsL1;
    }

    public void setSensorsL1(String sensorsL1) {
        this.sensorsL1 = sensorsL1;
    }

    public String getSensorsL2() {
        return sensorsL2;
    }

    public void setSensorsL2(String sensorsL2) {
        this.sensorsL2 = sensorsL2;
    }

    public String getPressureSensorNumber() {
        return pressureSensorNumber;
    }

    public void setPressureSensorNumber(String pressureSensorNumber) {
        this.pressureSensorNumber = pressureSensorNumber;
    }

    public String getCheckStabilityWater() {
        return checkStabilityWater;
    }

    public void setCheckStabilityWater(String checkStabilityWater) {
        this.checkStabilityWater = checkStabilityWater;
    }

    public String getCoss() {
        return coss;
    }

    public void setCoss(String coss) {
        this.coss = coss;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(String amplitude) {
        this.amplitude = amplitude;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getPurity() {
        return purity;
    }

    public void setPurity(String purity) {
        this.purity = purity;
    }

    public String getPsiMaximumError() {
        return psiMaximumError;
    }

    public void setPsiMaximumError(String psiMaximumError) {
        this.psiMaximumError = psiMaximumError;
    }

    @Override
    public String toString() {
        return "ManufacturingInformation{" +
                "id=" + id +
                ", softwareVersion='" + softwareVersion + '\'' +
                ", designFeatures='" + designFeatures + '\'' +
                ", controllerBoard='" + controllerBoard + '\'' +
                ", preampBoard='" + preampBoard + '\'' +
                ", sensorSystem='" + sensorSystem + '\'' +
                ", weightPipe1='" + weightPipe1 + '\'' +
                ", weightPipe2='" + weightPipe2 + '\'' +
                ", sensorsL1='" + sensorsL1 + '\'' +
                ", sensorsL2='" + sensorsL2 + '\'' +
                ", pressureSensorNumber='" + pressureSensorNumber + '\'' +
                ", checkStabilityWater='" + checkStabilityWater + '\'' +
                ", coss='" + coss + '\'' +
                ", balance='" + balance + '\'' +
                ", amplitude='" + amplitude + '\'' +
                ", shift='" + shift + '\'' +
                ", purity='" + purity + '\'' +
                ", psiMaximumError='" + psiMaximumError + '\'' +
                '}';
    }
}


