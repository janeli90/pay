package com.janeli.pay.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.config.RequestConfig;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gaogang
 * @ClassName: Common
 * @Description: 公共类
 * @date 2016年7月12日 下午4:03:46
 */
public class Common {
 
	public static final int PAGE_SIZE_DEFAULT = 10;//每页条数
	public static final int DECIMAL_ROUNDING = BigDecimal.ROUND_HALF_UP;  //平台四舍五入的方式
    public static final String RSP_CODE = "resCode";//返回码
    public static final String RSP_MSG = "resMsg";//返回消息
    public final static String DES_KEY = "zxhk123321";//DES加密字符串
    public static final String RSP_CODE_VALUE = "0000";//返回码
    public static final String SUCCESS_CODE = "SUCCESS";//返回码
    public static final String FAIL_CODE = "FAIL";//返回码



    /**
     * @param message
     * @throws
     * @Title: successJsonString
     * @Description: TODO(操作成功返回结果)
     * @return:Map 返回类型
     */
    public static String successJsonString(String message) {
        Map result = new HashMap();
        result.put(RSP_CODE, SUCCESS_CODE);
        result.put(RSP_MSG, message);
        return JSONArray.toJSON(result).toString();
    }

    public static Map<String, Object> successMap(String message) {
        Map<String, Object> result = new HashMap();
        result.put(RSP_CODE, SUCCESS_CODE);
        result.put(RSP_MSG, message);
        return result;
    }

    public static Map<String, Object> returnMap(String message, String code) {
        Map<String, Object> result = new HashMap();
        result.put(RSP_CODE, code);
        result.put(RSP_MSG, message);
        return result;
    }

    /**
     * @param message
     * @throws
     * @Title: successPutMap
     * @Description: TODO(操作成功返回结果)
     * @return:Map 返回类型
     */
    public static void successPutMap(JSONObject map, String message) {
        map.put(RSP_CODE, SUCCESS_CODE);
        map.put(RSP_MSG, message);
    }
    public static void successPutMap(Map<String, Object> map, String message) {
        map.put(RSP_CODE, SUCCESS_CODE);
        map.put(RSP_MSG, message);
    }
    public static void errPutMap(Map<String, Object> map, String message) {
        map.put(RSP_CODE,FAIL_CODE );
        map.put(RSP_MSG, message);
    }
    public static void main(String[] args) {
		BigDecimal  rebAmt = new BigDecimal("111.00").multiply(new BigDecimal("0.05").divide(new BigDecimal("100"), 5, Common.DECIMAL_ROUNDING)).setScale(2,
				Common.DECIMAL_ROUNDING);
		System.out.println(rebAmt);
        Map result = new HashMap();
        result.put(RSP_CODE, SUCCESS_CODE);
        result.put(RSP_MSG, "test1");
        System.out.println(JSONArray.toJSON(result));
        System.out.println(JSONObject.toJSON(result));
        String json = JSONObject.toJSON(result).toString();
        JSONObject test1 = JSONObject.parseObject(json);
        System.out.println(test1.getString(RSP_CODE));
        JSONObject test2 = JSONObject.parseObject(json);
        System.out.println(test2.getString(RSP_MSG));

        String relAuthFlag = "0";
        JSONObject outPut = new JSONObject();
        outPut.put("relAuthFlag", relAuthFlag);
        Common.successPutMap(outPut, "验证成功!");
        System.out.println(outPut);
    }

    /**
     * @param message
     * @throws
     * @Title: errorJsonString
     * @Description: TODO(操作失败返回结果)
     * @return:Map 返回类型
     */
    public static String errorJsonString(String message) {
        Map result = new HashMap();
        result.put(RSP_CODE, FAIL_CODE);
        result.put(RSP_MSG, message);
        return JSONArray.toJSON(result).toString();
    }

    /**
     * @param message
     * @throws
     * @Title: errorMap
     * @Description: TODO(操作失败返回结果)
     * @return:Map 返回类型
     */
    public static Map<String, Object> errorMap(String message) {
        Map<String, Object> result = new HashMap();
        result.put(RSP_CODE, FAIL_CODE);
        result.put(RSP_MSG, message);
        return result;
    }

    /**
     * @param message
     * @throws
     * @Title: errorMap
     * @Description: TODO(操作失败返回结果)
     * @return:Map 返回类型
     */
    public static Map<String, Object> errorMap(String message, String resCode) {
        Map<String, Object> result = new HashMap();
        result.put(RSP_CODE, FAIL_CODE);
        result.put(RSP_MSG, message);
        return result;
    }

    /**
     * @param message
     * @throws
     * @Title: errorJsonString
     * @Description: TODO(操作失败返回结果)
     * @return:Map 返回类型
     */
    public static String errorJsonString(String message, String resCode) {
        Map result = new HashMap();
        result.put(RSP_CODE, resCode);
        result.put(RSP_MSG, message);
        return JSONArray.toJSON(result).toString();
    }

    /**
     * http4.3设置超时时间   4.3版本不设置超时的话，一旦服务器没有响应，等待时间N久(>24小时)
     */
    public static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(45000)
            .setConnectTimeout(30000)
            .setConnectionRequestTimeout(60000)
            .build();

    public static RequestConfig sendUnderReqCfg = RequestConfig.custom()
            .setSocketTimeout(50000)
            .setConnectTimeout(50000)
            .setConnectionRequestTimeout(50000)
            .build();
}
