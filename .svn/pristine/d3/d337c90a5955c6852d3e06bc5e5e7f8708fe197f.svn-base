
package org.rasterdb.domain;

import java.io.Serializable;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;


/**
 * 缩略图
 * @author Administrator
 */

@Table("thumbimage")

public class Thumbimage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id

    @Column("id")
    private Integer id;

    @Column("sno")
    private String sno;

    @Column("thumbimg32")
    private byte[] thumbimg32;

    @Column("thumbimg128")
    private byte[] thumbimg128;

    public Thumbimage() {
    }

    public Thumbimage(Integer id) {
        this.id = id;
    }

    public Thumbimage(Integer id, String sno) {
        this.id = id;
        this.sno = sno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public byte[] getThumbimg32() {
        return thumbimg32;
    }

    public void setThumbimg32(byte[] thumbimg32) {
        this.thumbimg32 = thumbimg32;
    }

    public byte[] getThumbimg128() {
        return thumbimg128;
    }

    public void setThumbimg128(byte[] thumbimg128) {
        this.thumbimg128 = thumbimg128;
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
        if (!(object instanceof Thumbimage)) {
            return false;
        }
        Thumbimage other = (Thumbimage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.rasterdb.domain.Thumbimage[ id=" + id + " ]";
    }
    
}
