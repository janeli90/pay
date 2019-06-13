package com.janeli.pay.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xiao
 * Date: 2018-07-30
 * Time: 16:05
 */
public class HttpsClientUtil {



    public static <T,X> T httpPost(String url,X req ,Class<T> clazz) throws Exception{
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("location",url);
        CloseableHttpClient client = new SSLClient();
        String respContent = null;
        String reqStr = JSON.toJSONString(req);
        System.out.println("康付支付请求报文：{}"+reqStr);
        StringEntity entity = new StringEntity(reqStr,"utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        HttpResponse resp = client.execute(httpPost);
        if(resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he,"UTF-8");
        }
        System.out.println("康付支付响应报文：{}"+respContent);
        T res = JSON.parseObject(respContent,clazz);
        return res;
    }
}
