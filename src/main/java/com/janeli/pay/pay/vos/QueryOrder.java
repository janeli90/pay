/**
 * ========================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ========================================================
 */
package com.janeli.pay.pay.vos;

import com.janeli.pay.pay.utils.StringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "订单查询请求参数")
public class QueryOrder extends TransDataBaseVo {

    @ApiModelProperty("系统订单ID必传")
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    @Override
    public boolean checkTransData() {
        if(StringUtil.isEmpty(orderId)){
            return false;
        }
        return true;
    }

    @Override
    public String version() {
        return "V1.0.0";
    }
}
