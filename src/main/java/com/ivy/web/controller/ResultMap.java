package com.ivy.web.controller;

import java.io.Serializable;

/**
 * ResultMap
 * 页面返回的结果数据封装对象
 *
 * @Author: ivy
 * @CreateTime: 2021-07-01
 */
public class ResultMap implements Serializable {
    //对应操作返回的消息
    private String message;
    //对应操作返回的结果是否成功
    private Boolean flag;
    //对应操作返回的具体数据
    private Object data;
    //对应操作返回的状态码
    private Integer code;

    public ResultMap() {
    }

    public ResultMap(String failMsg) {
        this.message = failMsg;
        this.data = null;
        this.code = Code.FAIL;
        this.flag = false;
    }

    public ResultMap(String message, Object data) {
        this.message = message;
        this.data = data;
        this.code = Code.SUCCESS;
        this.flag = true;
    }

    public ResultMap(String message, Boolean flag, Object data, Integer code) {
        this.message = message;
        this.flag = flag;
        this.data = data;
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResultMap{" +
                "message='" + message + '\'' +
                ", flag=" + flag +
                ", data=" + data +
                ", code=" + code +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}
