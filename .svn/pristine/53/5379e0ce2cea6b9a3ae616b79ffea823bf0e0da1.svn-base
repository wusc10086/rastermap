/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.modules;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.rasterdb.domain.MapPruductData;
import org.rasterdb.service.MapProductService;


/**
 *
 * @author yangzz
 */
@At("/mapdatainfo")
@Ok("json")
@IocBean
public class MapProductinfoMudule {

    @Inject
    private  MapProductService mapProductService;

    @At("/mapdatasourceinfo/mapid/?")
    @GET
    public MapPruductData getDataProductinfo(String mn) {
        return mapProductService.queryMapPruductData(mn);
    }
}
