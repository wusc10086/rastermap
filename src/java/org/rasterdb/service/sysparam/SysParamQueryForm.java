/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service.sysparam;

import java.io.Serializable;

/**
 *
 * @author wuyiwei
 */
public class SysParamQueryForm implements Serializable{
    
    
    
    
    private String paramCategory;

    public String getParamCategory() {
        return paramCategory;
    }

    public void setParamCategory(String paramCategory) {
        this.paramCategory = paramCategory;
    }
    
    
    
}
