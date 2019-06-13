/**
 * @Title: LoggerUtils.java
 * @Package com.webside.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author tianlei
 * @date 2016年9月11日 下午3:48:49
 * @version V1.0
 */
package com.janeli.pay.utils;

//import org.apache.log4j.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

/**@ClassName: LoggerUtils
 * @Description: TODO(日志工具类)
 * @author zhangjinchang
 * @date 2017年11月7日
 */
public class LoggerUtils {
    private static Logger logger = null;

    /**
     * @Title: logException
     * @Description: TODO(输出异常日志)
     * @param e
     * @return:void 返回类型
     * @throws
     */
    public static void logException(Exception e) {
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String newClass = stacks[1].getClassName();
        if (logger == null || !newClass.equals(logger.getName())) {
            logger = LoggerFactory.getLogger(newClass);
        }
        StringBuffer logInfo = new StringBuffer(100);
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logInfo.append(" Exception:");
        logInfo.append(trace.toString());
        logger.error(logInfo.toString());
    }

    /**
     * @Title: logException
     * @Description: TODO(输出异常日志)
     * @param e
     * @return:void 返回类型
     * @throws
     */
    public static void logException(String message, Exception e) {
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String newClass = stacks[1].getClassName();
        if (logger == null || !newClass.equals(logger.getName())) {
            logger = LoggerFactory.getLogger(newClass);
        }
        StringBuffer logInfo = new StringBuffer(100);
        logInfo.append("Message:" + message);
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logInfo.append(" Exception:");
        logInfo.append(trace.toString());
        logger.error(logInfo.toString());
    }

    /**
     * @Title: logInfo
     * @Description: TODO(输出info日志)
     * @param message
     * @return:void 返回类型
     * @throws
     */
    public static void logInfo(String message) {
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String newClass = stacks[1].getClassName();
        if (logger == null || !newClass.equals(logger.getName())) {
            logger = LoggerFactory.getLogger(newClass);
        }
        logger.info(message);
    }

    /**
     * @Title: logError
     * @Description: TODO(输出error日志)
     * @param message
     * @return:void 返回类型
     * @throws
     */
    public static void logError(String message) {
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String newClass = stacks[1].getClassName();
        if (logger == null || !newClass.equals(logger.getName())) {
            logger = LoggerFactory.getLogger(newClass);
        }
        logger.error(message);
    }

    /**
     * @Title: logDebug
     * @Description: TODO(输出debug日志)
     * @param message
     * @return:void 返回类型
     * @throws
     */
    public static void logDebug(String message) {
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String newClass = stacks[1].getClassName();
        if (logger == null || !newClass.equals(logger.getName())) {
            logger = LoggerFactory.getLogger(newClass);
        }
        logger.debug(message);
    }

    public static void logTransaction(String message, String fileName) {

        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String newClass = stacks[1].getClassName();
        if (logger == null || !newClass.equals(logger.getName())) {
            logger = LoggerFactory.getLogger(newClass);
        }
        logger.info("[tranId:"+ fileName + "]--" + message);
    }

    /**
     * 生成对应目录的log
     * @param message
     * @param dir
     * @param fileName
     */
    public static void logTrc(String message, String dir, String fileName) {
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String newClass = stacks[1].getClassName();
        if (logger == null || !newClass.equals(logger.getName())) {
            logger = LoggerFactory.getLogger(newClass);
        }
        logger.info("[tranId："+ fileName + "]--" + message);
    }
}
