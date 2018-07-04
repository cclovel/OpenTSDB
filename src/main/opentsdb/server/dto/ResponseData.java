package com.ygsoft.opentsdb.server.dto;

/**
 * 使用ResponDate作为统一json响应格式
 * Created by dingshuo on 2017/4/13.
 */
public class ResponseData<T> {
    private boolean success;
    private T data;
    private String msg;

    public ResponseData() {
        this.success = false;
    }

    public ResponseData(String msg) {
        this.success = false;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
