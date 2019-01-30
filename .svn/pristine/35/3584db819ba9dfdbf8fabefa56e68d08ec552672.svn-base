/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service.sysparam;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.rasterdb.utils.HYPager;

/**
 *
 * @author wuyiwei
 */
@IocBean( name = "sysParamService")
public class SysParamService {
    
    static final Logger LOG = LogManager.getLogger(SysParamService.class);
    
    private static final String COLUMNS = "param_name, param_value, param_category, short_desc, long_desc, ctime, mtime";
    
    private static final String ALL_COLUMNS = "id, " + COLUMNS;
    
    private static final String SQL_QUERY_ALL = "select " + ALL_COLUMNS + " from sys_param";
    
    /**
     * 缓存更新的时间间隔
     */
    private static final long REFRESH_INTERTVAL = 3600 * 1000;
    
    private static long lastUpdateTime = 0;
    
    private static Map<String, SysParam> allSysParams = new ConcurrentHashMap<String, SysParam>();
    
    @Inject("dao2")
    protected Dao dao2;
    
    /**
     * 获得所有配置的系统参数。key为参数名。
     * @return 
     */
    public Map<String, SysParam> allSysParams() {
        checkForUpdate();
        return Collections.unmodifiableMap(allSysParams);
    }
    
    public SysParam getByParamName(String paramName) {
        checkForUpdate();
        return allSysParams.get(paramName);
    }
    
    private void checkForUpdate() {
        long now = System.currentTimeMillis();
        if ((now - lastUpdateTime) > REFRESH_INTERTVAL) {
            lastUpdateTime = now;
            Map<String, SysParam> dbData = allSysParamsFromDB();
            allSysParams.clear();
            allSysParams.putAll(dbData);
        }
    } 
    
    /**
     * 获得所有配置的系统参数。key为参数名。
     * @return 
     */
    private Map<String, SysParam> allSysParamsFromDB() {
        String sql = SQL_QUERY_ALL;
        
        Sql sqls = Sqls.create(sql);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<SysParam> allSysParamsList = new ArrayList<SysParam>();
                
                while (rs.next()) {
                    SysParam p = new SysParam();
                    p.setId(rs.getInt("id"));
                    p.setParamName(rs.getString("param_name"));
                    p.setParamValue(rs.getString("param_value"));
                    p.setParamCategory(rs.getString("param_category"));
                    p.setShortDesc(rs.getString("short_desc"));
                    p.setLongDesc(rs.getString("long_desc"));
                    p.setCtime(rs.getTimestamp("ctime"));
                    p.setMtime(rs.getTimestamp("mtime"));
                    allSysParamsList.add(p);
                }
                
                return allSysParamsList;
            }
        });
        
        List<SysParam> allSysParamsList = dao2.execute(sqls).getObject(List.class);
        
        Map<String, SysParam> allSysParams = new HashMap<String, SysParam>();
        for (SysParam p : allSysParamsList) {
            allSysParams.put(p.getParamName(), p);
        }
        return allSysParams;
    }
    
    private void refresh() {
        lastUpdateTime = 0;
    }
    
    
    // ====================== 供前台调用 =====================//
    
    /**
     * 根据查询条件，分页查询系统参数数据
     * @param queryForm
     * @param pager
     * @return 
     */
    public List<SysParam> queryWithPager(HYPager<SysParamQueryForm> pager) {
        SysParamQueryForm queryForm = pager.getQueryForm();
        
        int totalCount = pager.getTotalCount();
        if (totalCount < 0) {
            Cnd cnd = makeCondition(queryForm);
            //totalCount = dao2.func("T_USER", "count", "1", cnd);
            totalCount = dao2.count(SysParam.class, cnd);
            pager.setTotalCount(totalCount);
        }
        
        if (totalCount <= 0) {
            return null;
        }
        
        Cnd cnd = makeCondition(queryForm);
        if (cnd != null) {
            cnd.orderBy("id", "asc");
        } else {
            cnd = Cnd.NEW();
            cnd.getOrderBy().asc("id");
        }
        
        return dao2.query(SysParam.class, cnd, pager);
    }
    
    
    private Cnd makeCondition(SysParamQueryForm queryForm) {
        if (queryForm == null) return null;
        
        Cnd cnd = null;
        
        String paramCategory = queryForm.getParamCategory();
        if (StringUtils.isNotBlank(paramCategory)) {
            if (cnd == null) {
                cnd = Cnd.where("paramCategory", "like", "%" + paramCategory + "%");
            } else {
                 SqlExpressionGroup group = Cnd.exps("paramCategory", "like", "%" + paramCategory + "%");
                 cnd = cnd.and(group);
            }
        }
        
        return cnd;
    }
    
    
     /**
     * 添加SysParam对象到数据库
     * @param sysParam 
     */
    public SysParam addSysParam(SysParam sysParam) {
        sysParam.setCtime(new Timestamp(System.currentTimeMillis()));
        sysParam.setMtime(new Timestamp(System.currentTimeMillis()));
        SysParam newSysParam = dao2.insert(sysParam);
        
        refresh();
        return newSysParam;
    }
    
    /**
     * 根据传入SysParam对象信息，获得SysParam对象。
     * @param sysParam
     * @return 
     */
    public SysParam getSysParam(SysParam sysParam) {
        if (sysParam == null || sysParam.getId() == null) {
            return null;
        }
        
        SysParam dbSysParam = dao2.fetch(SysParam.class, sysParam.getId());
        return dbSysParam;
    }
    
    /**
     * 添加SysParam对象到数据库
     * @param sysParam 
     */
    public boolean updateSysParam(SysParam sysParam) {
        SysParam dbSysParam = getSysParam(sysParam);
        if (dbSysParam == null) {
            return false;
        }
        
        dbSysParam.setParamName(sysParam.getParamName());
        dbSysParam.setParamValue(sysParam.getParamValue());
        dbSysParam.setParamCategory(sysParam.getParamCategory());
        dbSysParam.setShortDesc(sysParam.getShortDesc());
        dbSysParam.setLongDesc(sysParam.getLongDesc());
        dbSysParam.setMtime(new Timestamp(System.currentTimeMillis()));
        dao2.update(dbSysParam);
        
        refresh();
        return true;
    }
    
    public void delete(SysParam sysParam) {
        dao2.delete(sysParam);
        refresh();
    }

    public boolean existSameParamName(SysParam sysParam) {
        Integer id = sysParam.getId();
        String paramName = sysParam.getParamName();
        Cnd cnd = Cnd.where("paramName", "=", paramName);
        if (id != null) {
            cnd.and("id", "!=", id);
        }
        int count = dao2.count(SysParam.class, cnd);
        return count >= 1;
    }
    
}
