/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service.dataplayback;

import java.sql.Timestamp;
import org.rasterdb.utils.Utils;

/**
 *
 * @author wuyiwei
 */
public class HisDataQueryForm {
    
    /**
     * 查询的开始时间
     */
    private String startTime;
    /**
     * 查询的结束时间
     */
    private String endTime;
    /**
     * 查询的数据类型。1：按电场强度查询；2：按预警信息查询
     */
    private String dataType;
    /**
     * 查询的站号列表。多个站号之间逗号分隔。
     */
    private String deviceIds;
    

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDeviceIds() {
       return deviceIds;
    }

    public void setDeviceIds(String deviceIds) {
        this.deviceIds = deviceIds;
    }

    @Override
    public String toString() {
        return "HisDataQueryForm{" + "startTime=" + startTime + ", endTime=" + endTime + ", dataType=" + dataType + ", deviceIds=" + deviceIds + '}';
    }
    
    public Timestamp toStartTime() {
        return Utils.toTimestamp(startTime);
    }
    
    public Timestamp toEndTime() {
        return Utils.toTimestamp(endTime);
    }
    public String devicesIds() {
        String s1[] = deviceIds.split(",");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s1.length; i++) {
            if (i < s1.length - 1) {
                sb.append("'");
                sb.append(s1[i]);
                sb.append("',");
            } else {
                sb.append("'");
                sb.append(s1[i]);
                sb.append("',");
            }
        }
        String s = sb.substring(0, sb.length() - 1);
        return s;
    }
}
