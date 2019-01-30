
package org.rasterdb.service.parma;

/**
 *  数据源参数
 * @author Administrator
 */
public class DataSourceParm {
     private String satallite;
     private String cgq ;
     private String colormode;
     private String starttime;
     private String endtime;
     private String xzqhid;
     private String citytype;
     private Integer cloud;

    public String getSatallite() {
        return satallite;
    }

    public void setSatallite(String satallite) {
        this.satallite = satallite;
    }

    public String getCgq() {
        return cgq;
    }

    public void setCgq(String cgq) {
        this.cgq = cgq;
    }

    public String getStarttime() {
        return starttime.substring(0, 6);
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime.substring(0, 6);
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Integer getCloud() {
        return cloud;
    }

    public void setCloud(Integer cloud) {
        this.cloud = cloud;
    }

    public String getColormode() {
        return colormode;
    }

    public void setColormode(String colormode) {
        this.colormode = colormode;
    }

    public String getXzqhid() {
        return xzqhid;
    }

    public void setXzqhid(String xzqhid) {
        this.xzqhid = xzqhid;
    }

    public String getCitytype() {
        return citytype;
    }

    public void setCitytype(String citytype) {
        this.citytype = citytype;
    }
 }
