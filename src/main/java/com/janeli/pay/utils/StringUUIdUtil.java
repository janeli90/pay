package com.janeli.pay.utils;

import java.util.UUID;

/**
 * Created by Administrator on 2018/5/28 0028.
 */
public class StringUUIdUtil {
    public static void main(String args[]) {
        for (int i = 0; i < 9 ; i++) {
            System.out.println(StringUUIdUtil.getUUID());
        }
    }

    /**
     * 随机生成UUID
     * @return
     */
    public static String getUUID() {
        UUID id = UUID.randomUUID();
        StringBuilder mb = new StringBuilder(Long.toHexString(id.getMostSignificantBits()));
        while (mb.length() < 16) {
            mb.insert(0, '0');
        }
        return mb.toString();

    }

    /**
     * 随机生成UUID 14位
     * @return
     */
    public static String getUUID14() {
        String id = UUID.randomUUID().toString();
        String s = id.replaceAll("-", "");
        String substring = s.substring(0,14);
        return substring;
    }

}
