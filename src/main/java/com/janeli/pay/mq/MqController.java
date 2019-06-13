package com.janeli.pay.mq;

import com.janeli.pay.config.ResultMessage;
import com.janeli.pay.mq.config.ExchangeNames;
import com.janeli.pay.mq.config.QueueNames;
import com.janeli.pay.mq.config.RouteKeys;
import com.janeli.pay.mq.producer.RepayProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xiao
 * Date: 2018-07-29
 * Time: 15:38
 */
@RestController
@RequestMapping("/start")
public class MqController {

    @Autowired
    private QueueNames queueNames;

    @Autowired
    private RouteKeys routeKeys;

    @Autowired
    private ExchangeNames exchangeNames;

    @Autowired
    private RepayProducer repayProducer;

    @RequestMapping("/open")
    public ResultMessage<ExchangeNames> open(){
//        List<TxnTDO> list = txnJnlEntityMapper.selectTodayTxn("TEST",new Date());
//        for (TxnTDO t:list) {
//            repayProducer.produceWithHoldMsg(t);
//        }

        ResultMessage resultMessage =  new ResultMessage<>(1,true, "start......");
        resultMessage.setData(exchangeNames);
        return resultMessage;
    }
}
