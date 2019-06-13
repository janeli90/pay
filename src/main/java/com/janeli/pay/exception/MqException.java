package com.janeli.pay.exception;

/**
 * Created with IntelliJ IDEA.
 * Description: 队列异常类
 * User: xiao
 * Date: 2018-07-29
 * Time: 08:51
 */
public class MqException extends RuntimeException{

    private Integer code;

    private String msg;

    public MqException() {
    }

    public MqException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
