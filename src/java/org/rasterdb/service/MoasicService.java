///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.rasterdb.service;
//
//import java.io.File;
//import java.util.List;
//import org.nutz.ioc.loader.annotation.Inject;
//import org.nutz.ioc.loader.annotation.IocBean;
//
///**
// *
// * @author yangzz
// */
//@IocBean
//public class MoasicService {
//
//    @Inject
//    private String filePath;
//
//    @Inject
//    private DataProcessService dataProcessService;
//
//    public boolean ImageMosaic(FileIndexService fileIndexService, List<String> imageNames) {
//
//        String VrtString = filePath;
//        String distNameString = filePath;
//        String basePath = filePath + File.separator + "temp" + System.currentTimeMillis() + File.separator;
//        File dir = new File(basePath);
//        if (dir.exists()) {
//            return false;
//        }
//        dir.mkdir();
//        boolean copyfile = fileIndexService.copyFile(filePath, imageNames, basePath);
//        if (copyfile) {
//            boolean success = fileIndexService.getFileCommand().mosaicImages3(VrtString, distNameString, basePath);;
//            if (success) {
//
//                return success;
//            }
//        }
//        return false;
//    }
//
//    public static void main(String[] args) {
//
//        new MoasicService().ImageMosaic(fileIndexService, imageNames);
//    }
//}
