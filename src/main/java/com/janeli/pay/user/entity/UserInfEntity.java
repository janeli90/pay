package com.janeli.pay.user.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserInfEntity extends UserInfEntityKey {
    /**
     * 时间戳
     */
    private Date tmSmp;

    /**
     * 用户登录密码
     */
    private String usrLoginpwd;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 用户支付密码
     */
    private String usrPaypwd;

    /**
     * 用户编号
     */
    private String usrNo;

    /**
     * 创建时间
     */
    private Date uCreateTime;

    /**
     * 实名认证标识 0 未实名 1已实名
     */
    private String rlAuthFlag;

    /**
     * 身份证号码
     */
    private String idNo;

    /**
     * 身份证姓名
     */
    private String idNm;

    /**
     * 用户token
     */
    private String sessionId;

    /**
     * 最后一次登录时间
     */
    private String lastLoginTm;

    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    /**
     * 最后一次登录设备
     */
    private String lastDevice;

    /**
     * 密码错误次数
     */
    private Integer errPwdCnt;

    /**
     * 用户状态 1正常 0 不正常
     */
    private String usrSts;

    /**
     * 推荐人
     */
    private String refNm;

    /**
     * 用户级别 （0：普通用户，1：一级代理，2：区县合伙人，3：市级合伙人，4：升级合伙人）
     */
    private Integer usrLvl;


    /**
     * 用户等级第二标识  1：二级代理
     */
    private Integer usrLvlFlag;

    /**
     * 省代码
     */
    private String prov;

    /**
     * 市代码
     */
    private String city;

    /**
     * 区代码
     */
    private String area;

    private String inRefNm1;

    private String inRefNm2;

    /**
     *   `head_url` varchar(100) DEFAULT NULL,
     `pay_amount` decimal(10,2) DEFAULT NULL,
     `pay_back_amount` decimal(10,2) DEFAULT NULL,
     `email` varchar(64) DEFAULT NULL,
     `hand_open_flag` varchar(255) DEFAULT NULL,
     `hand_pw` varchar(64) DEFAULT NULL,
     * @return
     */

    private String headUrl;

    private BigDecimal payAmount;

    private BigDecimal payBackAmount;

    private String email;

    private String handOpenFlag; // 手势密码是否有效 1;有效 2;无效

    private String handPw;

    /**
     * 提现密码
     */
    private String billPwd;

    /**
     * 提现密码盐
     */
    private String billSalt;


    public String getBillSalt() {
        return billSalt;
    }

    public void setBillSalt(String billSalt) {
        this.billSalt = billSalt;
    }

    public String getBillPwd() {
        return billPwd;
    }

    public void setBillPwd(String billPwd) {
        this.billPwd = billPwd;
    }

    public Integer getUsrLvlFlag() {
        return usrLvlFlag;
    }

    public void setUsrLvlFlag(Integer usrLvlFlag) {
        this.usrLvlFlag = usrLvlFlag;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayBackAmount() {
        return payBackAmount;
    }

    public void setPayBackAmount(BigDecimal payBackAmount) {
        this.payBackAmount = payBackAmount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHandOpenFlag() {
        return handOpenFlag;
    }

    public void setHandOpenFlag(String handOpenFlag) {
        this.handOpenFlag = handOpenFlag;
    }

    public String getHandPw() {
        return handPw;
    }

    public void setHandPw(String handPw) {
        this.handPw = handPw;
    }

    public Date getTmSmp() {
        return tmSmp;
    }

    public void setTmSmp(Date tmSmp) {
        this.tmSmp = tmSmp;
    }

    public String getUsrLoginpwd() {
        return usrLoginpwd;
    }

    public void setUsrLoginpwd(String usrLoginpwd) {
        this.usrLoginpwd = usrLoginpwd == null ? null : usrLoginpwd.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getUsrPaypwd() {
        return usrPaypwd;
    }

    public void setUsrPaypwd(String usrPaypwd) {
        this.usrPaypwd = usrPaypwd == null ? null : usrPaypwd.trim();
    }

    public String getUsrNo() {
        return usrNo;
    }

    public void setUsrNo(String usrNo) {
        this.usrNo = usrNo == null ? null : usrNo.trim();
    }

    public Date getuCreateTime() {
        return uCreateTime;
    }

    public void setuCreateTime(Date uCreateTime) {
        this.uCreateTime = uCreateTime;
    }

    public String getRlAuthFlag() {
        return rlAuthFlag;
    }

    public void setRlAuthFlag(String rlAuthFlag) {
        this.rlAuthFlag = rlAuthFlag == null ? null : rlAuthFlag.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getIdNm() {
        return idNm;
    }

    public void setIdNm(String idNm) {
        this.idNm = idNm == null ? null : idNm.trim();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    public String getLastLoginTm() {
        return lastLoginTm;
    }

    public void setLastLoginTm(String lastLoginTm) {
        this.lastLoginTm = lastLoginTm == null ? null : lastLoginTm.trim();
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public String getLastDevice() {
        return lastDevice;
    }

    public void setLastDevice(String lastDevice) {
        this.lastDevice = lastDevice == null ? null : lastDevice.trim();
    }

    public Integer getErrPwdCnt() {
        return errPwdCnt;
    }

    public void setErrPwdCnt(Integer errPwdCnt) {
        this.errPwdCnt = errPwdCnt;
    }

    public String getUsrSts() {
        return usrSts;
    }

    public void setUsrSts(String usrSts) {
        this.usrSts = usrSts == null ? null : usrSts.trim();
    }

    public String getRefNm() {
        return refNm;
    }

    public void setRefNm(String refNm) {
        this.refNm = refNm == null ? null : refNm.trim();
    }

    public Integer getUsrLvl() {
        return usrLvl;
    }

    public void setUsrLvl(Integer usrLvl) {
        this.usrLvl = usrLvl;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov == null ? null : prov.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getInRefNm1() {
        return inRefNm1;
    }

    public void setInRefNm1(String inRefNm1) {
        this.inRefNm1 = inRefNm1 == null ? null : inRefNm1.trim();
    }

    public String getInRefNm2() {
        return inRefNm2;
    }

    public void setInRefNm2(String inRefNm2) {
        this.inRefNm2 = inRefNm2 == null ? null : inRefNm2.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tmSmp=").append(tmSmp);
        sb.append(", usrLoginpwd=").append(usrLoginpwd);
        sb.append(", salt=").append(salt);
        sb.append(", usrPaypwd=").append(usrPaypwd);
        sb.append(", usrNo=").append(usrNo);
        sb.append(", uCreateTime=").append(uCreateTime);
        sb.append(", rlAuthFlag=").append(rlAuthFlag);
        sb.append(", idNo=").append(idNo);
        sb.append(", idNm=").append(idNm);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", lastLoginTm=").append(lastLoginTm);
        sb.append(", lastLoginIp=").append(lastLoginIp);
        sb.append(", lastDevice=").append(lastDevice);
        sb.append(", errPwdCnt=").append(errPwdCnt);
        sb.append(", usrSts=").append(usrSts);
        sb.append(", refNm=").append(refNm);
        sb.append(", usrLvl=").append(usrLvl);
        sb.append(", prov=").append(prov);
        sb.append(", city=").append(city);
        sb.append(", area=").append(area);
        sb.append(", inRefNm1=").append(inRefNm1);
        sb.append(", inRefNm2=").append(inRefNm2);
        sb.append("]");
        return sb.toString();
    }
}