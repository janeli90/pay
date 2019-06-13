package com.janeli.pay.mq.producer;

import com.alibaba.fastjson.JSON;
import com.janeli.pay.mq.model.TxnTDO;
import com.janeli.pay.mq.utils.QueueUtils;
import com.janeli.pay.mq.config.ExchangeNames;
import com.janeli.pay.mq.config.RouteKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description: 还款发送消息队列
 * User: xiao
 * Date: 2018-07-27
 * Time: 15:17
 */
@Service
public class RepayProducer {

    private Logger log = LoggerFactory.getLogger(RepayProducer.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RouteKeys routeKeys;

    @Autowired
    private ExchangeNames exchangeNames;

    /**
     * 发送代扣请求消息
     *
     * @param msg
     */
    public void produceWithHoldMsg(TxnTDO msg) {
        // 添加延时队列
        amqpTemplate.convertAndSend(exchangeNames.repayCommonTopicExchange, routeKeys.repayPayDelayRoutingKey, msg, message -> {
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, TxnTDO.class.getName());
            message.getMessageProperties().setExpiration(QueueUtils.expire(msg));
            return message;
        });
        log.info("[发送时间] - [{}]  订单号：tran_id:[{}] 发送到 ===> 代扣延迟队列 ", LocalDateTime.now(), JSON.toJSONString(msg));
    }


    /**
     * 发送代扣查询请求消息
     *
     * @param msg
     */
    public void produceWithHoldQueryMsg(TxnTDO msg) {
        // 添加延时队列
        amqpTemplate.convertAndSend(exchangeNames.repayCommonTopicExchange, routeKeys.repayPayQueryDelayRoutingKey, msg, message -> {
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, TxnTDO.class.getName());
            message.getMessageProperties().setExpiration(QueueUtils.expire(msg));
            return message;
        });
        log.info("[发送时间] - [{}]  订单号：tran_id:[{}] 发送到 ===> 扣查询延迟队列 ", LocalDateTime.now(), JSON.toJSONString(msg));
    }

    /**
     * 发送代付请求消息
     *
     * @param msg
     */
    public void producePayBackMsg(TxnTDO msg) {
        // 添加延时队列
        amqpTemplate.convertAndSend(exchangeNames.repayCommonTopicExchange, routeKeys.repayPaybackDelayRoutingKey, msg, message -> {
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, TxnTDO.class.getName());
            message.getMessageProperties().setExpiration(QueueUtils.expire(msg));
            return message;
        });
        log.info("[发送时间] - [{}]  订单号：tran_id:[{}] 发送到 ===> 代付延迟队列 ", LocalDateTime.now(), JSON.toJSONString(msg));
    }

    /**
     * 发送代付查询请求消息
     *
     * @param msg
     */
    public void producePayBackQueryMsg(TxnTDO msg) {
        // 添加延时队列
        amqpTemplate.convertAndSend(exchangeNames.repayCommonTopicExchange, routeKeys.repayPaybackQueryDelayRoutingKey, msg, message -> {
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, TxnTDO.class.getName());
            message.getMessageProperties().setExpiration(QueueUtils.expire(msg));
            return message;
        });
        log.info("[发送时间] - [{}]  订单号：tran_id:[{}] 发送到 ===> 代付查询延迟队列 ", LocalDateTime.now(), JSON.toJSONString(msg));
    }



}
