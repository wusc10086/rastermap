package org.rasterdb.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.rasterdb.domain.MosaicTable;

/**
 * 数据拼接处理服务
 *
 * @IocBean( name = "rasterExtentService")
 * @author Administrator
 */
@IocBean(name = "dataProcessService")
public class DataProcessService {

    @Inject
    private String mosaicpath;

    @Inject
    protected Dao dao;

    /**
     * 创建拼接影像，不考虑影像顺序
     *
     * @param fileIndexService 文件索引服务
     * @param basePath 存放影像的基址
     * @param distname 生成的文件名称
     */
    @Logger
    public void createImageMosaic(FileIndexService fileIndexService, String basePath, String distname) {

        //不考虑顺序的情况下构建文件列表
        List<File> fileList = fileIndexService.indexImageFiles(basePath);

        if (distname == null) {
            distname = mosaicpath + System.currentTimeMillis() + ".tif";
        } else {
            distname = mosaicpath + distname + ".tif";
        }

        fileIndexService.getFileCommand().mosaicImages(fileList, distname);
    }

    /**
     * 根据传入的文件列表名称和先后顺序构建拼接
     *
     * @param fileIndexService 文件索引服务
     * @param basePath 存放影像的基址
     * @param distname 生成的文件名称
     * @param imageNames 影像文件名称列表
     */
    public boolean createImageMosaic(FileIndexService fileIndexService, String basePath, String distname, List<String> imageNames) {

        //考虑顺序的情况下构建文件列表
        List<File> fileList = new ArrayList<File>();
        if (imageNames.size() > 0) {
            for (int i = 0; i < imageNames.size(); i++) {
                String imgename = imageNames.get(i);
                fileList.add(new File(basePath + File.separator + imgename));
            }
        }

        if (distname == null) {
            distname = mosaicpath + File.separator + System.currentTimeMillis() + "";
        } else {
            distname = mosaicpath + File.separator + distname + "";
        }

        //return fileIndexService.getFileCommand().mosaicImages(fileList, distname);
        String VrtString = distname + ".vrt";
        String distNameString = distname + ".tif";
        return fileIndexService.getFileCommand().mosaicImages2(VrtString, distNameString, basePath);
    }

    /**
     * 创建融合影像
     *
     * @param fileIndexService
     * @param distname 生成文件名称
     * @param rp 全色影像
     * @param rm 多光谱影像
     * @return
     */
    public boolean createImageFusion(FileIndexService fileIndexService, String distname, String rp, String rm) {

        return fileIndexService.getFileCommand().fusionImages(rp, rm, distname);

    }

    /**
     * 在线镶嵌 yzz
     *
     * @param fileIndexService
     * @param imageNames
     * @return
     */
    public String createImageMosaic2(FileIndexService fileIndexService, List<String> imageNames) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < imageNames.size(); i++) {
            String name = imageNames.get(i);
            sb.append(name);
        }
        String tempdir = sb.toString();
        String basePath = mosaicpath + File.separator + "mosaicdir" + tempdir+File.separator;
        File dir = new File(basePath);
        dir.mkdir();
        boolean copyfile = fileIndexService.copyFile(mosaicpath, imageNames, basePath);
        if (copyfile) {
            String VrtString = basePath + "out.vrt";
            String distNameString = basePath + tempdir + "out.tif";
            String success = fileIndexService.getFileCommand().mosaicImages3(VrtString, distNameString, basePath);

            MosaicTable mosaicTable = new MosaicTable();
            if (success != null && !success.equals("faslestring")) {
                mosaicTable.setInputrasters(tempdir);
                mosaicTable.setSuccess(1);
                mosaicTable.setUserid(1);
                mosaicTable.setOutrasterurl(VrtString);
                mosaicTable.setOutrastername(distNameString);
                mosaicTable.setOutrasterid(String.valueOf(System.currentTimeMillis()));
                dao.insert(mosaicTable);
                String overview = basePath + tempdir + "overview.jpg";
                boolean s = fileIndexService.getFileCommand().createOverView(distNameString, overview, "50% 50%", 128);
                
                return distNameString;
            } else {
                mosaicTable.setInputrasters(tempdir);
                mosaicTable.setSuccess(0);
                mosaicTable.setUserid(1);
                mosaicTable.setOutrasterurl(VrtString);
                mosaicTable.setOutrastername(distNameString + tempdir + "out.tif");
                mosaicTable.setOutrasterid(String.valueOf(System.currentTimeMillis()));
                dao.insert(mosaicTable);
            }
            mosaicTable = null;
        }
        return "faslestring";
    }
}
