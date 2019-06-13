package com.janeli.pay.mq.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 还款处理器注解
 * User: xiao
 * Date: 2018-07-27
 * Time: 16:32
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RepayHandler {
    String value() default "";
}
