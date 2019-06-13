package com.janeli.pay.utils;


import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class OpenApiUtils {

    /**
     * 封装请求参数
     * @param requestUrl
     * @param v API协议版本
     * @param method API接口名称
     * @param timestamp 时间戳，允许的误差毫秒数(10分钟)
     * @param format 指定响应报文格式，默认xml，目前支持：xml、json
     * @param appKey 系统分配给应用的ID
     * @param signMethod 参数的加密方法选择，可选值：md5、hmac
     * @param secret 签名密钥
     * @param bizParams 应用级输入参数
     * @return
     */
    public static String callOpenApi(String requestUrl, String v, String method, String timestamp, String format,
                                     String appKey, String signMethod, String secret, Map<String, String> bizParams) throws Exception {
        Map<String, String> signParams = new HashMap<String, String>();
        /**
         * 系统级别输入参数
         */
        signParams.put(APIConstants.API_VERSION, v);//API协议版本，可选值：1.1
        signParams.put(APIConstants.API_METHOD, method);//API接口名称
        signParams.put(APIConstants.API_TIMESTAMP, timestamp);//时间戳，格式为yyyy-mm-dd HH:mm:ss，例如：2013-08-01 09:02:05
        signParams.put(APIConstants.API_FORMAT, format);//指定响应报文格式，默认xml，目前支持：xml、json
        signParams.put(APIConstants.API_APP_KEY, appKey);//系统分配给应用的ID
        signParams.put(APIConstants.API_SIGN_METHOD, signMethod);//参数的加密方法选择，可选值：md5、hmac
        /**
         * 应用级输入参数
         */
        signParams.putAll(bizParams);
        //计算签名
        String sign = SignUtils.sign(signParams, secret, signMethod);
        if (sign == "" || sign == null){
            System.err.println("计算签名失败");
            return "";
        }
        signParams.put(APIConstants.API_SIGN, sign);//签名

        LoggerUtils.logInfo("K通道请求参数:" + signParams.get("from_card_holder_mobile")+ "-------->"+ JSON.toJSONString(signParams));
//        String result = sendPost(requestUrl, getRequestData(signParams));
        String result = HttpClientUtil.post(requestUrl, signParams);
        result = result.replaceAll("\n","").replaceAll("\r","");
        LoggerUtils.logInfo("K通道返回参数:"+ signParams.get("from_card_holder_mobile")+ "-------->"+result);
        return result;
    }

    public static String getRequestData(Map<String,String> map){
        StringBuilder retStr = new StringBuilder();
        //获取map集合中的所有键，存入到Set集合中，
        Set<Map.Entry<String,String>> entry = map.entrySet();
        //通过迭代器取出map中的键值关系，迭代器接收的泛型参数应和Set接收的一致
        Iterator<Map.Entry<String,String>> it = entry.iterator();
        while (it.hasNext())
        {
            //将键值关系取出存入Map.Entry这个映射关系集合接口中
            Map.Entry<String,String>  me = it.next();
            //使用Map.Entry中的方法获取键和值
            String key = me.getKey();
            String value = me.getValue();
            retStr.append(key + "=" + value+"&");
        }
        return retStr.toString().substring(0,retStr.length()-1);
    }

    /**
     * 向指定URL发送POST方法的请求
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuilder resultBuffer=new StringBuilder();
            String line=null;
            while ((line = in.readLine()) != null) {
                resultBuffer.append(line);
            }
            return resultBuffer.toString();
        } catch (Exception e) {
           throw new RuntimeException(e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
