package com.bwie.juan_mao.jingdong_kanglijuan.bean;

/**
 * Created by 卷猫~ on 2018/10/26.
 */

public class MessageBean<T> {

    private String msg;
    private String code;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}