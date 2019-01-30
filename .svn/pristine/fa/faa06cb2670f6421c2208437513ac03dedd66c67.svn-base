package org.rasterdb.modules;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;
import org.rasterdb.service.VectorQueryService;
import org.rasterdb.utils.KMLFileUtil;
import org.rasterdb.utils.ShpFileUtil;
import org.rasterdb.utils.ZipUtil;

/**
 * 文件上传解析查询
 *
 * @author Administrator
 */
@At("/upload")
@IocBean
public class FileUploadModule {

    @Inject
    private ShpFileUtil shpFileUtil;
    @Inject
    private KMLFileUtil kmlFileUtil;

    @Inject
    private VectorQueryService vectorQueryService;

    /**已经废弃 
     * 实现矢量查询和前台查询结果绘制，不足还不能绘制查询文件
     * gml
     * @param id
     * @param tf
     * @return 
     */
    //@At("/uploadfile")
    @Ok("JSON")
    @AdaptBy(type = UploadAdaptor.class, args = {"${app.root}/tmp"})
    public List<List<Map>> uploadFile(@Param("id") int id, @Param("shpfile") TempFile tf) {
        File f = tf.getFile();
        String fileName = tf.getSubmittedFileName().toLowerCase();
        List<List<Map>> resList = null;
        if (fileName.indexOf(".kml")> -1) {
            try {
                List<String> polygons = kmlFileUtil.parserKMLFile(f);
               // resList = vectorQueryService.queryRasterInfo(polygons);

            } catch (IOException ex) {
                Logger.getLogger(FileUploadModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (fileName.indexOf(".zip")> -1) {
            ZipUtil.unzip(f.getAbsolutePath());
            String shpfilePath = f.getParent() + File.separator + tf.getSubmittedFileName().replace(".zip", ".shp");
            File shpfile = new File(shpfilePath);
            boolean b=shpfile.exists();
            if(!b){
                return  resList;
            }
            try {
                List<String> polygons = shpFileUtil.parserShpFile(shpfile);
             //  resList = vectorQueryService.queryRasterInfo(polygons);
            } catch (IOException ex) {
                Logger.getLogger(FileUploadModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resList;
    }
    
    /**
     * yzz 更新修改 返回查询数据+上传shp
     * @param id
     * @param tf
     * @return 
     */
    @At("/uploadfile")
    @Ok("JSON")
    @AdaptBy(type = UploadAdaptor.class, args = {"${app.root}/yzztmp"})
    public List<Object> yzzuploadFile(@Param("id") int id, @Param("shpfile") TempFile tf) {
        File f = tf.getFile();
        String fileName = tf.getSubmittedFileName().toLowerCase();
        List<Object> resList = null;
        if (fileName.indexOf(".kml")> -1) {
            try {
                List<String> polygons = kmlFileUtil.parserKMLFile(f);
                resList = vectorQueryService.queryRasterInfo(polygons);

            } catch (IOException ex) {
                Logger.getLogger(FileUploadModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (fileName.indexOf(".zip")> -1) {
            ZipUtil.unzip(f.getAbsolutePath());
            String shpfilePath = f.getParent() + File.separator + tf.getSubmittedFileName().replace(".zip", ".shp");
            File shpfile = new File(shpfilePath);
            boolean b=shpfile.exists();
            if(!b){
                return  resList;
            }
            try {
                List<String> polygons = shpFileUtil.parserShpFile(shpfile);
                resList = vectorQueryService.queryRasterInfo(polygons);
            } catch (IOException ex) {
                Logger.getLogger(FileUploadModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resList;
    }
    
    /**
     * 已废弃，此方法能返回用户上传shp文件，并在前台解析绘制
     * @param id
     * @param tf
     * @return 
     */ 
    @At("/uploadfile2")
    @Ok("Json")
    @AdaptBy(type = UploadAdaptor.class, args = {"${app/root}/tmp2"})
    public List<String> YZZOuploadFileList(@Param("id") int id, @Param("shpfile") TempFile tf) {
        File f = tf.getFile();
        List<String> polygons = null;
        String fileName = tf.getSubmittedFileName().toLowerCase();
        if (fileName.indexOf(".kml") > -1) {
            try {
                polygons = kmlFileUtil.parserKMLFile(f);
            } catch (IOException ex) {
                Logger.getLogger(FileUploadModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (fileName.indexOf(".zip") > -1) {
            ZipUtil.unzip(f.getAbsolutePath());
            String shpfilePath = f.getParent() + File.separator + tf.getSubmittedFileName().replace(".zip", ".shp");
            File shpfile = new File(shpfilePath);
            boolean b = shpfile.exists();
            if (b) {
                try {
                    polygons = shpFileUtil.parserShpFile(shpfile);
                } catch (IOException ex) {
                    Logger.getLogger(FileUploadModule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return polygons;
    }
}
    
       

