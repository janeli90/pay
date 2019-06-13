package com.janeli.pay.mq.utils;

/**
 * Created with IntelliJ IDEA.
 * Description: 执行步骤枚举类型
 * User: xiao
 * Date: 2018-07-29
 * Time: 08:44
 */
public enum StepCode {
    // 代扣
    WITH_HOLD,

    // 代扣查询
    With_HOLD_QUERY,

    // 代付
    PAYBACK,

    // 代付查询
    PAYBACK_QUERY,

}
