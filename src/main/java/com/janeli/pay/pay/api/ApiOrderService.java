/**
 * ===================================================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ===================================================================================
 */
package com.janeli.pay.pay.api;

import com.janeli.pay.pay.vos.ApiResponse;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @公司名称：
 * @类功能说明：

 * @author caoyangjie
 * @创建日期： 创建于16:57 2017/12/13
 * @修改说明：
 * @修改日期： 修改于16:57 2017/12/13
 * @版本号： V1.0.0
 */
public interface ApiOrderService {
    /**
     * 根据订单ID获取订单信息
     * @param orderId 订单Id
     * @return 返回订单信息
     */
    ApiResponse getOrderInfo(String orderId);

    /**
     * 创建一个系统订单
     * @param applyId 订单关联对象id
     * @param totalPrice 订单总金额
     * @return
     */
    ApiResponse createSystemOrder(String applyId, BigDecimal totalPrice);

    /**
     * 根据订单id和订单状态获取订单信息
     * @param orderId   订单id
     * @param status    订单状态
     * @return 订单对象
     */
    Map<String,Object> getOrderInfo(String orderId, String status);

    /**
     * 更新订单状态
     * @param orderId 订单id
     * @param status 订单状态
     * @return 返回受影响行数
     */
    int updOrderStatus(String orderId, String status,String price);

    /**
     * 订单失败
     * @param orderId
     * @return
     */
    int orderFailed(String orderId);
}
