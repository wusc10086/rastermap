/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.modules.rtalarm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.math.NumberUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.ViewModel;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.rasterdb.domain.EFDevice;
import org.rasterdb.modules.runmonitor.DeviceStateModule;
import org.rasterdb.service.device.DeviceManagerService;
import org.rasterdb.service.sysparam.SysParam;
import org.rasterdb.service.sysparam.SysParamService;

/**
 *
 * @author wuyiwei
 */
@IocBean
@At("/rtalarm")
public class RTAlarmModule {
    
    @Inject()
    private SysParamService sysParamService;
    
    @Inject()
    private DeviceManagerService deviceManagerService;
    
    @At("/rtalarm")
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
        p = sysParamService.getByParamName("device_warningdata_zoom_pointsize");
        String zoomAndSymboSize = p.getParamValue();
        
        model.put("centerLng", centerLng);
        model.put("centerLat", centerLat);
        model.put("mapZoom", mapZoom);
        model.put("mapMinZoom", mapMinZoom);
        model.put("mapMaxZoom", mapMaxZoom);
        model.put("zoomAndSymboSize", zoomAndSymboSize);
        
        return "jsp:/rtalarm/rtalarm.jsp";
    }
    
    
     /**
     * 获得当前所有设备的最新电场数据
     * @return 
     */
    @At("/currentDevicesEFData")
    @Ok("json")
    public List<DeviceStateModule.DeviceAndEFData> currentDevicesEFData() {
        // 所有站
        List<EFDevice> allDevices = deviceManagerService.allDeviceDetails();
        
        // 整理数据
        Map<String, DeviceStateModule.DeviceAndEFData> currentDevicesEFDataMap = new HashMap<String, DeviceStateModule.DeviceAndEFData>();
        for (EFDevice device : allDevices) {
            DeviceStateModule.DeviceAndEFData dd = DeviceStateModule.DeviceAndEFData.createDeviceAndEFData(device);
            currentDevicesEFDataMap.put(dd.getDeviceId(), dd);
        }
        
        return new ArrayList(currentDevicesEFDataMap.values());
    }
    
    /**
     * 获得当前所有设备的最新电场数据
     * @return 
     */
    @At("/getWarnings")
    @Ok("json")
    public List<Map> getWarnings() {
        // 所有站
        List<EFDevice> allDevices = deviceManagerService.allDeviceDetails();
        
        // 整理数据
        List<Map> listmap = new ArrayList<Map>();
        for (EFDevice device : allDevices) {
            Map map = new HashMap();
            map.put("num", device.getNum());
            map.put("Latitude", device.getLatitude());
            map.put("Longitude", device.getLongitude());
            map.put("AlertArede", device.getAlarmlevel());
            //map.put("seconddata", rs.getString("seconddata"));
            map.put("curdata", device.getEfvalue());
            
            String num = device.getNum();
            int level = NumberUtils.toInt(num) % 5;
            map.put("AlertArede", ""+level);
            
            listmap.add(map);
        }
        
        return listmap;
    }
    
}
