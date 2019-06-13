package com.janeli.pay.mq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xiao
 * Date: 2018-07-30
 * Time: 09:44
 */
@Component
public class QueueNames {

    /**
     * 还款代扣执行队列
     */
    public static final String REPAY_PAY_QUEUE_NAME = "repay.pay.task";


    /**
     * 还款查询队列
     */
    public static final String REPAY_PAY_QUERY_QUEUE_NAME = "repay.pay.query.task";

    /**
     * 还款代付执行队列
     */
    public static final String REPAY_PAYBACK_QUEUE_NAME = "repay.payback.task";

    /**
     * 还款代付查询对列
     */
    public static final String REPAY_PAYBACK_QUERY_QUEUE_NAME = "repay.payback.query.task";
    /**
     * 申请信用卡以及网贷分润队列
     */
    public static final String APPLY_CARD_LOAN_QUEUE_NAME = "apply.card.and.loan.profit.queue.zhuyingjinfu";
    /**
     * 申请信用卡以及网贷分润回调队列
     */
    public static final String APPLY_CARD_LOAN_CALLBACK_ROUTING_KEY = "apply.card.and.loan.callback.queue.zhuyingjinfu";

    /**
     * 还款代扣延迟队列
     */
    @Value("${queue.repay.pay.delay}")
    public String repayPayDelayQueryName;

    /**
     * 还款代扣执行队列
     */
    @Value("${queue.repay.pay.task}")
    public String repayPayQueueName;


    /**
     * 还款代扣延迟查询队列
     */
    @Value("${queue.repay.query.delay}")
    public String repayPayQueryDelayQueueName;

    /**
     * 还款查询队列
     */
    @Value("${queue.repay.query.task}")
    public String repayPayQueryQueueName;


    /**
     * 还款代付延迟队列
     */
    @Value("${queue.repay.payback.delay}")
    public String repayPaybackDelayQueueName;

    /**
     * 还款代付执行队列
     */
    @Value("${queue.repay.payback.task}")
    public String repayPaybackQueueName;


    /**
     * 还款代付结果查询延迟队列
     */
    @Value("${queue.repay.payback.query.delay}")
    public String repayPaybackQueryDelayName;


    /**
     * 还款代付查询对列
     */
    @Value("${queue.repay.payback.query.task}")
    public String repayPaybackQueryQueueName;

}
