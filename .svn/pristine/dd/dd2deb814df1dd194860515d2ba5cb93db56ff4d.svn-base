/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.modules.runmonitor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.ViewModel;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.rasterdb.domain.EFData;
import org.rasterdb.domain.EFDevice;
import org.rasterdb.service.EFDataService;
import org.rasterdb.service.device.DeviceManagerService;
import org.rasterdb.service.sysparam.SysParam;
import org.rasterdb.service.sysparam.SysParamService;
import org.rasterdb.utils.Utils;

/**
 *
 * @author wuyiwei
 */
@IocBean
@At("/runmonitor")
public class DeviceStateModule {
    
    @Inject()
    private EFDataService efDataService;
    @Inject()
    private DeviceManagerService deviceManagerService;
    @Inject()
    private SysParamService sysParamService;
    
    @At("/deviceState")
    @Ok("re")
    public String alarm(ViewModel model) {
        
        SysParam p = sysParamService.getByParamName("geo_center_lng");
        String centerLng = p.getParamValue();
        p = sysParamService.getByParamName("geo_center_lat");
        String centerLat = p.getParamValue();
        p = sysParamService.getByParamName("geo_map_zoom");
        String mapZoom = p.getParamValue();
        p = sysParamService.getByParamName("geo_map_minzoom");
        String mapMinZoom = p.getParamValue();
        p = sysParamService.getByParamName("geo_map_maxzoom");
        String mapMaxZoom = p.getParamValue();
        p = sysParamService.getByParamName("device_statedata_zoom_symbolsize");
        String zoomAndSymboSize = p.getParamValue();
        
        List<EFDevice> allDevices = deviceManagerService.allDevices();
        
        model.put("centerLng", centerLng);
        model.put("centerLat", centerLat);
        model.put("mapZoom", mapZoom);
        model.put("mapMinZoom", mapMinZoom);
        model.put("mapMaxZoom", mapMaxZoom);
        model.put("zoomAndSymboSize", zoomAndSymboSize);
        model.put("allDevices", allDevices);
        
        return "jsp:/runmonitor/device-state.jsp";
    }
    
    /**
     * 获得当前所有设备的最新电场数据
     * @return 
     */
    /*
    @At("/currentDevicesEFData")
    @Ok("json")
    public List<DeviceAndEFData> currentDevicesEFData() {
        // 所有站
        List<EFDevice> allDevices = deviceManagerService.allDeviceDetails();
        
        // 最近n分钟的电场数据
        SysParam p = sysParamService.getByParamName("device_statedata_check_timeOnMinute");
        String nMinStr = (p == null ? null : p.getParamValue());
        int nMin = NumberUtils.toInt(nMinStr, 2);
        
        long now = System.currentTimeMillis();
        Timestamp startTime = Utils.PreviousNMin(now, nMin);
        Timestamp endTime = new Timestamp(now);
        List<EFData> efDataList = efDataService.getEFDatas(startTime, endTime, allDevices);
        
        // 整理数据
        Map<String, DeviceAndEFData> currentDevicesEFDataMap = new HashMap<String, DeviceAndEFData>();
        for (EFDevice device : allDevices) {
            DeviceAndEFData dd = DeviceAndEFData.createDeviceAndEFData(device);
            currentDevicesEFDataMap.put(dd.getDeviceId(), dd);
        }
        
        for (EFData efData : efDataList) {
            String deviceId = efData.getDeviceid();
            DeviceAndEFData dd = currentDevicesEFDataMap.get(deviceId);
            if (dd == null) {
                continue;
            }
            
            if (efData.getSendTime() == null) {
                continue;
            }
            
            Timestamp sendTs = dd.getSendTimestamp();
            if (sendTs == null) {
                DeviceAndEFData.fillEFData(dd, efData);
            } else if (efData.getSendTime().compareTo(sendTs) > 0) {
                DeviceAndEFData.fillEFData(dd, efData);
            } else {
                // do nothing 电场数据比较老，什么都不做。
            }
        }
        
        return new ArrayList(currentDevicesEFDataMap.values());
    }
    */
    
    /**
     * 获得当前所有设备的最新电场数据
     * @return 
     */
    @At("/currentDevicesEFData")
    @Ok("json")
    public List<DeviceAndEFData> currentDevicesEFData() {
        // 所有站
        List<EFDevice> allDevices = deviceManagerService.allDeviceDetails();
        
        // 整理数据
        Map<String, DeviceAndEFData> currentDevicesEFDataMap = new HashMap<String, DeviceAndEFData>();
        for (EFDevice device : allDevices) {
            DeviceAndEFData dd = DeviceAndEFData.createDeviceAndEFData(device);
            currentDevicesEFDataMap.put(dd.getDeviceId(), dd);
        }
        
        return new ArrayList(currentDevicesEFDataMap.values());
    }
    
