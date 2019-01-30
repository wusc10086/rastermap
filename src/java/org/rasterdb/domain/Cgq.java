package org.rasterdb.domain;

import java.io.Serializable;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;


/**
 *  传感器
 * @author Administrator
 */

@Table("cgq")

public class Cgq implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id

    @Column("id")
    private Integer id;
    @Column("name")
    private String name;

    public Cgq() {
    }

    public Cgq(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Cgq)) {
            return false;
        }
        Cgq other = (Cgq) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.rasterdb.domain.Cgq[ id=" + id + " ]";
    }
    
}
