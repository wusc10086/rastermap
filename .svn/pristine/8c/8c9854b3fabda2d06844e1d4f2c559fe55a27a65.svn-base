package org.rasterdb.domain;

import java.io.Serializable;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.SQL;
import org.nutz.dao.entity.annotation.Table;

/**
 *  用户对象
 * @author gis
 */

@Table("T_USER")

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final String USER_ROLE_ADMIN = "管理员";
    
    public static final String USER_ROLE_COMM_USER = "普通用户";
    
    // 用户状态：正常
    public static final int STATUS_NORMAL = 1; 
    // 用户状态：停用
    public static final int STATUS_DISABLE = 0; 
    
    
    //@Id(auto=false)
    @Id(auto=true)
    @Column("ID")
    //@Prev(@SQL("select nextval('seq_user')"))
    private Integer id;
    @Column("USERNAME")
    private String username;
    @Column("PASSWD")
    private String passwd;
    @Column("DEPT")
    private String dept;
    @Column("roles")
    private String roles;
    @Column("user_real_name")
    private String userRealName;
    @Column("cityid")
    private String cityid;
    @Column("user_status")
    private int status = STATUS_NORMAL;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String username, String passwd) {
        this.id = id;
        this.username = username;
        this.passwd = passwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.rasterdb.domain.User[ id=" + id + " ]";
    }
    
    
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    
}
