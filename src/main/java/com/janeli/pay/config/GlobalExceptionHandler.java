package com.janeli.pay.config;

 import com.janeli.pay.common.Constants;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

 import com.janeli.pay.exception.BusinessException;
 
import java.util.HashMap;
import java.util.Map;

//TODO  原全局异常捕获被注释  2018.4.28
@ControllerAdvice
//如果返回的为json数据或其它对象，添加该注解
@ResponseBody
public class GlobalExceptionHandler {

    private Logger log= LoggerFactory.getLogger(this.getClass());
    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
     @ExceptionHandler(value = Exception.class)
     @ResponseBody
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("resCode", Constants.COMMON_ERROR_CODE);
        map.put("resMsg", Constants.COMMON_ERROR_MSG);
        log.error("系统错误：{}",ex);
        return map;
    }

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
     @ExceptionHandler(value = BusinessException.class)
     @ResponseBody
    public Map serviceHandler(BusinessException ex) {
        Map map = new HashMap();
        map.put("resCode", ex.getCode());
        map.put("resMsg", ex.getMsg());
        log.error("业务错误：code={},msg={},ex:",ex.getCode(), ex.getMsg(),ex);
        return map;
    }
}
