/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author wuyiwei
 */
public class Utils {
    
    
    public static Timestamp toTimestamp(String s) {
        if (StringUtils.isBlank(s)) {
            return null;
            
        }
        
        Timestamp t = null;
        
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = df.parse(s);
            t = new Timestamp(date.getTime());
        } catch (ParseException ex) {
            
        }
        
        if (t == null) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = df.parse(s);
                t = new Timestamp(date.getTime());
            } catch (ParseException ex) {

            }
        }
        
        return t;
    }
    
    /**
     * n分钟前的起始分钟
     * @param now
     * @param nMin
     * @return 
     */
    public static Timestamp PreviousNMin(long now, int nMin) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.MINUTE, -nMin);
        return new Timestamp(calendar.getTimeInMillis());
    }
    
    
    public static Integer toInteger(String s) {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        try {
            return Integer.parseInt(s, 10);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Float toEfdataValue(String efValue) {
        Integer curvalueInt = Utils.toInteger(efValue);
        return curvalueInt == null ? null : curvalueInt / 100.0F;
    }
    
}
