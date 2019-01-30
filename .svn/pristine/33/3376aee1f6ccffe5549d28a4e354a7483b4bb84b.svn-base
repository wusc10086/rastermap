package org.rasterdb.service;

import com.vividsolutions.jts.geom.GeometryFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;
import org.rasterdb.domain.Area;
import org.rasterdb.domain.City;
import org.rasterdb.domain.CityDistArea;
import org.rasterdb.domain.ProvDistArea;
import org.rasterdb.domain.Province;
import org.rasterdb.domain.TownDistArea;
import org.rasterdb.service.wrap.XzqhResultWrap;
import org.wololo.jts2geojson.GeoJSONWriter;

/**
 * 省市县数据获取
 *
 * @author Administrator
 */
@IocBean( name = "cityService")
public class CityService  {

    
       
    @Inject("dao2")
    protected Dao dao2;
    private final GeoJSONWriter writer = new GeoJSONWriter();
    private final GeometryFactory factory = new GeometryFactory();

    /**
     * 获取所有省
     *
     * @return
     */
    public List<Province> getAllProvinces() {

        return dao2.query(Province.class, null);

    }

    /**
     * 获取省行政
     *
     * @param proid
     * @return
     */
    public XzqhResultWrap<List<City>, List<ProvDistArea>> getCitiesByProId(String proid) {
        List<City> list = dao2.query(City.class, Cnd.where("provinceid", "=", proid));
        List<ProvDistArea> lc = getProvincePolygon(proid);
        return new XzqhResultWrap<List<City>, List<ProvDistArea>>(list, lc);
    }

    /**
     * 获取所有区和县
     *
     * @param cityid
     * @return
     */
    public XzqhResultWrap< List<Area>, List<CityDistArea>> getAraeasByCityId(String cityid) {

        List<Area> list = dao2.query(Area.class, Cnd.where("cityid", "=", cityid));
        List<CityDistArea> calist = getCityPolygon(cityid);

        return new XzqhResultWrap< List<Area>, List<CityDistArea>>(list, calist);

    }

    public List<ProvDistArea> getProvincePolygon(String id) {
        double pid = Double.parseDouble(id);
        //postgis BeyonDB
        String q = "select name,distid,geomwkt as geom from prov_dist_area where distid=" + pid;

//  Oracle
//        String q = "select name,distid,SDO_GEOMETRY.GET_WKT(the_geom) as geom from prov_dist_area where distid=" + pid;
        Sql sql = Sqls.create(q);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<ProvDistArea> list = new ArrayList<ProvDistArea>();
                while (rs.next()) {
                    ProvDistArea td = new ProvDistArea();
                    td.setName(rs.getString("name"));
                    td.setDistid(rs.getDouble("distid"));
                    td.setTheGeom(rs.getString("geom"));
                    list.add(td);
                }
                return list;
            }
        });

        dao2.execute(sql);
        return sql.getList(ProvDistArea.class);
    }
    /**
     *  地级市查询
     * @param id
     * @return 
     */
    
    public List<CityDistArea> getCityPolygon(String id) {
        double pid = Double.parseDouble(id);
        String q = "select name,distid,st_astext(geom) as geom from city_dist_area where distid=" + pid;
//        String q = "select name,distid,SDO_GEOMETRY.GET_WKT(the_geom) as geom from city_dist_area where distid=" + pid;
        Sql sql = Sqls.create(q);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<CityDistArea> list = new ArrayList<CityDistArea>();
                while (rs.next()) {
                    CityDistArea td = new CityDistArea();
                    td.setName(rs.getString("name"));
                    td.setDistid(rs.getDouble("distid"));
                    td.setTheGeom(rs.getString("geom"));
                    list.add(td);
                }

                return list;
            }
        });

        dao2.execute(sql);
        return sql.getList(CityDistArea.class);
    }

    public List<TownDistArea> getTownPolygon(String id) {
        double pid = Double.parseDouble(id);
        String q = "select name,distid,st_astext(geom) as geom from town_dist_area where distid=" + pid;
        
        //oracle 数据库空间数据查询
//        String q = "select name,distid,SDO_GEOMETRY.GET_WKT(the_geom) as geom from town_dist_area where distid=" + pid;
        Sql sql = Sqls.create(q);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<TownDistArea> list = new ArrayList<TownDistArea>();

                while (rs.next()) {
                    TownDistArea td = new TownDistArea();
                    td.setName(rs.getString("name"));
                    td.setDistid(rs.getDouble("distid"));
                    td.setTheGeom(rs.getString("geom"));
                    list.add(td);
                }

                return list;
            }
        });

        dao2.execute(sql);
        return sql.getList(TownDistArea.class);
    }

        /**
     * 获取所有地级市。地级市按cityid排序。
     * @param proid
     * @return 
     */
    public List<City> allCitiesByProId(String proid) {
        Cnd cnd =  Cnd.where("provinceid", "=", proid);
        cnd.orderBy("cityid", "asc");
        List<City> list = dao2.query(City.class,cnd);
        return list;
    }
    
    /**
     * 根据地市id，获得所有的县
     * @param cityid
     * @return 
     */
    public List<Area> allAreasByCityid(String cityid) {
        Cnd cnd =  Cnd.where("cityid", "=", cityid);
        cnd.orderBy("areaid", "asc");
        List<Area> list = dao2.query(Area.class,cnd);
        return list;
    }

}
