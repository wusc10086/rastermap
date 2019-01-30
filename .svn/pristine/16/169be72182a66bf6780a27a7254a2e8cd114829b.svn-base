/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hongxin
 */
public class Resp {
    private String code;
    private List<String> messages = new ArrayList<String>(2);
    private Object data;

    public Resp(){
        
    }
    
    public Resp(String code, List<String> messages) {
        this.code = code;
        this.messages = messages;
    }
    
    public Resp(String code, List<String> messages, Object data) {
        this.code = code;
        this.messages = messages;
        this.data = data;
    }

    public void addMessage(String message ){
        messages.add(message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
    

   

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Resp{" + "code=" + code + ", messages=" + messages + ", data=" + data + '}';
    }
    
    
    
}
