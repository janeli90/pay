package com.janeli.pay.mq.schdjob;

import com.janeli.pay.utils.LoggerUtils;
import com.wq.core.tdo.RepayTDO;
import com.wq.queue.prod.Exchange;
import com.wq.queue.prod.PayQueue;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description: 发送订单到还款中心
 * User: xiaok
 * Email: 306934150@qq.com
 * Date: 2018-09-13
 * Time: 10:13
 */
@Service
public class RepayTask {

    @Autowired
    private AmqpTemplate rabbitTemplate;


    @Scheduled(cron = "0 0 9 ? * * ")
    public void timeTask1() {
        LoggerUtils.logInfo("9点代还任务任务启动");
        this.sendOrder();
    }

    public void sendOrder() {
        sendRespayOrder(new RepayTDO());
    }

    private void sendRespayOrder(RepayTDO msg) {
        msg.setExpire("3000");
        rabbitTemplate.convertAndSend(Exchange.PAY_QUEUE_EXCHANGE, PayQueue.PAY_ROUTE, msg);
    }

    public static void sleep() {
        try {
            Thread.sleep(2000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
