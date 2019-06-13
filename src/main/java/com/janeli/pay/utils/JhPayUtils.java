package com.janeli.pay.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 聚合支付
 *
 * @author :xiaok
 * @date :2018/4/14 0014
 */
public class JhPayUtils {
    // HttpClient请求的相关设置，可以不用配置，用默认的参数，这里设置连接和超时时长(毫秒)
    public static RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(30000).setSocketTimeout(30000).build();
    public static final String rechargeKey = "f825e51891d6bb1f5dde7fefda0b6491";//申请的接口Appkey
    public static final String openId = "JH8248d735043f60a1bbdc72b4c90b6261";//在个人中心查询
    public static final String telCheckUrl = "http://op.juhe.cn/ofpay/mobile/telcheck?cardnum=*&phoneno=!&key=" + rechargeKey;
    public static final String telQueryUrl = "http://op.juhe.cn/ofpay/mobile/telquery?cardnum=*&phoneno=!&key=" + rechargeKey;
    public static final String onlineUrl = "http://op.juhe.cn/ofpay/mobile/onlineorder?key=" + rechargeKey + "&phoneno=!&cardnum=*&orderid=@&sign=$";
    public static final String yueUrl = "http://op.juhe.cn/ofpay/mobile/yue?key=" + rechargeKey + "&" + "timestamp=%&sign=$";
    public static final String orderstaUrl = "http://op.juhe.cn/ofpay/mobile/ordersta?key=" + rechargeKey + "&orderid=!";
    public static final Set<String> telCheckCardNums = new HashSet<>();


    //    =======================汽油充值配置常量
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    public static final String COME_ON_KEY = "";//汽油充值key
    public static final String COME_ON_URL = "http://op.juhe.cn/ofpay/sinopec/onlineorder";//加油请求
    public static final String COME_QUERY_URL = "http://op.juhe.cn/ofpay/sinopec/ordersta";//加油查询请求接口地址

    //    =======================汽车违规查询
    public static final String VIOLATION_QUERY_SUPPORT_CITY = "http://v.juhe.cn/sweizhang/citys";//获取支持违章查询城市地址
    public static final String VIOLATION_QUERY = "http://v.juhe.cn/sweizhang/query";//违规查询
    public static final String VIOLATION_QUERY_CAR_CITY = "http://v.juhe.cn/sweizhang/carPre";//违规查询
    public static final String VIOLATION_KEY = "";//查询违规key

    //    四要素验证地址
    public static final String FOURELEMENTSVERIFYURL = "http://v.juhe.cn/verifybankcard4/query";
    //public static final String FOURELEMENTSVERIFYKEY = "55fda519f6a9b5d3146f02dff9b55df0";
    public static final String FOURELEMENTSVERIFYKEY = "94d68967c44fa196c3dc863b59a04df5"; // TODO: 2019/4/17 调整

    static {
        telCheckCardNums.add("1");
        telCheckCardNums.add("2");
        telCheckCardNums.add("5");
        telCheckCardNums.add("10");
        telCheckCardNums.add("20");
        telCheckCardNums.add("30");
        telCheckCardNums.add("50");
        telCheckCardNums.add("100");
        telCheckCardNums.add("200");
        telCheckCardNums.add("300");
        telCheckCardNums.add("500");
    }

    /**
     * 1.检测手机号码是否能充值接口
     *
     * @param phone   手机号码
     * @param cardnum 充值金额,目前可选：1、2、5、10、20、30、50、100、200、300、500
     * @return 返回错码，0为允许充
     * 值的手机号码及金额，其他为不可以或其他错误
     * @throws Exception
     */
    public static int telCheck(String phone, String cardnum) throws Exception {
        String result = get(telCheckUrl.replace("*", cardnum + "").replace("!", phone), 0);
        Map map = (Map) JSONObject.parse(result);
        int error_code = (Integer) map.get("error_code");
        return error_code;
    }

    /**
     * 2.根据手机号和面值查询商品信息
     *
     * @param phone   手机号码
     * @param cardnum 充值金额,目前可选：5、10、20、30、50、100、300
     * @return String类型结果
     * @throws Exception
     */
    public static String telQuery(String phone, int cardnum) throws Exception {
        String result = get(telQueryUrl.replace("*", cardnum + "").replace("!", phone), 0);
        return result;
    }

    /**
     * 3.依据用户提供的请求为指定手机直接充值
     *
     * @param phone   手机号码
     * @param cardnum 充值金额,目前可选：5、10、20、30、50、100、300
     * @param orderid 商家订单号，8-32位字母数字组合，自定义
     * @return 返回String结果
     * @throws Exception
     */
    public static String onlineOrder(String phone, int cardnum, String orderid) throws Exception {
        String result = null;
        String sign = MD5Utils.encrypt((openId + rechargeKey + phone + cardnum + orderid));
        result = get(onlineUrl.replace("*", cardnum + "").replace("!", phone).replace("@", orderid).replace("$", sign), 0);
        return result;
    }

    /**
     * 4.查询账户余额
     *
     * @return
     * @throws Exception
     */
    public static String yuE() throws Exception {
        String timestamp = System.currentTimeMillis() / 1000 + "";
//        String sign = Md5Util.MD5(openId+key+timestamp);
        String sign = Md5Crypt.md5Crypt((openId + rechargeKey + timestamp).getBytes());
        String result = get(yueUrl.replace("%", timestamp).replace("$", sign), 0);
        return result;
    }

