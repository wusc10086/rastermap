package org.rasterdb.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.rasterdb.domain.RasterExtent;

/**
 *
 * @author Administrator
 */


@IocBean( name = "rasterExtentService")
public class RasterExtentService {
    
    @Inject("dao2")
    protected Dao dao2;
    
    public RasterExtent queryRasterExtent(String sno){
        String query = "select id,sno,st_astext(extent) as extent2 from rasterextent where sno='"+sno+"'";
        Sql sqls = Sqls.create(query);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                RasterExtent extent = new RasterExtent();
                if(rs.next()){
                    extent.setId(rs.getInt("id"));
                    extent.setSno(rs.getString("sno"));
                    extent.setExtent(rs.getString("extent2"));
                }
                return extent;
            }
        });
        
        RasterExtent res = dao2.execute(sqls).getObject(RasterExtent.class);
        return res;
    }
    
    /**
     * 做一次相交查询，查出指定区域的信息
     * @param xzqhid  江苏
     * @return 
     */
    public Map intersectQuery(String xzqhid,String table){
        double pid = Double.parseDouble(xzqhid);
   
      String q  = "select  r.location,st_astext(r.geom) as extent from js_rasterextent r,"+table+" g where st_intersects(r.geom,g.geom)=true and g.distid=@xzqhid";
      Sql sqls = Sqls.create(q);
        sqls.params().set("xzqhid", pid);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
               Map m =new HashMap();
               while(rs.next()){
                   String temp = rs.getString("location");
                   String sn = temp.substring(temp.lastIndexOf("\\")+1,temp.lastIndexOf("."));
                   String ext = rs.getString("extent");
                   m.put(sn,ext);
               }
               return m; 
            }
        });
        return dao2.execute(sqls).getObject(Map.class);
    }
}
