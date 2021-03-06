/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.modules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.ViewModel;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;
import org.rasterdb.domain.User;
import org.rasterdb.service.CityService;
import org.rasterdb.service.DeviceService;
import org.rasterdb.service.user.UserManagerService;
import org.rasterdb.service.user.UserQueryForm;
import org.rasterdb.utils.Errors;
import org.rasterdb.utils.HYPager;
import org.rasterdb.utils.ProjectInfo;
import org.rasterdb.utils.ProjectInfo.XCity;

/**
 *
 * @author wuyiwei
 */
@IocBean
@At("/userManager")
@Filters(@By(type=CheckSession.class, args={"user", "/login.jsp"}))
public class UserManagerModule {
    
    private static final int DEFAULT_SHOW_PAGE_COUNT = 1; // HYPager.DEFAULT_SHOW_PAGE_COUNT;
    
    private static final int DEFAULT_PAGE_SIZE = 15;
    
    @Inject()
    private UserManagerService userManagerService;
    
    @Inject
    public DeviceService deviceService;
    
    @Inject
    protected CityService cityService;
    
    @Inject
    private ProjectInfo projectInfo;
    
    @At("/listInit")
    @Ok("re")
    public String listInit(ViewModel model) {
        UserQueryForm queryForm = null;
        
        HYPager<UserQueryForm> pager = new HYPager<UserQueryForm>();
        pager.setShowPageCount(DEFAULT_SHOW_PAGE_COUNT);
        pager.setPageSize(DEFAULT_PAGE_SIZE);
        pager.setQueryForm(queryForm);
        
        List<User> userList = userManagerService.queryWithPager(pager);
        model.put("userList", userList);
        model.put("pager", pager);
        model.put("pagerString", HYPager.encode(pager));
        
        //allDevices(model);
        cities(model);
        setCityName(userList, model);
        
        return "jsp:/user/user-list.jsp";
    }
    
    @At("/searchInit")
    @Ok("re")
    public String searchInit(@Param("..") UserQueryForm queryForm, ViewModel model) {
        HYPager<UserQueryForm> pager = new HYPager<UserQueryForm>();
        pager.setShowPageCount(DEFAULT_SHOW_PAGE_COUNT);
        pager.setPageSize(DEFAULT_PAGE_SIZE);
        pager.setQueryForm(queryForm);
        
        List<User> userList = userManagerService.queryWithPager(pager);
        model.put("userList", userList);
        model.put("pager", pager);
        model.put("pagerString", HYPager.encode(pager));
        
        //allDevices(model);
        cities(model);
        setCityName(userList, model);
        
        return "jsp:/user/user-list.jsp";
    }
    
    @At("/doList")
    @Ok("re")
    public String doList(@Param("pager") String pagerString,
            @Param("pageNo") String pageNo, ViewModel model) {
        HYPager pager = HYPager.decode(pagerString);
        if (pager == null){
            pager = new HYPager<UserQueryForm>();
            pager.setShowPageCount(DEFAULT_SHOW_PAGE_COUNT);
            pager.setPageSize(DEFAULT_PAGE_SIZE);
        }
        
        int pageNoInt = NumberUtils.toInt(pageNo, -1);
        if (pageNoInt != -1) {
            pager.setPageNumber((pageNoInt <= 1 ? 1 : pageNoInt));
        }
        
        List<User> userList = userManagerService.queryWithPager(pager);
        model.put("userList", userList);
        model.put("pager", pager);
        model.put("pagerString", HYPager.encode(pager));
        
        //allDevices(model);
        cities(model);
        setCityName(userList, model);
        
        return "jsp:/user/user-list.jsp";
    }
    
    @At("/addInit")
    @Ok("re")
    public String addInit(@Param("pager") String pagerString, ViewModel model) {
        User user = new User();
        user.setRoles(User.USER_ROLE_COMM_USER);
        user.setStatus(User.STATUS_NORMAL);
        model.put("user", user);
        model.put("pagerString", pagerString);
        
        //allDevices(model);
        cities(model);
        
        return "jsp:/user/user-add.jsp";
    }
    
    @At("/addSave")
    @Ok("re")
    public String addSave(@Param("..") User user, @Param("pager") String pagerString, 
            ViewModel model) {
        Errors errors = validate(user);
        
        if (errors != null && !errors.isEmpty()) {
            model.put("errors", errors);
            model.put("user", user);
            model.put("pagerString", pagerString);
            
            //allDevices(model);
            cities(model);
            
           return "jsp:/user/user-add.jsp";
        }
        
        boolean exist = userManagerService.existSameUsername(user);
        if (exist) {
            errors.put("username", "已经存在相同的用户登录名了，请输入其它的用户登录名。");
            model.put("errors", errors);
            model.put("user", user);
            model.put("pagerString", pagerString);
            
            //allDevices(model);
            cities(model);
            
           return "jsp:/user/user-add.jsp";
        }
        
        userManagerService.addUser(user);
        return ">>:/userManager/doList?pager="+(pagerString == null ? "" : pagerString);
    }
    
