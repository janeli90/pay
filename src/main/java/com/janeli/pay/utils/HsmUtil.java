package com.janeli.pay.utils;

/**
 * 加密工具类
 */
public class HsmUtil {
    /*
     * 对明文进行加密
     */
    public static String encrypt(String plainText) throws Exception {

        return DesUtil.DesEncrypt(plainText);
    }

    //解密
    public static String decrypt(String cipherText) throws Exception {
        return DesUtil.DesDecrypt(cipherText);
    }

}
