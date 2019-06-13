package com.janeli.pay.mq.model;

import com.janeli.pay.mq.utils.ResCode;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xiao
 * Date: 2018-07-27
 * Time: 16:53
 */
public class RePayDTO {


    private ResCode code;

    private String msg;

    public RePayDTO() {
    }

    public RePayDTO(ResCode code) {
        this.code = code;
    }

    public RePayDTO(ResCode code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResCode getCode() {
        return code;
    }

    public void setCode(ResCode code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}