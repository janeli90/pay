/**
 * ===================================================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ===================================================================================
 */
package com.janeli.pay.pay.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.janeli.pay.pay.entity.SystemOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @公司名称：
 * @类功能说明：
 * @创建日期： 创建于16:40 2017/12/15
 * @修改日期： 修改于16:40 2017/12/15
 * @版本号： V1.0.0
 */

@Mapper
public interface OrderMapper extends BaseMapper<SystemOrder> {

    Map<String,Object> getOrderInfo(@Param("orderId") String orderId, @Param("status") String status);

    /**
     * 添加系统订单
     * @param order
     * @return
     */
    Long addSystemOrder(SystemOrder order);

    int updateOrderStatus(@Param("orderId") String orderId, @Param("status") String status, @Param("price") String payPrice);

    int orderFailed(String orderId);
}
