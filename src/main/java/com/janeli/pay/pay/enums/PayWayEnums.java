/**
 * ========================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ========================================================
 */
package com.janeli.pay.pay.enums;

/**
 * @公司名称：
 * @类功能说明：

 * @创建日期： 创建于 2017/8/1 10:52
 * @修改说明：
 * @修改日期： 修改于 2017/8/1 10:52
 * @版本号： V1.0.0
 */
public enum PayWayEnums {
    weixin_pay(1,"wxPay","微信支付"),
    ali_pay(2,"alipay","支付宝支付"),
    gome_pay(3,"gomepay","国美支付"),
    UNKOWN(4,"unkown","未知状态");


    int data;//提交值
    String dbValue;//数据库保存值
    String desc;//值说明

    PayWayEnums(int data, String dbValue, String desc)
    {
        this.data=data;
        this.dbValue=dbValue;
        this.desc = desc;
    }
    /**
     * 根据请求参数获取枚举类型
     * @param data 传入参数值
     * @return 返回枚举类型
     */
    public static PayWayEnums getEnum(int data){
        PayWayEnums[] ary = PayWayEnums.values();
        for (int i = 0; i < ary.length; i++) {
            if(ary[i].data==data){
                return ary[i];
            }
        }
        return PayWayEnums.UNKOWN;
    }

    public String getDbValue() {
        return dbValue;
    }

    public int getData() {
        return data;
    }
}
