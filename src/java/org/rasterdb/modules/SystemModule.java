package org.rasterdb.modules;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.rasterdb.utils.RasterGeoUtil;

/**
 * 系统功能
 *
 * @author gis
 */
@At("/system")
@Ok("json")
@IocBean
public class SystemModule {

    @Inject
    RasterGeoUtil rasterGeoUtil;

    /**
     * 重建影像数据空间范围表数据
     * @return 
     */
    @At("/extent")
    public boolean createRasterExtent() {
        boolean success= false;
        try {
            rasterGeoUtil.clearData();
            rasterGeoUtil.initGeoTable();
            success= true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return success;
    }

    /**
     * 删除所有空间范围数据
     */
    @At("/clean")
    public void justClean() {
        rasterGeoUtil.clearData();
    }

}
