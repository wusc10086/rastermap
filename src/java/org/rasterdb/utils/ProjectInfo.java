/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.rasterdb.domain.Area;
import org.rasterdb.domain.City;
import org.rasterdb.service.CityService;
import org.rasterdb.service.sysparam.SysParam;
import org.rasterdb.service.sysparam.SysParamService;

/**
 *
 * @author wuyiwei
 */
public class ProjectInfo {
    
    private static final Set<String> municipalityProvinceId = new HashSet<String>();
    static {
        municipalityProvinceId.add("110000"); // 北京市
        municipalityProvinceId.add("120000"); // 天津市
        municipalityProvinceId.add("310000"); // 上海市
        municipalityProvinceId.add("500000"); // 重庆市
    }
    
    /**
     * 本项目部署的省id
     */
    private String provinceId;
    /**
     * 数据导出时，临时文件的存放目录。
     */
    private String exportTmpDir;
    
    protected CityService cityService;
    
    protected SysParamService sysParamService;

    public String getProvinceId() {
        //return provinceId;
        if (provinceId == null) {
            SysParam p = sysParamService.getByParamName("geo_proviceid");
            provinceId = p == null ? null : p.getParamValue();
            if (StringUtils.isBlank(provinceId)) {
                provinceId = "110000";
            }
        }
        
        return "110000";
    }

    public void setProvinceId(String provinceId) {
        //this.provinceId = provinceId;
    }

    public CityService getCityService() {
        return cityService;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public String getExportTmpDir() {
        //return exportTmpDir;
        SysParam p = sysParamService.getByParamName("exportTmpDir");
        if (p != null) {
            return p.getParamValue();
        }
        return null;
    }

    public void setExportTmpDir(String exportTmpDir) {
        this.exportTmpDir = exportTmpDir;
    }

    public SysParamService getSysParamService() {
        return sysParamService;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }
    
    /**
     * 是否是直辖市，如果是，返回true。否则返回false。
     * @return 
     */
    public boolean isMunicipality() {
        return municipalityProvinceId.contains(getProvinceId());
    }
    
    public List<XCity> cities() {
        boolean municipality = isMunicipality();
        if (municipality) { // 直辖市
            List<City> cities = cityService.allCitiesByProId(getProvinceId());
            if (cities == null || cities.isEmpty()) {
                return null;
            }
            
            City city = cities.get(0);
            List<Area> areas = cityService.allAreasByCityid(city.getCityid());
            return XCity.convertByArea(areas);
        } else { // 非直辖市
            List<City> cities = cityService.allCitiesByProId(getProvinceId());
            return XCity.convertByCity(cities);
        }
    }
    
    public static class XCity implements Serializable{
        private String cityid;
        private String name;

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "XCity{" + "cityid=" + cityid + ", name=" + name + '}';
        }
        
        public static XCity byCity(City city) {
            XCity c = new XCity();
            c.setCityid(city.getCityid());
            c.setName(city.getName());
            return c;
        }

        public static XCity byArea(Area area) {
            XCity c = new XCity();
            c.setCityid(area.getAreaid());
            c.setName(area.getName());
            return c;
        }
        
        public static List<XCity> convertByCity(List<City> cityList) {
            if (cityList == null || cityList.isEmpty()) {
                return null;
            }
            
            List<XCity> xcityList = new ArrayList<XCity>(cityList.size());
            for (City c : cityList) {
                XCity x = byCity(c);
                xcityList.add(x);
            }
            return xcityList;
        }
        
        public static List<XCity> convertByArea(List<Area> areaList) {
            if (areaList == null || areaList.isEmpty()) {
                return null;
            }
            
            List<XCity> xcityList = new ArrayList<XCity>(areaList.size());
            for (Area a : areaList) {
                XCity x = byArea(a);
                xcityList.add(x);
            }
            return xcityList;
        }
    }
    
}
