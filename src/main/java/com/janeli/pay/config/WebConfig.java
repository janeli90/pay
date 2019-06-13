package com.janeli.pay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.janeli.pay.corgpay.LoggerInterceptor;
import com.janeli.pay.corgpay.RequestInterceptor;

@Component
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private RequestInterceptor requestInterceptor;
    @Autowired
    private LoggerInterceptor loggerInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(loggerInterceptor).addPathPatterns("/**");
        //注册拦截器
        registry
                .addInterceptor(requestInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/checkSMSVerification")
                .excludePathPatterns("/error")
                .excludePathPatterns("/creditQueryNotify")
                .excludePathPatterns("/corgNotify/*")
                .excludePathPatterns("/app/**/**")
                .excludePathPatterns("/share/**")
                .excludePathPatterns("/integral/getBankNames")
                .excludePathPatterns("/integral/notify")
                .excludePathPatterns("/npsBigAmtNotify/*")
                .excludePathPatterns("/npsSlNotify/*")
                .excludePathPatterns("/client/*")
                .excludePathPatterns("/jiupayPay/singleTransfer")
                .excludePathPatterns("/jiupayPay/singleTransferNotify")
                .excludePathPatterns("/jiupayPay/qrcodeSpdbPreOrderNotify")
                .excludePathPatterns("/integral/getBankInfo")
                .excludePathPatterns("/kfPay/kfpayNotify")
                .excludePathPatterns("/safeloan/*")
                .excludePathPatterns("/dict/**")
        ;
//
    }
}
