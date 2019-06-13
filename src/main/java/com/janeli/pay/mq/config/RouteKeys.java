package com.janeli.pay.mq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xiao
 * Date: 2018-07-30
 * Time: 09:02
 */
@Component
public class RouteKeys {


    /**
     * 还款代扣延迟队列路由key
     */
    @Value("${routingkey.pay.delay}")
    public String repayPayDelayRoutingKey;

    /**
     * 还款代扣执行队列路由key
     */
    @Value("${routingkey.pay.task}")
    public String repayPayRoutingKey;

    /**
     * 还款代扣延迟查询对列路由key
     */
    @Value("${routingkey.pay.query.delay}")
    public String repayPayQueryDelayRoutingKey;

    /**
     * 还款查询对列路由key
     */
    @Value("${routingkey.pay.query.task}")
    public String repayPayQueryRoutingKey;


    /**
     * 还款代付延迟队列路由key
     */
    @Value("${routingkey.payback.delay}")
    public String repayPaybackDelayRoutingKey;

    /**
     * 还款代付执行队列路由key
     */
    @Value("${routingkey.payback.task}")
    public String repayPaybackRoutingKey;

    /**
     * 还款代付结果查询延迟队列路由key
     */
    @Value("${routingkey.payback.query.delay}")
    public String repayPaybackQueryDelayRoutingKey;

    /**
     * 还款代付查询对列路由key
     */
    @Value("${routingkey.payback.query.task}")
    public String repayPaybackQueryRoutingKey;



    /**
     * 网贷及办卡对列路由key
     */
    @Value("${apply.and.loan.prifit.callback.routekey}")
    public String applyAndLoanPrifitCallbackRoutekey;
}
