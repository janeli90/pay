/**
 * ===================================================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ===================================================================================
 */
package com.janeli.pay.pay.controller;

import com.janeli.pay.pay.spring.SpringContextHolder;
import com.janeli.pay.pay.vos.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;


public class CoreController {
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String UTF_8 = "UTF-8";
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    public ApiResponse getTryCatchExceptions(Exception exp) {
        return ApiResponse.generator("999999",exp.getMessage());
    }

    public ApiResponse getDefaultApiRosponse()
    {
        return ApiResponse.generator("000000","操作成功!");
    }

    /**
     *  根据请求参数获取对应的服务实现对象
     * @param serviceName       服务名称
     * @param serviceClazz      服务接口类
     * @param version           服务版本
     * @param <T>               服务的实现对象
     * @return                  返回服务的实现对象
     */
    public <T> T getServiceBy(String serviceName,Class serviceClazz,String version)
    {
        // 这里通过自定义根据版本号获取Service服务
        T t = (T) SpringContextHolder.getBean(serviceName);
        return t;
    }

    public String invokeISOtoUTF8(String values){
        String returnStr = null;
        try {
            returnStr = new String(values.getBytes(ISO_8859_1), UTF_8);
        } catch (UnsupportedEncodingException e) {
        }
        return returnStr;
    }

    public String version()
    {
        return "V1.0.0";
    }
}
