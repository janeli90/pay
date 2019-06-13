package com.janeli.pay.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.janeli.pay.common.Common;
import net.sf.json.JSONObject;

/**
 * Created by lid on 2018/12/4 0004.
 * 聚合数据短信发送工具类
 */
public class JuhePhoneMessageUtil {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    //public static final String KEY = "584341d8c8107f23c7b4f754e2be240b";

    public static final String KEY = "8f484258d894eec6badc718b69a1b08d";// TODO: 2019/4/17 调整
    public static Map<String, Object> mobileQuery(String mobile, String tplId, String code) {
        String result = null;
        String url = "http://v.juhe.cn/sms/send";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("mobile", mobile);
        params.put("tpl_id", tplId);
        params.put("tpl_value", "#code#=" + code);
        params.put("key", KEY);
        try {
            result = net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                return Common.returnMap("请求短信验证成功", "0");
            } else {
                LoggerUtils.logInfo(mobile+"请求短信验证失败！{"+object.get("reason")+"}");
                return Common.returnMap("请求短信验证失败", "01");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.returnMap("请求短信验证失败", "01");
    }

    public static Map<String, Object> sendPlanFailMsg(String mobile, String tplId, String planNO, String reason) {
        String result = null;
        String url = "http://v.juhe.cn/sms/send";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("mobile", mobile);
        params.put("tpl_id", tplId);
        params.put("tpl_value", "#planNO#=" + planNO + "&#reason#=" + reason);
        params.put("key", KEY);
        try {
            result = net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                return Common.returnMap("请求短信验证成功", "0");
            } else {
                LoggerUtils.logInfo(mobile+"请求短信验证失败！{"+object.get("reason")+"}");
                return Common.returnMap("请求短信验证失败", "01");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.returnMap("请求短信验证失败", "01");
    }

    public static void main(String[] args) {
//        JuhePhoneMessageUtil.mobileQuery("17375808066", "119303", "123456");
        //{"sid":"60604b2ced8a4578a83ec4605cf3bb45","fee":1,"count":1}  成功返回
        JuhePhoneMessageUtil.sendPlanFailMsg("17375808066", "119306", "123456", "测试");
    }

    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
