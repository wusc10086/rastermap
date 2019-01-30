/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.domain;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 *
 * @author yangzz
 */
@IocBean
@Table("mosaic")
public class MosaicTable {
   
    @Column("outrasterid")
    private String outrasterid;
    
    @Column("outrastername")
    private String outrastername;
    
    @Column("inputrasters")
    private String inputrasters;
    
    @Column("outrasterurl")
    private String outrasterurl;
    
    @Column("userid")
    private int userid;
    
    @Column("success")
    private int success;

    public String getOutrasterid() {
        return outrasterid;
    }

    public void setOutrasterid(String outrasterid) {
        this.outrasterid = outrasterid;
    }

    public String getOutrastername() {
        return outrastername;
    }

    public void setOutrastername(String outrastername) {
        this.outrastername = outrastername;
    }

    public String getInputrasters() {
        return inputrasters;
    }

    public void setInputrasters(String inputrasters) {
        this.inputrasters = inputrasters;
    }

    public String getOutrasterurl() {
        return outrasterurl;
    }

    public void setOutrasterurl(String outrasterurl) {
        this.outrasterurl = outrasterurl;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
