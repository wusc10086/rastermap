
package org.rasterdb.service;

import java.util.List;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;
import org.rasterdb.domain.Cgq;

/**
 *  传感器服务
 * @author Administrator
 */
@IocBean( name = "cgqService")
public class CgqService {
    
    @Inject("dao2")
    protected Dao dao2;
    
    /**
     * 获取传感器列表
     * @return 
     */
    public List<Cgq> getCgqList(){
       return dao2.query(Cgq.class, null);
    }
    
}
