package com.jdch.blog3.model;

/**
 * description:控制层返回结果
 *
 * @author Zoisitesky
 * @date 2020-11-01 22:06
 */
public class ResultModel<T> {
    //状态码: 0 代表成功  1 代表失败
    private int code;

    //返回的数据
    private T t;

    //返回的信息
    private String message;

    public ResultModel() {
    }

    public ResultModel(int code, T t, String message) {
        this.code = code;
        this.t = t;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
