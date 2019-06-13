package com.janeli.pay.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.janeli.pay.common.Common;

import java.util.Map;

/**
 * Created by lid on 2018/6/29 0029.
 * 阿里云短信发送工具
 */
public class PhoneMessage {
    public static void main(String args[]) throws ClientException {

        PhoneMessage.sendMsg("13087345959", PhoneMessage.loginTemplateCode, "养提还",
                "654321");

//        PhoneMessage.sendPlanFailedMsg("17375808066", PhoneMessage.planFaildCode, "信智还",
//                "123456", "《测试》");
    }

    public static final String appkey = "LTAIA4Y7Jj1Hvzjf";
    public static final String secret = "W3P7XUuGkkalrCzBZn55z0rUy7M3b5";

//    public static final String signName = "扫码钱包";
    public static final String signName = "01卡管家";

    //注册短信模板
    public static final String registerTemplateCode = "SMS_129758246";

    //登录短信模板
    public static final String loginTemplateCode = "SMS_133001302";

    //密码重置短信模板
    public static final String resetPasswordCode = "SMS_130790011";

    //计划失败短信模板  尊敬的用户，您的计划${code1}因${code2}失败，请及时关注并重置计划！
    public static final String planFaildCode = "SMS_143610504";

    /**
     * 发送短信
     *
     * @param phoneNo       电话号码
     * @param templateCode  模板id
     * @param templateParam 模板参数
     * @throws ClientException
     */
    public static Map<String, Object> sendMsg(String phoneNo, String templateCode, String signName, String templateParam) throws ClientException {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK

        final String accessKeyId = PhoneMessage.appkey;//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = PhoneMessage.secret;//你的accessKeySecret，参考本文档步骤2

        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        //request.setPhoneNumbers("17375808066");
        request.setPhoneNumbers(phoneNo);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        //request.setTemplateCode("SMS_129758246");
        request.setTemplateCode(templateCode);

        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        //request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");

        //request.setTemplateParam("{\"code\":\"123456\"}");
        request.setTemplateParam("{\"code\":\"" + templateParam + "\"}");

        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        LoggerUtils.logInfo(phoneNo + "sendSmsResponse:" + sendSmsResponse.getCode() + "," + sendSmsResponse.getMessage());
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            LoggerUtils.logInfo(phoneNo+"请求短信验证成功！{"+templateCode+"}");
            //请求成功
            return Common.returnMap("请求短信验证成功", "0");
        }else{
            LoggerUtils.logInfo(phoneNo+"请求短信验证失败！{"+templateCode+"}");
            return Common.returnMap("请求短信验证失败", "01");
        }
    }

    /**
     * 发送还款计划失败提醒短信
     *
     * @param phoneNo       电话号码
     * @param templateCode  模板id
     * @param templateParam 模板参数
     * @throws ClientException
     */
    public static Map<String, Object> sendPlanFailedMsg(String phoneNo, String templateCode, String signName, String param1, String param2) throws ClientException {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK

        final String accessKeyId = PhoneMessage.appkey;//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = PhoneMessage.secret;//你的accessKeySecret，参考本文档步骤2

        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        //request.setPhoneNumbers("17375808066");
        request.setPhoneNumbers(phoneNo);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        //request.setTemplateCode("SMS_129758246");
        request.setTemplateCode(templateCode);

        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
//        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");

        //request.setTemplateParam("{\"code\":\"123456\"}");
//        request.setTemplateParam("{\"code1\":\"" + param1 + "\"", "\"code2:\""+ param2 + "\"}");

        request.setTemplateParam("{\"code1\":\"" + param1 + "\", \"code2\":\"" + param2 + "\"}");

        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        System.out.println("sendSmsResponse:" + sendSmsResponse.getCode() + "," + sendSmsResponse.getMessage());

        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            LoggerUtils.logInfo(phoneNo+"请求短信验证成功！{"+templateCode+"}");
            //请求成功
            return Common.returnMap("请求短信验证成功", "0");
        }else{
            return Common.returnMap("请求短信验证失败"+sendSmsResponse.getMessage(), "01");
        }
    }
}
