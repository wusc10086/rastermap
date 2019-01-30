package org.rasterdb.service.wrap;

/**
 *  查询结果包装 
 * @author gis 
 */
public class ResultWrap<T> extends MessageWrap{
 
    public T data;

    public ResultWrap(int code, Object message) {
        super(code, message);
    }

    public ResultWrap(T data, int code, Object message) {
        super(code, message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
