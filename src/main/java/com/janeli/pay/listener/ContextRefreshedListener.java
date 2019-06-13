package com.janeli.pay.listener;

import com.janeli.pay.mq.annotation.RepayHandler;
import com.janeli.pay.mq.handler.IRepayHandler;
import com.janeli.pay.mq.utils.HandlerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xiao
 * Date: 2018-07-27
 * Time: 16:40
 */
@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

        private static final Logger log = LoggerFactory.getLogger(ContextRefreshedListener.class);

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            log.info("=============onApplicationEvent time : {}", LocalDateTime.now());

            // 根容器为Spring容器
            if(event.getApplicationContext().getParent()==null){
                Map<String,Object> beans = event.getApplicationContext().getBeansWithAnnotation(RepayHandler.class);
                for(Object bean:beans.values()){
                    String annoName = getAnnotationValue(bean);
                    if (annoName == null){
                        continue;
                    }
                    HandlerUtils.HANDLER_MAP_CNL.put(annoName, (IRepayHandler) bean);
                }
            }
            HandlerUtils.accomplish();
        }

        public static String getAnnotationValue(Object obj){
            if (obj == null){
                return null;
            }
            Class<?> clz = obj.getClass();
            if (clz.isAnnotationPresent(RepayHandler.class) && Arrays.stream(clz.getInterfaces()).anyMatch( x -> x.equals(IRepayHandler.class))) {
                // 获取类上的注解
                RepayHandler annotation = clz.getAnnotation(RepayHandler.class);
                // 输出注解上的属性
                if (annotation.value() != null && (!"".equals(annotation.value()))){
                    return annotation.value();
                }
            }
            return null;
        }
}
