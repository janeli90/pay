
package com.janeli.pay.utils;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: TODO {一句话描述类是干什么的}<br/>
 *
 * @author Administrator
 * @version 1.0
 * @date: 2017年10月12日 下午7:30:48
 * @since JDK 1.7
 */
public class RSAUtil {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RSAUtil.class);
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK3KSopTFyCWFQNgw6vIpkB4SfoOsYV4UDulwYZ3Rs1M0n7tLKwkqgEJlA/oJI/5zbzZ9RCL/Cxg7vruk1KFgNqSINcFwVgGMzBc/g4qyNEuc1QXaTVZ9bct7YcHCT71utPKG8FwJ8itrEWEP+/llGQPWfLqdpE+x3H6hFciWodhAgMBAAECgYAH/28+vmQqE74T0bgvh6RfAyokX9i7k7XdiNtHHOYdPJPTU4bixS0trwspUD2egph9QGqOwGJyiGN3GNOe0JYFZW0cqxxDSJn9/Sw6WTM7BGuM0pomcRLXyaycyq+7pKzDh+eIjF4wS6/gZDu8HOqW4C7mOuYinpPOO7bJkZJroQJBAPFRo+sTzNHtaRFbaG0+Jxjb1N8vb+5g8k6pHxUWbE1KeK/7DrLTebJ6cqKCw3a4PxEda0P9D+T7nqektZRWlNMCQQC4XPE3zDyzfDjEUTPhDrhfurOptqB6aTyuAKG5NCcqGhmEeksxjaGqX6cS9YFCJzJWs39ZXzuwKymabRpk7yJ7AkEA0Bkp9LcfYkyL1Wbw93uodXxk5o3exT8e1QxsfF/YxxIYXxFtzHInOOz03AVNj5w07opjDL82rKz7VO/pkeDT9wJBAImb7fx8+I3r2Z9HERcaWqoxfCrgAtZ7uGwUUqIfeGAnjQZiFcAN/C5kKRuwneNXyG4ImrSFrdKn1OzMJNhT2gMCQCI7N+hiZzkOHc5WHgwV6ffjrfxvF7QrmfJW/kTANq7q5f421Oacd60g+Z8Hb+OKdhd1oNqSdVY0mpt5voFzMD0=";
    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtykqKUxcglhUDYMOryKZAeEn6DrGFeFA7pcGGd0bNTNJ+7SysJKoBCZQP6CSP+c282fUQi/wsYO767pNShYDakiDXBcFYBjMwXP4OKsjRLnNUF2k1WfW3Le2HBwk+9brTyhvBcCfIraxFhD/v5ZRkD1ny6naRPsdx+oRXIlqHYQIDAQAB";
    private static RSAPrivateKey RSA_PRIVATE_KEY;
    private static RSAPublicKey RSA_PUBLIC_KEY;

    static {
        try {
            byte[] buffer = Base64.decodeBase64(PRIVATE_KEY);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSA_PRIVATE_KEY = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);

            buffer = Base64.decodeBase64(PUBLIC_KEY);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(buffer);
            RSA_PUBLIC_KEY =  (RSAPublicKey)keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (Exception e) {
            LOGGER.error("初始化密钥失败", e);
            e.printStackTrace();
        }
    }

    public static String rsaEncrypt(String cipherText) throws Exception {
        if (RSA_PUBLIC_KEY == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        byte[] cipherData = cipherText.getBytes();
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, RSA_PUBLIC_KEY);
            byte[] output = cipher.doFinal(cipherData);
            return Base64.encodeBase64String(output);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("无此加密算法", e);
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            LOGGER.error("无此加密算法", e);
            throw new Exception("无此加密算法");
        } catch (InvalidKeyException e) {
            LOGGER.error("加密公钥非法", e);
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            LOGGER.error("密文长度非法", e);
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            LOGGER.error("密文数据已损坏", e);
            throw new Exception("密文数据已损坏");
        }
    }

    public static String rsaDecrypt(String cipherText) throws Exception {
        if (RSA_PRIVATE_KEY == null) {
            throw new Exception("解密私钥为空, 请设置");
        }
        byte[] cipherData = Base64.decodeBase64(cipherText);
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, RSA_PRIVATE_KEY);
            byte[] output = cipher.doFinal(cipherData);
            return new String(output, DEFAULT_ENCODING);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("无此解密算法", e);
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            LOGGER.error("无此解密算法", e);
            throw new Exception("无此解密算法");
        } catch (InvalidKeyException e) {
            LOGGER.error("解密私钥非法", e);
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            LOGGER.error("密文长度非法", e);
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            LOGGER.error("密文数据已损坏", e);
            throw new Exception("密文数据已损坏");
        }
    }

    /*
    * 生成指定长度的公私钥对
    * length：初始化密钥对生成器，密钥大小为96-1024位
    */
    public static void genKeyPair(int length) {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        keyPairGen.initialize(length, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        try {
            // 得到公钥字符串
            String publicKeyString = Base64.encodeBase64String(publicKey.getEncoded());
            // 得到私钥字符串
            String privateKeyString = Base64.encodeBase64String(privateKey.getEncoded());
            System.out.println("公钥---" + publicKeyString);
            System.out.println("私钥---" + privateKeyString);
            // 将密钥对写入到文件
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Map<String, String> getRsaDecrypt(String resText) {
        Map<String, String> result = new HashMap<>();

        if (StringUtils.isNotBlank(resText)) {
            try {
                result.put("resCode", "0000");
                result.put("resMsg", "解密成功");
                result.put("rsaDecrypt", RSAUtil.rsaDecrypt(resText));
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                result.put("resCode", "0001");
                result.put("resMsg", e.getMessage());
                return result;
            }
        }
        result.put("resCode", "0002");
        result.put("resMsg", "入参为空");
        return result;
    }

    public static void main(String[] args) throws Exception {
        genKeyPair(1024);
//        System.out.println(rsaDecrypt("gN3zBOKgD8jIpKh0/tZfReRaObdTi5hqo4suFZ/djJsWzWlfIg3g2gq/NYax+TFRE9+4aC16IdO+k2Vz+G46Ing0vrXbU2N/J31iaGDkmoJuwybLhwbwVBYjs3dOt+GUjFLhYRhb8gwfYeBu9Dy+KPqmMA3REz9O4rRjTLhoPq8="));
//        System.out.println(Base64.decodeBase64("Hello World!"));
//        System.out.println(rsaEncrypt("Hello World!"));
//        System.out.println(rsaDecrypt("c0iC2pWoq5lpZ2Ot1M5wef2CFCT5+vNYlkA6HZo95rQNwJeEeGAXVl74596g5Eb169dyRVdG+pWK5wkZVL+ItAJPTeo1e1UAJolDaPRT3M87/dkUthTkkZxpeo6cejh7fZlkn+wOuN8evbHZ0VAvBmELCEHRmAY4LUJqk3PD+Nw="));
//    	System.out.println(rsaEncrypt("21312333"));

//        System.out.println(rsaDecrypt("vmDiQcq6qXQ="));

    }
}
