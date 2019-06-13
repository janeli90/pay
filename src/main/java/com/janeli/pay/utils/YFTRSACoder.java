package com.janeli.pay.utils;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class YFTRSACoder {
    private static String projectPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private static final String Algorithm = "RSA";
    public static final String Signature_Algorithm_MD5 = "MD5withRSA";
    public static final String Signature_Algorithm_SHA1 = "SHA1WithRSA";
    private static final String yft_public_key = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7dLp5XjVY+LjAsct3wfg\n" +
            "3gHnrKNsepRp9F0EZNmPR+3GHmRymQWQcUxVoEOMPzapk96fWBijNsGH/A4HgwSH\n" +
            "uOURWcan5Z2Rpg3oxxNhOkdoM7G88qWSTKmrHfVSJ15XSsIGSVtTpt5RIbURBiLx\n" +
            "t+5hJFQv6mc4CdS8iN0c9FEFPg35oVs/8SjMw+4jXIcXIpte2p1RMAtjc4MqbD5+\n" +
            "ouBg7TE8VzAE/weJD1ebBKx4+ZUDfqRb5u4nn8tG03sfymtasEtzEldaFV8gG/CF\n" +
            "0y5Bu+LLFhx8nywW3QewFSALOa5WHQ4M3R94wxvqFEuzEV5WxGVhUzhI4jC89+YR\n" +
            "kQIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    private static final String yft_private_key = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEogIBAAKCAQEA7dLp5XjVY+LjAsct3wfg3gHnrKNsepRp9F0EZNmPR+3GHmRy\n" +
            "mQWQcUxVoEOMPzapk96fWBijNsGH/A4HgwSHuOURWcan5Z2Rpg3oxxNhOkdoM7G8\n" +
            "8qWSTKmrHfVSJ15XSsIGSVtTpt5RIbURBiLxt+5hJFQv6mc4CdS8iN0c9FEFPg35\n" +
            "oVs/8SjMw+4jXIcXIpte2p1RMAtjc4MqbD5+ouBg7TE8VzAE/weJD1ebBKx4+ZUD\n" +
            "fqRb5u4nn8tG03sfymtasEtzEldaFV8gG/CF0y5Bu+LLFhx8nywW3QewFSALOa5W\n" +
            "HQ4M3R94wxvqFEuzEV5WxGVhUzhI4jC89+YRkQIDAQABAoIBAAQqeaZnY3WId+mI\n" +
            "i6kofivlZKWFh1TuXJzHNFh/5x6fb6UApXFAw7L4X1ebV02UK4xHjgsBA3rmEPe9\n" +
            "VyxYW/hFKvSjojVHeVGrVLSIBl/6+DxPlLu7YsCtCC5SKco+wNCybWHDk8eMhWdN\n" +
            "wKqW2h0as+DrCJvWiwjBDTxogyVUbVjh/fTpnFj2ekcSTyuMLU49NOv83lQtSCnS\n" +
            "2xhksZ4WBFHDz+jobCYKeeJkq5UuG1ZQEqPVtUjzmeo34VMcZ0FbGgPh2tNx0Ybk\n" +
            "g2JHWMlVF7jSUa8ldkywKb5ShrLBSD6AAmfO0JTrF7YMmLOaloHGqEo0gk3dDOJT\n" +
            "j7S2ywECgYEA/wiM3L1c86Z9zEN7EBal/uwcOEC58lxBnPvZ3qBvFdDyk9OI66GT\n" +
            "Gxd/8HJ7jyfd1+zr23seSj8qgswhydjiFf1wfjIId+414FemFJiS4osG9yuSCxqA\n" +
            "Ya7CDiW2LI7I2+V1WgJjEvi57Oha7MtkNXgDPZN1Fo85dZuqmDRYDJkCgYEA7rmq\n" +
            "Zyty3gDlJoL+ngI74wwDWhHoNKu8UdEUISXyo/aU9UnLnwsrZVdVqV3Z0lzgWn0u\n" +
            "veNVpup3L5Ilk4DnAWFqf/QIZePDmbGsDKyuIAIo1xIXGqN+WOYNngP4Fmqxf6gD\n" +
            "Q4D5YlBmTETU5EO19e+ebbzNZBqpeVLpl8TID7kCgYBSV8hjXSCt6oujcAZD44wl\n" +
            "CsbxwrRG9FKbBRYLM3GqcZRg9mO28Kw2ixfb4prSVeTi2rQsYUweXNa09IPUUBpv\n" +
            "Ty0LEcx6LIdor+QHslt6mbjeBUtaGmxpHau6NaLkMBXaKfQB49NpRLWDMGJrIiBw\n" +
            "wxcIwpuDdSrwNhqWmg/zwQKBgAFmFRmFp6alWrJfEh7a5H7wExT0BehJGVFhaDzJ\n" +
            "fCJ5z2WgiRDlf9uYJ2hWpHAXGzlsglmae2X++bPNp2q11qSrbXRSebofG4ZLBDcO\n" +
            "y/ZkDNk1wezmCFd9AZdrboizF1coJNzAZLWuwuqvfI/Z1bcbMFJ0SL/NaDDNy5CY\n" +
            "FpQBAoGAF7FqARDiZQ2i6IRzprSGF7Oos0HQdOYVUt9GIsE1TTV6qREgZyXpup8m\n" +
            "OqW70tvCVuFT2bKHnnbpFqvvjesAg6Lp0IalXtRl/DbnZPouZIjzYc9uPvv6k6X7\n" +
            "P+qxKO9326/8fyeYcUAWxj/vhR3n+93wL0dwGcPNTN1fDQw4u64=\n" +
            "-----END RSA PRIVATE KEY-----\n";

    public static String signMS(String data, String privateKeyFile) throws Exception {
        PrivateKey privateKey = getPrivateKeyPem(privateKeyFile);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance(Algorithm);
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        Signature signature = Signature.getInstance(Signature_Algorithm_SHA1);
        signature.initSign(priKey);
        signature.update(data.getBytes("UTF-8"));
        return YFTBase64.encode(signature.sign());
    }

    private static PrivateKey getPrivateKeyPem(String fileName) throws Exception {
//    	String key = readFile(fileName, "UTF-8");
        String key = yft_private_key;
        byte[] keyBytes = buildPKCS8Key(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(Algorithm);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    private static byte[] buildPKCS8Key(String privateKey) throws IOException {
        if (privateKey.contains("-----BEGIN PRIVATE KEY-----")) {
            return YFTBase64.decode(privateKey.replaceAll("-----\\w+ PRIVATE KEY-----", ""));
        } else if (privateKey.contains("-----BEGIN RSA PRIVATE KEY-----")) {
            final byte[] innerKey = YFTBase64.decode(privateKey.replaceAll("-----\\w+ RSA PRIVATE KEY-----", ""));
            final byte[] result = new byte[innerKey.length + 26];
            System.arraycopy(YFTBase64.decode("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKY="), 0, result, 0, 26);
            System.arraycopy(BigInteger.valueOf(result.length - 4).toByteArray(), 0, result, 2, 2);
            System.arraycopy(BigInteger.valueOf(innerKey.length).toByteArray(), 0, result, 24, 2);
            System.arraycopy(innerKey, 0, result, 26, innerKey.length);
            return result;
        } else {
            return YFTBase64.decode(privateKey);
        }
    }

    private static String readFile(String filePath, String charSet) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        try {
            FileChannel fileChannel = fileInputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            return new String(byteBuffer.array(), charSet);
        } finally {
            fileInputStream.close();
        }

    }

    public static boolean verifyMS(String data, String signData, String publicKeyFile)
            throws Exception {
        PublicKey publicKey = getPubliceKeyPem(publicKeyFile);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance(Algorithm);
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(Signature_Algorithm_SHA1);
        signature.initVerify(pubKey);
        signature.update(data.getBytes("UTF-8"));
        return signature.verify(YFTBase64.decode(signData));
    }

    private static PublicKey getPubliceKeyPem(String fileName) throws Exception {
//    	String key = readFile(fileName, "UTF-8");
        String key = yft_public_key;
        key = key.replaceAll("\\-{5}[\\w\\s]+\\-{5}[\\r\\n|\\n]", "");
        KeyFactory keyFactory = KeyFactory.getInstance(Algorithm);
        byte[] encodedKey = YFTBase64.decode(key);
        PublicKey pubKey = keyFactory
                .generatePublic(new X509EncodedKeySpec(encodedKey));
        return pubKey;
    }

    /**
     * �ù�Կ���ַ������м���
     * <p>
     * ˵����
     *
     * @param ��������
     * @param publickey ��Կ�洢·�� +�ļ���  ���磺 C:\\publickey.dat
     * @return
     * @throws Exception
     */
    public static String pemencryptStr(String data, String publickey) throws Exception {
        byte[] bytes = pemencrypt(data, publickey);
        return YFTCoder.encryptBASE64(bytes);
    }

    public static String pemencryptStr(String data) throws Exception {
        return pemencryptStr(data, projectPath + "yft/hnzxhj_pub.pem");
    }

    /**
     * �ù�Կ���ַ������м���
     * <p>
     * ˵����
     *
     * @param ��������
     * @param publickey ��Կ�洢·�� +�ļ���  ���磺 C:\\publickey.dat
     * @return
     * @throws Exception
     */
    public static byte[] pemencrypt(String data, String publickey) throws Exception {
        PublicKey keyPublic = getPubliceKeyPem(publickey);

        // ��������
        Cipher cp = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cp.init(Cipher.ENCRYPT_MODE, keyPublic);
        // �����ݼ���  


        return cp.doFinal(data.getBytes("UTF-8"));

    }
}
