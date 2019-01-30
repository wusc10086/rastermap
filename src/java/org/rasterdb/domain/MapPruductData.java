/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.domain;

import java.io.Serializable;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 *
 * @author yangzz
 */
@Table("js_product_data")
public class MapPruductData implements Serializable {

    @Id
    @Column("id")
    private Integer id;
    @Column("shpurl")
    private String shpurl;
    @Column("mapurl")
    private String mapurl;
    @Column("mapname")
    private String mapname;
    @Column("cityid")
    private String cityid;
    @Column("mapid")
    private String mapid;
    @Column("provinceid")
    private String provinceid;
    @Column("datasource")
    private String datasource;
    @Column("photodate")
    private String photodate;
    @Column("productdate")
    private String productdate;
    @Column("dataamount")
    private String dataamount;
    @Column("format")
    private String format;
    @Column("cor")
    private String cor;
    @Column("ownership")
    private String ownership;
    @Column("tel")
    private String tel;
    @Column("email")
    private String email;
    @Column("description")
    private String description;
    @Column("productor")
    private String productor;
    @Column("keywords")
    private String keywords;

    /**
     * Get the value of keywords
     *
     * @return the value of keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * Set the value of keywords
     *
     * @param keywords new value of keywords
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * Get the value of productor
     *
     * @return the value of productor
     */
    public String getProductor() {
        return productor;
    }

    /**
     * Set the value of productor
     *
     * @param productor new value of productor
     */
    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }

   
    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of tel
     *
     * @return the value of tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * Set the value of tel
     *
     * @param tel new value of tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * Get the value of ownership
     *
     * @return the value of ownership
     */
    public String getOwnership() {
        return ownership;
    }

    /**
     * Set the value of ownership
     *
     * @param ownership new value of ownership
     */
    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    /**
     * Get the value of cor
     *
     * @return the value of cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * Set the value of cor
     *
     * @param cor new value of cor
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * Get the value of format
     *
     * @return the value of format
     */
    public String getFormat() {
        return format;
    }

    /**
     * Set the value of format
     *
     * @param format new value of format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Get the value of dataamount
     *
     * @return the value of dataamount
     */
    public String getDataamount() {
        return dataamount;
    }

    /**
     * Set the value of dataamount
     *
     * @param dataamount new value of dataamount
     */
    public void setDataamount(String dataamount) {
        this.dataamount = dataamount;
    }

    /**
     * Get the value of productdate
     *
     * @return the value of productdate
     */
    public String getProductdate() {
        return productdate;
    }

    /**
     * Set the value of productdate
     *
     * @param productdate new value of productdate
     */
    public void setProductdate(String productdate) {
        this.productdate = productdate;
    }

    /**
     * Get the value of photodate
     *
     * @return the value of photodate
     */
    public String getPhotodate() {
        return photodate;
    }

    /**
     * Set the value of photodate
     *
     * @param photodate new value of photodate
     */
    public void setPhotodate(String photodate) {
        this.photodate = photodate;
    }

    /**
     * Get the value of datasource
     *
     * @return the value of datasource
     */
    public String getDatasource() {
        return datasource;
    }

    /**
     * Set the value of datasource
     *
     * @param datasource new value of datasource
     */
    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    /**
     * Get the value of provinceid
     *
     * @return the value of provinceid
     */
    public String getProvinceid() {
        return provinceid;
    }

    /**
     * Set the value of provinceid
     *
     * @param provinceid new value of provinceid
     */
    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    /**
     * Get the value of mapid
     *
     * @return the value of mapid
     */
    public String getMapid() {
        return mapid;
    }

    /**
     * Set the value of mapid
     *
     * @param mapid new value of mapid
     */
    public void setMapid(String mapid) {
        this.mapid = mapid;
    }

    /**
     * Get the value of cityid
     *
     * @return the value of cityid
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * Set the value of cityid
     *
     * @param cityid new value of cityid
     */
    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    /**
     * Get the value of mapname
     *
     * @return the value of mapname
     */
    public String getMapname() {
        return mapname;
    }

    /**
     * Set the value of mapname
     *
     * @param mapname new value of mapname
     */
    public void setMapname(String mapname) {
        this.mapname = mapname;
    }

    /**
     * Get the value of mapurl
     *
     * @return the value of mapurl
     */
    public String getMapurl() {
        return mapurl;
    }

    /**
     * Set the value of mapurl
     *
     * @param mapurl new value of mapurl
     */
    public void setMapurl(String mapurl) {
        this.mapurl = mapurl;
    }

    /**
     * Get the value of shpurl
     *
     * @return the value of shpurl
     */
    public String getShpurl() {
        return shpurl;
    }

    /**
     * Set the value of shpurl
     *
     * @param shpurl new value of shpurl
     */
    public void setShpurl(String shpurl) {
        this.shpurl = shpurl;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MapPruductData{" + "id=" + id + ", shpurl=" + shpurl + ", mapurl=" + mapurl + ", mapname=" + mapname + ", cityid=" + cityid + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final MapPruductData other = (MapPruductData) obj;
        if ((this.shpurl == null) ? (other.shpurl != null) : !this.shpurl.equals(other.shpurl)) {
            return false;
        }
        if ((this.cityid == null) ? (other.cityid != null) : !this.cityid.equals(other.cityid)) {
            return false;
        }
        return true;
    }

}
