package com.ly.toolsproject.base.http;

/**
 * Created by Administrator on 2017/9/22 0022.
 */

public class Response<T> {
    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Response() {
    }

    /**
     * code : 500
     * msg : 发送失败，请稍后再试
     * data : {"code":"15","msg":"Remote service error","sub_code":"isv.BUSINESS_LIMIT_CONTROL","sub_msg":"触发业务流控","request_id":"10fjrmdbfkvzl"}
     */

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
