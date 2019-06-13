/**
 * ========================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ========================================================
 */
package com.janeli.pay.pay.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.janeli.pay.pay.entity.WeXinUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @公司名称：
 * @类功能说明：
 * @创建日期： 创建于 2017/9/5 9:37
 * @修改说明：
 * @修改日期： 修改于 2017/9/5 9:37
 * @版本号： V1.0.0
 */
@Mapper
public interface WeXinMapper extends BaseMapper<WeXinUserInfo> {
    Map<String,Object> getOpenId(@Param("userId") long userId);

    int addWeXinUser(@Param("map") Map<String, Object> map);

    int saveWeXinUserInfo(WeXinUserInfo userInfo);

    WeXinUserInfo getWexinUserInfoByOpenId(@Param("openId") String openId);
}
