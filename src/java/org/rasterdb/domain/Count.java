/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.domain;

/**
 *
 * @author zhang
 */
public class Count {
    private Integer count;
    private String deviceid;
    private Double ratio;
    private String deviceName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
    
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public Count(Integer count, String deviceid) {
        this.count = count;
        this.deviceid = deviceid;
    }
    public Count(){
        
    }

    @Override
    public String toString() {
        return "Count{" + "count=" + count + ", deviceid=" + deviceid + '}';
    }
    
}
