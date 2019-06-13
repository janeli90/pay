/**
 * ========================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ========================================================
 */
package com.janeli.pay.pay.entity;


import java.math.BigDecimal;

/**
 * @公司名称：
 * @类功能说明：
 * @创建日期： 创建于 2017/7/17 16:28
 * @修改说明：
 * @修改日期： 修改于 2017/7/17 16:28
 * @版本号： V1.0.0
 */
public class SystemOrder extends CoreEntity {
    private Long orderId;
    private String applyId;
    private BigDecimal totalPrice;
    private String orderStatus;
    private String payStatus;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyId() {
        return applyId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }
}
