package org.rasterdb.modules;

import java.util.List;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.filter.CheckSession;
import org.rasterdb.domain.Area;
import org.rasterdb.domain.City;
import org.rasterdb.domain.CityDistArea;
import org.rasterdb.domain.ProvDistArea;
import org.rasterdb.domain.Province;
import org.rasterdb.domain.TownDistArea;
import org.rasterdb.service.CityService;
import org.rasterdb.service.wrap.XzqhResultWrap;

/**
 *  行政区划模块
 * @author Administrator
 */
@IocBean
@At("/xzqh")
@Filters(@By(type=CheckSession.class, args={"user", "/login.jsp"}))
public class CityModule {

    @Inject
    private CityService cityService;

//    @At("/province")
//    public List<Province> queryProvinces() {
//        return cityService.getAllProvinces();
//    }

    @At("/province/?")
    @GET
    public XzqhResultWrap<List<City> , List<ProvDistArea>> queryProvince(String provinceid) {
        return cityService.getCitiesByProId(provinceid);
    }

    @At("/area/?")
    @GET
    public  XzqhResultWrap< List<Area>,List<CityDistArea> > queryArea(String cityid) {
        return cityService.getAraeasByCityId(cityid);
    }
    
    @At("/area/poly/?")
    @GET
    public List<TownDistArea> queryTownPoly(String areaid){
        return cityService.getTownPolygon(areaid);
    }
}
