/**
 * ===================================================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ===================================================================================
 */
package com.janeli.pay.pay.impl;

import com.janeli.pay.pay.api.YuancreditPayConfig;
import com.janeli.pay.pay.api.YuancreditPayGateway;
import com.janeli.pay.pay.entity.PayOrder;
import com.janeli.pay.pay.enums.PayWayEnums;
import com.janeli.pay.pay.exceptions.PayException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @公司名称：
 * @类功能说明：
 * @创建日期： 创建于11:29 2017/12/15
 * @修改说明：
 * @修改日期： 修改于11:29 2017/12/15
 * @版本号： V1.0.0
 */
@Service("gmPayGateway")
public class GmPayGatewayImpl extends YuancreditPayGateway {
    @Override
    public String getPayName() {
        return PayWayEnums.gome_pay.getDbValue();
    }

    @Override
    public Map<String, String> payResultNotice(HttpServletRequest request) throws PayException {
        return null;
    }

    @Override
    protected Map<String, String> queryOrderStatus(PayOrder order) {
        return null;
    }

    @Override
    protected Map<String, Object> createOrder(PayOrder order) {
        return null;
    }

    @Override
    protected YuancreditPayConfig initConfig() {
        return null;
    }
}
