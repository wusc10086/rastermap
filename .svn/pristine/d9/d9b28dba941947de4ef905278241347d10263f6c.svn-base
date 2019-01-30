/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.pager.Pager;

/**
 *
 * @author wuyiwei
 * @param <T> 查询表单的类型
 */
public class HYPager<T> extends Pager {
    
    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_SHOW_PAGE_COUNT = 5;

    /**
     * 显示的页的数量
     */
    private int showPageCount;
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 查询表单的序列化字符串
     */
    private T queryForm;

    public HYPager() {
        super();
        totalCount = -1;
        this.showPageCount = DEFAULT_SHOW_PAGE_COUNT;
    }

    public int getShowPageCount() {
        return showPageCount;
    }

    public void setShowPageCount(int showPageCount) {
        this.showPageCount = showPageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.setRecordCount(totalCount);
    }

    public T getQueryForm() {
        return queryForm;
    }

    public void setQueryForm(T queryForm) {
        this.queryForm = queryForm;
    }

    /**
     * 获得翻页控件的起始页号
     * @return 
     */
    public int getStartPageNumber() {
        int pageNumber = this.getPageNumber();
        if (pageNumber <= 1) {
            pageNumber = 1;
        }
        
        /*
        int pageCount = this.getPageCount();
        int startPageNumber = pageNumber - (showPageCount / 2);
        startPageNumber = (startPageNumber <= 1 ? 1 : startPageNumber);
        return startPageNumber;
        */
        
        return ((pageNumber - 1) / showPageCount) * showPageCount + 1;
    }

    /**
     * 翻页控件的结束页号
     * @return 
     */
    public int getEndPageNumber() {
        int pageCount = this.getPageCount();
        int endPageNumber = getStartPageNumber() + showPageCount - 1;
        endPageNumber = (endPageNumber >= pageCount ? pageCount : endPageNumber);
        return endPageNumber;
    }
    
    /**
     * 是否显示“向前”按钮
     * @return 
     */
    public boolean showPrev() {
        int pageCount = this.getPageCount();
        if (pageCount <= showPageCount) {
            return false;
        }
        
        int start = getStartPageNumber();
        return start > 1;
    }
    
    /**
     * 是否显示“往后”按钮
     * @return 
     */
    public boolean showNext() {
        int pageCount = this.getPageCount();
        int end = getEndPageNumber(); 
        return end < pageCount;
    }
    
    /**
     * 序列化HYPager对象
     * @param pager
     * @return
     */
    public static String encode(HYPager pager) {
        if (pager == null) return null;
        
        byte[] pagerData = SerializationUtils.serialize(pager);
        String base64String = Base64.getUrlEncoder().encodeToString(pagerData);
        return base64String;
    }
    
    /**
     * 反序列化HYPager对象字符串到HYPager对象
     * @param pagerString
     * @return 
     */
    public static HYPager decode(String pagerString) {
        if (StringUtils.isBlank(pagerString)) {
            return null;
        }
        byte[] data = Base64.getUrlDecoder().decode(pagerString);
        return SerializationUtils.deserialize(data);
    }

}
