/**
 * ===================================================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ===================================================================================
 */
package com.janeli.pay.pay.controller;

import com.janeli.pay.pay.api.ApiPayGateway;
import com.janeli.pay.pay.exceptions.PayException;
import com.janeli.pay.pay.vos.ApiResponse;
import com.janeli.pay.pay.enums.PayWayEnums;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/payNotice")
public class PayNoticeController extends CoreController {

    private static final String GATEWAY = "Gateway";

    /**
     * @see PayWayEnums
     * @param payWay 支持的模式,详见 @see
     * @param request
     * @return
     */
    @RequestMapping(value = "/{payWay}",method = RequestMethod.POST)
    public ApiResponse NoticePayOrder(@PathVariable String payWay, HttpServletRequest request) {
        try{
            Map<String,String> payResult;
            ApiPayGateway gateway = getServiceBy(payWay+ GATEWAY,ApiPayGateway.class,version());
            payResult = Optional.ofNullable(gateway.payResultNotice(request)).orElseThrow(()->new PayException("支付结果回调操作异常!"));
            gateway.noticeOrder(payResult);
            return getDefaultApiRosponse();
        }catch(Exception e){
            logger.error(e.getMessage());
            return getTryCatchExceptions(e);
        }
    }
}
