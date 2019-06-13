/**
 * ===================================================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ===================================================================================
 */
package com.janeli.pay.pay.api;

import com.janeli.pay.pay.exceptions.PayException;
import com.janeli.pay.pay.vos.ApiResponse;
import com.janeli.pay.pay.vos.TransOrderBase;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @公司名称：
 * @类功能说明：
 * @创建日期： 创建于9:40 2017/7/7
 * @修改说明：
 * @修改日期： 修改于9:40 2017/7/7
 * @版本号： V1.0.0
 */
public interface ApiPayGateway {

    String getPayName();

    ApiResponse execute(TransOrderBase orderInfo) throws PayException;

    Map<String, Object> payAgain(String orderNo) throws PayException;

    Map<String, String> queryOrder(String orderNo) throws PayException;

    boolean noticeOrder(Map<String, String> context) throws PayException;

    Map<String, String> payResultNotice(HttpServletRequest request) throws PayException;
}
