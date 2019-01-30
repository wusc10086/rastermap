package org.rasterdb.modules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.impl.AdaptorErrorContext;
import org.rasterdb.domain.Cgq;
import org.rasterdb.domain.JsDataProductinfo;
import org.rasterdb.domain.Order;
import org.rasterdb.domain.RProductinfo;
import org.rasterdb.domain.SatelliteView;
import org.rasterdb.service.CgqService;
import org.rasterdb.service.FileIndexService;
import org.rasterdb.service.QueryService;
import org.rasterdb.service.RasterExtentService;
import org.rasterdb.service.SatelliteService;
import org.rasterdb.service.parma.DataSourceParm;
import org.rasterdb.service.parma.JSproductionParm;
import org.rasterdb.service.parma.ProductParma;
import org.rasterdb.service.parma.RasterParm;
import org.rasterdb.service.wrap.ResultWrap;

/**
 * 数据查询模块
 *
 * @author Administrator
 */
@At("/query")
@Ok("json")
@IocBean
@Filters(
        @By(type = CheckSession.class, args = {"user", "/login.jsp"}))
public class QueryModule {

    @Inject
    private CgqService cgqService;

    @Inject
    private SatelliteService satelliteService;

    @Inject
    private QueryService queryService;

    @Inject
    private RasterExtentService rasterExtentService;

    @Inject
    private FileIndexService fileIndexService;

    @Inject
    private Dao dao2;

    @At("/cgq")
    public List<Cgq> queryCgqs() {
        return cgqService.getCgqList();
    }

//    @At("/satellite")
//    public List<Satellite> querySatellites() {
//        return satelliteService.getSatelliteList();
//    }
    @At("/satelliteview")
    public List<SatelliteView> querySatellitesView() {
        return satelliteService.getSatelliteViewList();
    }

    @At("/productinfo")//NGCC 版本的
    public List<RProductinfo> queryProductinfos() {
        return queryService.getProductInfos();
    }

    @At("//datasourceinfo/?")
    @Ok("json")
    @POST //江苏   条件查询  yzz
    @GET
    @AdaptBy(type = JsonAdaptor.class)
    public ResultWrap<List<JsDataProductinfo>> queryJsdata(JSproductionParm parm, AdaptorErrorContext errCtx) throws ParseException {
        if (errCtx != null) {
            return new ResultWrap<List<JsDataProductinfo>>(1, "请求产品数据参数有误！");
        }

        List<JsDataProductinfo> infos = queryService.getJsProduction(parm);

        String tb = "pro_dist_area";
        String xzqhid = parm.getXzqhid();
        if (parm.getCitytype().equals("province")) {
            tb = "prov_dist_area";
        } else if (parm.getCitytype().equals("city")) {
            tb = "city_dist_area";
        } else {
            tb = "town_dist_area";
        }
        Map extm = rasterExtentService.intersectQuery(xzqhid, tb);
        List<JsDataProductinfo> newInfos = new ArrayList<JsDataProductinfo>();
        if (infos.size() > 0) {
            for (int i = 0; i < infos.size(); i++) {
                JsDataProductinfo sourceMap = infos.get(i);
                String sn = sourceMap.getNumImage();
                String o = (String) extm.get(sn);
                if (o != null) {
                    //设置空间范围
                    infos.get(i).setExtent(o);
                    newInfos.add(infos.get(i));
                }
            }
        }
        if (newInfos != null) {
            return new ResultWrap<List<JsDataProductinfo>>(newInfos, 0, "");
        }
        return new ResultWrap<List<JsDataProductinfo>>(1, "请求数据发生内部错误！");
    }

    @At("/pbandtime")
    public String queryPbandLastTime() {
        String date = queryService.getLastPbandDataTime();
        String newdate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
        return newdate;
    }

    @At("/rgbtime")
    public String queryRgbLastTime() {
        String date = queryService.getLastRGBDataTime();
        String newdate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
        return newdate;
    }

    /**
     * 最新数据日期
     *
     * @return
     */
    @At("/lasttime")
    public Map<String, String> getLastDataTime() {
        String pband = queryPbandLastTime();
        String rgb = queryRgbLastTime();
        Map m = new HashMap();
        m.put("pband", pband);
        m.put("rgb", rgb);
        return m;
    }

    @At("/complex")
    public void queryRasterFiles(RasterParm rp) {

    }

