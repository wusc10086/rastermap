package org.rasterdb.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.NameEntityService;

/**
 * 查询生产者和产品名称
 * @author Administrator
 */

@IocBean( fields = {"dao"},name = "propertiesQueryService")
public class PropertiesQueryService extends NameEntityService{
    
    /**
     * 查询产品名称列表
     * @return 
     */
    public Map queryProductName(){
        
        Sql sqls = Sqls.create("select DISTINCT productname from r_productinfo");
        
         sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                 Map m = new HashMap();
                 List<String> prod = new ArrayList<String>();
                 while (rs.next()) {                    
                    String productname = rs.getString("productname");
                    if(productname!= null&& !productname.isEmpty()){
                          prod.add(productname);
                    }
                  
                }
                 m.put("product", prod);
                 return m;
            }
        });
        
        Map pro = this.dao().execute(sqls).getObject(Map.class);
        
        return pro;
        
        
    }
    
    /**
     * 查询生产单位列表
     * @return 
     */
    public Map queryProducer(){
        
        Sql sqls = Sqls.create("select DISTINCT producer from r_productinfo");
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                 Map m = new HashMap();
                 List<String> list = new ArrayList<String>();
                 while (rs.next()) {                    
                    String producer = rs.getString("producer");
                      if(producer!= null&& !producer.isEmpty()){
                          list.add(producer);
                    }
//                    list.add(producer);
                }
                 m.put("producer", list);
                 return m;
            }
        });
        
        Map pro = this.dao().execute(sqls).getObject(Map.class);
        
        return pro;
    }
    
}
