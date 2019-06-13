package com.janeli.pay.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xiao
 * Date: 2018-07-05
 * Time: 18:57
 */
public class BeanInitUtils {

    public static <T> T initNullFieldValue(T t) {
        Class<?> clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
               if (fields[i].getType().equals(BigDecimal.class) && fields[i].get(t) == null) {
                    fields[i].set(t, new BigDecimal(0));
                }
                if (fields[i].getType().equals(Long.class) && fields[i].get(t) == null) {
                    fields[i].set(t, 0L);
                }
                if (fields[i].getType().equals(Integer.class) && fields[i].get(t) == null) {
                    fields[i].set(t, 0);
                }
                if (fields[i].getType().equals(String.class) && fields[i].get(t) == null) {
                    fields[i].set(t, "");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }
}
