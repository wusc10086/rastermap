/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.rasterdb.domain.MapPruductData;
import org.rasterdb.service.parma.MapPruductDataParm;

/**
 *制图产品信息查询
 * @author yangzz
 */
@IocBean(name = "mapProductService")
public class MapProductService {
    
    @Inject
    protected Dao dao2;
    
    public List<MapPruductData> getQuery(MapPruductDataParm parms) throws ParseException{
        Cnd cnd=Cnd.where("1","=", 1);
       String str=parms.getCitytype();
         if(str!=null && str.equals("province")){
            cnd.and("provinceid","=",parms.getMapxzqhid());
        }
        if (parms.getStarttime()!=null && !parms.getStarttime().isEmpty() ) {
           // cnd.and("productdate",">=",parms.getStarttime());
            String s = parms.getStarttime();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date d=sdf.parse(s);
            cnd.and("ProductDate", ">=", d);
        }
          if (parms.getEndtime()!=null && !parms.getEndtime().isEmpty() ) {
           // cnd.and("productdate","<=",parms.getEndtime());
            String s = parms.getEndtime();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date d=sdf.parse(s);
            cnd.and("ProductDate", "<=", d);
        }
         
         if (str!=null && str.equals("city")) {
            cnd.and("cityid","=",parms.getMapxzqhid());
        }
         if(parms.getKeywords()!=null && !parms.getKeywords().isEmpty() ){
            cnd.and("mapname","like","%"+parms.getKeywords()+"%");
         }
        return dao2.query(MapPruductData.class, cnd);
    }
    /**
     * 单个详细信息查询
     * @param mapid
     * @return 
     */
    public MapPruductData queryMapPruductData(String mapid) {
        return dao2.fetch(MapPruductData.class, Cnd.where("mapid","=", mapid));
    }
    
}
