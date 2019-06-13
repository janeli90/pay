/**
 * ========================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ========================================================
 */
package com.janeli.pay.pay.api;

import com.janeli.pay.pay.vos.ApiResponse;
import com.janeli.pay.pay.vos.QueryOrder;

/**
 * @公司名称：
 * @类功能说明：
 * @创建日期： 创建于 2017/8/9 18:23
 * @修改说明：
 * @修改日期： 修改于 2017/8/9 18:23
 * @版本号： V1.0.0
 */
public interface ApiQueryOrderService {
    ApiResponse getQueryOrder(QueryOrder queryOrder);

    ApiResponse getQueryOrderSuccess(QueryOrder queryOrder);
}
