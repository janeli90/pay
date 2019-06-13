
package com.janeli.pay.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.Map.Entry;
import java.util.TreeMap;


public class YFTSignUtil {
    private static String projectPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    public static String signData(TreeMap<String, String> treeMap) throws Exception {
        LoggerUtils.logInfo("projectPath:" + projectPath);
        return signData(treeMap, projectPath + "yft/hnzxhj_prv.pem");
    }

    public static boolean verferSignData(TreeMap<String, String> treeMap) throws Exception {
        return verferSignData(treeMap, projectPath + "yft/hnzxhj_prv.pem");
    }

    public static String signData(TreeMap<String, String> treeMap, String privateKeyFile) throws Exception {
        TreeMap<String, String> tMap = new TreeMap<String, String>();
        for (Entry<String, String> entry : treeMap.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                tMap.put(entry.getKey(), entry.getValue());
            }
        }
        StringBuffer buf = new StringBuffer();
        for (String key : tMap.keySet()) {
            buf.append(key).append("=").append((String) tMap.get(key)).append("&");
        }
        String signatureStr = buf.substring(0, buf.length() - 1);
        LoggerUtils.logInfo("易富通加密排序除空的参数串:" + signatureStr);
        String signData = YFTRSACoder.signMS(signatureStr, privateKeyFile);
        return signData;
    }

    public static boolean verferSignData(TreeMap<String, String> treeMap, String publicKeyFile) throws Exception {
        if (treeMap != null && treeMap.size() > 0) {
            StringBuffer buf = new StringBuffer();
            String signature = "";
            for (String key : treeMap.keySet()) {
                if ("signature".equals(key)) {
                    signature = treeMap.get(key).replace(" ", "+");
                } else if (StringUtils.isNoneBlank(treeMap.get(key))) {
                    buf.append(key).append("=").append((String) treeMap.get(key)).append("&");
                } else {
                    //忽略为空的字段
                }
            }
            String signatureStr = buf.substring(0, buf.length() - 1);
            return YFTRSACoder.verifyMS(signatureStr, signature, publicKeyFile);
        }
        return false;
    }
}
