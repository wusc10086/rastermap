/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.modules;

import java.io.File;
import java.util.Arrays;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.impl.AdaptorErrorContext;
import org.rasterdb.service.DataProcessService;
import org.rasterdb.service.FileIndexService;
import org.rasterdb.service.parma.YzzMosaicParam;
import org.rasterdb.service.wrap.MessageWrap;

/**
 *
 * @author yangzz
 */
@IocBean
@At("/yzzprocess")
public class MoasicModule {

    @Inject
    private FileIndexService fileIndexService;

    @Inject
    private DataProcessService dataProcessService;

    /**
     * 实现影像拼接服务
     *
     * @param param
     * @param errCtx
     * @return
     */
    @At("/createmosaic/**")
    @GET
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    public Object crateRasterMosaic(YzzMosaicParam param, AdaptorErrorContext errCtx) {

        if (errCtx != null) {
            MessageWrap<String> messge = new MessageWrap<String>(1, "传入的影像拼接参数初始化出错");
            return messge;
        }
        String success = dataProcessService.createImageMosaic2(fileIndexService, Arrays.asList(param.getFilelist()));
        
        if (success != null && !success.equals("faslestring")) {
            //
           
            int t=success.lastIndexOf("\\");
            String name=success.substring(t+1);
            MessageWrap<String> messge = new MessageWrap<String>(0, name);

            return messge;
        }
        MessageWrap<String> messge = new MessageWrap<String>(1, "创建拼接影像失败！");
        
        return messge;
    }
}
