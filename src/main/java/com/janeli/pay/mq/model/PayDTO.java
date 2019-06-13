package com.janeli.pay.mq.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/14 0014.
 */
public class PayDTO {

    private String orderId;

    private Date tmSmp;

    private String payOrderId;

    private String repayOrderId;

    private String channelNo;

    private String usrNo;

    private String oemId;

    private String txnMercNm;

    private Integer tranType;

    private Integer orderStatus;

    private String planNo;

    private BigDecimal txnAmt;

    private BigDecimal repayAmt;

    private BigDecimal txnRate;

    private BigDecimal feeAmt;

    private Integer txnCnt;

    private Date executeDate;

    private Date executeTime;

    private Date payTime;

    private Date repayTime;

    private String txnCardNo;

    private String clrCardNo;

    private String retCode;

    private String retMsg;

    private String stlType;

    private String rebSts;

    private BigDecimal capAmt;

    private BigDecimal orgFeeAmt;

    private Integer checkStatus;

    private String remark;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getTmSmp() {
        return tmSmp;
    }

    public void setTmSmp(Date tmSmp) {
        this.tmSmp = tmSmp;
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public String getRepayOrderId() {
        return repayOrderId;
    }

    public void setRepayOrderId(String repayOrderId) {
        this.repayOrderId = repayOrderId;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getUsrNo() {
        return usrNo;
    }

    public void setUsrNo(String usrNo) {
        this.usrNo = usrNo;
    }

    public String getOemId() {
        return oemId;
    }

    public void setOemId(String oemId) {
        this.oemId = oemId;
    }

    public String getTxnMercNm() {
        return txnMercNm;
    }

    public void setTxnMercNm(String txnMercNm) {
        this.txnMercNm = txnMercNm;
    }

    public Integer getTranType() {
        return tranType;
    }

    public void setTranType(Integer tranType) {
        this.tranType = tranType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo;
    }

    public BigDecimal getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(BigDecimal txnAmt) {
        this.txnAmt = txnAmt;
    }

    public BigDecimal getRepayAmt() {
        return repayAmt;
    }

    public void setRepayAmt(BigDecimal repayAmt) {
        this.repayAmt = repayAmt;
    }

    public BigDecimal getTxnRate() {
        return txnRate;
    }

    public void setTxnRate(BigDecimal txnRate) {
        this.txnRate = txnRate;
    }

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    public Integer getTxnCnt() {
        return txnCnt;
    }

    public void setTxnCnt(Integer txnCnt) {
        this.txnCnt = txnCnt;
    }

    public Date getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(Date executeDate) {
        this.executeDate = executeDate;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public String getTxnCardNo() {
        return txnCardNo;
    }

    public void setTxnCardNo(String txnCardNo) {
        this.txnCardNo = txnCardNo;
    }

    public String getClrCardNo() {
        return clrCardNo;
    }

    public void setClrCardNo(String clrCardNo) {
        this.clrCardNo = clrCardNo;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getStlType() {
        return stlType;
    }

    public void setStlType(String stlType) {
        this.stlType = stlType;
    }

    public String getRebSts() {
        return rebSts;
    }

    public void setRebSts(String rebSts) {
        this.rebSts = rebSts;
    }

    public BigDecimal getCapAmt() {
        return capAmt;
    }

    public void setCapAmt(BigDecimal capAmt) {
        this.capAmt = capAmt;
    }

    public BigDecimal getOrgFeeAmt() {
        return orgFeeAmt;
    }

    public void setOrgFeeAmt(BigDecimal orgFeeAmt) {
        this.orgFeeAmt = orgFeeAmt;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
