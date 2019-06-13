package com.janeli.pay.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


/**
 * 
 * @author ganjing RSA算法工具类
 */
public class RSAKeyUtil {
	//RSA秘钥长度1024
    public static int EncryptionLength_1024 = 1024;
    //RSA秘钥长度2048
    public static int EncryptionLength_2048 = 2048;
    //状态
    public static String CREATE_KEY_SUCCESS = "200";
    //状态
    public static String CREATE_KEY_FAIL = "500";
    //pubicKeyStr
    public static String RETURNMAP_PUBLICKEY_NAME = "pubicKeyStr";
    //privateKeyStr
    public static String RETURNMAP_PRIVATEKEY_NAME = "privateKeyStr";
    //生成公钥文件后缀名
    public static String PUBLICKEY_SUFFIX_NAME = "-public";
    //生成私钥文件后缀名
    public static String PRIVATEKEY_SUFFIX_NAME = "-private";
    //key文件类型
    public static String KEY_TYPE_PEM = ".pem";
    //pkcs8文件编码前缀
    public static String PKCS8_PREFIX_NAME = "-pkcs8";
    
    private static Cipher cipher = null;
    
    
	/**
	 * 得到RSA基类
	 * @param number
	 */
	public static KeyPair genRSAKeyPair(int EncryptionLength) {
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		keyPairGen.initialize(EncryptionLength, new SecureRandom());
		KeyPair keyPair = keyPairGen.generateKeyPair();
		return keyPair;
	}
//	/**
//	 * 得到key的字符串
//	 * @param key
//	 * @return
//	 * @throws Exception
//	 */
//	public static Map<String,String> getKeyString(int EncryptionLength) {
//		Map<String,String> map = new HashMap<String,String>();
//		try {
//			//得到RSA基类
//			KeyPair keyPair = genRSAKeyPair(EncryptionLength);
//			//公钥
//			byte[] pubicKeyBytes = keyPair.getPublic().getEncoded();
//			String pubicKeyStr = Base64.toBase64String(pubicKeyBytes);
//			map.put(RETURNMAP_PUBLICKEY_NAME, pubicKeyStr);
//			//私钥
//			byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
//			String privateKeyStr = Base64.toBase64String(privateKeyBytes);
//			map.put(RETURNMAP_PRIVATEKEY_NAME, privateKeyStr);
//			map.put("status", CREATE_KEY_SUCCESS);
//		} catch (Exception e) {
//			map.put("status", CREATE_KEY_FAIL);
//			map.put("msg", "生成公钥或者私钥时失败。");
//		}
//        return map;
//    }
	 /**
     * 得到公钥
     * @param key 
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
          byte[] keyBytes;
          keyBytes = Base64.decode(key);
          X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
          PublicKey publicKey = keyFactory.generatePublic(keySpec);
          return publicKey;
    }
    /**
     * 得到私钥
     * @param key
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
          byte[] keyBytes;
          keyBytes = Base64.decode(key);
          PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
          PrivateKey privateKey =keyFactory.generatePrivate(keySpec);
          return privateKey;
    }
    /** 
     * 公钥加密过程 
     * @param publicKey 公钥 
     * @param plainTextData 明文数据 
     * @return 
     * @throws Exception 加密过程中的异常信息 
     */   
	public static synchronized byte[] publicKey_encrypt(PublicKey publicKey,
			byte[] plainTextData) throws Exception {
		if (publicKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		try {
			if (cipher == null) {
				cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
			}
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}
	
    /** 
     * 私钥加密过程 
     * @param publicKey 公钥 
     * @param plainTextData 明文数据 
     * @return 
     * @throws Exception 加密过程中的异常信息 
     */   
	public static synchronized byte[] privateKey_encrypt(PrivateKey privateKey,
			byte[] plainTextData) throws Exception {
		if (privateKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		try {
			if (cipher == null) {
				cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
			}
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}
	
    /** 
     * 私钥解密过程 
     * @param privateKey 私钥 
     * @param cipherData 密文数据 
     * @return 明文 
     * @throws Exception 解密过程中的异常信息 
     */   
	public static synchronized byte[] privateKey_decrypt(PrivateKey privateKey,
			byte[] cipherData) throws Exception {
		if (privateKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		try {
			if (cipher == null) {
				cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
			}
			
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}
 
    /** 
     * 公钥解密过程 
     * @param privateKey 私钥 
     * @param cipherData 密文数据 
     * @return 明文 
     * @throws Exception 解密过程中的异常信息 
     */   
	public static synchronized byte[] publicKey_decrypt(PublicKey publicKey,
			byte[] cipherData) throws Exception {
		if (publicKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		try {
			if (cipher == null) {
				cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
			}
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}
 
    /** 
     * 从文件中输入流中加载公钥 
     * @param in 公钥输入流 
     * @throws Exception 加载公钥时产生的异常 
     */  
    public String loadPublicKey(InputStream in) throws Exception{  
    	BufferedReader br = null;
        try {  
            br= new BufferedReader(new InputStreamReader(in));  
            String readLine= null;  
            StringBuilder sb= new StringBuilder();  
            while((readLine= br.readLine())!=null){  
                if(readLine.charAt(0)=='-'){  
                    continue;  
                }else{  
                    sb.append(readLine);  
                    sb.append('\r');  
                }  
            }  
            return sb.toString();  
        } catch (IOException e) {  
            throw new Exception("公钥数据流读取错误");  
        } catch (NullPointerException e) {  
            throw new Exception("公钥输入流为空");  
        }finally {  
            if (in != null) {  
            	in.close();
            } 
            if (br != null) {  
            	 br.close();
            } 
            
        }   
    }  
  
  
    /** 
     * 从字符串中加载公钥 
     * @param publicKeyStr 公钥数据字符串 
     * @throws Exception 加载公钥时产生的异常 
     */  
    public void loadPublicKey(String publicKeyStr) throws Exception{  
        try {  
            //BASE64Decoder base64Decoder= new BASE64Decoder();  
            byte[] buffer= Base64.decode(publicKeyStr);
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");  
            X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);  
            keyFactory.generatePublic(keySpec);  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此算法");  
        } catch (InvalidKeySpecException e) {  
            throw new Exception("公钥非法");  
        } catch (NullPointerException e) {  
            throw new Exception("公钥数据为空");  
        }  
    }  
  
    /** 
     * 从文件中加载私钥 
     * @param keyFileName 私钥文件名 
     * @return 是否成功 
     * @throws Exception  
     */  
    public String loadPrivateKey(InputStream in) throws Exception{ 
    	BufferedReader br = null;
        try {  
        	br = new BufferedReader(new InputStreamReader(in));  
            String readLine= null;  
            StringBuilder sb= new StringBuilder();  
            while((readLine= br.readLine())!=null){  
                if(readLine.charAt(0)=='-'){  
                    continue;  
                }else{  
                    sb.append(readLine);  
                    sb.append('\r');  
                }  
            }  
            return sb.toString();
        } catch (IOException e) {  
            throw new Exception("私钥数据读取错误");  
        } catch (NullPointerException e) {  
            throw new Exception("私钥输入流为空");  
        }finally {  
            if (in != null) {  
            	in.close();
            } 
            if (br != null) {  
            	 br.close();
            } 
            
        }     
    }  
  
    public void loadPrivateKey(String privateKeyStr) throws Exception{  
        try {  
            //BASE64Decoder base64Decoder= new BASE64Decoder();  
            byte[] buffer=  Base64.decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);  
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");  
            keyFactory.generatePrivate(keySpec); 
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此算法");  
        } catch (InvalidKeySpecException e) {  
            throw new Exception("私钥非法");  
        } catch (NullPointerException e) {  
            throw new Exception("私钥数据为空");  
        }  
    }  
    public static PrivateKey get(String filename)throws Exception {  
        File f = new File(filename);  
        FileInputStream fis = new FileInputStream(f);  
        DataInputStream dis = new DataInputStream(fis);  
        byte[] keyBytes = new byte[(int)f.length()];  
        dis.readFully(keyBytes);  
        fis.close();
        dis.close(); 
        PKCS8EncodedKeySpec spec =new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory kf = KeyFactory.getInstance("RSA");  
        return kf.generatePrivate(spec);  
      }  
    
  public static String encrypt(String value,PublicKey publicKey) throws Exception{
    	
    	byte[] strPubbyteCiphertext = value.getBytes("utf-8");
		byte[] strPubbyte = RSAKeyUtil.publicKey_encrypt(publicKey,strPubbyteCiphertext);
		
		return new String(Base64.encode(strPubbyte),"utf-8");
    }

  
    public static void main(String[] args) {
		try {
			String str = "嘻嘻哈哈的人生";
			File publicFile = new File("/usr/local/uploadKey/skh1234567-public.pem");
			File privateFile = new File("/usr/local/skh1234567_512_private_key_pkcs8.pem");
			RSAKeyUtil util = new RSAKeyUtil();
			//公钥加密
			InputStream inputStream = new FileInputStream(publicFile);
			String keyStr = util.loadPublicKey(inputStream);
			PublicKey publicKey = getPublicKey(keyStr);
			byte[] byte1 = RSAKeyUtil.publicKey_encrypt(publicKey, str.getBytes("utf-8"));
			//私钥解密
			InputStream inputStream1 = new FileInputStream(privateFile);
			String keyStr1 = util.loadPrivateKey(inputStream1);
			PrivateKey privateKey = getPrivateKey(keyStr1);
			byte[] byte2 = RSAKeyUtil.privateKey_decrypt(privateKey, byte1);
			//私钥加密
			byte[] byte3 = RSAKeyUtil.privateKey_encrypt(privateKey,str.getBytes("utf-8"));
			//公钥解密
			byte[] byte4 = RSAKeyUtil.publicKey_decrypt(publicKey, byte3);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
}
