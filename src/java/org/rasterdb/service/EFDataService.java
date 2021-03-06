/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.cri.SqlExpression;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.rasterdb.domain.Count;
import org.rasterdb.domain.EFData;
import org.rasterdb.domain.EFDataWarning;
import org.rasterdb.domain.EFDevice;
import org.rasterdb.service.dataplayback.DataExporter;
import org.rasterdb.service.dataplayback.HisDataQueryForm;
import org.rasterdb.service.device.DeviceManagerService;
import org.rasterdb.utils.HYPager;
import org.rasterdb.utils.Utils;

/**
 *
 * @author zhang
 */
@IocBean(name = "efDataService")
public class EFDataService {

    //查询电场强度
    private static final String SQL_BASE_QUERY_EFDATA
            = "SELECT * FROM efdata  WHERE SendTime >= @startTime AND SendTime <= @endTime  AND DeviceId IN ( $devicesIds )";

    private static final String SQL_BASE_QUERY_EFDATA_COUNT
            = "SELECT count(1) FROM efdata  WHERE SendTime >= @startTime AND SendTime <= @endTime  AND DeviceId IN ( $devicesIds )";
    //
    private static final String SQL_EFDATA_COUNT
            = "SELECT count(1),deviceid FROM efdata  WHERE SendTime >= @startTime AND SendTime <= @endTime  AND DeviceId IN ( $devicesIds ) group by deviceid";
    //查询预警表
    private static final String SQL_BASE_QUERY_WARNING
            = "SELECT * FROM efdata_warning  WHERE SendTime >= @startTime AND SendTime <= @endTime  AND DeviceId IN ( $devicesIds )";

    private static final String SQL_BASE_QUERY_WARNING_COUNT
            = "SELECT count(1) FROM efdata_warning  WHERE SendTime >= @startTime AND SendTime <= @endTime  AND DeviceId IN ( $devicesIds )";
    @Inject("dao2")
    protected Dao dao2;

    @Inject("deviceManagerService")
    protected DeviceManagerService deviceManagerService;

    public List<EFData> getEFDataByNum(String num) {
        Date day = new Date();
        Long yesDate = day.getTime() - 60 * 60000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endtime = sdf.format(day);
        String startime = sdf.format(yesDate);
        Timestamp endTime = Utils.toTimestamp(endtime);
        Timestamp starTime = Utils.toTimestamp(startime);

        Cnd cnd = Cnd.where("sendTime", ">=", starTime).and("sendTime", "<=", endTime).and("deviceid", "=", num);
        cnd.orderBy("sendTime", "ASC");
        return dao2.query(EFData.class, cnd);
    }

    /**
     * 获取下一分数据
     *
     * @param num
     * @return
     */
    public List<EFData> getEFDatasByNumNexold(String num) {
        Date day = new Date();
        Long yesDate = day.getTime() - 2 * 60000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endtime = sdf.format(day);
        String startime = sdf.format(yesDate);
        Timestamp endTime = Utils.toTimestamp(endtime);
        Timestamp starTime = Utils.toTimestamp(startime);
        Cnd cnd = Cnd.where("sendTime", ">=", starTime).and("sendTime", "<=", endTime).and("deviceid", "=", num);
        cnd.orderBy("sendTime", "ASC");
        List<EFData> result = dao2.query(EFData.class, cnd);
        return result;
    }

