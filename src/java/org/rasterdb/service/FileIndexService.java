package org.rasterdb.service;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.rasterdb.dataprocess.FileCommand;

/**
 * 影像文件索引，同时生成缩略图
 *
 * @author Administrator
 */
@IocBean
public class FileIndexService {

    @Inject
    private String distpath;

    @Inject
    private String mapdistpath;

    @Inject
    private String mosaicpath;

    @Inject
    private FileCommand fileCommand;

    public String getDistpath() {
        return distpath;
    }

    public void setDistpath(String distpath) {
        this.distpath = distpath;
    }

    public FileCommand getFileCommand() {
        return fileCommand;
    }

    public void setFileCommand(FileCommand fileCommand) {
        this.fileCommand = fileCommand;
    }

    /**
     * 获取指定的缩略图文件
     *
     * @param sn 文件名称
     * @return 缩略图图像 yzz 江苏 查询缩略图 yzz
     *
     */
    public BufferedImage getImageFile(String sn) {
        //去掉标识数据类型的字符
        //sn = sn.substring(0, sn.length());
        //  File f = new File("D:\\MZT" + File.separator + sn + "MZT.tif");
        File f = new File(distpath + File.separator + sn + "MZT500.tif");
        File mfFile2 = new File(distpath + File.separator + "nodata.png");
        BufferedImage bufferedImage = null;
        if (f.exists()) {
            try {
                bufferedImage = ImageIO.read(f);
            } catch (IOException ex) {
                Logger.getLogger(FileIndexService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                bufferedImage = ImageIO.read(mfFile2);
            } catch (IOException ex) {
                Logger.getLogger(FileIndexService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bufferedImage;
    }

    /**
     * yzz 获取 镶嵌 影像 缩略图
     *
     * @param sn
     * @return
     */
    public BufferedImage getMosaicImage(String sn) {
        int i = sn.indexOf("out");
        String folder = sn.substring(0, i);
        File f = new File(mosaicpath + File.separator + "mosaicdir" + folder + File.separator + folder + "overview.jpg");
        BufferedImage bufferedImage = null;
        if (f.exists()) {
            try {
                bufferedImage = ImageIO.read(f);
            } catch (IOException ex) {
                Logger.getLogger(FileIndexService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            bufferedImage=null;
        }
        return bufferedImage;
    }

    /**
     * yzz 获取 制图产品 缩略图
     *
     * @param mn
     * @return
     */
    public BufferedImage getMapImageFile(String mn) {
        File mfFile = new File(mapdistpath + File.separator + mn + "map.tif");
        BufferedImage mapimage = null;
        if (mfFile.exists()) {
            try {
                mapimage = ImageIO.read(mfFile);
            } catch (IOException ex) {
                Logger.getLogger(FileIndexService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            File mfFile2 = new File(mapdistpath + File.separator + "nodata.png");
            try {
                mapimage = ImageIO.read(mfFile2);
            } catch (IOException ex) {
                Logger.getLogger(FileIndexService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mapimage;
    }

    /**
     * 创建缩略图文件
     *
     * @param basePath 基础地址
     * @param params 参数
     */
    public boolean createRasterOverview(String basePath, String params) {
        boolean created = false;
        List<File> fileList = this.indexImageFiles(basePath);
        if (fileList != null) {
            for (int i = 0; i < fileList.size(); i++) {
                File f = fileList.get(i);
                String sourceFile = f.getPath();
                //生成的缩略图不包含数据类型标识符
                String distfileName = f.getName().substring(0, f.getName().indexOf(".")) + "MZT500.tif";
                String distFile = distpath + File.separator + distfileName;
                created = fileCommand.createOverView(sourceFile, distFile, params, 128);
            }
        }
        return created;
    }

    /**
     * 索引所有图像
     *
     * @param basePath
     * @return
     */
    public List<File> indexImageFiles(String basePath) {
        List<File> images = new ArrayList<File>();
        File file = new File(basePath);

        if (file.exists()) {
            System.out.println("路径正确！" + basePath);
            images = ergodic(file, images);
        } else {
            System.out.println("路径错误！" + basePath);
        }
        return images;
    }

    /**
     *  获取所有影像文件
     *
     * @param file 目录文件
     * @param resultFileName 结果列表
     * @return
     */
    private List<File> ergodic(File file, List<File> resultFileName) {
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return true;
                } else {
                    String name = pathname.getName();
                    name = name.toLowerCase();
                    if (name.endsWith(".img") || name.endsWith(".tif")
                            || name.endsWith(".tiff") || name.endsWith(".geotiff")) {
                        return true;
                    }
                }
                return false;
            }
        });
        if (files == null) {
            return resultFileName;// 判断目录下是不是空的
        }
        for (File f : files) {
            if (f.isDirectory()) {// 判断是否文件夹
                ergodic(f, resultFileName);// 递归调用自身,查找子目录
            } else {
                System.out.println("找到文件：" + f.getPath());
                resultFileName.add(f);
            }
        }
        return resultFileName;
    }

    /**
     * 复制拼接图像至临时文件夹，
     *  若 被复制的文件不存在 删除临时目录 返回 false
     * @return
     */
    public boolean copyFile(String filepdir, List<String> namesList, String tempdir) {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        
        for (int i = 0; i < namesList.size(); i++) {
            File inFile = new File(filepdir + File.separator + namesList.get(i).toString() + ".tif");
            File outFile = new File(tempdir + File.separator + namesList.get(i).toString() + ".tif");
         
            //如果复制的文件不存在，删除临时目录
            if (!inFile.exists()) {
                File dir = new File(tempdir);
                dir.delete();
                return false;
            }
            try {
                inBuff = new BufferedInputStream(new FileInputStream(inFile));
                outBuff = new BufferedOutputStream(new FileOutputStream(outFile));
                byte[] b = new byte[1024 * 10];
                int len;
                while ((len = inBuff.read(b)) != -1) {
                    outBuff.write(b, 0, len);
                }
                outBuff.flush();
                inBuff.close();
                outBuff.close();

            } catch (Exception e) {
                return false;
            } finally {

            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        List<String> nameList = new ArrayList<String>();
//        nameList.add("I50D003010");
//        nameList.add("I50D003011");
//        new FileIndexService().copyFile("D:\\moasic", nameList, "D:\\moasic\\temp1474358320090");
//    }
}
