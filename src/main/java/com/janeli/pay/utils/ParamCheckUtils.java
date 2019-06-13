package com.janeli.pay.utils;

import com.janeli.pay.exception.BusinessException;

/**
 * @author :xiaok
 * @date :2018/4/14 0014
 */
public class ParamCheckUtils {
    /**
     * 参数检测
     * @param args
     * @param <T>
     */
    public static <T> void checkParams(T ... args) throws BusinessException{
        for (T t:args) {
            if (t == null){
                throw new BusinessException("2","请求参数未传！");
            }
        }
    }
}
