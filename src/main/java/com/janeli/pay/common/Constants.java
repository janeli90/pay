package com.janeli.pay.common;

import java.math.BigDecimal;

public interface Constants {
	
	 String  GE_TUI_URL = "http://sdk.open.api.igexin.com/apiex.htm";
	
	 String REQUEST_SESSION_ID = "sessionId";
    
	 String SESSION_INVALID_CODE = "1901";
	 
	 String SESSION_TIMEOUT_CODE = "1902";
	 
	 String USR_STS_INVALID = "1003";
	 
	 String REQUEST_USER_ENTITY = "userInfEntity";
	 
	 String COMMON_ERROR_CODE = "9999";
	 
	 String COMMON_ERROR_MSG = "系统异常";
	 
	 String COMMON_SUCCESS_CODE = "0";
	 
	 String COMMON_SUCCESS_MSG = "操作成功";
	
	 int PWD_ERROR_MAX_CNT = 10;

	String RATE = "baseRate";
    String HANDLE_FEE = "handlerfee";
	String REPAY_TYPE = "repayment";
    String QUICK_PAY_TYPE = "quickpay";
	String CREDIT_PAY_TYPE = "creditTxn";
	String MIN_TXN_AMT = "minTxnAmt";
	String MAX_TXN_AMT = "maxTxnAmt";

	//睿付通道
	String RUIFU_CHANNEL = "0";
	//神州支付通道
	String SZPAY_CHANNEL = "1";

	/*取现类型*/
	String QUICK_PAY = "1";
	String BP_QUICK_PAY = "2";
		/*
	 * 一级5000-10000
                      	 区县10000-50000
                         市20000-100000
                         省200000-300000
	 */
	BigDecimal ONE_MIN_AMT = new BigDecimal("5000");
	BigDecimal ONE_MAX_AMT = new BigDecimal("20000");
	BigDecimal TWO_MIN_AMT = new BigDecimal("10000");
	BigDecimal TWO_MAX_AMT = new BigDecimal("200000");
	BigDecimal THREE_MIN_AMT = new BigDecimal("20000");
	BigDecimal THREE_MAX_AMT = new BigDecimal("300000");
	BigDecimal FOURE_MIN_AMT = new BigDecimal("200000");
	BigDecimal FOURE_MAX_AMT = new BigDecimal("500000");
    /**
     * 助赢付费升级金额
     */
	BigDecimal ONE_AMT = new BigDecimal("298");
	BigDecimal TWO_AMT = new BigDecimal("17888");
	BigDecimal THREE_AMT = new BigDecimal("57888");
}
