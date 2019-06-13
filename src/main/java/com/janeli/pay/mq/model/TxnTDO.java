package com.janeli.pay.mq.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 还款交易传输信息
 * User: xiao
 * Date: 2018-07-27
 * Time: 14:38
 */
public class TxnTDO {

    /**
     * 交易订单号
     */
    private String tranId;

    /**
     * 代付订单号
     */
    private String corgTranId;

    /**
     * 代扣订单号
     */
    private String outTranId;

    /**
     * 用户编号
     */
    private String usrNo;

    /**
     * 交易通道
     */
    private String cnlNo;


    /**
     * 交易类型 1信用卡还款 2无积分提现，4，有积分提现
     */
    private String tranType;

    /**
     * 交易状态 0：未进行 1：进行中 2：成功 3：失败 4：代扣成功 5：代付处理中   6：代扣处理中  7： 放入代扣延迟队列
     */
    private String tranSts;

    /**
     * 总单编号
     */
    private String planNo;
    /**
     * 交易金额
     */
    private BigDecimal txnAmt;

    /**
     * 还款金额
     */
    private BigDecimal paybackAmt;

    /**
     * 交易费率
     */
    private BigDecimal txnRate;

    /**
     * 手续费
     */
    private BigDecimal feeAmt;

    /**
     * 计划还款时间
     */
    private Date createTm;


    /**
     * 交易时间
     */
    private Date txnTm;

    /**
     * 交易重发次数
     */
    private Integer txnCnt;

    /**
     * 超时时间 1s = 1000ms
     */
    private String expire;

    /**
     * 执行步骤
     */
    private String step;


    /**
     * 备注
     */
    private String remark;

    @Override
    public String toString() {
        return "TxnTDO{" +
                "tranId='" + tranId + '\'' +
                ", corgTranId='" + corgTranId + '\'' +
                ", outTranId='" + outTranId + '\'' +
                ", usrNo='" + usrNo + '\'' +
                ", cnlNo='" + cnlNo + '\'' +
                ", tranType='" + tranType + '\'' +
                ", tranSts='" + tranSts + '\'' +
                ", planNo='" + planNo + '\'' +
                ", txnAmt=" + txnAmt +
                ", paybackAmt=" + paybackAmt +
                ", txnRate=" + txnRate +
                ", feeAmt=" + feeAmt +
                ", createTm=" + createTm +
                ", txnTm=" + txnTm +
                ", txnCnt=" + txnCnt +
                ", expire='" + expire + '\'' +
                ", step='" + step + '\'' +
                '}';
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo;
    }

    public String getExpire() {

        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public Date getTxnTm() {
        return txnTm;
    }

    public void setTxnTm(Date txnTm) {
        this.txnTm = txnTm;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getCorgTranId() {
        return corgTranId;
    }

    public void setCorgTranId(String corgTranId) {
        this.corgTranId = corgTranId;
    }

    public String getOutTranId() {
        return outTranId;
    }

    public void setOutTranId(String outTranId) {
        this.outTranId = outTranId;
    }

    public String getUsrNo() {
        return usrNo;
    }

    public void setUsrNo(String usrNo) {
        this.usrNo = usrNo;
    }

    public String getCnlNo() {
        return cnlNo;
    }

    public void setCnlNo(String cnlNo) {
        this.cnlNo = cnlNo;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getTranSts() {
        return tranSts;
    }

    public void setTranSts(String tranSts) {
        this.tranSts = tranSts;
    }

    public BigDecimal getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(BigDecimal txnAmt) {
        this.txnAmt = txnAmt;
    }

    public BigDecimal getPaybackAmt() {
        return paybackAmt;
    }

    public void setPaybackAmt(BigDecimal paybackAmt) {
        this.paybackAmt = paybackAmt;
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

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

    public Integer getTxnCnt() {
        return txnCnt;
    }

    public void setTxnCnt(Integer txnCnt) {
        this.txnCnt = txnCnt;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
