package com.janeli.pay.config;

/**
 * Created by Administrator on 2018/4/23 0023.
 */
public class ResultMessage<E> {

    // 当success不能满足需要时使用code
    private int code = 0;

    private String resCode;

    private boolean success = false;
    private String message;
    // 返回数据可以是任意对象
    private E data;

    public ResultMessage() {
    }

    public ResultMessage(int code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public ResultMessage(int code, String resCode, boolean success, String message) {
        this.code = code;
        this.resCode = resCode;
        this.success = success;
        this.message = message;
    }

    public ResultMessage(int code, String resCode, boolean success, String message, E data) {
        this.code = code;
        this.resCode = resCode;
        this.success = success;
        this.message = message;
        this.data = data;
    }
// 快速创建消息的方法。

    public static ResultMessage newSuccess() {
        return new ResultMessage(1, true, "操作成功！");
    }

    public static ResultMessage newSuccess(String message) {
        if (message == null) {
            message = "操作成功！";
        }
        return new ResultMessage(1, true, message);
    }

    public static ResultMessage newFailure() {
        return new ResultMessage(0, false, "操作失败！");
    }

    public static ResultMessage newFailure(String message) {
        if (message == null) {
            message = "操作失败！";
        }
        return new ResultMessage(0, false, message);
    }

    public ResultMessage toSuccess() {
        this.code = 1;
        this.success = true;
        this.message = "操作成功！";
        return this;
    }

    public ResultMessage toFailure() {
        this.code = 0;
        this.success = false;
        this.message = "操作失败！";
        return this;
    }

    public int getCode() {
        return code;
    }

    public ResultMessage setCode(int code) {
        this.code = code;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResultMessage setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public E getData() {
        return data;
    }

    public ResultMessage setData(E data) {
        this.data = data;
        return this;
    }
}
