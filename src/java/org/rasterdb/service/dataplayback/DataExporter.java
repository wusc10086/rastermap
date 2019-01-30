/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service.dataplayback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.NumberFormatter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.LogManager;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.rasterdb.service.device.DeviceManagerService;
import org.rasterdb.utils.ProjectInfo;

/**
 *
 * @author wuyiwei
 */
public class DataExporter {
    static org.apache.log4j.Logger LOG = LogManager.getLogger(DataExporter.class);
    
    private static final String FILE_SURFIX = ".xlsx";
    
    protected  ProjectInfo projectInfo;
    
    public DataExporter(ProjectInfo projectInfo){
        this.projectInfo = projectInfo;
    }
    
    /**
     * 获得数据导出临时文件存放目录
     * @return 
     */
    public File getTmpDir() {
        String dirStr = projectInfo == null ? null : projectInfo.getExportTmpDir();
        
        if (StringUtils.isBlank(dirStr)) {
            File tmpDir = SystemUtils.getJavaIoTmpDir();
            LOG.info("数据导出：JavaIoTmpDir = " + tmpDir);
            return tmpDir;
        }
        
        File tmpDir = new File(dirStr);
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
            return tmpDir;
        } else if (tmpDir.isFile()){
            LOG.error("您配置的数据导出临时文件存放目录不正确，它是一个文件而不是一个目录，请检查配置是否正确：dirStr = " + dirStr);
            return  null;
        } else {
            return tmpDir;
        }
    }
    
    File tmpExcelFile = null;
    FileOutputStream out = null;
    private Workbook wb = null;
    private Sheet sheet = null;
    private int rowIndex = 1;
    
    /**
     * 输出一行数据到excel
     * @param tmpDir
     * @param efData 
     */
    public void export(File tmpDir, EFData efData){
        if (wb == null) {
            clearTmpDir(tmpDir);
            String prefix = "电场强度";
            try {
                prefix = new String(prefix.getBytes("UTF-8"),"ISO-8859-1");
            } catch (UnsupportedEncodingException ex) {
                 LOG.error(ex);
            }
            createNewExcel(tmpDir, prefix);
        }
        
        Row row = sheet.createRow(rowIndex++);
        String[] datas = efData.toDatas();
        for (int i = 0; i < datas.length; i++) {
            Cell c = row.createCell(i);
            c.setCellValue(datas[i]);
        }
    }
      public void exportwarning(File tmpDir, EFDataWarn warning){
        if (wb == null) {
            clearTmpDir(tmpDir);
            String prefix = "预警信息" ;
            try {
                prefix = new String(prefix.getBytes("UTF-8"),"ISO-8859-1");
            } catch (UnsupportedEncodingException ex) {
                LOG.error(ex);
            }
            createNewExcelWarning(tmpDir, prefix);
        }
        
        Row row = sheet.createRow(rowIndex++);
        String[] datas = warning.toDatas();
        for (int i = 0; i < datas.length; i++) {
            Cell c = row.createCell(i);
            c.setCellValue(datas[i]);
        }
    }
    
    /**
     * 获得Excel文件
     * @return 
     */
    public File getTmpExcelFile() {
        return tmpExcelFile;
    }
    
    private void createNewExcel(File tmpDir, String prefix) {
        tmpExcelFile = getTmpEmptyExcelDataFile(tmpDir, prefix);
        try {
            out = new FileOutputStream(tmpExcelFile);
            //wb = new HSSFWorkbook();
            //wb = new XSSFWorkbook();
            wb = new SXSSFWorkbook(200000);
            sheet = wb.createSheet();

            String sheetName = prefix;
            wb.setSheetName(0, sheetName);
            createColumnHeaders();
        } catch (Exception ex) {
            LOG.error("创建excel文件时出错。file=" + tmpExcelFile, ex);
        }
    }
    
    private void createColumnHeaders(){
        Row row = sheet.createRow(0);
        String[] titles = EFData.getColTitles();
        for (int i = 0; i < titles.length; i++) {
            Cell c = row.createCell(i);
            c.setCellValue(titles[i]);
        }
    }
   private void createNewExcelWarning(File tmpDir, String prefix) {
        tmpExcelFile = getTmpEmptyExcelDataFile(tmpDir, prefix);
        try {
            out = new FileOutputStream(tmpExcelFile);
            //wb = new HSSFWorkbook();
            //wb = new XSSFWorkbook();
            wb = new SXSSFWorkbook(200000);
            sheet = wb.createSheet();

            String sheetName = prefix;
            wb.setSheetName(0, sheetName);
            createColumnHeadersWarning();
        } catch (Exception ex) {
            LOG.error("创建excel文件时出错。file=" + tmpExcelFile, ex);
        }
    }
    
    private void createColumnHeadersWarning(){
        Row row = sheet.createRow(0);
        String[] titles = EFDataWarn.getColTitles();
        for (int i = 0; i < titles.length; i++) {
            Cell c = row.createCell(i);
            c.setCellValue(titles[i]);
        }
    } 
    
    private void clearTmpDir(File tmpDir) {
        File[] allFiles = tmpDir.listFiles();
        if (allFiles == null || allFiles.length == 0) {
            return;
        }
        
        long now = System.currentTimeMillis();
        for (File f : allFiles) {
            if (f.isDirectory()) {
                continue;
            }
            if (!f.getName().endsWith((FILE_SURFIX))) {
                continue;
            }
            if (now - f.lastModified()  > 2 * 3600 * 1000) {
                f.delete();
            }
        }
    }
    
    /**
     * 输出excel文件数据后，最后关闭文件。
     */
    public void close() {
        if (wb != null) {
            try {
                wb.write(out);
                out.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 生成临时文件
     * @param tmpDir
     * @return 
     */
    private File getTmpEmptyExcelDataFile(File tmpDir, String prefix) {
        String tmpExcelFileName = getTmpExcelFileName(prefix);
        File tmpExcelFile = new File(tmpDir, tmpExcelFileName);
        return tmpExcelFile;
    }
   
    
    /**
     * 生成临时文件名
     * @return 
     */
    private String getTmpExcelFileName (String prefix) {
        String nowStr = prefix + "_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
       // nowStr += "_" + RandomStringUtils.randomNumeric(4);
        return nowStr + FILE_SURFIX;
    }
    
    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }
    
    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }
    
    /**
     * 电场强度
     */
    public static class EFData {
        
        private String deviceId;
        
        private String deviceName;
        
        private Timestamp sendTime;
        
        private String secondData;
        
        private String lowValue;
        
        private String highValue;
        
        private String average;
        
        private String communication;
        
        private String status;

        private String temperature;

        private String voltage;

        private String rotate;

        private String frequency;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public Timestamp getSendTime() {
            return sendTime;
        }

        public void setSendTime(Timestamp sendTime) {
            this.sendTime = sendTime;
        }

        public String getSecondData() {
            return secondData;
        }

        public void setSecondData(String secondData) {
            this.secondData = secondData;
        }

        public String getLowValue() {
            return lowValue;
        }

        public void setLowValue(String lowValue) {
            this.lowValue = lowValue;
        }

        public String getHighValue() {
            return highValue;
        }

        public void setHighValue(String highValue) {
            this.highValue = highValue;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            if (StringUtils.isBlank(average)) {
                this.average = null;
                return;
            }
            
            try {
                Float averageF=Float.parseFloat(average);
                Float averageFloats=averageF/100;
                DecimalFormat  formatter = new DecimalFormat ("##0.00");
                this.average = formatter.format(averageFloats);
            } catch (Exception e) {
                this.average = average;
            }
        }
        public String getCommunication() {
            return communication;
        }

        public void setCommunication(String communication) {
            this.communication = communication;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getVoltage() {
            return voltage;
        }

        public void setVoltage(String voltage) {
            this.voltage = voltage;
        }

        public String getRotate() {
            return rotate;
        }

        public void setRotate(String rotate) {
            this.rotate = rotate;
        }

        public String getFrequency() {
            return frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency;
        }
        
        public String getSendTimeStr() {
            if (this.sendTime == null) {
                return null;
            }
            return DateFormatUtils.format(sendTime, "yyyy-MM-dd HH:mm:ss");
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }
        
        public String[] toDatas() {
            String[] datas = new String[13];
            int index = 0;
            datas[index++] = deviceId;
            datas[index++] = deviceName;
            datas[index++] = getSendTimeStr();
//            datas[index++] = secondData;
//            datas[index++] = lowValue;
//            datas[index++] = highValue;
              datas[index++] = average;
            //datas[index++] = communication;
            //datas[index++] = status;
//            datas[index++] = temperature;
//            datas[index++] = voltage;
//            datas[index++] = rotate;
//            datas[index++] = frequency;
            return datas;
        }
        
        public static String[] getColTitles() {
            String[] titles = new String[13];
            int index = 0;
            titles[index++] = "站号";
            titles[index++] = "站名";
            titles[index++] = "时间日期";
//            titles[index++] = "秒数据";
//            titles[index++] = "最低值";
//            titles[index++] = "最高值";
              titles[index++] = "平均电场强度(KV/m)";
//            titles[index++] = "通信状态";
           // titles[index++] = "设备状态";
//            titles[index++] = "温度";
//            titles[index++] = "电源电压";
//            titles[index++] = "转速";
//            titles[index++] = "震动频率";
            return titles;
        }

        @Override
        public String toString() {
            return "EFData{" + "deviceId=" + deviceId + ", deviceName=" + deviceName + ", sendTime=" + sendTime + ", secondData=" + secondData + ", lowValue=" + lowValue + ", highValue=" + highValue + ", average=" + average + ", communication=" + communication + ", status=" + status + ", temperature=" + temperature + ", voltage=" + voltage + ", rotate=" + rotate + ", frequency=" + frequency + '}';
        }
       
    }
    
    public static class EFDataWarn {
    private String deviceid;
    private Timestamp sendTime;
    private String alertArede;
    private String secData;
    private String firstData;
    private String cruData;
    private String deviceName;

    public String getDeviceid() {
            return deviceid;
        }

        public void setDeviceid(String deviceid) {
            this.deviceid = deviceid;
        }

        public String getSendTimeStr() {
            if (this.sendTime == null) {
                return null;
            }
            return DateFormatUtils.format(sendTime, "yyyy-MM-dd HH:mm:ss");
        }

        public void setSendTime(Timestamp sendTime) {
            this.sendTime = sendTime;
        }

        public String getAlertArede() {
            return alertArede;
        }

        public void setAlertArede(String alertArede) {
            this.alertArede = alertArede;
        }

        public String getSendData() {
            return secData;
        }

        public void setSendData(String secData) {
            this.secData = secData;
        }

        public String getSendData2() {
            return firstData;
        }

        public void setSendData2(String firstData) {
            this.firstData = firstData;
        }

        public String getSendData3() {
            return cruData;
        }

        public void setSendData3(String cruData) {
            this.cruData = cruData;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        @Override
        public String toString() {
            return "EFDataWarn{deviceid=" + deviceid + ", sendTime=" + sendTime + ", alertArede=" + alertArede + ", secData=" + secData + ", firstData=" + firstData + ", cruData=" + cruData + ", deviceName=" + deviceName + '}';
        }
         public String[] toDatas() {
            String[] datas = new String[4];
            int index = 0;
            datas[index++] = deviceid;
            datas[index++] = deviceName;
            datas[index++] = getSendTimeStr();
            datas[index++] = alertArede;
            return datas;
        }
        public static String[] getColTitles() {
            String[] titles = new String[4];
            int index = 0;
            titles[index++] = "站号";
            titles[index++] = "站名";
            titles[index++] = "时间日期";
            titles[index++] = "预警信息";
            return titles;
        }
        
        
    }
            
    public static void main(String[] args) throws FileNotFoundException {
        /*
        String s = "\u0422\u0435\u0441\u0442\u043E\u0432\u0430\u044F"
                + "\u0421\u0442\u0440\u0430\u043D\u0438\u0447\u043A\u0430";
        System.out.println(unicode2String(s));

        s = "中文";
        System.out.println(string2Unicode(s));
        s = string2Unicode(s);
        s = "\\u4e2d\\u6587";
        System.out.println(unicode2String(s));
        */
        
        ProjectInfo projectInfo = null;
        DataExporter exporter = new DataExporter(projectInfo);
        
        //File tmpDir = exporter.getTmpDir();
        File tmpDir = new File("D:/tmp/efdata");
        
        int length = 100;
        for (int i = 0; i < length; i++) {
            EFData efData = new EFData();
            efData.setDeviceId("1000"+RandomStringUtils.randomNumeric(2));
            efData.setDeviceName(efData.getDeviceId()+"中文测试");
            efData.setSendTime(new Timestamp(System.currentTimeMillis()));
            efData.setSecondData(RandomStringUtils.randomAscii(100));
            efData.setLowValue("1."+RandomStringUtils.randomNumeric(3));
            efData.setHighValue("1."+RandomStringUtils.randomNumeric(3));
            efData.setAverage("1."+RandomStringUtils.randomNumeric(3));
            efData.setCommunication("1."+RandomStringUtils.randomNumeric(3));
            efData.setStatus("0");
            efData.setTemperature("1."+RandomStringUtils.randomNumeric(3));
            efData.setVoltage("1."+RandomStringUtils.randomNumeric(3));
            efData.setRotate("1."+RandomStringUtils.randomNumeric(3));
            efData.setFrequency("1."+RandomStringUtils.randomNumeric(3));
            exporter.export(tmpDir, efData);
        }
        
        exporter.close();

        File tmpExcelFile = exporter.getTmpExcelFile();
        System.out.println(tmpExcelFile);
    }
}
