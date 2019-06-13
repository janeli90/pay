package com.janeli.pay.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 *@ClassName: BankAuthUtils
 * @Description: 银行卡实名认证接口
 *
 */
@SuppressWarnings("all")
public class BankAuthUtils {
	/** 用于生成TransactionId的自增器 */
	private static final AtomicLong transAutoIncIdx = new AtomicLong(1000000000000l);
	/**
	 * =======
	 * 信联鉴权接口常量
	 * =======
	 **/
	private static final String url = "http://106.14.142.254:8757/xlzxop/certificate/auth/relCard.do";
	private static final String hostUrl = "http://106.15.192.101:8566";
	private static final String signKey="wlbS9zFcNeakLulg0rVQCIxfuY1mVoq9";
	private static final String corgId="WangQuan";

	//private static final String url = "http://123.58.34.208:8188/xlzxop/certificate/auth/relCard.do";
	/** 信联的机构标识 */
	private static final String insId = "INS6180408000001";
	/** 信联的操作员 */
	private static final String operId = "hlzxhj";
	/** 鉴权信息匹配 */
	public static final String BABK_AUTH_S = "S";
	/** 鉴权信息不匹配 */
	public static final String BABK_AUTH_E = "E";
	/** 鉴权信息不确定 */
	public static final String BABK_AUTH_R = "R";

	/**
	 * 鉴权用户输入银行卡的信息
	 * @param bankEntityMap
	 * @return Map<String,String>
	 */
	public static Map<String,String> checkBankInfo(Map<String,Object> bankEntityMap) {
		//调用接口确保没有错误
//        String authmsg =checkBankInfoXL(bankEntityMap);
//        JSONObject obj = JSONObject.parseObject(authmsg);
//        return getXLAuthMessageMap(obj);

		Map<String,String> map = new TreeMap<String,String>();
		map.put("corgId", corgId);
		map.put("dateTime", DateTime.now().toString("yyyyMMddHHmmss"));
		map.put("idName", bankEntityMap.get("idNm").toString());
		map.put("idNo", bankEntityMap.get("idNo").toString());
		map.put("cardNo", bankEntityMap.get("cardNo").toString());
		map.put("rsdTel", bankEntityMap.get("resTel").toString());
		String sign= MD5SignUtil.sign(map,signKey);
		map.put("sign", sign);

		String reqUrl=hostUrl+"/auth/authfourElement";
		String result = HttpClientUtils.sendPostRequest(reqUrl, map,"utf-8","utf-8");
		JSONObject json = JSONObject.parseObject(result);
		Map<String,String> retMap = new HashMap<>();
		for (String key : json.keySet()) {
			retMap.put(key, json.getString(key));
		}

		return  retMap;
	}

	/**
	 * 接口的返回信息包装
	 * @param index
	 * @param msg
	 * @return
	 */
	private static Map<String ,String> getAliAuthMessageMap(String index,String msg){
		Map<String,String> map = new HashMap<String, String>();
		map.put("CnlNo", "Ali");
		map.put("CorgMercId", "0000001");
		map.put("code", index);
		map.put("msg", msg);
		return map;
	}

	/**
	 * 接口的返回信息包装
	 * @param obj
	 * @return
	 */
	private static Map<String ,String> getXLAuthMessageMap(JSONObject obj){
		Map<String,String> map = new HashMap<String, String>();
		map.put("CnlNo", "XL");
		map.put("CorgMercId", "0000002");
		if("01".equals(obj.getString(("validateStatus")))){// validateStatus 为 01 是银行卡信息表示认证一致
			map.put("code", BABK_AUTH_S);
			map.put("msg", "鉴权成功");
			return map;
		}else{
			if("02".equals(obj.getString(("validateStatus")))){// validateStatus 为 02 是表示银行卡信息认证为不一致
				map.put("code", BABK_AUTH_E);
				if ("交易成功".equals(obj.getString("rspMsg")) || StringUtils.isEmpty(obj.getString("rspMsg"))) {
					map.put("msg", StringUtils.isNotEmpty(obj.getString(("remark"))) ? obj.getString(("remark")): "银行卡信息不确定，请换一张卡尝试");
				}else{
					map.put("msg", obj.getString("rspMsg"));
				}
			} else if("03".equals(obj.getString("validateStatus"))){ //validateStatus 为 03是表示银行卡信息认证为不确定 不收费
				map.put("code", BABK_AUTH_R);
				map.put("msg", "银行卡信息不确定，请换一张卡尝试");
				return map;
			}else if("04".equals(obj.getString("validateStatus"))){//validateStatus 为 04是表示银行卡信息认证失败  不收费
				map.put("code", BABK_AUTH_E);
				map.put("msg", "鉴权失败，请换一张卡尝试");
				return map;
			}
			return map;
		}
	}

