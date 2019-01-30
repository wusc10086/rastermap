/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service.parma;

/**
 *
 * @author zhangpan
 */
public class Warning {
    
    private String num;
    private String latitude;
    private String longitude;
    private String alertArede;
    private String secdata;

    /**
     * @return the num
     */
    public String getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
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
    public String getSendData() {
        return secdata;
    }

    /**
     * @param secdata the secdata to set
     */
    public void setSendData(String secdata) {
        this.secdata = secdata;
    }
    
    
}