    /**
     * 查询产品信息
     *
     * @param pa
     * @param errCtx
     * @return
     */
    @At("/productinfo/?")
    @GET
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    public ResultWrap<List<RProductinfo>> queryProductInfos(ProductParma pa, AdaptorErrorContext errCtx) {
        if (errCtx != null) {
            return new ResultWrap<List<RProductinfo>>(1, "请求产品数据参数有误！");
        }
        List<RProductinfo> infos = queryService.getProductInfos(pa);

        String tb = "pro_dist_area";
        String xzqhid = pa.getXzqhid();
        if (pa.getCitytype().equals("province")) {
            tb = "prov_dist_area";
        } else if (pa.getCitytype().equals("city")) {
            tb = "city_dist_area";
        } else {
            tb = "town_dist_area";
        }

        Map extm = rasterExtentService.intersectQuery(xzqhid, tb);
        List<RProductinfo> newInfos = new ArrayList<RProductinfo>();
        if (infos.size() > 0) {
            for (int i = 0; i < infos.size(); i++) {
                RProductinfo sourceMap = infos.get(i);
                String sn = sourceMap.getSn();
                String o = (String) extm.get(sn);
                if (o != null) {
                    //设置空间范围
                    infos.get(i).setExtent(o);
                    newInfos.add(infos.get(i));
                }
            }
        }
        if (newInfos != null) {
            return new ResultWrap<List<RProductinfo>>(newInfos, 0, "");
        }
        return new ResultWrap<List<RProductinfo>>(1, "请求数据发生内部错误！");
    }

    /**
     * 查询源数据信息 NDCC版本
     *
     * @param pa
     * @param errCtx
     * @return
     */
    // @At("/datasourceinfo/?")
    @GET
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    public ResultWrap<List<Map>> queryDataSourceInfos(DataSourceParm pa, AdaptorErrorContext errCtx) {
        if (errCtx != null) {
            return new ResultWrap<List<Map>>(1, "请求数据源数据参数有误！");
        }

        String tb = "pro_dist_area";
        String xzqhid = pa.getXzqhid();
        if (pa.getCitytype().equals("province")) {
            tb = "prov_dist_area";
        } else if (pa.getCitytype().equals("city")) {
            tb = "city_dist_area";
        } else {
            tb = "town_dist_area";
        }

        Map extm = rasterExtentService.intersectQuery(xzqhid, tb);

        List<Map> infos = queryService.getDataSourceList(pa);
        List<Map> newInfos = new ArrayList<Map>();

        if (infos.size() > 0) {
            for (int i = 0; i < infos.size(); i++) {
                Map sourceMap = infos.get(i);
                String sn = (String) sourceMap.get("sn");
                Object o = extm.get(sn);
                if (o != null) {

                    //设置空间范围
                    infos.get(i).put("extent", o);
                    newInfos.add(infos.get(i));
                }
            }
        }

        if (newInfos != null) {
            return new ResultWrap<List<Map>>(newInfos, 0, "");
        }
        return new ResultWrap<List<Map>>(1, "请求数据发生内部错误！");
    }

    /**
     * 获取对应的影像缩略图
     *
     * @param sn 影像名称
     * @return 影像缩略图文件
     */
    @Ok("raw:jpg")
    @At("/image/?")
    @GET
    @POST
    public Object queryImageFile(String sn) {
        return fileIndexService.getImageFile(sn);
    }
    
    
    
     /*
       获取 镶嵌影像的 缩略图
    */
    @Ok("raw:jpg")
    @At("/mosaicimage/?")
    @GET
    @POST
    public Object queryMosaicImage(String sn) {
       return fileIndexService.getMosaicImage(sn);
    }
    
     /**
     * 提交订购
     * @return 
     */
    @At("/cellecttion/?")
    public String saveFileinCollection(String userandimage){
       return queryService.saveInColletion(userandimage);
    }
    /**
     * 删除订购
     * @param userandimage
     * @return 
     */
    @At("/removeOrder/?")
    public String backFileCollection(String userandimage){
        return queryService.removeFromColletion(userandimage);
    }
    
    @At("/order/?")
    public List<Order> queryOrder(String username){
         return queryService.queryOrderservice(username);
    } 
    @At("/adminorder")
    public List<Order> adminQueryOrder(){
        return  queryService.adminQueryService();
    }
    @At("/adUpdateUIStatus/?")
    public int adUpdateOrderStatus(String parameters){
    return queryService.adUpdateOrderStatusService(parameters);
    }
    @At("/dldupdatesta/?")
    public int userUpdateOrderStatus(String parameters){
       return queryService.userUpdateOrderStatusService(parameters);
    }
}
