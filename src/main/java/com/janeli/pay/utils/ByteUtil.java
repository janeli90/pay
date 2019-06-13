package com.janeli.pay.utils;

/**
 * 加密机辅助基础功能
 */
public final class ByteUtil {

    private ByteUtil() {
    }

    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 合并
     *
     * @param a
     * @param b
     * @return
     */
    public static byte[] byteAdd(byte[] a, byte[] b) {
        byte[] ret = new byte[a.length + b.length];
        System.arraycopy(a, 0, ret, 0, a.length);
        System.arraycopy(b, 0, ret, a.length, b.length);
        return ret;
    }

    /**
     * 合并
     *
     * @param a
     * @param b
     * @return
     */
    public static byte[] byteAdd(byte[] a, Byte... b) {
        byte[] ret = new byte[a.length + b.length];
        System.arraycopy(a, 0, ret, 0, a.length);
        for (int i = 0, le = b.length; i < le; i++) {
            ret[a.length + i] = b[i];
        }
        //System.arraycopy(b, 0, ret, a.length, b.length);
        return ret;
    }

    /**
     * 包长度计算
     *
     * @param cmdLen
     * @param lengthBytes
     * @return
     */
    public static byte[] getPackageLenByte(int cmdLen, int lengthBytes) {
        int l = cmdLen;
        byte[] buf = new byte[lengthBytes];
        int pos = 0;
        if (lengthBytes == 4) {
            buf[0] = (byte) ((l & 0xff000000) >> 24);
            pos++;
        }
        if (lengthBytes > 2) {
            buf[pos] = (byte) ((l & 0xff0000) >> 16);
            pos++;
        }
        if (lengthBytes > 1) {
            buf[pos] = (byte) ((l & 0xff00) >> 8);
            pos++;
        }
        buf[pos] = (byte) (l & 0xff);
        return buf;
    }

    /**
     * 16进制字符串异或
     *
     * @param first
     * @param second
     * @return
     */
    public static String hexXor(String first, String second) {
        int le = first.length() / 2;
        StringBuilder tempStr = new StringBuilder();
        for (int i = 0; i < le; i++) {
            Integer temp = Integer.parseInt(
                    first.substring(i * 2, (i + 1) * 2), 16)
                    ^ Integer
                    .parseInt(second.substring(i * 2, (i + 1) * 2), 16);
            String hex = Integer.toHexString(temp);
            if (hex.length() == 1) {
                tempStr.append("0");
            }
            tempStr.append(hex.toUpperCase());
        }
        return tempStr.toString();
    }

    /**
     * 取字节数组的16进制字符串
     *
     * @param bs
     * @return
     */
    public static String getHexStr(byte[] bs) {
        StringBuffer sb = new StringBuffer("");
        if (bs != null) {
            for (int i = 0; i < bs.length; i++) {
                sb.append(getHexStr(bs[i]));
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 16进制字符串转字节数组
     */
    public static byte[] getHexByte(String byteStr) {
        if (byteStr.length() % 2 != 0) {
            byteStr = "0" + byteStr;
        }
        byte[] retByte = new byte[byteStr.length() / 2];

        for (int i = 0; i < byteStr.length() / 2; i++) {
            byte[] b = new byte[1];
            b[0] = int2byte(Integer.parseInt(byteStr
                    .substring(2 * i, 2 * i + 2), 16))[3];

            retByte[i] = int2byte(Integer.parseInt(byteStr.substring(2 * i,
                    2 * i + 2), 16))[3];
        }
        return retByte;
    }

    /**
     * 取字节的16进制字符串
     *
     * @param bs
     * @return
     */
    public static String getHexStr(byte bs) {
        String retStr = "";
        if (Integer.toHexString((int) bs).length() > 1) {
            retStr += Integer.toHexString((int) bs).substring(
                    Integer.toHexString((int) bs).length() - 2);
        } else {
            retStr += "0"
                    + Integer.toHexString((int) bs).substring(
                    Integer.toHexString((int) bs).length() - 1);
        }
        return retStr;
    }

    /**
     * int转换为字节数组
     *
     * @param n
     * @return
     */
    public static byte[] int2byte(int n) {
        byte b[] = new byte[4];
        b[0] = (byte) (n >> 24);
        b[1] = (byte) (n >> 16);
        b[2] = (byte) (n >> 8);
        b[3] = (byte) n;
        return b;
    }
}
