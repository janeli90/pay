/**
 * ===================================================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ===================================================================================
 */
package com.janeli.pay.pay.vos;

import java.io.Serializable;
import java.util.Map;

public class ApiResponse<T> implements Serializable{
    private String apiCode;
    private String apiMessage;
    private Map<String,Object> rspData;
    private T rspVo;

    public static ApiResponse generator(String apiCode, String apiMessage)
    {
        ApiResponse rsp = new ApiResponse();
        rsp.apiCode = apiCode;
        rsp.apiMessage = apiMessage;
        return rsp;
    }

    public ApiResponse initData(Map<String,Object> rspData)
    {
        this.rspData = rspData;
        return this;
    }

    public ApiResponse initData(T rspData) {
        this.rspVo = rspData;
        return this;
    }

    public String getApiCode() {
        return apiCode;
    }

    public String getApiMessage() {
        return apiMessage;
    }

    public Map<String, Object> getRspData() {
        return rspData;
    }

    public T getRspVo() {
        return rspVo;
    }
}
