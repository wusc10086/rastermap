/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;
import org.rasterdb.domain.EFData;
import org.rasterdb.domain.EFDevice;
import org.rasterdb.utils.Utils;

/**
 *
 * @author admin 设备服务层
 */
@IocBean(fields = {"dao"}, name = "deviceService")
public class DeviceService extends IdEntityService {

    @Inject("dao2")
    protected Dao dao2;

    /**
     * 获取所有设备数据
     *
     * @return
     */
//    public List<Map> getAllDevice() {
//        String conditionString = "select t1.Num,t1.DeviceName,t1.Latitude,t4.Status,t1.Longitude,t1.Elevation,t1.Manufaceture,t1.InstallTime,t1.LastInspect,t1.LastRepair,t2.AlertArede,t2.SecData from efdevice t1\n"
//                + "left join efdata_warning  t2 on t2.deviceid=t1.Num \n"
//                + "left join (select s1.deviceid,s1.Status from efdata s1 , (select deviceid,max(SendTime)  as MaxDate from efdata group by deviceid) s2 where s1.SendTime=s2.MaxDate and s1.deviceid=s2.deviceid) t4 on t4.deviceid=t1.Num";
//        System.out.println(conditionString);
//        Sql sqls = Sqls.create(conditionString);
//        sqls.setCallback(new SqlCallback() {
//            @Override
//            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
//                List<Map> listmap = new ArrayList<Map>();
//                while (rs.next()) {
//                    Map map = new HashMap();
//                    map.put("Num", rs.getString("Num"));
//                    map.put("DeviceName", rs.getString("DeviceName"));
//                    map.put("Latitude", rs.getString("Latitude"));
//                    map.put("Status", rs.getString("Status"));
//                    map.put("Longitude", rs.getString("Longitude"));
//                    map.put("Elevation", rs.getString("Elevation"));
//                    map.put("Manufaceture", rs.getString("Manufaceture"));
//                    map.put("InstallTime", rs.getString("InstallTime"));
//                    map.put("LastInspect", rs.getString("LastInspect"));
//                    map.put("LastRepair", rs.getString("LastRepair"));
//                    map.put("AlertArede", rs.getString("AlertArede"));
//                    map.put("SecData", rs.getString("SecData"));
//                    listmap.add(map);
//                }
//                return listmap;
//            }
//        });
//
//        List<Map> listmap = this.dao().execute(sqls).getObject(List.class);
//        return listmap;
//    }
   public List<EFDevice> getAllDeviceStatue() {
       Date day=new Date(); 
       Long yesDate=day.getTime()-60 * 60000;
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endtime=sdf.format(day);
        String startime=sdf.format(yesDate);
        Timestamp endTime=Utils.toTimestamp(endtime);
        Timestamp starTime=Utils.toTimestamp(startime);
//        String conditionString = "select t1.Num,t1.DeviceName,t1.Latitude,t4.Status,t1.Longitude,t1.Elevation,t1.Manufaceture,t1.InstallTime,t1.LastInspect,t1.LastRepair,t2.AlertArede,t2.SecData from efdevice t1\n"
//                + "left join efdata_warning  t2 on t2.deviceid=t1.Num \n"
//                + "left join (select s1.deviceid,s1.Status from efdata s1 , "
//                + "(select deviceid,max(SendTime)  as MaxDate from efdata group by deviceid) s2 "
//                + "where s1.SendTime=s2.MaxDate and s1.deviceid=s2.deviceid) t4 on t4.deviceid=t1.Num";
        return dao2.query(EFDevice.class, null);
       
    }
    /**
     * 获取设备各个状态统计结果
     *
     * @return
     */
    public Map getDeviceStatus() {
        String conditionString = "select t1.Num,t1.DeviceName,t1.Latitude,t4.Status,t1.Longitude,t1.Elevation,t1.Manufaceture,t1.InstallTime,t1.LastInspect,t1.LastRepair,t2.AlertArede,t2.SecData from efdevice t1\n"
                + "left join efdata_warning  t2 on t2.deviceid=t1.Num \n"
                + "left join (select s1.deviceid,s1.Status from efdata s1 , (select deviceid,max(SendTime)  as MaxDate from efdata group by deviceid) s2 where s1.SendTime=s2.MaxDate and s1.deviceid=s2.deviceid) t4 on t4.deviceid=t1.num";
        Sql sqls = Sqls.create(conditionString);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Map> listmap = new ArrayList<Map>();

                while (rs.next()) {
                    Map map = new HashMap();
                    map.put("Num", rs.getString("Num"));
                    map.put("DeviceName", rs.getString("DeviceName"));
                    map.put("Latitude", rs.getString("Latitude"));
                    map.put("Status", rs.getString("Status"));
                    map.put("Longitude", rs.getString("Longitude"));
                    map.put("Elevation", rs.getString("Elevation"));
                    map.put("Manufaceture", rs.getString("Manufaceture"));
                    map.put("InstallTime", rs.getString("InstallTime"));
                    map.put("LastInspect", rs.getString("LastInspect"));
                    map.put("LastRepair", rs.getString("LastRepair"));
                    map.put("AlertArede", rs.getString("AlertArede"));
                    map.put("SecData", rs.getString("SecData"));
                    listmap.add(map);
                }
                return listmap;
            }
        });

        List<Map> listmap = this.dao().execute(sqls).getObject(List.class);
        Map<String, String> result = new HashMap<String, String>();
        Integer z = 0, w = 0, y = 0, yy = 0;
        for (int i = 0; i < listmap.size(); i++) {
            Map map = listmap.get(i);
            if ("2".equals(map.get("Status"))) {
                y++;//异常
            } else if ("0".equals(map.get("Status"))) {
                w++;//无数据
            } else if ("1".equals(map.get("Status")) && (!"".equals(map.get("AlertArede")))) {
                yy++;//无数据
            } else {
                z++;//正常
            }
        }
        result.put("Z", z.toString());
        result.put("W", w.toString());
        result.put("Y", y.toString());
        result.put("YY", y.toString());
        return result;
    }

    /**
     * 获取各个电场站名
     *
     * @return
     */
    public List<Map> getAddress() {
        String conditionString = "select Num ,DeviceName from efdevice";
        Sql sqls = Sqls.create(conditionString);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Map> listmap = new ArrayList<Map>();

                while (rs.next()) {
                    Map map = new HashMap();
                    map.put("Num", rs.getString("Num"));
                    map.put("DeviceName", rs.getString("DeviceName"));
                    listmap.add(map);
                }
                return listmap;
            }
        });

        List<Map> listmap = this.dao().execute(sqls).getObject(List.class);
        return listmap;
    }
}
