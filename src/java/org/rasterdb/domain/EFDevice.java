/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 *
 * @author Administrator
 * 电场设备
 */
@Table("efdevice")
public class EFDevice  implements  Serializable{
    private static final long serialVersionUID = 1L;
    
    public  EFDevice()
    {}
    
    //@Id(auto=false)
    @Name
    @Column("Num")
    private String num;
    @Column("DeviceName")
    private String deviceName;
    @Column("Latitude")
    private BigDecimal latitude;
    @Column("Longitude")
    private BigDecimal longitude;
    @Column("Elevation")
    private BigDecimal elevation;
    @Column("Manufaceture")
    private String manufaceture;
    
    @Column("InstallTime")
    private Date installTime;
    @Column("LastInspect")
    private Date lastInSpect;
    @Column("LastRepair")
    private Date lastRepair;
    @Column("cityid")
    private String cityid;
    @Column("Status")
    private String status;
    @Column("Lastalarmtime")
    private Timestamp lastalarmtime;//最近一次预警时间
    @Column("Laststatustime")
    private Timestamp laststatustime;//最近一次状态时间
    @Column("Efvalue")
    private String efvalue;//电场值
    @Column("alarmlevel")
    private Integer alarmlevel;

    public Integer getAlarmlevel() {
        return alarmlevel;
    }

    public void setAlarmlevel(Integer alarmlevel) {
        this.alarmlevel = alarmlevel;
    }

    public Timestamp getLastalarmtime() {
        return lastalarmtime;
    }

    public void setLastalarmtime(Timestamp lastalarmtime) {
        this.lastalarmtime = lastalarmtime;
    }

   
    public Timestamp getLaststatustime() {
        return laststatustime;
    }

    public void setLaststatustime(Timestamp laststatustime) {
        this.laststatustime = laststatustime;
    }
     

    public EFDevice(String num, String deviceName, BigDecimal latitude, Double BigDecimal, BigDecimal elevation, String manufaceture, Date installTime, Date lastInSpect, Date lastRepair, String cityid, String status, String Lastalarmtime, String laststatustime, String efvalue, Integer alarmlevel) {
        this.num = num;
        this.deviceName = deviceName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.manufaceture = manufaceture;
        this.installTime = installTime;
        this.lastInSpect = lastInSpect;
        this.lastRepair = lastRepair;
        this.cityid = cityid;
        this.status = status;
        this.efvalue = efvalue;
        this.alarmlevel = alarmlevel;
    }
    
    public String getEfvalue() {
        return efvalue;
    }

    public void setEfvalue(String efvalue) {
        this.efvalue = efvalue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
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
     * @return the deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * @param deviceName the deviceName to set
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * @return the latitude
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the elevation
     */
    public BigDecimal getElevation() {
        return elevation;
    }

    /**
     * @param elevation the elevation to set
     */
    public void setElevation(BigDecimal elevation) {
        this.elevation = elevation;
    }

    /**
     * @return the manufaceture
     */
    public String getManufaceture() {
        return manufaceture;
    }

    /**
     * @param manufaceture the manufaceture to set
     */
    public void setManufaceture(String manufaceture) {
        this.manufaceture = manufaceture;
    }

    /**
     * @return the installTime
     */
    public Date getInstallTime() {
        return installTime;
    }

    /**
     * @param installTime the installTime to set
     */
    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    /**
     * @return the lastInSpect
     */
    public Date getLastInSpect() {
        return lastInSpect;
    }

    /**
     * @param lastInSpect the lastInSpect to set
     */
    public void setLastInSpect(Date lastInSpect) {
        this.lastInSpect = lastInSpect;
    }

    /**
     * @return the lastRepair
     */
    public Date getLastRepair() {
        return lastRepair;
    }

    /**
     * @param lastRepair the lastRepair to set
     */
    public void setLastRepair(Date lastRepair) {
        this.lastRepair = lastRepair;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.num != null ? this.num.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EFDevice other = (EFDevice) obj;
        if ((this.num == null) ? (other.num != null) : !this.num.equals(other.num)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EFDevice{" + "num=" + num + ", deviceName=" + deviceName + ", latitude=" + latitude + ", longitude=" + longitude + ", elevation=" + elevation + ", manufaceture=" + manufaceture + ", installTime=" + installTime + ", lastInSpect=" + lastInSpect + ", lastRepair=" + lastRepair + ", cityid=" + cityid + '}';
    }
    
}
