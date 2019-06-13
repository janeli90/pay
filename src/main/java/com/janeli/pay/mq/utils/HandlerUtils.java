package com.janeli.pay.mq.utils;

import com.janeli.pay.exception.MqException;
import com.janeli.pay.mq.handler.IRepayHandler;
import com.janeli.pay.mq.model.TxnTDO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xiao
 * Date: 2018-07-27
 * Time: 17:30
 */
public class HandlerUtils {
    public static final Map<String,IRepayHandler> HANDLER_MAP_CNL = new HashMap<>();

    /**
     * 容器初始化标识
     */
    private static volatile boolean initFlag = false;

    public static void accomplish(){
        initFlag = true;
    }

    public static void isAccomplish(){
        if (initFlag){
            return;
        }
        while (!initFlag){
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }


    /**
     * 获取处理器
     * @param cnl
     * @return
     */
    public static IRepayHandler getRepayHandler(String cnl) throws MqException {
        isAccomplish();
        IRepayHandler handler =  HANDLER_MAP_CNL.get(cnl);
        if (handler == null){
            throw new MqException(1, "通道"+ cnl+"处理器未配置!");
        }
        return handler;
    }

    /**
     * 检查交易次数是否超限
     * @param txnTDO
     * @return
     */
    public static boolean checkTxnCnl(TxnTDO txnTDO){
        if (txnTDO == null){
            return false;
        }
        if (txnTDO.getTxnCnt() < 0){
            return false;
        }
        return true;
    }

}
