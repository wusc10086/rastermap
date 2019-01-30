/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service.sysparam;

import java.io.Serializable;
import java.sql.Timestamp;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;

/**
 * 系统参数
 * @author wuyiwei
 */
public class SysParam implements Serializable {
    
    @Id(auto=true)
    @Column("ID")
    private Integer id;
    /**
     * 参数名称
     */
    @Column("param_name")
    private String paramName;
    /**
     * 参数值
     */
    @Column("param_value")
    private String paramValue;
    /**
     * 参数类型
     */
    @Column("param_category")
    private String paramCategory;
    /**
     * 参数中文名称
     */
    @Column("short_desc")
    private String shortDesc;
    /**
     * 参数说明
     */
    @Column("long_desc")
    private String longDesc;
    /**
     * 创建时间
     */
    @Column("ctime")
    private Timestamp ctime;
    /**
     * 修改时间
     */
    @Column("mtime")
    private Timestamp mtime;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final SysParam other = (SysParam) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SysParam{" + "id=" + id + ", paramName=" + paramName + ", paramValue=" + paramValue + ", paramCategory=" + paramCategory + ", shortDesc=" + shortDesc + ", longDesc=" + longDesc + ", ctime=" + ctime + ", mtime=" + mtime + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamCategory() {
        return paramCategory;
    }

    public void setParamCategory(String paramCategory) {
        this.paramCategory = paramCategory;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    public Timestamp getMtime() {
        return mtime;
    }

    public void setMtime(Timestamp mtime) {
        this.mtime = mtime;
    }
    
    
}
