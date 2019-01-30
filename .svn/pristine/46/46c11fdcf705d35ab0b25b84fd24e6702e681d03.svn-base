/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service.device;

import java.io.Serializable;

/**
 * 设备查询的表单form
 * @author wuyiwei
 * 
 * 修改t_user表的sql：
 * alter table t_user add column user_real_name character varying(50);
 * alter table t_user add column device_num character varying(50);
 * alter table t_user add column user_status int;
 * create sequence seq_user increment by 1 minvalue 100 no maxvalue start with 100;
 * 
 */
public class DeviceQueryForm implements Serializable{
    
    /**
     * 地市
     */
    private String cityid;

    @Override
    public String toString() {
        return "UserQueryForm{" + "cityid=" + cityid + '}';
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

}
