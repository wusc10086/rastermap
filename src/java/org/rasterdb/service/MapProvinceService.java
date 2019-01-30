/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.ArrayDelegatingEList;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.rasterdb.domain.CityDistArea;
import org.rasterdb.domain.MapProvince;
import org.rasterdb.domain.MapCity;
import org.rasterdb.domain.ProvDistArea;
import org.rasterdb.service.wrap.XzqhResultWrap;

/**
 * dao 操作
 *
 * @author yangzz
 */
@IocBean(name = "mapProvinceService")
public class MapProvinceService {

    @Inject("dao2")
    protected Dao dao2;

    public List<MapProvince> getAllProvince() {
        return dao2.query(MapProvince.class, null);
    }

//    public List<MapCity> getAllCity(){
//        return dao2.query(MapCity.class,null);
//    }
    /**
     * 获取有数据的地级市和省多边形信息
     *
     * @param provinceid
     * @return
     */
    public XzqhResultWrap<List<MapCity>, List<ProvDistArea>> getCitiesById(String provinceid) {
        List<MapCity> list = dao2.query(MapCity.class, Cnd.where("provinceid", "=", provinceid));
        List<ProvDistArea> listProvDistAreas = getProvincePolygon(provinceid);
        return new XzqhResultWrap<List<MapCity>, List<ProvDistArea>>(list, listProvDistAreas);
    }

    public List<CityDistArea> getCitiesPolggon(String cityid) {
        List<CityDistArea> list = getCityPolygon(cityid);
        return list;
    }

    /**
     * 获取省界多变形
     *
     * @param provinceid
     * @return
     */
    private List<ProvDistArea> getProvincePolygon(String provinceid) {
        double pid = Double.parseDouble(provinceid);
        String str = "select name,distid,st_astext(geom) as geom from prov_dist_area where distid=" + pid;
        Sql sql = Sqls.create(str);
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

    private List<CityDistArea> getCityPolygon(String cityid) {
       List<CityDistArea> list=null;
       double id=Double.parseDouble(cityid);
       String sqlstr="select name,distid,st_astext(geom) as geom from city_dist_area where distid="+id;
       Sql sql=Sqls.create(sqlstr);
       sql.setCallback(new SqlCallback() {
           @Override
           public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
               List<CityDistArea> list=new ArrayList<CityDistArea>();
               while (rs.next()) {
                   CityDistArea tDistArea= new CityDistArea();
                   tDistArea.setName(rs.getString("name"));
                   tDistArea.setDistid(rs.getDouble("distid"));
                   tDistArea.setTheGeom(rs.getString("geom"));
                   list.add(tDistArea);
                   
               }
               return list;
           }
       });
       dao2.execute(sql);
       return sql.getList(CityDistArea.class);
    }
}
