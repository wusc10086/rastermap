/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service.parma;

/**
 *
 * @author yangzz
 */
public class MapPruductDataParm {

    private String mapxzqhid;

    private String citytype;

    private String starttime;

    private String endtime;

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
            
    private String keywords;

    private String description;

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

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
     * Get the value of citytype
     *
     * @return the value of citytype
     */
    public String getCitytype() {
        return citytype;
    }

    /**
     * Set the value of citytype
     *
     * @param citytype new value of citytype
     */
    public void setCitytype(String citytype) {
        this.citytype = citytype;
    }

    /**
     * Get the value of mapxzqhid
     *
     * @return the value of mapxzqhid
     */
    public String getMapxzqhid() {
        return mapxzqhid;
    }

    /**
     * Set the value of mapxzqhid
     *
     * @param mapxzqhid new value of mapxzqhid
     */
    public void setMapxzqhid(String mapxzqhid) {
        this.mapxzqhid = mapxzqhid;
    }

}
