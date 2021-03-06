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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

/**
 *当天趋势数据
 * @author admin
 */
@IocBean(fields = {"dao"},name = "eFDataTodayService")
public class EFDataTodayService  extends IdEntityService{
    
    @Inject("dao2")
    protected Dao dao2;
    
    /**
     * 获取制定区间的最大时间数据
     * @param startTime
     * @param endTime
     * @return 
     */
    public List<Map<String,String>> getTrandData(String startTime,String endTime)
    {
     String condition="select t3.Latitude,t3.Longitude, t1.deviceid,t1.average,t1.sendtime from efdevice t3,efdata_today t1,(select deviceid,max(SendTime)  as max from efdata_today where SendTime>'"+startTime+"' and SendTime<'"+endTime+"' group by deviceid ) t2 where t1.SendTime=t2.max and t1.deviceid=t2.deviceid and t3.Num=t2.deviceid";
//String condition="select deviceid,max(SendTime)  as max from efdata_today where SendTime>'2017-08-14 15:16:00' and SendTime<'2017-08-14 15:20:00' group by deviceid";

        Sql sqls = Sqls.create(condition);

        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Map<String,String>> listmap = new ArrayList<Map<String,String>>();
                 rs.last();//移到最后一行
                 int count = rs.getRow();
                 rs.beforeFirst();//移到初始位置
                 System.out.println(count);
                while (rs.next()) {
                    Map<String,String> map = new HashMap<String,String>();
                   // map.put("Num", rs.getString("Num"));
                    map.put("average", rs.getString("average"));
                    map.put("Latitude", rs.getString("Latitude"));
                    map.put("Longitude", rs.getString("Longitude"));
                    map.put("time",rs.getString("sendtime"));
                    listmap.add(map);
                }
                return listmap;
            }
        });

        List<Map<String,String>> listmap1 = dao2.execute(sqls).getObject(List.class);
        return listmap1;
    }
}
