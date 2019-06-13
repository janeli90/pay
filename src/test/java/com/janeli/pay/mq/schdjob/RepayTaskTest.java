package com.janeli.pay.mq.schdjob;

import com.wq.core.tdo.RepayTDO;
import com.wq.queue.prod.Exchange;
import com.wq.queue.prod.PayQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2018/12/14 0014.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RepayTaskTest {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void send(){
            sendRespayOrder(new RepayTDO());
    }

    private void sendRespayOrder(RepayTDO msg) {
        msg.setExpire("3000");
        rabbitTemplate.convertAndSend(Exchange.PAY_QUEUE_EXCHANGE, PayQueue.PAY_ROUTE, msg);
    }
}