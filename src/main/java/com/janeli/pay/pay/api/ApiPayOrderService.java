/**
 * ===================================================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ===================================================================================
 */
package com.janeli.pay.pay.api;

import com.janeli.pay.pay.entity.PayOrder;
import com.janeli.pay.pay.enums.PayStatusEnum;
import com.janeli.pay.pay.vos.TransOrderBase;

import java.util.List;
import java.util.Map;

/**
 * @公司名称：
 * @类功能说明：

 * @author caoyangjie
 * @创建日期： 创建于10:46 2017/7/7
 * @修改说明：
 * @修改日期： 修改于10:46 2017/7/7
 * @版本号： V1.0.0
 */
public interface ApiPayOrderService {

    public PayOrder createOrder(TransOrderBase orderBase);

    public PayOrder queryOrder(String orderNo);

    public PayOrder queryOrderInfo(String orderId,String payWay,String payType);

    public int updPayCodeUrl(String orderNo, String codeUrl, String payWay,PayStatusEnum payStatus);

    public String getOrderId(String orderNo);

    public int updOrderOutTradeNo(String orderNo, String outTradeNo, PayStatusEnum paysuccess);

    public List<Map<String,Object>> getPayOrder(String orderId, String payWay, String payType);

    public String queryOrderStatus(String orderId);

}
