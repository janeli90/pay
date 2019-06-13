/**
 * ===================================================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ===================================================================================
 */
package com.janeli.pay.pay.vos;

import com.janeli.pay.pay.exceptions.PayException;
import com.janeli.pay.pay.utils.StringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @公司名称：
 * @类功能说明：

 * @创建日期： 创建于17:49 2017/7/7
 * @修改说明：
 * @修改日期： 修改于17:49 2017/7/7
 * @版本号： V1.0.0
 */
@ApiModel("创建订单信息参数")
public class TransOrderBase implements Serializable {

    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("订单编号 （必须）")
    private String orderId;
    @ApiModelProperty("订单流水")
    private String orderNo;
    @ApiModelProperty("支付模式（必须）")
    private String payWayCode;
    @ApiModelProperty("下单IP")
    private String orderIp;
    @ApiModelProperty("备注(可选)")
    private String remark;
    @ApiModelProperty("支付类型（必须）")
    private String payType;
    @ApiModelProperty("公众号支付必须")
    private String openId;
    @ApiModelProperty("支付金额")
    private BigDecimal payPrice;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderIp() {
        return orderIp;
    }

    public void setOrderIp(String orderIp) {
        this.orderIp = orderIp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPayWayCode() {
        return payWayCode;
    }

    public void setPayWayCode(String payWayCode) {
        this.payWayCode = payWayCode;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public boolean checkTransData() {
        //TODO
        if(StringUtil.isEmpty(orderId)){
            throw new PayException("订单Id不允许为空!");
        }
        if(StringUtil.isEmpty(payWayCode)){
            throw new PayException("支付方式不允许为空!");
        }
        if (StringUtil.isEmpty(payType)){
            throw new PayException("支付类型不允许为空!");
        }
        return true;
    }
}
