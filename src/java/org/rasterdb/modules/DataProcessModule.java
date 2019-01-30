package org.rasterdb.modules;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.impl.AdaptorErrorContext;
import org.rasterdb.dataprocess.FileCommand;
import org.rasterdb.service.DataProcessService;
import org.rasterdb.service.FileIndexService;
import org.rasterdb.service.parma.MosaicParam;
import org.rasterdb.service.wrap.MessageWrap;
import org.rasterdb.service.wrap.ResultWrap;

/**
 * 数据处理模块
 *
 * @author Administrator
 */
@IocBean
@At("/dataprocess")
public class DataProcessModule {

    @Inject
    private FileIndexService fileIndexService;

    @Inject
    private DataProcessService dataProcessService;

    @At("/createoverview")
    public boolean createOverveiews(@Param("basePath") String basePath, @Param("quality") String quality) {
        //String parms = "-outsize 5% 5%";
         String parms = "-outsize "+quality+"% "+quality+"%";

        //此处判断代码暂时无用
        if (quality != null) {
            int q = Integer.parseInt(quality);
            System.out.println(q);
           //parms = String.format(parms, q, q);
        }
        System.out.println(basePath);
        boolean created = fileIndexService.createRasterOverview(basePath, parms);  
        return created;

    }

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
    public Object crateRasterMosaic(MosaicParam param, AdaptorErrorContext errCtx) {

        if (errCtx != null) {
            MessageWrap<String> messge = new MessageWrap<String>(1, "传入的影像拼接参数初始化出错");
            return messge;
        }

        boolean success = dataProcessService.createImageMosaic(fileIndexService, param.getBasepath(), param.getDistname(), Arrays.asList(param.getFilelist()));

        if (success) {
            MessageWrap<String> messge = new MessageWrap<String>(0, "创建拼接影像成功！");
            return messge;
        }

        MessageWrap<String> messge = new MessageWrap<String>(1, "创建拼接影像失败！");
        return messge;
    }

    /**
     * 加载指定路径洗的所有影像文件
     *
     * @param param 拼接参数
     * @param errCtx
     * @return 影像文件名称列表
     */
    @At("/loadimages/**")
    @GET
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    public Object loadImages(MosaicParam param, AdaptorErrorContext errCtx) {
        if (errCtx != null || param.getBasepath().isEmpty()) {
            MessageWrap<String> messge = new MessageWrap<String>(1, "传入的影像基址参数初始化出错");
            return messge;
        }

        //获取所有影像文件
        List<File> files = fileIndexService.indexImageFiles(param.getBasepath());

        List<String> filenames = new ArrayList<String>();

        for (int i = 0; i < files.size(); i++) {
            File image = files.get(i);
            filenames.add(image.getName());
        }

        if (filenames.size() > 0) {
            MessageWrap<List<String>> messge = new ResultWrap<List<String>>(filenames, 0, "成功加载影像文件！");
            return messge;
        }

        MessageWrap<List<String>> messge = new ResultWrap<List<String>>(filenames, 1, "加载影像文件数为0！");
        return messge;

    }

    /**
     * 创建融合影像
     *
     * @param param
     * @param errCtx
     * @return
     */
    @At("/fusionimage/**")
    @GET
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    public Object fusionImage(MosaicParam param, AdaptorErrorContext errCtx) {
        if (errCtx != null || param.getBasepath().isEmpty()) {
            MessageWrap<String> messge = new MessageWrap<String>(1, "传入的融合影像参数初始化出错");
            return messge;
        }

        String basepath = param.getBasepath();
        String rp = basepath + File.separator + param.getRp();
        String rm = basepath + File.separator + param.getRm();
        String distname = basepath + File.separator +param.getDistname()+"_fusion.tif";
        boolean success = fileIndexService.getFileCommand().fusionImages(rp, rm, distname);
        if (success) {
            MessageWrap<String> messge = new MessageWrap<String>(0, "融合影像创建成功！");
            return messge;
        }

        MessageWrap<String> messge = new MessageWrap<String>(1, "融合影像创建失败！");
        return messge;
    }

}
