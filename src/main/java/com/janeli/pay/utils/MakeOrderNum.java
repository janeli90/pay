package com.janeli.pay.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: MakeOrderNum
 * @CreateTime 2015年9月13日 下午4:51:02
 * @author : mayi
 * @Description: 订单号生成工具，生成非重复订单号，理论上限1毫秒1000个，可扩展
 *
 */
public class MakeOrderNum {
	/**
	 * 锁对象，可以为任意对象
	 */
	private static Object lockObj = "lockerOrder";
	
	/**
	 * 锁对象，可以为任意对象
	 */
	private static Object lockObj_query = "lockerOrder";
	
	/**
	 * 订单号生成计数器
	 */
	private static long orderNumCount = 0L;
	/**
	 * 每毫秒生成订单号数量最大值
	 */
	private static int maxPerMSECSize=1000;
	
	/**
	 * 订单号生成计数器
	 */
	private static long orderNumCount_query = 0L;
	/**
	 * 每毫秒生成订单号数量最大值
	 */
	private static int maxPerMSECSize_query=1000;
	/**
	 * 生成非重复订单号，理论上限1毫秒1000个，可扩展
	 * @param tname 测试用
	 */
	public static synchronized String makeOrderNum() {
		try {
			// 最终生成的订单号
			String finOrderNum = "";
			synchronized (lockObj) {
				// 取系统当前时间作为订单号变量前半部分，精确到毫秒
				String nowLong = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
				// 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
				if (orderNumCount > maxPerMSECSize) {
					orderNumCount = 0L;
				}
				String countStr=maxPerMSECSize +orderNumCount+"";
				finOrderNum=nowLong+countStr.substring(1);
				orderNumCount++;
				return finOrderNum;
				// Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static synchronized String makeMerchantNum() {
		try {
			// 最终生成的订单号
			String finOrderNum = "";
			synchronized (lockObj) {
				// 取系统当前时间作为订单号变量前半部分，精确到毫秒
				String nowLong = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
				// 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
				if (orderNumCount > maxPerMSECSize) {
					orderNumCount = 0L;
				}
				String countStr=maxPerMSECSize +orderNumCount+"";
				finOrderNum=nowLong+countStr.substring(1);
				orderNumCount++;
				return finOrderNum;
				// Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static synchronized String makeQueryMerchantNum() {
		try {
			// 最终生成的订单号
			String finOrderNum = "";
			synchronized (lockObj_query) {
				// 取系统当前时间作为订单号变量前半部分，精确到毫秒
				String nowLong = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
				// 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
				if (orderNumCount_query > maxPerMSECSize_query) {
					orderNumCount_query = 0L;
				}
				String countStr=maxPerMSECSize_query +orderNumCount_query+"";
				finOrderNum=nowLong+countStr.substring(1);
				orderNumCount_query++;
				return finOrderNum;
				// Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 public static synchronized int makeRandomCnt(int maxCnt){
	        return (int) Math.round((Math.random() * maxCnt));
	 }
	
	public static void main(String[] args) {
		/*System.out.println(makeMerchantNum());*/
		System.out.println(makeRandomCnt(2));
		//System.out.println((int)1.8);
	}
}
