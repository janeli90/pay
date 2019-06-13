package com.janeli.pay.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    private static final String DEFAULT_ENCODING = "UTF-8";

    public Md5Util() {
    }

    public static String MD5(String input) {
        return MD5(input, "UTF-8");
    }

    public static String MD5(String text, String enocding) {
        if (text == "" || text == null){
            return "";
        }
        if (enocding == "" || enocding == null){
            enocding = DEFAULT_ENCODING;
        }

        try {
            byte[] output = MessageDigest.getInstance("MD5").digest(text.getBytes(enocding));
            StringBuffer out = new StringBuffer();

            for(int i = 0; i < output.length; ++i) {
                String t = Integer.toHexString(output[i] & 255);
                out.append(t.length() == 1 ? "0" + t : t);
            }
            return out.toString().toUpperCase();
        } catch (NoSuchAlgorithmException var6) {
            var6.printStackTrace();
        } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String s = MD5("123456");
        System.out.println(s);
    }
}
