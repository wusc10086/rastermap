
package org.rasterdb.domain;

import java.io.Serializable;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;


/**
 *
 * @author Administrator
 */

@Table("city_dist_area")

public class CityDistArea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column("gid")
    private Integer gid;
    @Column("id")
    private Double id;
    @Column("type")
    private Double type;
    @Column("name")
    private String name;
    @Column("pyname")
    private String pyname;
    @Column("enname")
    private String enname;
    @Column("distid")
    private Double distid;
    @Column("level")
    private Double level;
    @Column("mapname")
    private String mapname;
    @Column("prefix")
    private Double prefix;
    @Column("from")
    private String from;
    @Column("editor")
    private String editor;
    @Column("remark")
    private String remark;
  
    @Column("geom")
    private Object theGeom;

    public CityDistArea() {
    }

    public CityDistArea(Integer gid) {
        this.gid = gid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Double getType() {
        return type;
    }

    public void setType(Double type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPyname() {
        return pyname;
    }

    public void setPyname(String pyname) {
        this.pyname = pyname;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public Double getDistid() {
        return distid;
    }

    public void setDistid(Double distid) {
        this.distid = distid;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public String getMapname() {
        return mapname;
    }

    public void setMapname(String mapname) {
        this.mapname = mapname;
    }

    public Double getPrefix() {
        return prefix;
    }

    public void setPrefix(Double prefix) {
        this.prefix = prefix;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Object getTheGeom() {
        return theGeom;
    }

    public void setTheGeom(Object theGeom) {
        this.theGeom = theGeom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gid != null ? gid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CityDistArea)) {
            return false;
        }
        CityDistArea other = (CityDistArea) object;
        if ((this.gid == null && other.gid != null) || (this.gid != null && !this.gid.equals(other.gid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.rasterdb.domain.CityDistArea[ gid=" + gid + " ]";
    }
    
}
 