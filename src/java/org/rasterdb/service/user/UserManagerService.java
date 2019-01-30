/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.rasterdb.domain.EFDevice;
import org.rasterdb.domain.User;
import org.rasterdb.utils.HYPager;

/**
 *
 * @author wuyiwei
 */
@IocBean( name = "userManagerService")
public class UserManagerService {
    
    @Inject("dao2")
    protected Dao dao2;
    
    /**
     * 根据查询条件，分页查询用户数据
     * @param queryForm
     * @param pager
     * @return 
     */
    public List<User> queryWithPager(HYPager<UserQueryForm> pager) {
        UserQueryForm queryForm = pager.getQueryForm();
        
        int totalCount = pager.getTotalCount();
        if (totalCount < 0) {
            Cnd cnd = makeCondition(queryForm);
            //totalCount = dao2.func("T_USER", "count", "1", cnd);
            totalCount = dao2.count(User.class, cnd);
            pager.setTotalCount(totalCount);
        }
        
        if (totalCount <= 0) {
            return null;
        }
        
        Cnd cnd = makeCondition(queryForm);
        if (cnd != null) {
            cnd.orderBy("cityid", "asc");
            cnd.orderBy("userRealName", "asc");
        } else {
            cnd = Cnd.NEW();
            cnd.getOrderBy().asc("userRealName");
        }
        
        return dao2.query(User.class, cnd, pager);
    }
    
    
    private Cnd makeCondition(UserQueryForm queryForm) {
        if (queryForm == null) return null;
        
        Cnd cnd = null;
        String cityid = queryForm.getCityid();
        if (StringUtils.isNotBlank(cityid) && !"0".equals(cityid)) {
            cnd = Cnd.where("cityid", "=", cityid);
        }
        String userRealName = queryForm.getUserRealName();
        if (StringUtils.isNotBlank(userRealName)) {
            if (cnd == null) {
                cnd = Cnd.where("userRealName", "like", "%" + userRealName + "%").or("username", "like", "%" + userRealName + "%");
            } else {
                 SqlExpressionGroup group = Cnd.exps("userRealName", "like", "%" + userRealName + "%").or("username", "like", "%" + userRealName + "%");
                 cnd = cnd.and(group);
            }
        }
        
        return cnd;
    }
    
    /**
     * 获得所有的设备
     * @return 
     */
    /*
    public List<EFDevice> allDevices() {
        String conditionString="select t1.\"Num\",t1.\"DeviceName\" from efdevice t1 order by t1.\"DeviceName\"" ;
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
     * 添加User对象到数据库
     * @param user 
     */
    public User addUser(User user) {
        return dao2.insert(user);
    }
    
    /**
     * 根据传入user对象信息，获得User对象。
     * @param user
     * @return 
     */
    public User getUser(User user) {
        if (user == null || user.getId() == null) {
            return null;
        }
        
        User dbUser = dao2.fetch(User.class, user.getId());
        if (!dbUser.getUsername().equals(user.getUsername())) {
            return null;
        }
        return dbUser;
    }
    
    /**
     * 添加User对象到数据库
     * @param user 
     */
    public boolean updateUser(User user) {
        User dbUser = getUser(user);
        if (dbUser == null) {
            return false;
        }
        
        dbUser.setUserRealName(user.getUserRealName());
        dbUser.setRoles(user.getRoles());
        dbUser.setCityid(user.getCityid());
        dbUser.setStatus(user.getStatus());
        dao2.update(dbUser);
        return true;
    }
    
    /**
     * 重置用户密码
     * @param user
     * @param newPasswd
     * @return 
     */
    public boolean updatePasswd(User user, String newPasswd) {
        User dbUser = getUser(user);
        if (dbUser == null) {
            return false;
        }
        
        dbUser.setPasswd(newPasswd);
        dao2.update(dbUser, "^passwd$");
        return true;
    }
    
    public void delete(User user) {
        dao2.delete(user);
    }

    public boolean existSameUsername(User user) {
        Integer id = user.getId();
        String username = user.getUsername();
        Cnd cnd = Cnd.where("username", "=", username);
        if (id != null) {
            cnd.and("id", "!=", id);
        }
        int count = dao2.count(User.class, cnd);
        return count >= 1;
    }
}
