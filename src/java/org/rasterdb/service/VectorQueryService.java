package org.rasterdb.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * 矢量空间查询服务
 *
 * @author Administrator
 */
@IocBean(name = "vectorQueryService")
public class VectorQueryService {

    @Inject("dao2")
    protected Dao dao2;
  

    /**yzz 更新
     * 可返回上传的shp文件+查询到的数据库数据
     * 多个polygon查询数据
     * @param polygons
     * @return 
     */
    public List<Object> queryRasterInfo(List<String> polygons) {
        List<Object> list = new ArrayList<Object>();
        list.add(polygons);
        for (int i = 0; i < polygons.size(); i++) {
            String poly = polygons.get(i);
            List<Map> maplist = intersectQuery(poly);
            if(maplist!=null&&maplist.size()>0){
                list.add(maplist);
            }
        }
        return list;
    }

    /**
     * <p> 指定查询范围的相交查询</p>
     * <p>TODO //该方法用于空间数据和属性数据都存储在同一数据库的情况,这种查询可以一步到位，但实际情况是oracle +postgis，故后期需要修改查询模式 </p>
     * @param polygon 指定的范围
     * @return 相交的结果
     */
    public List<Map> intersectQuery(String polygon) {

        String query = "select r.numimage,st_astext(r.geom) as geom, p.imagecolor,p.productdate from js_rasterextent r,js_image p where st_intersects(geom,st_geomfromtext('" + polygon + "',4326))=true and p.numimage=r.numimage";
        
        Sql sqls = Sqls.create(query);
        System.out.println(sqls.toString());        
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Map> list = new ArrayList<Map>();
                while (rs.next()) {
                    Map m = new HashMap();
                    String sn = rs.getString("numimage");
                    String ext = rs.getString("geom");
                    String date = rs.getString("productdate");
                    String colormodel = rs.getString("imagecolor");
                    m.put("sn", sn);
                    m.put("extent",ext);
                    m.put("producedate",date);
                    m.put("imgcolormodel", colormodel);
                    list.add(m);
                }
                return list;
            }
        });
        return dao2.execute(sqls).getObject(List.class);
    }
}
