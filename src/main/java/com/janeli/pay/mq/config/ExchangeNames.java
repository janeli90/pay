package com.janeli.pay.mq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description: 交换机bean
 * User: xiao
 * Date: 2018-07-30
 * Time: 08:31
 */


@Component
public class ExchangeNames {

    /**
     * 还款代扣执行路由器
     */
    @Value("${exchange.repay.pay.task}")
    public String repayPayExchange;


    /**
     * 还款查询路由器
     */
    @Value("${exchange.repay.pay.query.task}")
    public String repayPayQueryExchange;


    /**
     * 还款代付执行路由器
     */
    @Value("${exchange.repay.payback.task}")
    public String repayPaybackExchange;


    /**
     * 还款代付查询路由器
     */
    @Value("${exchange.repay.payback.query.task}")
    public String repayPaybackQueryExchange;


    /**
     * 还款公共路由器
     */
    @Value("${exchange.repay.common.topic}")
    public String repayCommonTopicExchange ;


    /**
     * 办卡网贷分润路由器
     */
    @Value("${apply.and.loan.prifit.exchage}")
    public String applyAndLoanPrifitExchage;




}
