/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 *
 * @author admin
 */
@IocBean(name ="todayEFDataService")
public class TodayEFDataService {
    
    @Inject("dao2")
    protected  Dao dao2;
    
    
}