    /**
     * 获得某设备最近一小时最新电场数据
     * @param deviceId
     * @return 
     */
    @At("/getDeviceEfdatas")
    @Ok("json")
    public List<Object[]> getDeviceEfdatas(@Param("deviceId") String deviceId) {
        // 设备最近n分钟的电场数据
        SysParam p = sysParamService.getByParamName("device_statedata_hour_datas");
        String nMinStr = (p == null ? null : p.getParamValue());
        int nMin = NumberUtils.toInt(nMinStr, 60);
        
        long now = System.currentTimeMillis();
        Timestamp startTime = Utils.PreviousNMin(now, nMin);
        Timestamp endTime = new Timestamp(now);
        List<EFDevice> device = new ArrayList<EFDevice>(1);
        EFDevice d = new EFDevice();
        d.setNum(deviceId);
        device.add(d);
        List<EFData> efDataList = efDataService.getEFDatas(startTime, endTime, device);
        
        // 转换为TimeAndValue对象列表，并排序
        Map<Calendar, TimeAndValue> timeAndValueMap = new HashMap<Calendar, TimeAndValue>();
        for (EFData efData : efDataList) {
            String curvalue = efData.getCurvalue();
            Float curvalueF = Utils.toEfdataValue(curvalue);
            TimeAndValue tv = new TimeAndValue(efData.getSendTime(), 
                    curvalueF == null ? 0 : curvalueF.doubleValue());
            timeAndValueMap.put(tv.getSendTime(), tv);
        }
        
        
        // 再转换为前台需要的值。如果有缺失的数据，则补上。
        List<Object[]> list = new ArrayList<Object[]>(nMin);
        long startTimeTs = startTime.getTime();
        for (int i = 0; i < nMin; i++) {
            long timeTs = startTimeTs + i * 60 * 1000L;
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timeTs);
            TimeAndValue tv = timeAndValueMap.get(calendar);
            if (tv == null) {
                tv = new TimeAndValue(calendar, null);
            }
            list.add(tv.toArray());
        }
        
