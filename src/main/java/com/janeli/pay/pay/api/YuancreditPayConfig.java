/**
 * ===================================================================================
 * <p>
 * <p>
 * <p>
 * <p>
 * ===================================================================================
 */
package com.janeli.pay.pay.api;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

/**
 * @公司名称：
 * @类功能说明：

 * @创建日期： 创建于14:12 2017/7/7
 * @修改说明：
 * @修改日期： 修改于14:12 2017/7/7
 * @版本号： V1.0.0
 */
public interface YuancreditPayConfig extends WXPayConfig {

    /**
     * 获取 App ID
     *
     * @return App ID
     */
    @Override
    public String getAppID();


    /**
     * 获取 Mch ID
     *
     * @return Mch ID
     */
    @Override
    public String getMchID();


    /**
     * 获取 API 密钥
     *
     * @return API密钥
     */
    @Override
    public String getKey();


    /**
     * 获取商户证书内容
     *
     * @return 商户证书内容
     */
    @Override
    public InputStream getCertStream();

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     *
     * @return
     */
    @Override
    public int getHttpConnectTimeoutMs();

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     *
     * @return
     */
    @Override
    public int getHttpReadTimeoutMs();



    default String getAliPublicKey(){
        return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr/SJypvD9WrqI1J45bRU++v9Tajp8XF4jZ5nu5ZtEu9VqcvhJofcXM/F9n0hgxIs59zwpToFyr10WuTgkdmazj3F41aEzBkyDb51OXsvEV+OMG0f9f5V2jqL5NcmVFm2aFmWnbEBxAKAUU/XAxyOqh2nb/ow+TkWlRCnwNwBKcVD2SR3Jeg7EKutfCVsSC16Jvy9J6eGLpRoYHWaTo49CcbPL6nG3KWKMK3cmcGsd4DUyWSmL+SS1dwU+fEGd3VZc+qgMknXf7zX/g51E8l8xjG1fiJo6Hgyn+58Mjs+uBp8a2iVGl1H9RtW78Usqw/bOhTyoJObWPTkBurgedIwWwIDAQAB";
    }
}