	/**
	 * 鉴权用户输入银行卡的信息
	 * @param auth
	 * @return
	 */
	private static String checkBankInfoXL(Map<String,Object> map){
		Map<String, String> paramMap = new HashMap<String, String>();
		String transactionId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+ transAutoIncIdx.getAndDecrement();
		// 业务请求报文
		paramMap.put("cardNo", map.get("cardNo").toString());
		paramMap.put("name", map.get("idNm").toString());
		paramMap.put("cidNo", map.get("idNo").toString());
		paramMap.put("mobile", map.get("resTel").toString());
		paramMap.put("cooperSerialNo", transactionId);
		LoggerUtils.logInfo("cardNo:" + map.get("cardNo").toString());
		LoggerUtils.logInfo("idNm:" + map.get("idNm").toString());
		LoggerUtils.logInfo("idNo:" + map.get("idNo").toString());
		LoggerUtils.logInfo("resTel:" + map.get("resTel").toString());
		String str = null;
		try {
			str = request(insId,operId,paramMap, url);
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.logException(e);
		} catch (Exception e) {
			LoggerUtils.logException(e);
		}
		return str;
	}
	/**
	 * 向发送请求
	 * @param insId
	 * @param operId
	 * @param paramMap
	 * @param url
	 * @return
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	public static String  request(String insId,String operId,Map<String, String> paramMap,String url)
			throws Exception, UnsupportedEncodingException {
		String jsonStr = JSONArray.toJSONString(paramMap);
		RSAHelperUtils cipher = new RSAHelperUtils();// 初始化自己的私钥,对方的公钥以及密钥长度.
		cipher.initKey(2048);
		// 签名
		byte[] signBytes = cipher.signRSA(jsonStr.getBytes("UTF-8"), false,"UTF-8");
		// 对明文加密
		byte[] cryptedBytes = cipher.encryptRSA(jsonStr.getBytes("UTF-8"),false, "UTF-8");
		// 创建默认的 HttpClient 实例
		HttpClient httpClient = new DefaultHttpClient();
		String content = "";
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("insId", insId));
		formParams.add(new BasicNameValuePair("operId", operId));
		String sign = Base64.encodeBase64String(signBytes);// Base64编码
		String encrypt = Base64.encodeBase64String(cryptedBytes);// Base64编码
		formParams.add(new BasicNameValuePair("sign", sign));
		formParams.add(new BasicNameValuePair("encrypt", encrypt));
		//请求接口的操作
		try {
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(
					formParams, "UTF-8");
			httpPost.setEntity(urlEncodedFormEntity);
			HttpResponse httpResponse = null;
			httpResponse = httpClient.execute(httpPost);
			HttpEntity resEntity = httpResponse.getEntity();
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			LoggerUtils.logInfo("信联鉴权请求结果：" + statusCode);
			if (statusCode == HttpStatus.SC_OK) {
				LoggerUtils.logInfo("信联鉴权请求成功。");
				if (resEntity != null) {
					content = EntityUtils.toString(resEntity, "UTF-8");
				}
				EntityUtils.consume(resEntity);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.getConnectionManager().shutdown();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JSONObject obj = JSONObject.parseObject(content);
		//签名	
		signBytes=	Base64.decodeBase64(obj.getString("sign"));
		//密文
		cryptedBytes=Base64.decodeBase64(obj.getString("encrypt"));
		// 对密文解密
		byte[] decryptedBytes = cipher.decryptRSA(cryptedBytes, false, "UTF-8");
		String body=new String(decryptedBytes,"UTF-8");
		return body;
	}
}
