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
 * @author zhangpan 设备预警
 *
 */
@Table("efdata_warning")
public class EFDataWarning implements Serializable {

    public EFDataWarning() {
    }

    @Column("Id")
    private Integer id;
    @Column("DeviceId")
    private String deviceid;

    @Column("SendTime")
    private Timestamp sendTime;


    @Column("AlertArede")
    private String alertArede;
    @Column("SecData")
    private String secdata;

    @Column("FirstData")
    private String firstData;

    @Column("CurData")
    private String curdata;
    @Column("DeviceName")
    private String deviceName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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
     * @return the alertArede
     */
    public String getAlertArede() {
        return alertArede;
    }

    /**
     * @param alertArede the alertArede to set
     */
    public void setAlertArede(String alertArede) {
        this.alertArede = alertArede;
    }

    /**
     * @return the secdata
     */
    public String getSecdata() {
        return secdata;
    }

    /**
     * @param secdata the secdata to set
     */
    public void setSecdata(String secdata) {
        this.secdata = secdata;
    }

    /**
     * @return the firstData
     */
    public String getFirstData() {
        return firstData;
    }

    /**
     * @param firstData the firstData to set
     */
    public void setFirstData(String firstData) {
        this.firstData = firstData;
    }

    /**
     * @return the curdata
     */
    public String getCurData() {
        return curdata;
    }

    /**
     * @param curdata the curdata to set
     */
    public void setCurData(String curdata) {
        this.curdata = curdata;
    }

}
