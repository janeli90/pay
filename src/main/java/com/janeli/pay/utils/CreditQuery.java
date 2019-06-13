package com.janeli.pay.utils;

import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;

public class CreditQuery {
    private static final String REQ_URL = "http://ca.vbillbank.com/app";
    private static final String SYS = "YQu94YB3pILyljF3C5B";
    private static final String DES_KEY = "5da189be8b4c4c38a76f84f4f25c7593";
    private static final String QRY_CARDS = "/public/api4j/bankApiService/queryCards?sys=%s&params=%s";
    private static final String QRY_BALANCE = "/public/api4h5/bankApiService/queryBalance?sys=%s&params=%s";

    public static void main(String[] args) throws Exception {
        qryBanlance("", "", "");
        //qryCards();
    }

    public static void qryCards() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("sourceUserId", "30100000"); //源系统用户标识
        String json = JSONObject.toJSONString(params);
        System.out.println(json);
        String param = DESUtils.encrypt(json, DES_KEY);
        String reqUrl = String.format(REQ_URL + QRY_CARDS, SYS, param);
        System.out.println(reqUrl);
//	   String result = HttpUtil.get(reqUrl);
//	   System.out.println(result);

    }

    public static String qryBanlance(String usrTel, String cardNo, String relName) {
        Map<String, String> params = new HashMap<>();
        params.put("sourceUserId", "30100000"); //源系统用户标识
        params.put("mobile", usrTel); //用户手机号
        params.put("cardNo", cardNo); //用户银行卡号
        params.put("realName", relName); //用户真实姓名
        String json = JSONObject.toJSONString(params);
        System.out.println(json);
        String param = null;
        try {
            param = DESUtils.encrypt(json, DES_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        String reqUrl = String.format(REQ_URL + QRY_BALANCE, SYS, param);
        System.out.println(reqUrl);
        return reqUrl;


    }

}
