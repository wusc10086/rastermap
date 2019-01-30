/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.modules;

import java.io.File;
import java.text.ParseException;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.rasterdb.domain.MapProvince;
import java.util.List;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.impl.AdaptorErrorContext;
import org.rasterdb.domain.CityDistArea;
import org.rasterdb.domain.MapCity;
import org.rasterdb.domain.MapPruductData;
import org.rasterdb.domain.ProvDistArea;
import org.rasterdb.service.FileDownLoadService;

import org.rasterdb.service.FileIndexService;
import org.rasterdb.service.MapProductService;
import org.rasterdb.service.MapProvinceService;
import org.rasterdb.service.parma.MapPruductDataParm;
import org.rasterdb.service.wrap.XzqhResultWrap;

/**
 * 制图产品查询
 *
 * @author yangzz
 */
@IocBean
@At("/map")
public class MapQuery {

    @Inject
    public MapProvinceService mapProvinceService;
    
    @Inject
    public MapProductService mapProductService;

    @Inject
    public FileIndexService fileIndexService;
    
    @Inject
    public FileDownLoadService fileDownLoadService;

    @At("/province")
    public List<MapProvince> queryProvince() {
        return mapProvinceService.getAllProvince();
    }

    @At("/province/?")
    @GET
    public XzqhResultWrap<List<MapCity>, List<ProvDistArea>> queryProvince(String provinceid) {
        return mapProvinceService.getCitiesById(provinceid);
    }

    @At("/city/?")
    @GET
    public List<CityDistArea> queryCity(String cityid) {
        return mapProvinceService.getCitiesPolggon(cityid);
    }

    @At("/product/?")
    @POST
    @GET
    @Ok("json")
    public List<MapPruductData> MapQueryPruductData(MapPruductDataParm parms, AdaptorErrorContext errCtx) throws ParseException {
        if (errCtx != null) {
            // return new List<MapPruductData>(1, "请求产品数据参数有误！");
            return null;
        }
        List<MapPruductData> pd = mapProductService.getQuery(parms);
        if (pd != null) {
            return pd;
        }
        return null;
    }

    /**
     * 获取制图产品缩略图
     *
     * @param mn 影像名称
     * @return 影像缩略图文件
     */
    @Ok("raw:jpg")
    @At("/mapimage/?")
    @GET
    @POST
    public Object queryImageFile(String mn) {
        return fileIndexService.getMapImageFile(mn);
    }
    
    
    /**
     * 制图文件下载
     * 文件下载
     * @param name 下载文件
     * @return 
     */
    @Ok("raw")
    @At("/download/?")
    @GET
    @POST
    public File filedownload(@Param("name") String name) {
        return fileDownLoadService.getDownFile(name);
    }
    /**
     * 影像文件下载
     * @param name
     * @return 
     */
    @Ok("raw")
    @At("/imagedownload/?")
    @GET
    @POST
    public File imageFileDld(@Param("name") String name){
        return fileDownLoadService.getImageDownFile(name);
    }
}
