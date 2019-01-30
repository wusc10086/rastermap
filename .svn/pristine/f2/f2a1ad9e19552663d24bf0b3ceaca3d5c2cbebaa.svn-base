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
import org.nutz.mvc.annotation.POST;
import org.rasterdb.domain.RasterExtent;
import org.rasterdb.service.RasterExtentService;

/**
 * 影像范围请求
 * @author Administrator
 */

@At("/raster")
@IocBean
public class RasterExtentModule {
    
    @Inject
    private RasterExtentService rasterExtentService;
    
    @At("/extent/?")
    @POST
    @GET
    public  RasterExtent getExtent(String sno){
         return rasterExtentService.queryRasterExtent(sno);
    }
    
}
