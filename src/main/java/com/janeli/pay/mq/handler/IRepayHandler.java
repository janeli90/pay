package com.janeli.pay.mq.handler;

import com.janeli.pay.mq.model.RePayDTO;
import com.janeli.pay.mq.model.TxnTDO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xiao
 * Date: 2018-07-27
 * Time: 16:18
 */
public interface IRepayHandler {

    /**
     * 代扣
     * @param tdo
     * @return
     */
    RePayDTO withHold(TxnTDO tdo);

    /**
     * 代扣状态查询
     * @param tdo
     * @return
     */
    RePayDTO withHoldQuery(TxnTDO tdo);


    /**
     * 代还
     * @param tdo
     * @return
     */
    RePayDTO repay(TxnTDO tdo);

    /**
     * 代还查询
     * @param tdo
     * @return
     */
    RePayDTO repayQuery(TxnTDO tdo);


}