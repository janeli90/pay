package com.janeli.pay.utils;

import com.alibaba.fastjson.JSONObject;
import com.janeli.pay.common.Common;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ZhangJinChang
 * @ClassName: SendSmsUtils
 * @Description: TODO()
 * @date 2017年10月27日 9:56
 */
public class SendSmsUtils {

    public static final String BASE_URL = "https://api.miaodiyun.com/20150822";
    private static final String OPERATION = "/industrySMS/sendSMS";
    //    private static final String ACCOUNT_SID = "3053a0729b9f45a99a834b61bda2ada1";
//    private static final String AUTH_TOKEN = "40bbb995cb9e4bd9939cd1ae0b5126af";
    private static final String ACCOUNT_SID = "f31944d4149640d9a9b3ce12ddf2b38e";
    private static final String AUTH_TOKEN = "fefa26b273104cf986eb9f5d163e7a1a";

    private static final String hostUrl = "http://106.15.192.101:8566";
    private static final String signKey="wlbS9zFcNeakLulg0rVQCIxfuY1mVoq9";
    private static final String corgId="WangQuan";

    /**
     * 短信通道参数
     *
     * @return
     */
    private static String createCommonParam() {
        // 时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        // 签名
        String sig = DigestUtils.md5Hex(ACCOUNT_SID + AUTH_TOKEN + timestamp);
        return "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=" + "JSON";
    }

    /**
     * 发送短信
     *
     * @param smsContent
     * @param usrTel
     * @return
     */
    public static Map<String, Object> sendSms(String smsContent, String usrTel) {

        Map<String,String> map = new TreeMap<String,String>();
        map.put("corgId", corgId);
        map.put("dateTime", DateTime.now().toString("yyyyMMddHHmmss"));
        map.put("phone_num", usrTel);
        map.put("sms_content", smsContent);
        String sign= MD5SignUtil.sign(map,signKey);
        map.put("sign", sign);
        LoggerUtils.logInfo("手机号码:" + usrTel + ",短信内容:" + smsContent + ",开始发送短信.......!");
        String reqUrl=hostUrl+"/sms/verifyCodeSmsSend";
        String responseContent = HttpClientUtils.sendPostRequest(reqUrl,
                map,"utf-8","utf-8");
        LoggerUtils.logInfo("手机号码:" + usrTel + ",短信内容:" + smsContent + "。短信网关响应内容："+responseContent);

        if (StringUtils.isNotBlank(responseContent)) {
            // 提交请求
            JSONObject result = JSONObject.parseObject(responseContent);
            return Common.returnMap(result.getString("resMsg"), result.getString("resCode").equals("0000") ? "0":"01");
        }else {
            LoggerUtils.logInfo("手机号码:" + usrTel + ",短信内容:" + smsContent + ",失败原因：发送验证通道失败!");
            return Common.errorMap("发送验证通道失败!", "04");
        }
    }
}
