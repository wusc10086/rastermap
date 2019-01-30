package org.rasterdb.domain;

import java.io.Serializable;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 数据源信息
 * @author Administrator
 */

@Table("R_DATASOURCEINFO")

public class RDatasourceinfo implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @Column("ID")
    private Integer id;
    
    @Column("SN")
    private String sn;
    
    @Column("SATENAME")
    private String satename;
    
    @Column("PBANDSENSORTYPE")
    private String pbandsensortype;
    
    @Column("SATERESOLUTION")
    private Integer sateresolution;
    
    @Column("PBANDORBITCODE")
    private String pbandorbitcode;
    
    @Column("PBANDDATE")
    private String pbanddate;
    
    @Column("MULTIBANDSENSORTYPE")
    private String multibandsensortype;
    @Column("MULTIBANDNUM")
    private Integer multibandnum;
    
    @Column("MULTIBANDNAME")
    private String multibandname;
    @Column("MULTIBANDRESOLUTION")
    private Integer multibandresolution;
    
    @Column("MULTIBANDORBITCODE")
    private String multibandorbitcode;
    
    @Column("MULTIBANDDATE")
    private String multibanddate;
    
    @Column("SATEIMGQUALITY")
    private String sateimgquality;

    public RDatasourceinfo() {
    }

    public RDatasourceinfo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSatename() {
        return satename;
    }

    public void setSatename(String satename) {
        this.satename = satename;
    }

    public String getPbandsensortype() {
        return pbandsensortype;
    }

    public void setPbandsensortype(String pbandsensortype) {
        this.pbandsensortype = pbandsensortype;
    }

    public Integer getSateresolution() {
        return sateresolution;
    }

    public void setSateresolution(Integer sateresolution) {
        this.sateresolution = sateresolution;
    }

    public String getPbandorbitcode() {
        return pbandorbitcode;
    }

    public void setPbandorbitcode(String pbandorbitcode) {
        this.pbandorbitcode = pbandorbitcode;
    }

    public String getPbanddate() {
        return pbanddate;
    }

    public void setPbanddate(String pbanddate) {
        this.pbanddate = pbanddate;
    }

    public String getMultibandsensortype() {
        return multibandsensortype;
    }

    public void setMultibandsensortype(String multibandsensortype) {
        this.multibandsensortype = multibandsensortype;
    }

    public Integer getMultibandnum() {
        return multibandnum;
    }

    public void setMultibandnum(Integer multibandnum) {
        this.multibandnum = multibandnum;
    }

    public String getMultibandname() {
        return multibandname;
    }

    public void setMultibandname(String multibandname) {
        this.multibandname = multibandname;
    }

    public Integer getMultibandresolution() {
        return multibandresolution;
    }

    public void setMultibandresolution(Integer multibandresolution) {
        this.multibandresolution = multibandresolution;
    }

    public String getMultibandorbitcode() {
        return multibandorbitcode;
    }

    public void setMultibandorbitcode(String multibandorbitcode) {
        this.multibandorbitcode = multibandorbitcode;
    }

    public String getMultibanddate() {
        return multibanddate;
    }

    public void setMultibanddate(String multibanddate) {
        this.multibanddate = multibanddate;
    }

    public String getSateimgquality() {
        return sateimgquality;
    }

    public void setSateimgquality(String sateimgquality) {
        this.sateimgquality = sateimgquality;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RDatasourceinfo)) {
            return false;
        }
        RDatasourceinfo other = (RDatasourceinfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.rasterdb.domain.RDatasourceinfo[ id=" + id + " ]";
    }
    
}
