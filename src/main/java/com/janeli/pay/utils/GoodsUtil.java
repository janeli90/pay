package com.janeli.pay.utils;

import com.alibaba.fastjson.JSONObject;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xiao
 * Date: 2018-06-06
 * Time: 12:43
 */
public class GoodsUtil {

    /**
     * 当前日期
     * @return
     */
    public static String currentYYYYMMDD(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd");
        return sdf.format(new Date());
    }

    /**
     * 当前year年后的日期
     * @param year
     * @return
     */
    public static String currentAfter(Long year){
        LocalDate today = LocalDate.now();
        today = today.plusYears(year);
        return today.toString().replace("-","");
    }


    /**
     * 验签
     *
     * @param publicKeyStr
     * @param returnStr
     * @return
     * @throws Exception
     */
    public static boolean checkSign(String publicKeyStr, String returnStr)
            throws Exception {
        PublicKey publicKey = RSAKeyUtil.getPublicKey(publicKeyStr);
        Map<String, String> map = JSONObject.parseObject(returnStr,Map.class);
        Map<String,String> paramMap = new HashMap<>();
        for(Iterator<String> it = map.keySet().iterator(); it.hasNext();){
            String key = it.next();
            paramMap.put(key, String.valueOf(map.get(key)));
        }
        String sign = paramMap.remove("sign");
        //计算摘要之值
        String content = SignatureUtil.getSignatureContent(paramMap);
        return SignatureUtil.validateSign(content, publicKey, sign);
    }


    /**
     * 验签
     *
     * @param publicKeyStr
     * @param returnStr
     * @return
     * @throws Exception
     */
    public static boolean checkSign(PublicKey publicKey, String returnStr)
            throws Exception {

        Map<String, String> map = JSONObject.parseObject(returnStr,Map.class);

        Map<String,String> paramMap = new HashMap<String, String>();

        for(Iterator<String> it = map.keySet().iterator();it.hasNext();){
            String key = it.next();
            paramMap.put(key, String.valueOf(map.get(key)));
        }

        String sign = paramMap.remove("sign");
        if(StringUtils.isEmpty(sign)) return false;
        //计算摘要之值
        String content = SignatureUtil.getSignatureContent(paramMap);
        System.out.println("content:"+content);
        return SignatureUtil.validateSign(content, publicKey, sign);
    }

    public static String cardValidDateToYYYMMMDD(String validDate){

        if (validDate == null){
            return null;
        }
        return new StringBuilder("20").append(validDate.substring(2))
                    .append(validDate.substring(0,2)).append(27).toString();

    }

}
