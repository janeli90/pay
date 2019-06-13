package com.janeli.pay.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpClientUtilsNew {

    private RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(45000)
            .setConnectTimeout(30000)
            .setConnectionRequestTimeout(60000)
            .build();

    private static RequestConfig requestConfig2 = RequestConfig.custom().setSocketTimeout(20000)
            .setConnectTimeout(20000).setConnectionRequestTimeout(20000).build();

    private static HttpClientUtilsNew instance = null;

    private HttpClientUtilsNew() {
    }

    public static HttpClientUtilsNew getInstance() {
        if (instance == null) {
            instance = new HttpClientUtilsNew();
        }
        return instance;
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     */
    public String sendHttpPost(String httpUrl) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param params  参数(格式:key1=value1&key2=value2)
     */
    public String sendHttpPost(String httpUrl, String params) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            //设置参数
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            LoggerUtils.logException("发送失败!", e);
        }
        return sendHttpPost(httpPost);
    }


    public String sendProxyPay(String httpUrl, String params, String encode) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost

        try {
            //设置参数
            StringEntity stringEntity = new StringEntity(params, encode);
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, encode);
        } catch (Exception e) {
            LoggerUtils.logException("发送失败!", e);
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param maps    参数
     */
    public String sendHttpPost(String httpUrl, Map<String, String> maps) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        // 创建参数队列
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (String key : maps.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
        }
        return sendHttpPost(httpUrl, nameValuePairs);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl    地址
     * @param valuePairs 参数
     */
    public String sendHttpPost(String httpUrl, List<NameValuePair> valuePairs) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(valuePairs, "UTF-8"));
        } catch (Exception e) {
            LoggerUtils.logException("发送失败!", e);
        }
        return sendHttpPost(httpPost);
    }

    /**
     * 发送Post请求
     *
     * @param httpPost
     * @return
     */
    private String sendHttpPost(HttpPost httpPost) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            LoggerUtils.logException("发送失败!", e);
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
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    /**
     * 发送 get请求
     *
     * @param httpUrl
     */
    public String sendHttpGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        return sendHttpGet(httpGet);
    }

    /**
     * 发送 get请求Https
     *
     * @param httpUrl
     */
    public String sendHttpsGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        return sendHttpsGet(httpGet);
    }

    /**
     * 发送Get请求
     *
     * @param httpGet
     * @return
     */
    private String sendHttpGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            LoggerUtils.logException("发送失败!", e);
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                LoggerUtils.logException("发送失败!", e);
            }
        }
        return responseContent;
    }

    /**
     * 发送Get请求Https
     *
     * @param httpGet
     * @return
     */
    private String sendHttpsGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL(httpGet.getURI().toString()));
            DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);
            httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            LoggerUtils.logException("发送失败!", e);
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    public String sendCommonHttp(String url, String param, String charset) {
        HttpClient httpclient = null;
        HttpResponse response = null;
        try {
            httpclient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);
            StringEntity entityParams = new StringEntity(param, charset);
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;charset=" + charset);
            response = httpclient.execute(httpPost);
            if (response != null && response.getEntity() != null) {
                HttpEntity entity = response.getEntity();
                String responseStr = EntityUtils.toString(entity, charset);
                return responseStr;
            } else {
                LoggerUtils.logError("核心交易系统返回为空");
                return null;
            }
        } catch (Exception e) {
            LoggerUtils.logException("发送失败!", e);
            return null;
        }
    }

    /**
     * 发送Post请求
     *
     * @param url
     * @param param
     * @param charset
     * @return
     */
    public String sendCommonHttpNew(String url, String param, String charset) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        HttpPost httpPost = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            StringEntity entityParams = new StringEntity(param, charset);
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;charset=" + charset);
            httpPost.setConfig(requestConfig2);
            // 执行请求
            response = httpClient.execute(httpPost);
            if (response != null && response.getEntity() != null) {
                entity = response.getEntity();
                String responseStr = EntityUtils.toString(entity, charset);
                return responseStr;
            } else {
                LoggerUtils.logError("核心交易系统返回为空url:" + url);
                return null;
            }
        } catch (Exception e) {
            LoggerUtils.logException("发送失败!url:" + url, e);
            e.printStackTrace();
        } finally {
            try {
                // 终止请求
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
                LoggerUtils.logException("发送失败!url:" + url, e);
                e.printStackTrace();
            }
        }
        return responseContent;
    }
}

