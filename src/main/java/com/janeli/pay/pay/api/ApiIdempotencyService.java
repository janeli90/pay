/**
 * ===================================================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ===================================================================================
 */
package com.janeli.pay.pay.api;

import java.util.Map;

/**
 * @公司名称：
 * @类功能说明：

 * @author caoyangjie
 * @创建日期： 创建于17:35 2017/7/17
 * @修改说明：
 * @修改日期： 修改于17:35 2017/7/17
 * @版本号： V1.0.0
 */
@FunctionalInterface
public interface ApiIdempotencyService<T> {
    public T query(Map<String, String> context);
}