    @At("/editInit")
    @Ok("re")
    public String editInit(@Param("..") User user, @Param("pager") String pagerString, ViewModel model) {
        User dbUser = userManagerService.getUser(user);
        model.put("user", dbUser);
        model.put("pagerString", pagerString);
        
        //allDevices(model);
        cities(model);
        
        return "jsp:/user/user-edit.jsp";
    }
    
    @At("/editSave")
    @Ok("re")
    public String editSave(@Param("..") User user, @Param("pager") String pagerString, 
            ViewModel model) {
        user.setPasswd("xx");
        Errors errors = validate(user);
        
        if (errors != null && !errors.isEmpty()) {
            model.put("errors", errors);
            model.put("user", user);
            model.put("pagerString", pagerString);
            
            //allDevices(model);
            cities(model);
            
           return "jsp:/user/user-edit.jsp";
        }
        
        boolean exist = userManagerService.existSameUsername(user);
        if (exist) {
            errors.put("username", "已经存在相同的用户登录名了，请输入其它的用户登录名。");
            model.put("errors", errors);
            model.put("user", user);
            model.put("pagerString", pagerString);
            
            //allDevices(model);
            cities(model);
            
           return "jsp:/user/user-edit.jsp";
        }
        
        userManagerService.updateUser(user);
        return ">>:/userManager/doList?pager="+(pagerString == null ? "" : pagerString);
    }
    
    @At("/resetPasswdInit")
    @Ok("re")
    public String resetPasswdInit(@Param("..") User user, 
            @Param("pager") String pagerString, ViewModel model) {
        User dbUser = userManagerService.getUser(user);
        model.put("user", dbUser);
        model.put("pagerString", pagerString);
        
        return "jsp:/user/reset-passwd.jsp";
    }
    
    @At("/resetPasswdSave")
    @Ok("re")
    public String resetPasswdSave(@Param("..") User user, @Param("newPasswd") String newPasswd, 
            @Param("pager") String pagerString, ViewModel model) {
        Errors errors = new Errors();
        if (StringUtils.isBlank(newPasswd)) {
            errors.put("newPasswd", "您不能将用户登录密码设为空密码，请重新输入登录密码。");
        }
        
        if (!errors.isEmpty()) {
            model.put("errors", errors);
            model.put("user", user);
            model.put("pagerString", pagerString);
            
           return "jsp:/user/reset-passwd.jsp";
        }
        
        userManagerService.updatePasswd(user, newPasswd);
        return ">>:/userManager/doList?pager="+(pagerString == null ? "" : pagerString);
    }
    
    @At("/delete")
    @Ok("re")
    public String deleteUser(@Param("..") User user, 
            @Param("pager") String pagerString, ViewModel model) {
        userManagerService.delete(user);
        return ">>:/userManager/doList?pager="+(pagerString == null ? "" : pagerString);
    }
    
    /*
    private void allDevices(ViewModel model) {
        //List<Map> devices = deviceService.getAllDevice();
        List<EFDevice> devices = userManagerService.allDevices();
        model.put("devices", devices);
    }
*/
    
    /**
     * 获得地市，并添加到页面模型里。
     * @param model 
     */
    private void cities(ViewModel model) {
        List<XCity> cities = projectInfo.cities();
        model.put("cities", cities);
    }

    /**
     * 对用户列表的每一个user对象设置其cityname属性值。
     * @param userList
     * @param model 
     */
    private void setCityName(List<User> userList, ViewModel model) {
        if (userList == null || userList.isEmpty()) {
            return;
        }
        
        List<XCity> cities = (List<XCity>)model.get("cities");
        if (cities == null) {
            return;
        }
        
        Map<String, XCity> cityMap = new HashMap<String, XCity>();
        for (XCity city : cities) {
            cityMap.put(city.getCityid(), city);
        }
        
        for (User user : userList) {
            String cityid = user.getCityid();
            XCity city = cityMap.get(cityid);
            user.setCityName( city == null ? null : city.getName());
        }
    }
    
    /**
     * 验证用户提交的对象
     * @param user
     * @return 
     */
    private Errors validate(User user) {
        Errors errors = new Errors();
        String userRealName = user.getUserRealName();
        if (StringUtils.isBlank(userRealName)) {
            errors.put("userRealName", "用户名不能为空，请输入用户名。");
        }
        String username = user.getUsername();
        if (StringUtils.isBlank(username)) {
            errors.put("username", "用户登录名不能为空，请输入登录名。");
        }
        String passwd = user.getPasswd();
        if (StringUtils.isBlank(passwd)) {
            errors.put("passwd", "用户登录密码不能为空，请输入登录密码。");
        }
        String roles = user.getRoles();
        if (!User.USER_ROLE_ADMIN.equals(roles) 
                && !User.USER_ROLE_COMM_USER.equals(roles)) {
            errors.put("roles", "请选择用户角色。");
        }
        String cityid = user.getCityid();
        if (StringUtils.isBlank(cityid)) {
            errors.put("cityid", "请选择用户所属地市。");
        }
        int status = user.getStatus();
        if (status != 0 && status != 1) {
            errors.put("status", "请选择用户状态。");
        }
        return errors;
    }
}
