package org.rasterdb.dataprocess;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import org.nutz.ioc.loader.annotation.IocBean;
import org.rasterdb.service.FileIndexService;

/**
 * 处理文件命令
 *
 * @author Administrator
 */
@IocBean
public class FileCommand implements Command {

    private String MERGECMD = "";
    private String OVERVIEWCMD = "gdal_translate";
    private String INDEX = "gdaltindex";
    private String FUSION = "MapServerCmd";

    public String getMERGECMD() {
        return MERGECMD;
    }

    public void setMERGECMD(String MERGECMD) {
        this.MERGECMD = MERGECMD;
    }

    public String getOVERVIEWCMD() {
        return OVERVIEWCMD;
    }

    public void setOVERVIEWCMD(String OVERVIEWCMD) {
        this.OVERVIEWCMD = OVERVIEWCMD;
    }

    public String getINDEX() {
        return INDEX;
    }

    public void setINDEX(String INDEX) {
        this.INDEX = INDEX;
    }

    /**
     * 获取指定名称列表和大小的缩略图
     *
     * @param names 名称列表
     * @param size 缩略图大小 32*32，180*180
     * @return
     */
    public List<BufferedImage> getOverViews(List<String> names, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 获取指定名称和大小的缩略图
     *
     * @param name 文件名称
     * @param size 缩略图大小 32*32，180*180
     * @return
     */
    public BufferedImage getOverView(String name, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 创建缩略图
     *
     * @param filename 原始文件路径
     * @param distfilename 结果文件路径
     * @param parms 参数 创建缩略图的比例
     * @param size 大小
     */
    public boolean createOverView(String filename, String distfilename, String parms, int size) {
        boolean created = false;
        if (size == 32) {
            //TODO   
        } else {
            String cmd = OVERVIEWCMD +  " "+parms + " " + filename + " " + distfilename;
            System.out.println(cmd);
            created = execcmd(cmd);
        }
        return created;
    }

    /**
     * 影像文件拼接,默认生成geotiff格式的影像文件 官工程序
     *
     * @param files 文件列表
     * @param distName 生成后的文件名字
     * @return
     */
    public boolean mosaicImages(List<File> files, String distName) {
        StringBuilder builder = new StringBuilder(MERGECMD);
        builder.append(" ");
        builder.append("-o").append(" ");
        builder.append(distName).append(" ");
        for (int i = 0; i < files.size(); i++) {
            String filename = files.get(i).getPath();
            builder.append(filename).append(" ");
        }

        String cmd = builder.toString();
        //查看生成的命令
        System.out.println(cmd);
        boolean created = execcmd(cmd);
        return created;
    }

    /**
     * yzz mosaic 郭总拼接程序进行影像拼接
     */
    public boolean mosaicImages2(String disNameVRT, String disNameMosaic, String FilePath) {

        StringBuilder builder = new StringBuilder(MERGECMD);
        builder.append(" ");
        builder.append("0").append(" ");
        builder.append(disNameVRT).append(" ");
        builder.append(disNameMosaic).append(" ");
        builder.append(FilePath).append(" ");
        builder.append("2");
        String cmdString = builder.toString();
        System.out.println(cmdString);
        boolean created = execcmd(cmdString);
        return created;
    }
    
    /**
     * 镶嵌 并创建缩略图
     * @param disNameVRT
     * @param disNameMosaic
     * @param FilePath
     * @return 
     */

    public String mosaicImages3(String disNameVRT, String disNameMosaic, String FilePath) {
        
        StringBuilder builder = new StringBuilder(MERGECMD);
        builder.append(" ");
        builder.append("0").append(" ");
        builder.append(disNameVRT).append(" ");
        builder.append(disNameMosaic).append(" ");
        builder.append(FilePath).append(" ");
        builder.append("2");
        String cmdString = builder.toString();
        System.out.println(cmdString);
        boolean created = execcmd(cmdString);
        if (created) {
            return  disNameMosaic;
        }
        return "faslestring";
    }

    /**
     * 创建影像融合文件
     *
     * @param rb 全色影像
     * @param rm 多光谱影像
     * @param distName 生成的文件名称
     * @return 是否创建成功
     */
    public boolean fusionImages(String rb, String rm, String distName) {
        StringBuilder builder = new StringBuilder(FUSION);
        builder.append(" ");
        builder.append("1").append(" ");
        builder.append(rb).append(" ");
        builder.append(rm).append(" ");
        builder.append(distName).append(" ").append("2");
        String cmd = builder.toString();
        //查看生成的命令
        System.out.println(cmd);
        boolean created = execcmd(cmd);
        return created;
    }

    @Override
    public boolean execcmd(String commandStr) {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
//                 System.out.println(line);
            }

            System.out.println(sb.toString());
            int exitValue = p.waitFor();
            if (exitValue == 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
