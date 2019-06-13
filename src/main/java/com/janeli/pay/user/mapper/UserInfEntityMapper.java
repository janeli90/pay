package com.janeli.pay.user.mapper;

import com.janeli.pay.user.entity.UserInfEntity;
import com.janeli.pay.user.entity.UserInfEntityKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface UserInfEntityMapper {
    int deleteByPrimaryKey(UserInfEntityKey key);

    int deleteByUsrNo(@Param("usrNo") String usrNo);

    int insert(UserInfEntity record);

    int insertSelective(UserInfEntity record);

    UserInfEntity selectByPrimaryKey(UserInfEntityKey key);


    int updateByPrimaryKeySelective(UserInfEntity record);

    int updateByPrimaryKey(UserInfEntity record);
    
    UserInfEntity selectByUserNo(String usrNo);


    UserInfEntity selectBySessionId(String sessionId);
    
    int getRefCnt(Map<String, Object> param);
    
    String getUsrNoByRebType(@Param("usrNo") String usrNo, @Param("rebType") String rebType);

    String getUsrNoBySecondLvl(@Param("usrNo") String usrNo);

    Integer selectUserRealAuth(@Param("list") Set<String> sets);

    List<String> selectAllUsrNo();

    Integer selectRecommendCount(@Param("list") Set<String> sets);

    Integer updateUserPayAmount(@Param("usrNo") String usrNo, @Param("payAmount") BigDecimal payAmount);

    Integer updateUserPayBackAmount(@Param("usrNo") String usrNo, @Param("payBackAmount") BigDecimal payAmount);


    List<UserInfEntity> selectUserByUsrNos(@Param("list") Set<String> list);


    UserInfEntity getUserInfoByMobleId(Map<String, String> map);

    UserInfEntity getrecommendUserInfoByUserIdAndChannelId(Map<String, String> map);

    /**
     * 获取区县代理
     * @param usrNo
     * @param channelId
     * @return
     */
    String selectAreaPartnerUsrNo(@Param("usrNo") String usrNo, @Param("channelId") String channelId);

    /**
     * 获取城市合伙人
     * @param usrNo
     * @param channelId
     * @return
     */
    String selectCityPartnerUsrNo(@Param("usrNo") String usrNo, @Param("channelId") String channelId);

    /**
     * 获取省级代理
     * @param usrNo
     * @param channelId
     * @return
     */
    String selectProvPartnerUsrNo(@Param("usrNo") String usrNo, @Param("channelId") String channelId);



    List<UserInfEntity> getUpstream(List<String> userNo);

    int countUserByIdNoAndChannelId(@Param("idNo") String idNo, @Param("channelId") String channelId);

    List<UserInfEntity> getInfo();

    List<UserInfEntity> selectLoanUser(@Param("fristPhone") String accountId, @Param("lastPhone") String accountId1, @Param("channelId") String channelId);

    List<UserInfEntity> selectLoanUserByChannelIdAndPhone(@Param("channelId") String channelId, @Param("phone") String phone);

    /**
     * 获取当月有收益得用户
     * @return
     */
    List<UserInfEntity> selectUserHasProfitCurrentMonth();

    /**
     * 根据推荐人统计下级人数by 等级
     * @param usrLvl
     * @param refNm
     * @return
     */
    int countUserByLvlAndRefNum(@Param("usrLvl") int usrLvl, @Param("refNm") String refNm);
}