package com.janeli.pay.utils;

import java.io.UnsupportedEncodingException;

/**
 *
 *
 */
public abstract class CoderUtil {

    /**
     * 
     * @param text
     * @param enocding
     * @return
     */
    public static String encryptHMAC(String text, String enocding){
        try {
            byte[] output = Coder.encryptHMAC(text.getBytes(enocding), Coder.initMacKey());
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < output.length; i++) {
                String t = Integer.toHexString(output[i] & 0x00FF);
                out.append((t.length() == 1) ? ("0" + t) : t);
            }
            return out.toString().toUpperCase();
        } catch (UnsupportedEncodingException e) {

        } catch (Exception e) {

        }
        return "";
    }
    
}

