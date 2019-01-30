/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.modules;

import java.util.List;
import java.util.Map;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.rasterdb.service.EFDataTodayService;

/**
 *趋势数据controller
 * @author admin
 */

@IocBean
@At("trand")
@Ok("json")
public class TrandModule {
    @Inject
    public EFDataTodayService eFDataTodayService;
    
    @At("/getTrandData/?/?")
    public List<Map<String,String>> getTrandData(String startTime,String endTime)
    {
        return  eFDataTodayService.getTrandData(startTime, endTime);
    }
}
