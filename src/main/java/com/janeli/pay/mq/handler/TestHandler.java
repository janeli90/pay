package com.janeli.pay.mq.handler;

import com.janeli.pay.mq.model.RePayDTO;
import com.janeli.pay.mq.model.TxnTDO;
import com.janeli.pay.mq.utils.ResCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xiao
 * Date: 2018-07-27
 * Time: 17:38
 */
//@RepayHandler("TEST")
public class TestHandler implements IRepayHandler {
    public static final Logger log = LoggerFactory.getLogger(TestHandler.class);

    @Override
    public RePayDTO withHold(TxnTDO tdo) {
//        log.info("Handler 接收到数据代扣   === 线程号：[{}]    ==== tran_id:{}");
        log.info("withHold ; 顺序 1");
        return new RePayDTO(ResCode.QUERY);
    }

    @Override
    public RePayDTO withHoldQuery(TxnTDO tdo) {
        log.info("withHoldQuery ; 顺序 2");
        return new RePayDTO(ResCode.NEXT);
    }

    @Override
    public RePayDTO repay(TxnTDO tdo) {
//        log.info("mvnHandler 接收到数据代付=== 线程号：[{}]    ==== tran_id:{}", Thread.currentThread().getId(),tdo.getTranId());
        log.info("repay ; 顺序 3");
        return new RePayDTO(ResCode.QUERY);
    }

    @Override
    public RePayDTO repayQuery(TxnTDO tdo) {
        log.info("repayQuery ; 顺序 4");
        return new RePayDTO(ResCode.NEXT);
    }
}
