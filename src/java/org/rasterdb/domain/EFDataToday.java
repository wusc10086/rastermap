/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 *
 * @author admin
 * 当天数据
 */
@Table("efdata_today")
public class EFDataToday  implements  Serializable{
 private static final long serialVersionUID = 1L;
    
    public EFDataToday()
    {
        
    }
   
    @Column("Id")
    private Integer id;
 
    @Column("DeviceId")
    private String deviceid;
 
    @Column("SendTime")
    private Timestamp sendTime;
  
    @Column("SecondData")
    private String secondData;
    
    @Column("LowValue")
    private String lowValue;
    @Column("HighValue")
    private String highValue;
    
    @Column("Average")
    private String average;
    
    @Column("Communication")
    private String communication;
    
    @Column("Status")
    private String status;
    
    @Column("Temperature")
    private String temperature;
    @Column("Voltage")
    private String voltage;
    @Column("Rotate")
    private String rotate;
    @Column("Frequency")
    private  String frequency;
    @Column("Version")
    private String version;  
    @Column("SelfDef1")
    private String selfDef1;
    @Column("SelfDef2")
    private String selfDef2;
   @Column("Curvalue")
    private String curvalue;

    public String getCurvalue() {
        return curvalue;
    }

    public void setCurvalue(String curvalue) {
        this.curvalue = curvalue;
    }

    
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    
    
    /**
     * @return the lowValue
     */
    public String getLowValue() {
        return lowValue;
    }

    /**
     * @param lowValue the lowValue to set
     */
    public void setLowValue(String lowValue) {
        this.lowValue = lowValue;
    }

    /**
     * @return the highValue
     */
    public String getHighValue() {
        return highValue;
    }

    /**
     * @param highValue the highValue to set
     */
    public void setHighValue(String highValue) {
        this.highValue = highValue;
    }

    /**
     * @return the average
     */
    public String getAverage() {
        return average;
    }

    /**
     * @param average the average to set
     */
    public void setAverage(String average) {
        this.average = average;
    }

    /**
     * @return the communication
     */
    public String getCommunication() {
        return communication;
    }

    /**
     * @param communication the communication to set
     */
    public void setCommunication(String communication) {
        this.communication = communication;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the temperature
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the voltage
     */
    public String getVoltage() {
        return voltage;
    }

    /**
     * @param voltage the voltage to set
     */
    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    /**
     * @return the rotate
     */
    public String getRotate() {
        return rotate;
    }

    /**
     * @param rotate the rotate to set
     */
    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    /**
     * @return the frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the selfDef1
     */
    public String getSelfDef1() {
        return selfDef1;
    }

    /**
     * @param selfDef1 the selfDef1 to set
     */
    public void setSelfDef1(String selfDef1) {
        this.selfDef1 = selfDef1;
    }

    /**
     * @return the selfDef2
     */
    public String getSelfDef2() {
        return selfDef2;
    }

    /**
     * @param selfDef2 the selfDef2 to set
     */
    public void setSelfDef2(String selfDef2) {
        this.selfDef2 = selfDef2;
    }

    /**
     * @return the secondData
     */
    public String getSecondData() {
        return secondData;
    }

    /**
     * @param secondData the secondData to set
     */
    public void setSecondData(String secondData) {
        this.secondData = secondData;
    }
    
     private String deviceName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    
  
}