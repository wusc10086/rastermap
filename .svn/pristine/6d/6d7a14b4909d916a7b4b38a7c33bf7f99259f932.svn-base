/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.modules;

import java.util.List;
import java.util.Map;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.rasterdb.domain.EFDevice;
import org.rasterdb.service.DeviceService;

/**
 *
 * @author admin
 */
@IocBean
@At("/device")
@Ok("json")
public class DeviceModule {
    
    @Inject
    public DeviceService deviceService;
    
    @At("/getalldevice")
    public List<EFDevice> getAllDevices()
    {
        //return  deviceService.getAllDevice();
        return  deviceService.getAllDeviceStatue();
    }
    
    
    @At("/getAddress")
    public List<Map> getAddress()
    {
        return  deviceService.getAddress();
    }
    
    @At("/getDeviceStatus")
    public Map getDeviceStatus()
    {
        return  deviceService.getDeviceStatus();
    }
}
