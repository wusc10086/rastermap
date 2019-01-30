/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.rasterdb.service.PropertiesQueryService;

/**
 * 数据详细信息查询
 * @author Administrator
 */

@At("/properties")
@IocBean
public class PropertiesQueryModule {
    @Inject
   private PropertiesQueryService propertiesQueryService;
    
    /**
     * 查询生产单位和产品名称
     * @return 
     */
    @At("/product")
    public Object getProperties(){
        
        List objlist = new ArrayList();
        
        Map product = propertiesQueryService.queryProductName();
        Map producer = propertiesQueryService.queryProducer();
        objlist.add(product);
        objlist.add(producer);
        
        return objlist;
        
    }
}
