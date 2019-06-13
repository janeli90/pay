package com.janeli.pay.mq.utils;

import com.janeli.pay.mq.model.TxnTDO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xiao
 * Date: 2018-07-27
 * Time: 15:55
 */
public class QueueUtils {
    /**
     * 默认超时时间
     */
    public static final String EXPIRE_TIME = "10000";

    /**
     * 设置默认过期时间
     * @param txnTDO
     * @return
     */
    public static String expire(TxnTDO txnTDO){
        if (txnTDO.getExpire() == null){
            txnTDO.setExpire(EXPIRE_TIME);
            return EXPIRE_TIME;
        }
        return txnTDO.getExpire();
    }

}
