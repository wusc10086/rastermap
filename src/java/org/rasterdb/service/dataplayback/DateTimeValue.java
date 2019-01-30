/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.service.dataplayback;

/**
 *
 * @author hongxin
 */
public class DateTimeValue {
    
    private String sendTime;
    private int value;

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DateTimeValue(String sendTime, int value) {
        this.sendTime = sendTime;
        this.value = value;
    }
    public DateTimeValue(){
        
    }

    @Override
    public String toString() {
        return "DateTimeValue{" + "sendTime=" + sendTime + ", value=" + value + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.sendTime != null ? this.sendTime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DateTimeValue other = (DateTimeValue) obj;
        if ((this.sendTime == null) ? (other.sendTime != null) : !this.sendTime.equals(other.sendTime)) {
            return false;
        }
        return true;
    }
    
}
