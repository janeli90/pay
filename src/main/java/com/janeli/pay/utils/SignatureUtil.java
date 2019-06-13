package com.janeli.pay.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 
	 * 
	 * @Description:签名工具类 
	 * @author: ganjing
	 * @date:2017年6月26日
	 * @param:
 */

public class SignatureUtil {

	private final static String SIGN_ALGORITHMS = "SHA1WithRSA";
	private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";
	public final static String MD5 = "MD5";
	public final static String SHA = "SHA";
	public final static String STATUS_SUCCESS = "200";
	public final static String STATUS_FAIL = "500";
	public final static String SIGN_VALUE = "sign";
	/**
	 * 
		 * 
		 * @Description: 参数排序
		 * @author: ganjing
		 * @date:2017年6月26日
		 * @param:
	 */
	public static String getSignatureContent(Map<String, String> requestParams) {

		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(requestParams.keySet());
		Collections.sort(keys);
		int index = 0;
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = requestParams.get(key);
			if (StringUtils.areNotEmpty(key, value)) {
				content.append((index == 0 ? "" : "&") + key + "=" + value);
				index++;
			}
		}
		return content.toString();
	}
	/**
	 * 
		 * 
		 * @Description: 签名
		 * @author: ganjing
		 * @date:2017年6月26日
		 * @param:
	 */
	public static String rsaSign(String content, PrivateKey privateKey) {

		try {
			//PrivateKey priKey = getPrivateKeyFromPKCS8("RSA", privateKey);

			Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

			signature.initSign(privateKey);

			signature.update(content.getBytes("UTF-8"));

			byte[] signed = signature.sign();

			return new String(Base64.encodeBase64(signed));
		} catch (Exception ie) {
			throw new RuntimeException("");
		} 

	}
	/**
	 * 
		 * 
		 * @Description: 得到私钥
		 * @author: ganjing
		 * @date:2017年6月26日
		 * @param:
	 */
	public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, String privateKey) throws Exception {
		if (StringUtils.isEmpty(algorithm)) {
			return null;
		}

		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

		byte[] encodedKey = Base64.decodeBase64(privateKey);

		return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
	}
	/**
	 * 
		 * 
		 * @Description: 验证签名
		 * @author: ganjing
		 * @date:2017年6月26日
		 * @param:
	 */
	public static boolean validateSign(String content, PublicKey pubKey, String sign) {

		try {
			

			Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);

			signature.update(content.getBytes("UTF-8"));

			return signature.verify(Base64.decodeBase64(sign));

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("", e);
		}

	}
	/**
	 * 
		 * 
		 * @Description: 得到公钥
		 * @author: ganjing
		 * @date:2017年6月26日
		 * @param:
	 */
	public static PublicKey getPublicKeyFromPKCS8(String algorithm, String publicKey) throws Exception {

		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

		byte[] encodeKey = Base64.decodeBase64(publicKey);

		return keyFactory.generatePublic(new X509EncodedKeySpec(encodeKey));

	}

}
