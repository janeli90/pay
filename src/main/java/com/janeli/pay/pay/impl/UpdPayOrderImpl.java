/**
 * ========================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ========================================================
 */
package com.janeli.pay.pay.impl;

import com.janeli.pay.pay.api.ApiIdempotencyService;
import com.janeli.pay.pay.api.ApiOrderService;
import com.janeli.pay.pay.enums.OrderStatusEnums;
import com.janeli.pay.pay.mapper.PayOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @公司名称：
 * @类功能说明：

 * @创建日期： 创建于 2017/8/1 17:54
 * @修改说明：
 * @修改日期： 修改于 2017/8/1 17:54
 * @版本号： V1.0.0
 */
@Service(value = "idempotencyService")
public class UpdPayOrderImpl implements ApiIdempotencyService<Map> {

    public static final String OUT_TRADE_NO = "out_trade_no";
    @Autowired
    protected ApiOrderService orderService;

    @Autowired
    protected PayOrderMapper payOrderMapper;

    @Override
    public Map<String,Object> query(Map context) {
        String systemOrderId= payOrderMapper.getOrderId(context.get(OUT_TRADE_NO)+"");
        return orderService.getOrderInfo(systemOrderId, OrderStatusEnums.NEEDPAY.getStatus());
    }
}