    public List<EFData> getEFDatasByNumNext(String num) {
        List<EFData> list;
        Date day = new Date();
        Long yesDate = day.getTime() - 4 * 60000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endtime = sdf.format(day);
        String startime = sdf.format(yesDate);
        Timestamp endTime = Utils.toTimestamp(endtime);
        Timestamp starTime = Utils.toTimestamp(startime);
        String conditionString = "select * from efdata where sendTime >'" + starTime + "' and sendTime < '" + endtime + "' and deviceid='" + num + "' order by sendTime asc limit 0,1";
        System.out.println(conditionString);
        Sql sqls = Sqls.create(conditionString);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
               
                List<EFData> eFDatas = new ArrayList<EFData>();

                while (rs.next()) {
                     EFData eFData = new EFData();
                    eFData.setSendTime(rs.getTimestamp("sendTime"));
                    eFData.setAverage(rs.getString("average"));
                    System.out.println("电场："+rs.getString("average"));
                    eFDatas.add(eFData);
                }
                return eFDatas;
            }
        });

        List<EFData> listmap = dao2.execute(sqls).getObject(List.class);
        return listmap;
    }

    /**
     * 获取图表的数据
     *
     * @param startTime
     * @param endTime
     * @param devices
     * @return hightCharts。
     */
    public List<EFData> getEFDatas(HisDataQueryForm queryForm) {
        if (queryForm.getDeviceIds() == null || queryForm.getDeviceIds().isEmpty()) {
            return null;
        }
        //Cnd cnd = Cnd.where("sendTime", ">=", queryForm.getStartTime()).and("sendTime", "<=", queryForm.getEndTime()).and("deviceid", "in", queryForm.getDeviceIds());
        //cnd.orderBy("sendTime", "ASC");
        String conditionString = SQL_BASE_QUERY_EFDATA + " ORDER BY SendTime ASC  ";
        Sql sqls = Sqls.createf(conditionString);
        sqls.setParam("startTime", queryForm.toStartTime());
        sqls.setParam("endTime", queryForm.toEndTime());
        sqls.setVar("devicesIds", queryForm.devicesIds());
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<EFData> allEFDatas = new ArrayList<EFData>();

                while (rs.next()) {
                    EFData efdata = new EFData();
                    efdata.setAverage(rs.getString("average"));
                    efdata.setId(rs.getInt("id"));
                    efdata.setDeviceid(rs.getString("deviceId"));
                    efdata.setAverage(rs.getString("average"));
                    efdata.setSendTime(rs.getTimestamp("sendTime"));
                    efdata.setStatus(rs.getString("status"));
                    allEFDatas.add(efdata);
                }
                return allEFDatas;
            }
        });
        List<EFData> list = dao2.execute(sqls).getObject(List.class);
        return list;
    }

    /*数据统计分析
    * higchar 到报率
     */
    public List<Count> getEFDatasCount(HisDataQueryForm queryForm) {
        if (queryForm.getDeviceIds() == null || queryForm.getDeviceIds().isEmpty()) {
            return null;
        }
       
        // 查询总数
        String countString = SQL_EFDATA_COUNT;
        Sql sqls = Sqls.createf(countString);
        sqls.setParam("startTime", queryForm.toStartTime());
        sqls.setParam("endTime", queryForm.toEndTime());
        sqls.setVar("devicesIds", queryForm.devicesIds());
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Count> list = new ArrayList<Count>();
            
                while (rs.next()) {
                    Count countline=new Count();
                    countline.setCount(rs.getInt(1));
                    countline.setDeviceid(rs.getString("deviceid"));
                    list.add(countline);
                }
                return list;
            }
        });
        List<Count> list = dao2.execute(sqls).getObject(List.class);
        
           
        return list;
    }

    /**
     * 分页查询获得电场强度的数据列表
     *
     * @param pager
     * @return
     */
    public List<EFData> getEFDatasWithPager(HYPager<HisDataQueryForm> pager) {
        HisDataQueryForm queryForm = pager.getQueryForm();
        if (queryForm.getDeviceIds() == null || queryForm.getDeviceIds().isEmpty()) {
            return null;
        }

        // 查询总数
        int totalCount = pager.getTotalCount();
        if (totalCount < 0) {
            String sqlStr = SQL_BASE_QUERY_EFDATA_COUNT;
            Sql sqls = Sqls.createf(sqlStr);
            sqls.setParam("startTime", queryForm.toStartTime());
            sqls.setParam("endTime", queryForm.toEndTime());
            sqls.setVar("devicesIds", queryForm.devicesIds());
            sqls.setCallback(new SqlCallback() {
                @Override
                public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                    List<Integer> list = new ArrayList<Integer>();

                    while (rs.next()) {
                        list.add(rs.getInt(1));

                    }
                    return list;
                }
            });
            List<Integer> list = dao2.execute(sqls).getObject(List.class);

            totalCount = list.get(0);
            pager.setTotalCount(totalCount);
        }

        final Map<String, String> allDevices = allDevices();

        // 查询本页的数据
        int pageSize = pager.getPageSize();
        int startIndex = pager.getOffset();

        //String sqlStr = SQL_BASE_QUERY_EFDATA + " ORDER BY SendTime ASC LIMIT @pageSize OFFSET @startIndex ";
        String sqlStr = SQL_BASE_QUERY_EFDATA + " ORDER BY SendTime ASC LIMIT @startIndex, @pageSize  ";
        Sql sqls = Sqls.createf(sqlStr);
        sqls.setParam("startTime", queryForm.toStartTime());
        sqls.setParam("endTime", queryForm.toEndTime());
        sqls.setVar("devicesIds", queryForm.devicesIds());
        sqls.setParam("pageSize", pageSize);
        sqls.setParam("startIndex", startIndex);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<EFData> allEFDatas = new ArrayList<EFData>();

                while (rs.next()) {
                    EFData efdata = new EFData();

                    String deviceId = rs.getString("deviceId");
                    String deviceName = allDevices.get(deviceId);

                    efdata.setId(rs.getInt("id"));
                    efdata.setDeviceid(deviceId);
                    efdata.setDeviceName(deviceName);
                    efdata.setSendTime(rs.getTimestamp("sendTime"));
                    efdata.setSecondData(rs.getString("secondData"));
                    efdata.setLowValue(rs.getString("lowvalue"));
                    efdata.setHighValue(rs.getString("highvalue"));
                    efdata.setAverage(rs.getString("average"));
                    efdata.setCommunication(rs.getString("communication"));
                    efdata.setStatus(rs.getString("status"));
                    efdata.setTemperature(rs.getString("temperature"));
                    efdata.setVoltage(rs.getString("voltage"));
                    efdata.setRotate(rs.getString("rotate"));
                    efdata.setFrequency(rs.getString("frequency"));
                    allEFDatas.add(efdata);
                }
                return allEFDatas;
            }
        });
        List<EFData> list = dao2.execute(sqls).getObject(List.class);
        return list;
    }

    /**
     * 根据查询条件，获得符合条件的数据，用于输出数据到文件
     *
     * @param pager
     * @return
     */
    public void getEFDatasForExport(HisDataQueryForm queryForm, final File tmpDir,
            final DataExporter exporter) {
        if (queryForm.getDeviceIds() == null || queryForm.getDeviceIds().isEmpty()) {
            return;
        }

        final Map<String, String> allDevices = allDevices();
        int maxSize = (int) (5.5 * 1000 * 1000); // 最多输出5百万的数据
        //String sqlStr = SQL_BASE_QUERY_EFDATA + " ORDER BY SendTime ASC LIMIT @maxSize OFFSET 0 ";
        if (queryForm.getDataType().equals("1")) {
            String sqlStr = SQL_BASE_QUERY_EFDATA + " ORDER BY SendTime ASC LIMIT 0, @maxSize ";
            Sql sqls = Sqls.createf(sqlStr);
            sqls.setParam("startTime", queryForm.toStartTime());
            sqls.setParam("endTime", queryForm.toEndTime());
            sqls.setVar("devicesIds", queryForm.devicesIds());
            sqls.setParam("maxSize", maxSize);
            sqls.setCallback(new SqlCallback() {
                @Override
                public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                    while (rs.next()) {
                        DataExporter.EFData efdata = new DataExporter.EFData();

                        String deviceId = rs.getString("deviceId");
                        String deviceName = allDevices.get(deviceId);

                        efdata.setDeviceId(deviceId);
                        efdata.setDeviceName(deviceName);
                        efdata.setSendTime(rs.getTimestamp("sendTime"));
                        efdata.setSecondData(rs.getString("secondData"));
                        efdata.setLowValue(rs.getString("lowvalue"));
                        efdata.setHighValue(rs.getString("highvalue"));
                        efdata.setAverage(rs.getString("average"));
                        efdata.setCommunication(rs.getString("communication"));
                        efdata.setStatus(rs.getString("status"));
                        efdata.setTemperature(rs.getString("temperature"));
                        efdata.setVoltage(rs.getString("voltage"));
                        efdata.setRotate(rs.getString("rotate"));
                        efdata.setFrequency(rs.getString("frequency"));

                        exporter.export(tmpDir, efdata);
                    }
                    return null;
                }
            });
            dao2.execute(sqls).getResult();
        } else {
            String sqlStr = SQL_BASE_QUERY_WARNING + " ORDER BY SendTime ASC LIMIT 0, @maxSize ";
            Sql sqls = Sqls.createf(sqlStr);
            sqls.setParam("startTime", queryForm.toStartTime());
            sqls.setParam("endTime", queryForm.toEndTime());
            sqls.setVar("devicesIds", queryForm.devicesIds());
            sqls.setParam("maxSize", maxSize);
            sqls.setCallback(new SqlCallback() {
                @Override
                public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                    while (rs.next()) {
                        DataExporter.EFDataWarn warning = new DataExporter.EFDataWarn();

                        String deviceId = rs.getString("deviceId");
                        String deviceName = allDevices.get(deviceId);

                        warning.setDeviceid(deviceId);
                        warning.setDeviceName(deviceName);
                        warning.setSendTime(rs.getTimestamp("sendTime"));
                        warning.setAlertArede(rs.getString("alertArede"));

                        exporter.exportwarning(tmpDir, warning);
                    }
                    return null;
                }
            });
            dao2.execute(sqls).getResult();
        }

    }

    /**
     * 获取list列表数据 预警信息
     *
     * @param startTime
     * @param endTime
     * @param devices
     * @return list
     */
    public List<EFDataWarning> getListData(HYPager<HisDataQueryForm> pager) {
        HisDataQueryForm queryForm = pager.getQueryForm();
        if (queryForm.getDeviceIds() == null || queryForm.getDeviceIds().isEmpty()) {
            return null;
        }
//        Cnd cnd = Cnd.where("sendTime", ">=", queryForm.getStartTime()).
//                and("sendTime", "<=", queryForm.getEndTime()).and("deviceid", "in", queryForm.devicesIds());
//        cnd.orderBy("sendTime", "ASC");
//        return dao2.query(EFDataWarning.class, cnd);
        // 查询总数
        int totalCount = pager.getTotalCount();
        if (totalCount < 0) {
            String sqlStr = SQL_BASE_QUERY_WARNING_COUNT;
            Sql sqls = Sqls.createf(sqlStr);
            sqls.setParam("startTime", queryForm.toStartTime());
            sqls.setParam("endTime", queryForm.toEndTime());
            sqls.setVar("devicesIds", queryForm.devicesIds());
            sqls.setCallback(new SqlCallback() {
                @Override
                public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                    List<Integer> list = new ArrayList<Integer>();

                    while (rs.next()) {
                        list.add(rs.getInt(1));

                    }
                    return list;
                }
            });
            List<Integer> list = dao2.execute(sqls).getObject(List.class);

            totalCount = list.get(0);
            pager.setTotalCount(totalCount);
        }

        final Map<String, String> allDevices = allDevices();

        // 查询本页的数据
        int pageSize = pager.getPageSize();
        int startIndex = pager.getOffset();

        //String sqlStr = SQL_BASE_QUERY_EFDATA + " ORDER BY SendTime ASC LIMIT @pageSize OFFSET @startIndex ";
        String sqlStr = SQL_BASE_QUERY_WARNING + " ORDER BY SendTime ASC LIMIT @startIndex, @pageSize  ";
        Sql sqls = Sqls.createf(sqlStr);
        sqls.setParam("startTime", queryForm.toStartTime());
        sqls.setParam("endTime", queryForm.toEndTime());
        sqls.setVar("devicesIds", queryForm.devicesIds());
        sqls.setParam("pageSize", pageSize);
        sqls.setParam("startIndex", startIndex);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<EFDataWarning> allEFDatas = new ArrayList<EFDataWarning>();

                while (rs.next()) {
                    EFDataWarning warning = new EFDataWarning();

                    String deviceId = rs.getString("deviceId");
                    String deviceName = allDevices.get(deviceId);

                    warning.setId(rs.getInt("id"));
                    warning.setDeviceid(deviceId);
                    warning.setDeviceName(deviceName);
                    warning.setSendTime(rs.getTimestamp("sendTime"));
                    warning.setAlertArede(rs.getString("alertArede"));
                    allEFDatas.add(warning);
                }
                return allEFDatas;
            }
        });
        List<EFDataWarning> list = dao2.execute(sqls).getObject(List.class);
        return list;
    }

    /**
     * 获得某个时间段内，相关设备的EFData数据。
     *
     * @param startTime
     * @param endTime
     * @param devices
     * @return 如果传入的设备列表为空，则返回空。
     */
    public List<EFData> getEFDatas(Timestamp startTime, Timestamp endTime, List<EFDevice> devices) {
        if (devices == null || devices.isEmpty()) {
            return null;
        }

        Cnd cnd = Cnd.where("sendTime", ">=", startTime).and("sendTime", "<=", endTime);
        String inClause = inClause(devices);
        SqlExpression exp = Cnd.exp("deviceid", "in", inClause);
        cnd.and(exp);

        cnd.orderBy("deviceid", "ASC");
        cnd.orderBy("sendTime", "DESC");

        return dao2.query(EFData.class, cnd);
    }

    private String inClause(List<EFDevice> devices) {
        if (devices == null || devices.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (EFDevice d : devices) {
            sb.append("'").append(d.getNum()).append("'");
            sb.append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 获得相关设备的EFData数据。
     *
     * @param devices
     * @return 如果传入的设备列表为空，则返回空。
     */
    /*
    public List<EFDevice> queryDevice() {
        String conditionString = "select t1.Num,t1.DeviceName from efdevice t1  order by t1.Num ";
        Sql sqls = Sqls.create(conditionString);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<EFDevice> allDevices = new ArrayList<EFDevice>();

                while (rs.next()) {
                    EFDevice device = new EFDevice();
                    device.setNum(rs.getString("Num"));
                    device.setDeviceName(rs.getString("DeviceName"));
                    allDevices.add(device);
                }
                return allDevices;
            }
        });

        List<EFDevice> list = dao2.execute(sqls).getObject(List.class);
        return list;
    }
     */
    /**
     * 获得所有的站。key为num, value为站名。
     *
     * @return
     */
    public Map<String, String> allDevices() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        List<EFDevice> allDevices = deviceManagerService.allDevices();
        for (EFDevice d : allDevices) {
            map.put(d.getNum(), d.getDeviceName());
        }
        return map;
    }

    /**
     * highchars 预警信息
     *
     * @return
     */
    public List<EFDataWarning> getEFDatasWarning(HisDataQueryForm queryForm) {

        if (queryForm.getDeviceIds() == null || queryForm.getDeviceIds().isEmpty()) {
            return null;
        }
        //Cnd cnd = Cnd.where("sendTime", ">=", queryForm.getStartTime()).and("sendTime", "<=", queryForm.getEndTime()).and("deviceid", "in", queryForm.getDeviceIds());
        //cnd.orderBy("sendTime", "ASC");
        String conditionString = SQL_BASE_QUERY_WARNING + " ORDER BY SendTime ASC  ";
        Sql sqls = Sqls.createf(conditionString);
        sqls.setParam("startTime", queryForm.toStartTime());
        sqls.setParam("endTime", queryForm.toEndTime());
        sqls.setVar("devicesIds", queryForm.devicesIds());
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<EFDataWarning> allEFDatas = new ArrayList<EFDataWarning>();

                while (rs.next()) {
                    EFDataWarning efdataWaring = new EFDataWarning();
                    efdataWaring.setId(rs.getInt("id"));
                    efdataWaring.setDeviceid(rs.getString("deviceId"));
                    efdataWaring.setAlertArede(rs.getString("alertArede"));
                    efdataWaring.setSendTime(rs.getTimestamp("sendTime"));
                    allEFDatas.add(efdataWaring);
                }
                return allEFDatas;
            }
        });
        List<EFDataWarning> list = dao2.execute(sqls).getObject(List.class);
        return list;
    }

}
