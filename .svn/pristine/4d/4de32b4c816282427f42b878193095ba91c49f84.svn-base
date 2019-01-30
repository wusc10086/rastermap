
package org.rasterdb.service;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import org.rasterdb.domain.SatelliteView;

/**
 * 卫星服务
 * @author Administrator
 */
@IocBean(name = "satelliteService")
public class SatelliteService {
    
      @Inject("dao")
    protected Dao dao2;
    
//    public List<Satellite> getSatelliteList(){
//        return dao2.query(Satellite.class, null);
//        
//    }
    public List<SatelliteView> getSatelliteViewList(){
        return dao2.query(SatelliteView.class, null);
    }
}