    /**
     * 5.订单状态查询
     *
     * @param orderid 商家订单号
     * @return 订单结果
     * @throws Exception
     */
    public static String orderStat(String orderid) throws Exception {
        return get(orderstaUrl.replace("!", orderid), 0);
    }

    /**
     * 工具类方法
     * get 网络请求
     *
     * @param url 接收请求的网址
     * @param tts 重试
     * @return String类型 返回网络请求数据
     * @throws Exception 网络异常
     */
    public static String get(String url, int tts) throws Exception {
        if (tts > 3) {//重试3次
            return null;
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(config);
            response = httpClient.execute(httpGet);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = ConvertStreamToString(resEntity.getContent(), "UTF-8");
            }
            EntityUtils.consume(resEntity);
            return result;
        } catch (IOException e) {
            return get(url, tts++);
        } finally {
            response.close();
            httpClient.close();
        }
    }

    /**
     * 工具类方法
     * 此方法是把传进的字节流转化为相应的字符串并返回，此方法一般在网络请求中用到
     *
     * @param is      输入流
     * @param charset 字符格式
     * @return String 类型
     * @throws Exception
     */
    public static String ConvertStreamToString(InputStream is, String charset)
            throws Exception {
        StringBuilder sb = new StringBuilder();
        try (InputStreamReader inputStreamReader = new InputStreamReader(is,
                charset)) {
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\r\n");
                }
            }
        }
        return sb.toString();
    }


    //===============================================汽油充值
    //加油卡充值
    public static String rechargeGosline(Integer proid, String cardNum, String orderId, String gameUserId, String gasCardTel) throws Exception {
        Map params = new HashMap();//请求参数
        params.put("proid", proid);//产品id:10000(中石化50元加油卡)[暂不支持]、10001(中石化100元加油卡)、10003(中石化500元加油卡)、10004(中石化1000元加油卡)、10007(中石化任意金额充值)[暂不支持]、10008(中石油任意金额充值)
        params.put("cardnum", cardNum);//充值数量（产品id为10007、10008时为具体充值金额(整数)，其余产品id请传固定值1）；注：中石油任意冲(产品id:10008)暂时只支持100\200\500\1000
        params.put("orderid", orderId);//商家订单号，8-32位字母数字组合
        params.put("game_userid", gameUserId);//加油卡卡号，中石化：以100011开头的卡号、中石油：以9开头的卡号
        params.put("gasCardTel", gasCardTel);//持卡人手机号码
        params.put("key", COME_ON_KEY);//应用APPKEY(应用详细页查询)
        params.put("sign", MD5Utils.encrypt(openId + COME_ON_KEY + proid + cardNum + gameUserId, orderId));//校验值，md5(OpenID+key+proid+cardnum+game_userid+orderid)，OpenID在个人中心查询
        return net(COME_ON_URL, params, "GET");
    }

    //加油查询订单状态查询
    public static String queryGosline(String orderId) throws Exception {
        Map params = new HashMap();//请求参数
        params.put("orderid", orderId);//商家订单号，8-32位字母数字组合
        params.put("key", COME_ON_KEY);//应用APPKEY(应用详细页查询)
        return net(COME_QUERY_URL, params, "GET");
    }


    //四要素验证
    public static String fourElementsVerify(String realname, String idcard, String bankcard, String mobile, String uorderid) throws Exception {
        Map params = new HashMap();//请求参数
        params.put("key", FOURELEMENTSVERIFYKEY);
        params.put("realname", realname);//姓名
        params.put("idcard", idcard);//身份证号码
        params.put("bankcard", bankcard);//银行卡卡号
        params.put("mobile", mobile);//手机号码
        params.put("uorderid", uorderid);//三方订单号
        return net(FOURELEMENTSVERIFYURL, params, "GET");
    }

    //=========================================================违规查询

    /**
     * 获取支持城市
     *
     * @param province：省份拼音首字母
     * @return
     * @throws Exception
     */
    public static String getSupportCitys(String province) throws Exception {
        Map params = new HashMap();//请求参数
        if (province == null) {
            params.put("province", province);//商家订单号，8-32位字母数字组合
        }
        params.put("key", VIOLATION_QUERY_SUPPORT_CITY);//应用APPKEY(应用详细页查询)
        return net(VIOLATION_KEY, params, "GET");
    }


    /**
     * @param city:城市代码
     * @param hphm：号牌号码 完整7位 ,需要utf8 urlencode*
     * @return
     * @throws Exception
     */
    public static String queryCar(String city, String hphm) throws Exception {
        Map params = new HashMap();//请求参数
        params.put("city", city);//城市代码
        params.put("hphm", hphm);//车牌号
        params.put("key", VIOLATION_QUERY);//应用APPKEY(应用详细页查询)
        return net(VIOLATION_KEY, params, "GET");
    }

    /**
     * 获取汽车城市
     *
     * @param hphm：号牌号码 完整7位 ,需要utf8 urlencode*
     * @param isNer
     * @return
     * @throws Exception
     */
    public static String queryCarOfCityInfo(Integer isNer, String hphm) throws Exception {
        Map params = new HashMap();//请求参数
        params.put("isNer", isNer);//城市代码
        params.put("hphm", hphm);//车牌号
        params.put("key", VIOLATION_KEY);//应用APPKEY(应用详细页查询)
        return net(VIOLATION_QUERY_CAR_CITY, params, "GET");
    }


    /**
     * 加油充值网络调用
     *
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
            conn.setRequestProperty("User-agent", USER_AGENT);
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
