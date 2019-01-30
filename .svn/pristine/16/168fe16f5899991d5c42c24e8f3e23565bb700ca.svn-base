package org.rasterdb.modules;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;

import org.rasterdb.domain.JsDataProductinfo;
import org.rasterdb.domain.RDataproductioninfo;
import org.rasterdb.domain.RDatasourceinfo;

import org.rasterdb.service.RasterInfoService;

/**
 *
 * @author Administrator
 */
@At("/datainfo")
@Ok("json")
@IocBean
public class RasterInfoModule {

    @Inject
    private RasterInfoService rasterInfoService;

    @At("/productinfo/sno/?")
    @GET
    /*
     gml 之前查询
     public RProductinfo getProductinfo(String sno){
         return rasterInfoService.queryRProductinfo(sno);
     }
     */
    public JsDataProductinfo getDataProductinfo(String sno) {
        return rasterInfoService.queryJsDataProductinfo(sno);
    }

//     @At("/detail/?")
//     @GET
//     @POST
//     public RProductinfo getDetailInfo(String sno){
//         return rasterInfoService.queryRProductinfo(sno);
//     }
//     
    @At("/dataproduct/sno/?")
    @GET
    public RDataproductioninfo getProductioninfo(String sno) {
        return rasterInfoService.queryDataproductioninfo(sno);
    }

    @At("/datasourceinfo/sno/?")
    @GET
    public RDatasourceinfo getDatasourceinfo(String sno) {
        return rasterInfoService.queryDatasourceinfo(sno);
    }

}
