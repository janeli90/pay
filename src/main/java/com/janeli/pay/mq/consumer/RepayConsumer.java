package com.janeli.pay.mq.consumer;

import com.janeli.pay.exception.MqException;
import com.janeli.pay.mq.config.QueueNames;
import com.janeli.pay.mq.handler.IRepayHandler;
import com.janeli.pay.mq.model.RePayDTO;
import com.janeli.pay.mq.model.TxnTDO;
import com.janeli.pay.mq.producer.RepayProducer;
import com.janeli.pay.mq.utils.HandlerUtils;
import com.janeli.pay.mq.utils.ResCode;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description: 还款单处理器
 * User: xiao
 * Date: 2018-07-27
 * Time: 16:04
 */
@Service
public class RepayConsumer {
    private Logger log = LoggerFactory.getLogger(RepayConsumer.class);

    @Autowired
    private RepayProducer repayProducer;

    @RabbitListener(queues = {QueueNames.REPAY_PAY_QUEUE_NAME})
    public void withHold(TxnTDO msg, Message message, Channel channel) {
        log.info("[消费时间] - [{}] - [还款代扣 监听到消息] - tran_id:[{}]", LocalDateTime.now(), msg.getTranId());

        try {
            IRepayHandler handler = HandlerUtils.getRepayHandler(msg.getCnlNo());
            RePayDTO result = handler.withHold(msg);
            //switch (result.getCode()) {
            //    case ResCode.QUERY:
            //        repayProducer.produceWithHoldQueryMsg(msg);
            //        break;
            //    case ResCode.NEXT:
            //        repayProducer.producePayBackMsg(msg);
            //        break;
            //    case ResCode.CIRCULATION:
            //        repayProducer.produceWithHoldMsg(msg);
            //        break;
            //    case ResCode.CALLBACK:
            //        log.info("[消费时间] - [{}] - [还款代扣] 等待回调 - tran_id:[{}]", LocalDateTime.now(), msg.getTranId());
            //        break;
            //    default:
            //        log.info("[消费时间] - [{}] - [还款代扣] 丢弃消息 - tran_id:[{}]", LocalDateTime.now(), msg.getTranId());
            //}
        } catch (MqException e) {
            log.info("[代扣执行时间] - [{}] - [还款代扣]  - tran_id:[{}] 异常消息{}", LocalDateTime.now(), msg.getTranId(), e.getMsg());
        } finally {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                log.info("[代扣执行时间] - [{}] - [还款代扣]  - tran_id:[{}] 异常消息{}", LocalDateTime.now(), msg.getTranId(), e.getMessage());
                repayProducer.produceWithHoldMsg(msg);
            }
        }
    }

}
