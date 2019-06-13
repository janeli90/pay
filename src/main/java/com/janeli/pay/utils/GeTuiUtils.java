package com.janeli.pay.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.janeli.pay.common.Constants;
 import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class GeTuiUtils {
	private Logger logger= LoggerFactory.getLogger(this.getClass());

    private   String appId = "";
    private   String appKey = "";
    private   String masterSecret = "";
    private   String clientId = "";
    public GeTuiUtils(String appId,String appKey,String masterSecret,String clientId){
    	this.appId = appId;
    	this.appKey = appKey;
    	this.masterSecret = masterSecret;
    	this.clientId = clientId;    	
    }
    public Map<String, Object>  pushtoSingle(String osType,String title,String msg,String actionType) {
        IPushResult ret = null;
        IGtPush push = null;
        SingleMessage message = null;
        Target target = null;
        try {
        	  push = new IGtPush(Constants.GE_TUI_URL, appKey, masterSecret);
              message = new SingleMessage();
            message.setOffline(true);
            // 离线有效时间，单位为毫秒，可选
            message.setOfflineExpireTime(24 * 3600 * 1000);
            if("1".equals(osType))
              message.setData(getNotificationTemplate(title,msg,actionType));
            else
             message.setData(getIOSTemplate(title,msg,actionType));
            // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
            message.setPushNetWorkType(0); 
              target = new Target();
            target.setAppId(appId);
            target.setClientId(clientId);
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
        	logger.info("推送发送异常{}，尝试重发",e.getMessage());
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }catch (Exception e) {
        	logger.info("推送发送异常{}",e.getMessage());
            e.printStackTrace();
        }
        if (ret != null) {
        	logger.info("推送结果{}",ret.getResponse().toString());
        	return ret.getResponse();
        } else {
        	Map<String, Object> result = new HashMap<>();
        	result.put("result", "ExceptionHappened");
    		return result;		

        }
	}
	@SuppressWarnings("deprecation")
	private    NotificationTemplate getNotificationTemplate(String title,String msg,String actionType) {
	    NotificationTemplate template = new NotificationTemplate();
	    // 设置APPID与APPKEY
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    // 设置通知栏标题与内容
	    template.setTitle(title);
	    template.setText(msg);
	    // 配置通知栏图标
	    template.setLogo("icon.png");
	    // 配置通知栏网络图标
	    template.setLogoUrl("");
	    // 设置通知是否响铃，震动，或者可清除
	    template.setIsRing(true);
	    template.setIsVibrate(true);
	    template.setIsClearable(true);
	    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
	    template.setTransmissionType(1);
	    template.setTransmissionContent(actionType);
	    // 设置定时展示时间
	    // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
	    return template;
	}
	@SuppressWarnings("deprecation")
	private   TransmissionTemplate getIOSTemplate(String title,String msg,String actionType) {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    template.setTransmissionContent(actionType);
	    template.setTransmissionType(1);
	    APNPayload payload = new APNPayload();
	    payload.setBadge(1);
	    payload.setContentAvailable(1);
	    payload.setSound("default");
	    payload.setCategory("$由客户端定义");
	    //简单模式APNPayload.SimpleMsg 
	    //payload.setAlertMsg(new APNPayload.SimpleAlertMsg("到账100000"));
	    //字典模式使用下者
	    payload.setAlertMsg(getDictionaryAlertMsg(title,msg));
	    template.setAPNInfo(payload);
	    return template;
	}
	private  APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(String title,String msg){
	    APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
	    alertMsg.setBody(msg);
	    alertMsg.setActionLocKey("ActionLockey");
	    alertMsg.setLocKey("LocKey");
	    alertMsg.addLocArg("loc-args");
	    alertMsg.setLaunchImage("launch-image");
	    // IOS8.2以上版本支持
	    alertMsg.setTitle(title);
	    alertMsg.setTitleLocKey("TitleLocKey");
	    alertMsg.addTitleLocArg("TitleLocArg");
	    return alertMsg;
	}
}
