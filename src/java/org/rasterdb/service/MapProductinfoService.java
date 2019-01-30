/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import org.rasterdb.domain.MapPruductData;

/**
 *
 * @author yangzz
 */

@IocBean(fields = {"dao"}, name = "mapProductinfoService")
public class MapProductinfoService {
    
   @Inject
    protected Dao dao2;
    public MapPruductData queryMapPruductData(String mn) {
        return dao2.fetch(MapPruductData.class, Cnd.where("mapname","=", mn));
    }
}
