package com.janeli.pay.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lid on 2018/6/26 0026.
 */
public class BankFilter {
    /**
     * 工商银行：  102223007007
     农业银行：  103223007007
     交通银行：	301290000007
     中信银行：	302100011106
     光大银行：	303100000006
     华夏银行：  304100000013
     民生银行：	305100000013
     广发银行：	306331003281
     平安银行：	307584007998
     兴业银行：	309391000011
     邮储银行：	403100000004
     浦发银行：	310265000011
     */
    public static final List<String> bankList = new ArrayList<String>();

    static {
        bankList.add("102223007007");//工商银行 102223007007
        bankList.add("103223007007");//农业银行：  103223007007
        bankList.add("301290000007");//交通银行：	301290000007
        bankList.add("302100011106");//中信银行：	302100011106
        bankList.add("303100000006");//光大银行：	303100000006
        bankList.add("304100000013");//华夏银行：  304100000013
        bankList.add("305100000013");//民生银行：	305100000013
        bankList.add("306331003281");//广发银行：	306331003281
        bankList.add("307584007998");//平安银行：	307584007998
        bankList.add("309391000011");//兴业银行：	309391000011
        bankList.add("403100000004");//邮储银行：	403100000004
        bankList.add("310265000011");//浦发银行：	310265000011
    }

    public static boolean isSupport(String bankCode){
        if(bankList.contains(bankCode)){
            return true;
        }

        return false;
    }

    public static void main(String args[]){
        System.out.println(BankFilter.isSupport("310265000011"));
    }
}
