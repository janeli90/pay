package com.janeli.pay.corgpay;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.janeli.pay.utils.LoggerUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoggerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		long startTime = System.currentTimeMillis();
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		request.setAttribute("startTime", startTime);
		request.setAttribute("traceUuid",  uuid);
		if (handler instanceof HandlerMethod) {
			StringBuilder sb = new StringBuilder();

			sb.append("[").append(uuid)
					.append("]请求：\n");
			HandlerMethod h = (HandlerMethod) handler;
			sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
			sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
			sb.append("Params    : ").append(getParamString(request.getParameterMap())).append("\n");
			sb.append("URI       : ").append(request.getRequestURI()).append("\n");
			LoggerUtils.logInfo(sb.toString());
		}
        return true;
	}
	 public void postHandle(HttpServletRequest request,
	         HttpServletResponse response, Object handler,
	         ModelAndView modelAndView) throws Exception {
		 
	     long startTime = (Long) request.getAttribute("startTime");
	     String uuid = (String)request.getAttribute("traceUuid");
	     long endTime = System.currentTimeMillis();
	     long executeTime = endTime - startTime;
	     if(handler instanceof HandlerMethod){
	         StringBuilder sb = new StringBuilder();
	         sb.append("[").append("["+uuid+"]").append("]响应：");
	         sb.append("CostTime  : ").append(executeTime).append("ms") ;
			 LoggerUtils.logInfo(sb.toString());
	     }
	 }

	 private String getParamString(Map<String, String[]> map) {
	     StringBuilder sb = new StringBuilder();
	     for(Entry<String,String[]> e:map.entrySet()){
	         sb.append(e.getKey()).append("=");
	         String[] value = e.getValue();
	         if(value != null && value.length == 1){
	             sb.append(value[0]).append("\t");
	         }else{
	             sb.append(Arrays.toString(value)).append("\t");
	         }
	     }
	     return sb.toString();
	 }
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