        return list;
    }
    
    public static class DeviceAndEFData {

        // 下面为设备信息
        private String deviceId;

        private String deviceName;

        private BigDecimal lat;

        private BigDecimal lng;

        private BigDecimal elevation;

        private String manufaceture;

        private Date installTime;

        private Date lastInSpect;

        private Date lastRepair;

        private String cityid;
        
        
        // 下面为设备电场信息
        private Timestamp sendTimestamp;
        
        private String sendTime;

        private BigDecimal lowValue;

        private BigDecimal highValue;

        private BigDecimal average;
        // 状态值
        private int stateValue;
        
        // 下面为device里新增的属性
        // 最后到报的状态时间
        private Timestamp laststatustime;
        // 电场值：kv/s
        private Float efvalue;
        // 报警级别
        private Integer alarmlevel;
        // 最后报警的时间
        private Timestamp lastalarmtime;
        
        public static DeviceAndEFData createDeviceAndEFData(EFDevice device) {
            DeviceAndEFData dd = new DeviceAndEFData();
            dd.setDeviceId(device.getNum());
            dd.setDeviceName(device.getDeviceName());
            dd.setLat(device.getLatitude());
            dd.setLng(device.getLongitude());
            dd.setElevation(device.getElevation());
            dd.setManufaceture(device.getManufaceture());
            dd.setInstallTime(device.getInstallTime());
            dd.setLastInSpect(device.getLastInSpect());
            dd.setLastRepair(device.getLastRepair());
            dd.setCityid(device.getCityid());
            dd.setStateValue(-1); // 无数据
            
            dd.setStateValue(device.getStatus() == null ? 0 : NumberUtils.toInt(device.getStatus()));
            dd.setLaststatustime(device.getLaststatustime());
            dd.setLastalarmtime(device.getLastalarmtime());
            dd.setEfvalue(Utils.toEfdataValue(device.getEfvalue()));
            Integer alarmLevel = device.getAlarmlevel();
            dd.setAlarmlevel( ( alarmLevel == null || alarmLevel <= 0 ) ? null : alarmLevel);
            return dd;
        }
        
        public static void fillEFData(DeviceAndEFData dd, EFData efData) {
            dd.setSendTimestamp(efData.getSendTime());
            dd.setLowValue(efData.getLowValue() == null ? null : new BigDecimal(efData.getLowValue()));
            dd.setHighValue(efData.getHighValue() == null ? null : new BigDecimal(efData.getHighValue()));
            dd.setAverage(efData.getAverage() == null ? null : new BigDecimal(efData.getAverage()));
            dd.setStateValue(NumberUtils.toInt(efData.getStatus()));
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public BigDecimal getElevation() {
            return elevation;
        }

        public void setElevation(BigDecimal elevation) {
            this.elevation = elevation;
        }

        public String getManufaceture() {
            return manufaceture;
        }

        public void setManufaceture(String manufaceture) {
            this.manufaceture = manufaceture;
        }

        public Date getInstallTime() {
            return installTime;
        }

        public void setInstallTime(Date installTime) {
            this.installTime = installTime;
        }

        public Date getLastRepair() {
            return lastRepair;
        }

        public void setLastRepair(Date lastRepair) {
            this.lastRepair = lastRepair;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public BigDecimal getLowValue() {
            return lowValue;
        }

        public void setLowValue(BigDecimal lowValue) {
            this.lowValue = lowValue;
        }

        public BigDecimal getHighValue() {
            return highValue;
        }

        public void setHighValue(BigDecimal highValue) {
            this.highValue = highValue;
        }

        public BigDecimal getAverage() {
            return average;
        }

        public void setAverage(BigDecimal average) {
            this.average = average;
        }

        public int getStateValue() {
            return stateValue;
        }

        public void setStateValue(int stateValue) {
            this.stateValue = stateValue;
        }

        public BigDecimal getLat() {
            return lat;
        }

        public void setLat(BigDecimal lat) {
            this.lat = lat;
        }

        public BigDecimal getLng() {
            return lng;
        }

        public void setLng(BigDecimal lng) {
            this.lng = lng;
        }

        public Date getLastInSpect() {
            return lastInSpect;
        }

        public void setLastInSpect(Date lastInSpect) {
            this.lastInSpect = lastInSpect;
        }

        public Timestamp getSendTimestamp() {
            return sendTimestamp;
        }

        public void setSendTimestamp(Timestamp sendTimestamp) {
            this.sendTimestamp = sendTimestamp;
            if (sendTimestamp != null) {
                this.sendTime = DateFormatUtils.format(sendTimestamp, "yyyy-MM-dd HH:mm:ss");
            }
        }

        public Timestamp getLaststatustime() {
            return laststatustime;
        }

        public void setLaststatustime(Timestamp laststatustime) {
            this.laststatustime = laststatustime;
        }

        public Float getEfvalue() {
            return efvalue;
        }

        public void setEfvalue(Float efvalue) {
            this.efvalue = efvalue;
        }

        public Integer getAlarmlevel() {
            return alarmlevel;
        }

        public void setAlarmlevel(Integer alarmlevel) {
            this.alarmlevel = alarmlevel;
        }

        public Timestamp getLastalarmtime() {
            return lastalarmtime;
        }

        public void setLastalarmtime(Timestamp lastalarmtime) {
            this.lastalarmtime = lastalarmtime;
        }
    }
    
    private static class TimeAndValue implements Comparable{
        private Calendar sendTime;
        private Double dataValue;
        
        public TimeAndValue(Timestamp sendTime, Double dataValue) {
            this.sendTime = Calendar.getInstance();
            this.sendTime.setTimeInMillis(sendTime.getTime());
            this.sendTime.set(Calendar.MILLISECOND, 0);
            this.sendTime.set(Calendar.SECOND, 0);
            this.dataValue = dataValue;
        }
        
        public TimeAndValue(Calendar sendTime, Double dataValue) {
            this.sendTime = sendTime;
            this.sendTime.set(Calendar.MILLISECOND, 0);
            this.sendTime.set(Calendar.SECOND, 0);
            this.dataValue = dataValue;
        }
        
        public Object[] toArray() {
            int hour = sendTime.get(Calendar.HOUR_OF_DAY);
            int minute = sendTime.get(Calendar.MINUTE);
            String hourStr = (hour <= 9 ? "0" + hour : "" + hour) ;
            String minuteStr = (minute <= 9 ? "0" + minute : "" + minute) ;
            return new Object[]{hourStr + ":" + minuteStr, dataValue};
        }

        @Override
        public int compareTo(Object o) {
            TimeAndValue other = (TimeAndValue)o;
            return this.sendTime.compareTo(other.sendTime);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 53 * hash + (this.sendTime != null ? this.sendTime.hashCode() : 0);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final TimeAndValue other = (TimeAndValue) obj;
            if (this.sendTime != other.sendTime && (this.sendTime == null || !this.sendTime.equals(other.sendTime))) {
                return false;
            }
            return true;
        }
        
        

        @Override
        public String toString() {
            return "TimeAndValue{" + "sendTime=" + sendTime + ", dataValue=" + dataValue + '}';
        }

        public Calendar getSendTime() {
            return sendTime;
        }

        public void setSendTime(Calendar sendTime) {
            this.sendTime = sendTime;
        }

        public double getDataValue() {
            return dataValue;
        }

        public void setDataValue(double dataValue) {
            this.dataValue = dataValue;
        }
        
    }
    
}
