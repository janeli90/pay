package com.janeli.pay.utils;

/**
 * Created by lid on 2018/6/22 0022.
 */
public class StrUtil {

//    public static void main(String args[]){
//        System.out.println(StrUtil.getFailRetMsg("64|卡上的余额不足[1051201]"));
//    }

    public static String getFailRetMsg(String retMsg){

        //包含|和中括号
        if((retMsg.contains("["))
                &&(retMsg.contains("|"))){
            return retMsg.substring(retMsg.indexOf("|") + 1, retMsg.indexOf("["));
        }

        //包含小括号和中括号
        if((retMsg.contains("["))
                &&(retMsg.contains("("))){
            return retMsg.substring(retMsg.indexOf(")") + 1, retMsg.indexOf("["));
        }

        //包含中括号，只获取中括号内的内容
        if((retMsg.contains("["))
                &&(retMsg.contains("]"))){
            return retMsg.substring(retMsg.indexOf("[") + 1, retMsg.indexOf("]"));
        }

        //包含小括号的
        if((retMsg.contains("("))
                &&(retMsg.contains(")"))){
            return retMsg.substring(retMsg.indexOf(")") + 1, retMsg.length());
        }

        return retMsg;
    }

    /**
     * 参数检查
     * @param parms
     * @return
     */
    public static boolean checkParms(String... parms){
        for (String str:parms) {
            if (str == null || "".equals(str)){
                return true;
            }

        }
        return false;
    }
}
