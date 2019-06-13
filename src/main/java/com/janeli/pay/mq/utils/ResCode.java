package com.janeli.pay.mq.utils;

/**
 * Created with IntelliJ IDEA.
 * Description: 通道业务处理统一返回码
 * User: xiao
 * Date: 2018-07-27
 * Time: 16:56
 */
public enum ResCode {

    // 发送下一个队列
    NEXT,

    // 查询
    QUERY,

    // 重复代扣
    CIRCULATION,

    // 等待回调
    CALLBACK,

    // 返回上一步
    BEFORE,

    // 消息丢弃
    DROP,

    // 结束完成
    END
}
