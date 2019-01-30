/* yzz
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service;

import java.io.File;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.rasterdb.domain.Order;

/**
 *
 * @author yangzz
 */
@IocBean
public class FileDownLoadService {

    @Inject
    private String filePath;
    @Inject
    private String imagePath;
    
    /**制图
     * 文件下载
     *
     * @param name 文件名
     * @return
     */
    public File getDownFile(String name) {
        File dFile = new File(filePath + File.separator + name + ".zip");
        String str=filePath + File.separator + "nodata.txt";
        if (dFile.exists()) {
            return dFile;
        } else {
            return new File(str);
        }
    }
    
    public File getImageDownFile(String name) {
        File dFile = new File(imagePath + File.separator + name + ".zip");
        String str=imagePath + File.separator + "nodata.txt";
        if (dFile.exists()) {
            return dFile;
        } else {
            return new File(str);
        }
    }
   
    
     public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
