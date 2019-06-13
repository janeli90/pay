package com.janeli.pay.utils;

/**
 * Created by leo on 2017/10/18.
 */

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * MD5SignUtil 加解密工具类
 */
public class MD5SignUtil {




    /**
     * BASE64 解密
     * @param key 需要解密的字符串
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] decryptBase64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64 加密
     * @param key 需要加密的字节数组
     * @return 字符串
     * @throws Exception
     */
    public static String encryptBase64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }


    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String sign,String text, String key, String input_charset) {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
        if(mysign.equals(sign)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * 验签键值对
     * @param dataMap 键值对
     * @param encriptkey 加密key
     * @return
     */
    public static boolean verifySign(Map<String,String> dataMap, String encriptkey){
        Map<String,String> tMap=new TreeMap<String,String>(dataMap);
        StringBuffer buf = new StringBuffer();
        String inputSign=tMap.get("sign");
        for (String key : tMap.keySet()) {
            if("signType".equals(key)||"sign".equals(key)){
                continue;
            }
            if(StringUtils.isBlank(tMap.get(key))){
                continue;
            }
            buf.append(key).append("=").append((String) tMap.get(key)).append("&");
        }
        String signatureStr = buf.substring(0, buf.length() - 1);
        return MD5SignUtil.verify(inputSign,signatureStr,encriptkey,"utf-8");
    }

    /**
     * 签名键值对
     * @param dataMap 签名键值对
     * @param encriptkey 加密key
     * @return
     */
    public static String sign(Map<String,String> dataMap,String encriptkey){
        Map<String,String> tMap=new TreeMap<String,String>(dataMap);
        StringBuffer buf = new StringBuffer();
        for (String key : tMap.keySet()) {
            if("signType".equals(key)||"sign".equals(key)){
                continue;
            }
            if(StringUtils.isBlank(tMap.get(key))){
                continue;
            }
            buf.append(key).append("=").append((String) tMap.get(key)).append("&");
        }
        if(buf.length()<1){
            return "";
        }
        String signatureStr = buf.substring(0, buf.length() - 1);
        return MD5SignUtil.sign(signatureStr,encriptkey,"utf-8");
    }
    /**
     * 签名键值对(有固定顺序)
     * @param tMap 签名键值对
     * @param encriptkey 加密key
     * @return
     */
    public static String signOrder(LinkedHashMap<String,String> tMap, String encriptkey){
//        Map<String,String> tMap=new TreeMap<String,String>(dataMap);
        StringBuffer buf = new StringBuffer();
        for (String key : tMap.keySet()) {
            if("signType".equals(key)||"sign".equals(key)){
                continue;
            }
            if(StringUtils.isBlank(tMap.get(key))){
                continue;
            }
            buf.append(key).append("=").append((String) tMap.get(key)).append("&");
        }
        if(buf.length()<1){
            return "";
        }
        String signatureStr = buf.substring(0, buf.length() - 1);
        LoggerUtils.logInfo("待签名字符串："+signatureStr);
        return MD5SignUtil.sign(signatureStr,encriptkey,"utf-8");
    }


}