package com.janeli.pay.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 基础加密组件
 *
 * @author GuangMing
 * @version 1.0
 * @date 2016/03/15
 * @since 1.0
 */
@SuppressWarnings("restriction")
public abstract class YFTCoder {
    public static final String KEY_SHA = "SHA";

//    public static void main(String[] arr) throws Exception{
//    	String code = "";
//    	//BASE64加密
//    	code = encryptStrToBASE64("123456ABCDE");
//    	System.out.println("encryptStrToBASE64" + code);
//    	//BASE64解密
//    	code = decryptBASE64ToStr(code);
//    	System.out.println("decryptBASE64ToStr" + code);
//    }

    /**
     * 二进�?	 * @param bytes
     *
     * @return
     */
    public static String Hex2(byte[] bytes) {
        return binary(bytes, 2);
    }

    /**
     * 十六进制
     *
     * @param bytes
     * @return
     */
    public static String Hex16(byte[] bytes) {
        return binary(bytes, 16);
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return String
     * @throws Exception
     */
    public static String decryptBASE64ToStr(String key) throws Exception {
        return new String((new BASE64Decoder()).decodeBuffer(key), "UTF-8");
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param bytes
     * @return String
     * @throws Exception
     */
    public static String encryptBASE64(byte[] bytes) throws Exception {
        return (new BASE64Encoder()).encode(bytes);
    }

    /**
     * BASE64加密
     *
     * @param String
     * @return byte[]
     * @throws Exception
     */
    public static String encryptStrToBASE64(String str) throws Exception {
        return (new BASE64Encoder()).encode(str.getBytes("UTF-8"));
    }


    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);
        return sha.digest();
    }

    /**
     * Turns array of bytes into string
     *
     * @param buf Array of bytes to convert to hex string
     * @return Generated hex string
     */
    public static String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)
                strbuf.append("0");

            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符�?
     */
    private static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里�?代表正数  
    }

}