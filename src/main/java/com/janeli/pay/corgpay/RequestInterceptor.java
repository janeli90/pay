package com.janeli.pay.corgpay;

import com.janeli.pay.common.Constants;
import com.janeli.pay.exception.BusinessException;
import com.janeli.pay.user.entity.UserInfEntity;
import com.janeli.pay.user.mapper.UserInfEntityMapper;
import com.janeli.pay.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {
    private final static DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    @Autowired
    private UserInfEntityMapper userInfEntityMapper;
    @Value("${zxjfpay.session.timeout}")
    private int sessionTimeout;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws BusinessException {
        PageHelper.clearPage();
        String sessionId = request.getParameter(Constants.REQUEST_SESSION_ID);
        //UserInfEntity userInfEntity = userInfEntityMapper.selectBySessionId(sessionId);
        UserInfEntity userInfEntity = null;
        if (userInfEntity == null){ return true;}
        if (userInfEntity == null) {
            throw new BusinessException(Constants.SESSION_INVALID_CODE, "您的账号已在别的设备登录，请重新登录");
        }
        if (!"1".equals(userInfEntity.getUsrSts())) {
            throw new BusinessException(Constants.USR_STS_INVALID, "该用户状态不正常");
        }
        request.setAttribute(Constants.REQUEST_USER_ENTITY, userInfEntity);
        if (sessionTimeout > 0) {
            //检查登录超时


            String lastLoginTm = userInfEntity.getLastLoginTm();
            String curTm = DateUtils.getCurrentDateTimeToStr();
            try {
                Date d1 = df.parse(curTm);
                Date d2 = df.parse(lastLoginTm);
                long diff = d1.getTime() - d2.getTime();
                long seconds = diff / 1000;
                long maxSeconds = sessionTimeout * 60;
                if (seconds > maxSeconds) {
                    throw new BusinessException(Constants.SESSION_TIMEOUT_CODE, "登陆已失效，请重新登陆");
                }
            } catch (Exception e) {

            }
        }
        return true;
    }

}
