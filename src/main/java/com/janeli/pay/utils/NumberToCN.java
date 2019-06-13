package com.janeli.pay.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author ZhangJinChang
 * @ClassName: NumberToCN
 * @Description: TODO()
 * @date 2017年12月11日 16:51
 */
public class NumberToCN {

    //num 表示数字，lower表示小写，upper表示大写
    private static final String[] num_lower = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };

    //允许的格式
    private static final List<String> promissTypes = Arrays.asList("INTEGER","INT","LONG","DECIMAL","FLOAT","DOUBLE","STRING","BYTE","TYPE","SHORT");


    /**
     * 数字转化为大写的汉字
     *
     * @param input 将要转化的数字
     * @return
     */
    private static String GetCH(char input) {

        return num_lower[(new Integer(input) - 48)];
    }

    /**
     * 数字转换为汉字
     *
     * @param num 整数部分
     * @return
     */
    public static String numToChinese(String num){

        //按4位分割成不同的组（不足四位的前面补0）
        char[] numarray = num.toCharArray();

        StringBuffer sb = new StringBuffer();
        for(int i=0;i<numarray.length;i++){
            //格式化当前4位
            String r = GetCH(numarray[i]);
            sb.append(r);
        }
        return sb.toString();
    }


    /**
     * 检查数字格式
     *
     * @param num
     */
    private static void checkNum(String num) {
        if(num.indexOf(".") != num.lastIndexOf(".")){
            throw new RuntimeException("数字["+num+"]格式不正确!");
        }
        if(num.indexOf("-") != num.lastIndexOf("-") || num.lastIndexOf("-")>0){
            throw new RuntimeException("数字["+num+"]格式不正确！");
        }
        if(num.indexOf("+") != num.lastIndexOf("+")){
            throw new RuntimeException("数字["+num+"]格式不正确！");
        }
        if(num.indexOf("+") != num.lastIndexOf("+")){
            throw new RuntimeException("数字["+num+"]格式不正确！");
        }
        if(num.replaceAll("[\\d|\\.|\\-|\\+]", "").length()>0){
            throw new RuntimeException("数字["+num+"]格式不正确！");
        }
    }


    public static void main(String[] args) {
        System.out.println(numToChinese("6222081202007438433"));

    }
}
