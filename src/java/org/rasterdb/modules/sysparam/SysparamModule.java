/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.modules.sysparam;

import java.util.List;
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
import org.rasterdb.service.sysparam.SysParam;
import org.rasterdb.service.sysparam.SysParamQueryForm;
import org.rasterdb.service.sysparam.SysParamService;
import org.rasterdb.service.user.UserQueryForm;
import org.rasterdb.utils.Errors;
import org.rasterdb.utils.HYPager;
import org.rasterdb.utils.ProjectInfo;

/**
 *
 * @author wuyiwei
 */
@IocBean
@At("/sysparam")
@Filters(@By(type=CheckSession.class, args={"user", "/login.jsp"}))
public class SysparamModule {
    
    private static final int DEFAULT_SHOW_PAGE_COUNT = 1; // HYPager.DEFAULT_SHOW_PAGE_COUNT;
    
    private static final int DEFAULT_PAGE_SIZE = 15;
   
    
    @Inject
    protected SysParamService sysParamService;
    
    @Inject
    private ProjectInfo projectInfo;
    
    @At("/listInit")
    @Ok("re")
    public String listInit(ViewModel model) {
        SysParamQueryForm queryForm = null;
        
        HYPager<SysParamQueryForm> pager = new HYPager<SysParamQueryForm>();
        pager.setShowPageCount(DEFAULT_SHOW_PAGE_COUNT);
        pager.setPageSize(DEFAULT_PAGE_SIZE);
        pager.setQueryForm(queryForm);
        
        List<SysParam> sysparamList = sysParamService.queryWithPager(pager);
        model.put("sysparamList", sysparamList);
        model.put("pager", pager);
        model.put("pagerString", HYPager.encode(pager));
        
        return "jsp:/sysparam/sysparam-list.jsp";
    }
    
    @At("/searchInit")
    @Ok("re")
    public String searchInit(@Param("..") SysParamQueryForm queryForm, ViewModel model) {
        HYPager<SysParamQueryForm> pager = new HYPager<SysParamQueryForm>();
        pager.setShowPageCount(DEFAULT_SHOW_PAGE_COUNT);
        pager.setPageSize(DEFAULT_PAGE_SIZE);
        pager.setQueryForm(queryForm);
        
        List<SysParam> sysparamList = sysParamService.queryWithPager(pager);
        model.put("sysparamList", sysparamList);
        model.put("pager", pager);
        model.put("pagerString", HYPager.encode(pager));
        
        return "jsp:/sysparam/sysparam-list.jsp";
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
        
        List<SysParam> sysparamList = sysParamService.queryWithPager(pager);
        model.put("sysparamList", sysparamList);
        model.put("pager", pager);
        model.put("pagerString", HYPager.encode(pager));
        
        return "jsp:/sysparam/sysparam-list.jsp";
    }
    
    @At("/addInit")
    @Ok("re")
    public String addInit(@Param("pager") String pagerString, ViewModel model) {
        SysParam sysParam = new SysParam();
        model.put("sysParam", sysParam);
        model.put("pagerString", pagerString);
        
        return "jsp:/sysparam/sysparam-add.jsp";
    }
    
    @At("/addSave")
    @Ok("re")
    public String addSave(@Param("..") SysParam sysParam, @Param("pager") String pagerString, 
            ViewModel model) {
        Errors errors = validate(sysParam);
        
        if (errors != null && !errors.isEmpty()) {
            model.put("errors", errors);
            model.put("sysParam", sysParam);
            model.put("pagerString", pagerString);
            
           return "jsp:/sysparam/sysparam-add.jsp";
        }
        
        boolean exist = sysParamService.existSameParamName(sysParam);
        if (exist) {
            errors.put("paramName", "已经存在相同的参数名称，请输入其它的参数名称。");
            model.put("errors", errors);
            model.put("sysParam", sysParam);
            model.put("pagerString", pagerString);
            
           return "jsp:/sysparam/sysparam-add.jsp";
        }
        
        sysParamService.addSysParam(sysParam);
        return ">>:/sysparam/doList?pager="+(pagerString == null ? "" : pagerString);
    }
    
    @At("/editInit")
    @Ok("re")
    public String editInit(@Param("..") SysParam sysParam, @Param("pager") String pagerString, ViewModel model) {
        SysParam dbSysParam = sysParamService.getSysParam(sysParam);
        model.put("sysParam", dbSysParam);
        model.put("pagerString", pagerString);
        
        return "jsp:/sysparam/sysparam-edit.jsp";
    }
    
    @At("/editSave")
    @Ok("re")
    public String editSave(@Param("..") SysParam sysParam, @Param("pager") String pagerString, 
            ViewModel model) {
        Errors errors = validate(sysParam);
        
        if (errors != null && !errors.isEmpty()) {
            model.put("errors", errors);
            model.put("sysParam", sysParam);
            model.put("pagerString", pagerString);
            
           return "jsp:/sysparam/sysparam-edit.jsp";
        }
        
        boolean exist = sysParamService.existSameParamName(sysParam);
        if (exist) {
            errors.put("paramName", "已经存在相同的参数名称，请输入其它的参数名称。");
            model.put("errors", errors);
            model.put("sysParam", sysParam);
            model.put("pagerString", pagerString);
            
           return "jsp:/user/user-edit.jsp";
        }
        
        sysParamService.updateSysParam(sysParam);
        return ">>:/sysparam/doList?pager="+(pagerString == null ? "" : pagerString);
    }
    
    
    @At("/delete")
    @Ok("re")
    public String deleteSysParam(@Param("..") SysParam sysParam, 
            @Param("pager") String pagerString, ViewModel model) {
        //sysParamService.delete(sysParam);
        return ">>:/sysparam/doList?pager="+(pagerString == null ? "" : pagerString);
    }
  
    
    /**
     * 验证用户提交的对象
     * @param user
     * @return 
     */
    private Errors validate(SysParam sysParam) {
        Errors errors = new Errors();
        String paramName = sysParam.getParamName();
        if (StringUtils.isBlank(paramName)) {
            errors.put("userRealName", "参数名不能为空，请输入参数名。");
        }
        
        return errors;
    }
}
