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
import org.rasterdb.service.WarningService;

/**
 *
 * @author admin
 */
@IocBean
@At("warning")
@Ok("json")
public class WarningModule {
    
    @Inject
    public WarningService warningService;
    
    @At("/getWarnings")
    public List<Map> getWarnings()
    {
        return  warningService.getWarnings();
    }
}
