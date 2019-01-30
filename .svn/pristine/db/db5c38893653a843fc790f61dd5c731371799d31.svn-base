package org.rasterdb.service;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.service.NameEntityService;
import org.rasterdb.domain.JsDataProductinfo;
import org.rasterdb.domain.RDataproductioninfo;
import org.rasterdb.domain.RDatasourceinfo;


/**
 * 查询当个影像信息
 * @author Administrator
 */

@At("/rasterinfo")
@IocBean(fields = {"dao"}, name = "rasterInfoService")
public class RasterInfoService extends NameEntityService{
    
    public RDatasourceinfo queryDatasourceinfo(String sno){
        return this.dao().fetch(RDatasourceinfo.class, Cnd.where("sn", "=", sno));
    }
    
    
    public RDataproductioninfo queryDataproductioninfo(String sno){
        return this.dao().fetch(RDataproductioninfo.class, Cnd.where("sn", "=", sno));
    }
    
    
    //查询单个影像的信息gml
//    public RProductinfo queryRProductinfo(String sno){
//        return  this.dao().fetch(RProductinfo.class,Cnd.where("sn", "=", sno));
//    }
//    
    /*
    yzz 查询单个影像信息   江苏
    */
    public JsDataProductinfo queryJsDataProductinfo(String sno){
        return this.dao().fetch(JsDataProductinfo.class, Cnd.where("numimage","=", sno));
    }
    
    
}
