/**
 * ==================================================================================
 * <p>
 * <p>
 * <p>
 * ==================================================================================
 */
package com.janeli.pay.pay.impl;

import com.janeli.pay.pay.api.ApiOrderService;
import com.janeli.pay.pay.enums.OrderStatusEnums;
import com.janeli.pay.pay.vos.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @公司名称:
 * @类功能说明：
 * @创建日期： 11:41 2018/9/17
 * @修改说明：
 * @修改日期： 11:41 2018/9/17
 * @版本号： V1.0.0
 */
@Service(OrderServiceExtendsImpl.ORDER_SERVICE_EXTENDS)
public class OrderServiceExtendsImpl  extends BasePayService implements ApiOrderService {

    public static final String ORDER_SERVICE_EXTENDS = "orderServiceExtends";
    @Autowired
    @Qualifier("orderService")
    private ApiOrderService apiOrderService;

    @Override
    public ApiResponse getOrderInfo(String orderId) {
        return apiOrderService.getOrderInfo(orderId);
    }

    @Override
    public ApiResponse createSystemOrder(String applyId, BigDecimal totalPrice) {
        return apiOrderService.createSystemOrder(applyId,totalPrice);
    }

    @Override
    public Map<String, Object> getOrderInfo(String orderId, String status) {
        return apiOrderService.getOrderInfo(orderId,status);
    }

    @Override
    public int updOrderStatus(String orderId, String status, String price) {
        if( OrderStatusEnums.SUCCESS.getStatus().equals(status) ){
            //loanApplyMapper.updateLoanApplyStatus(loanApplyMapper.selectApplyId(orderId),"NEXT_HOME");
            // TODO: 2019/5/22 具体业务逻辑
        }
        return apiOrderService.updOrderStatus(orderId,status,price);
    }

    @Override
    public int orderFailed(String orderId) {
        return apiOrderService.orderFailed(orderId);
    }
}
