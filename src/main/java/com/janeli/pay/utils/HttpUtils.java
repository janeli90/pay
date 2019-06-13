/**
 * @Title: HttpUtils.java
 * @Package com.webside.online.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author tianlei
 * @date 2016年10月25日 上午10:54:56
 * @version V1.0
 */
package com.janeli.pay.utils;

import com.janeli.pay.common.Common;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**@ClassName: HttpUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author tianlei
 * @date 2016年10月25日 上午10:54:56 
 */
public class HttpUtils {
    public static final int CONNECTION_TIMEOUT = 15000;
    public static final int SOCKET_TIMEOUT = 60000;
    private static long startTime = 0L;
    private static long endTime = 0L;

    /**
     * 根据指定URL外发POST请求，入参为字符串
     *
     * @param keyValStr
     *            外发字符串
     * @param url
     *            外发地址
     * @param encoding
     *            编码
     * @return 返回字符串
     * @throws Exception
     */
    public static String sendPostMessage(String keyValStr, String url, String encoding) throws Exception {
        StringEntity strEntity = new StringEntity(keyValStr, encoding);
        return sendPostRequest(strEntity, url, encoding);
    }

    /**
     * 外发POST请求
     *
     * @param httpEntity
     *            外发对象
     * @param url
     *            外发地址
     * @param encoding
     *            编码
     * @return 返回字符串
     * @throws Exception
     */
    private static String sendPostRequest(HttpEntity httpEntity, String url, String encoding) throws Exception {
        LoggerUtils.logInfo("发送请求URL==>{}, 字符编码：{}" + url + encoding);
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = null;
        if (url.startsWith("https")) {
            // 执行 Https 请求.
            try {
                httpclient = createSSLInsecureClient();
            } catch (GeneralSecurityException e) {
                LoggerUtils.logError("createSSLInsecureClient Error ==> " + e.getMessage());
                throw e;
            }
        } else {
            httpclient = HttpClients.createDefault();
        }
        LoggerUtils.logInfo("HttpClient方法创建！");
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        LoggerUtils.logInfo("Post方法创建！");
        String resMes = null;
        try {

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CONNECTION_TIMEOUT).build();
            httppost.setConfig(requestConfig);
            httppost.setEntity(httpEntity);
            LoggerUtils.logInfo("请求URL:" + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                LoggerUtils.logInfo("StatusLine ==> {}");
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    resMes = EntityUtils.toString(entity, encoding);
                    LoggerUtils.logInfo("返回数据:" + resMes);
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            LoggerUtils.logException(e);
        } catch (UnsupportedEncodingException e) {
            LoggerUtils.logException(e);
        } catch (SocketTimeoutException e) {
            // 捕获服务器响应超时
            LoggerUtils.logException(e);
            throw e;
        } catch (IOException e) {
            LoggerUtils.logException(e);
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                LoggerUtils.logError("Close HttpClient Error==> ");
                LoggerUtils.logException(e);
            }
        }
        return resMes;
    }

    /**
     * @Title: createSSLInsecureClient
     * @Description: 创建发送https请求
     * @return
     * @throws GeneralSecurityException    设定文件
     */
    @SuppressWarnings("deprecation")
    protected static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType)
                        throws java.security.cert.CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, javax.net.ssl.SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, javax.net.ssl.SSLSocket ssl) throws IOException {
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }

                @Override
                public void verify(String host, X509Certificate cert) throws SSLException {
                }
            });
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (GeneralSecurityException e) {
            LoggerUtils.logException(e);
            throw e;
        }
    }


    public static String httpPostJson(String parameters, String url) {
        String body = null;
        int status = 0;
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = null;
        HttpPost method = new HttpPost(url);
        try {
            if (url.startsWith("https")) {
                // 执行 Https 请求.
                httpclient = createSSLInsecureClient();
            } else {
                httpclient = HttpClients.createDefault();
            }
            LoggerUtils.logInfo("HttpClient方法创建！");
            // 建立一个NameValuePair数组，用于存储欲传送的参数
            method.addHeader("Content-type", "application/json; charset=utf-8");
            method.setHeader("Accept", "application/json");
            method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
            method.setConfig(Common.requestConfig);
            startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpclient.execute(method);
            try {
                endTime = System.currentTimeMillis();
                int statusCode = response.getStatusLine().getStatusCode();
                LoggerUtils.logInfo("statusCode:" + statusCode);
                LoggerUtils.logInfo("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
                if (statusCode != HttpStatus.SC_OK) {
                    LoggerUtils.logError("Method failed:" + response.getStatusLine());
                    status = 1;
                }
                // Read the response body  
                body = EntityUtils.toString(response.getEntity());
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (Exception e) {
            LoggerUtils.logException(e);
            // 网络错误
            status = 3;
        } finally {
            // 关闭连接,释放资源
            try {
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                LoggerUtils.logError("Close HttpClient Error==> ");
                LoggerUtils.logException(e);
            }
            LoggerUtils.logInfo("调用接口状态：" + status);
        }
        return body;
    }


    public static String httpPostJson(String parameters, String url, String dir, String orderId) {
        String body = null;
        CloseableHttpClient httpclient = null;
        HttpPost method = new HttpPost(url);
        try {
            if (url.startsWith("https")) {
                // 执行 Https 请求.
                httpclient = createSSLInsecureClient();
            } else {
                httpclient = HttpClients.createDefault();
            }
            // 建立一个NameValuePair数组，用于存储欲传送的参数
            method.addHeader("Content-type", "application/json; charset=utf-8");
            method.setHeader("Accept", "application/json");
            method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
            method.setConfig(Common.requestConfig);
            CloseableHttpResponse response = httpclient.execute(method);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    LoggerUtils.logTrc("Method failed:" + response.getStatusLine(), dir, orderId);
                }
                body = EntityUtils.toString(response.getEntity());
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (Exception e) {
            LoggerUtils.logTrc("=====>发送上游返回异常:" + e.getMessage(), dir, orderId);
            LoggerUtils.logException(e);
        } finally {
            // 关闭连接,释放资源
            try {
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                LoggerUtils.logTrc("=====>发送上游返回异常,关闭连接释放资源报错:" + e.getMessage(), dir, orderId);
                LoggerUtils.logException(e);
            }
        }
        return body;
    }

    /**
     * 外发 post请求，返回map
     * @param httpUrl 地址
     * @param maps 参数
     */
    public static Map<String, String> sendHttpPost(String httpUrl, Map<String, String> maps) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost  
        // 创建参数队列  
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (String key : maps.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        } catch (Exception e) {
            LoggerUtils.logException("发送失败!", e);
        }
        return sendHttpPost(httpPost);
    }

    /**
     * 发送Post请求
     * @param httpPost
     * @return
     */
    private static Map<String, String> sendHttpPost(HttpPost httpPost) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        Map<String, String> result = new HashMap<String, String>();
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(Common.sendUnderReqCfg);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            result.put("ret_code", "0000");
            result.put("message", responseContent);
        } catch (ClientProtocolException e) {
            result.put("ret_code", "0001");
            result.put("message", e.getMessage());
        } catch (ParseException e) {
            result.put("ret_code", "0001");
            result.put("message", e.getMessage());
        } catch (IOException e) {
            result.put("ret_code", "0001");
            result.put("message", e.getMessage());
        } finally {
            try {
                //终止请求
                if (httpPost != null) {
                    httpPost.abort();
                }
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                result.put("ret_code", "0001");
                result.put("message", e.getMessage());
            }
        }
        return result;
    }
}
