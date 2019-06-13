package com.janeli.pay.utils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2018/4/26 0026.
 */
public class OrderCodeUtil {

    public static final IdWorker idWorker = new IdWorker(1);

    private static Map<String, List<String>> orderCodeMap = new HashMap<String, List<String>>();
    private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式

    public static void main(String args[]){
        System.out.println(OrderCodeUtil.getOrderCode());
    }

    /**
     * 获取单号,不重复
     *
     * @return
     */
    public static String getOrderCode() {
        //生成随机数:当前精确到秒的时间再加6位的数字随机序列
        long result = 0;
        try {
            result = idWorker.nextId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String orderNo = "wq" + result;
        return orderNo;
    }

    /**
     * 获取指定位数的随机数(纯数字)
     *
     * @param length 随机数的位数
     * @return String
     */
    public static String getRandomNum(int length) {
        if (length <= 0) {
            length = 1;
        }
        StringBuilder res = new StringBuilder();
        Random random = new Random();
        int i = 0;
        while (i < length) {
            res.append(random.nextInt(10));
            i++;
        }
        return res.toString();
    }
}
