/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.modules.runmonitor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import org.rasterdb.utils.HYPager;

/**
 *
 * @author wuyiwei
 */
@IocBean
@At("/runmonitor")
public class DeviceSeqPlanModule {
    
    private static final int DEFAULT_SHOW_PAGE_COUNT = HYPager.DEFAULT_SHOW_PAGE_COUNT;
    
    private static final int DEFAULT_PAGE_SIZE = 15;
    
    private static final int hourCount = 3;
    
    @Inject()
    private DeviceManagerService deviceManagerService;
    
    @Inject("efDataService")
    private EFDataService efDataService;
    
    @At("/listInit")
    @Ok("re")
    public String listInit(ViewModel model) {
        HYPager pager = new HYPager();
        pager.setShowPageCount(DEFAULT_SHOW_PAGE_COUNT);
        pager.setPageSize(DEFAULT_PAGE_SIZE);
        
        List<EFDevice> deviceList = deviceManagerService.queryWithPager(pager);
        
        long now = System.currentTimeMillis();
        Calendar startNow = Calendar.getInstance();
        startNow.setTimeInMillis(now);
        Timestamp startTime = before3H(startNow);
        Timestamp endTime = new Timestamp(now);
        List<EFData> efDataList = efDataService.getEFDatas(startTime, endTime, deviceList);
        
        List<XEFData> xefDataList = makeXEFData(deviceList, efDataList, startTime, now);
        model.put("xefDataList", xefDataList);
        model.put("hourStart", startNow.get(Calendar.HOUR_OF_DAY));
        model.put("hourCount", hourCount);
        
        model.put("pager", pager);
        model.put("pagerString", HYPager.encode(pager));
        
        
        return "jsp:/runmonitor/device-seq.jsp";
    }
    
    @At("/doList")
    @Ok("re")
    public String doList(@Param("pager") String pagerString,
            @Param("pageNo") String pageNo, ViewModel model) {
        HYPager pager = HYPager.decode(pagerString);
        if (pager == null){
            pager = new HYPager<Void>();
            pager.setShowPageCount(DEFAULT_SHOW_PAGE_COUNT);
            pager.setPageSize(DEFAULT_PAGE_SIZE);
        }
        
        int pageNoInt = NumberUtils.toInt(pageNo, -1);
        if (pageNoInt != -1) {
            pager.setPageNumber((pageNoInt <= 1 ? 1 : pageNoInt));
        }
        
        List<EFDevice> deviceList = deviceManagerService.queryWithPager(pager);
        
        long now = System.currentTimeMillis();
        Calendar startNow = Calendar.getInstance();
        startNow.setTimeInMillis(now);
        Timestamp startTime = before3H(startNow);
        Timestamp endTime = new Timestamp(now);
        List<EFData> efDataList = efDataService.getEFDatas(startTime, endTime, deviceList);
        
        List<XEFData> xefDataList = makeXEFData(deviceList, efDataList, startTime, now);
        model.put("xefDataList", xefDataList);
        model.put("hourStart", startNow.get(Calendar.HOUR_OF_DAY));
        model.put("hourCount", hourCount);
        
        model.put("pager", pager);
        model.put("pagerString", HYPager.encode(pager));
        
        return "jsp:/runmonitor/device-seq.jsp";
    }
    
    
    
    private Timestamp before3H(Calendar now) {
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        now.add(Calendar.HOUR_OF_DAY, -hourCount + 1);
        return new Timestamp(now.getTimeInMillis());
    }

    private List<XEFData> makeXEFData(List<EFDevice> deviceList, 
            List<EFData> efDataList, Timestamp startTime, long now) {
        if (deviceList == null || deviceList.isEmpty()) {
            return Collections.emptyList();
        }
        
        Map<String, XEFData> xefDataMap = new LinkedHashMap<String, XEFData>();
        
        for (EFDevice device : deviceList) {
            XEFData xefData = new XEFData(hourCount);
            xefData.setDeviceNum(device.getNum());
            xefData.setDeviceName(StringUtils.isBlank(device.getDeviceName()) ? device.getNum() : device.getDeviceName());
            xefDataMap.put(device.getNum(), xefData);
        }
        
        if (efDataList != null) {
            Collections.reverse(efDataList);
            for (EFData efData : efDataList) {
                String deviceId = efData.getDeviceid();

                XEFData xefData = xefDataMap.get(deviceId);
                if (xefData == null) {
                    continue;
                }

                Timestamp sendTime = efData.getSendTime();
                int min10Diff = (int) ((sendTime.getTime() - startTime.getTime()) / (10 * 60 * 1000));
                int[] statuses = xefData.getStatuses();
                int status;
                if ("0".equals(efData.getStatus())) {
                    status = 0;
                } else {
                    status = 1;
                }
                //statuses[statuses.length - 1 - min10Diff] = status;
                statuses[min10Diff] = status;
            }
        }

        Calendar end = Calendar.getInstance();
        end.setTimeInMillis(now);
        int curMinute = end.get(Calendar.MINUTE);
        for (XEFData xefData : xefDataMap.values()) {
            int[] statuses = xefData.getStatuses();
            for (int i = 0; i < 6 - (curMinute / 10); i++) {
                int index = statuses.length - 1 - i;
                if (statuses[index] != 0 && statuses[index] != 1) {
                    statuses[index] = -2;
                }
            }
        }
        
        for (XEFData xefData : xefDataMap.values()) {
            int[] statuses = xefData.getStatuses();
            reverse(statuses);
        }
        
        List<XEFData> list = new ArrayList<XEFData>(xefDataMap.values());
        return list;
    }
    
    
    private void reverse(int[] array) {
       for (int i = 0; i < array.length / 2; i++) {
           int v = array[i];
           array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = v;
       }
    }
    
    
    public static class XEFData implements Serializable{
        /**
         * 站号
         */
        private String deviceNum;
        /**
         * 站名
         */
        private String deviceName;
        /**
         * 最近三个小时的状态。10分钟一个点，共18个点。
         * 索引0：当前小时50-60分钟之间的状态；
         * 索引1：当前小时50-60分钟之间的状态；
         * .....
         * 
         * 状态：-2: 没有到时间；-1：没有数据，1：数据正常，0：系统异常。
         */
        private int[] statuses;
        
        public XEFData(int hourCount) {
            statuses = new int[hourCount * 6];
            for (int i = 0; i < statuses.length; i++) {
                statuses[i] = -1;
            }
        }

        public String getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(String deviceNum) {
            this.deviceNum = deviceNum;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public int[] getStatuses() {
            return statuses;
        }

        public void setStatuses(int[] statuses) {
            this.statuses = statuses;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 29 * hash + (this.deviceNum != null ? this.deviceNum.hashCode() : 0);
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
            final XEFData other = (XEFData) obj;
            if ((this.deviceNum == null) ? (other.deviceNum != null) : !this.deviceNum.equals(other.deviceNum)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "XEFData{" + "deviceNum=" + deviceNum + ", deviceName=" + deviceName + ", statuses=" + Arrays.toString(statuses) + '}';
        }
        
    }
    
}
