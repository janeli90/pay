package com.janeli.pay.utils;

/**
 *
 */
public final class APIConstants {

    /**
     * API协议版本，可选值：1.1
     */
    public static final String API_VERSION = "v";

    public static final String[] ALLOW_API_VERSION_VALUES = new String[] { "1.1" };

    /**
     * API接口名称
     */
    public static final String API_METHOD = "method";

    /**
     * 时间戳，格式为yyyy-mm-dd HH:mm:ss，例如：2013-08-12 15:51:05。API服务端允许客户端请求时间误差为10分钟
     */
    public static final String API_TIMESTAMP = "timestamp";

    /**
     * 时间截，允许的误差毫秒数(10分钟)
     */
    public static final int API_TIMESTAMP_ERROR_MILLIS = 10 * 60 * 1000;

    /**
     * 可选，指定响应格式。默认xml，目前支持：xml、json
     */
    public static final String API_FORMAT = "format";

    public static final String API_FORMAT_XML = "xml";

    public static final String API_FORMAT_JSON = "json";

    public static final String[] ALLOW_API_FORMAT_VALUES = new String[] {
            API_FORMAT_JSON, API_FORMAT_XML };

    /**
     * 系统分配给应用的AppKey
     */
    public static final String API_APP_KEY = "app_key";

    /**
     * 参数的加密方法选择，可选值：md5、hmac
     */
    public static final String API_SIGN_METHOD = "sign_method";

    public static final String API_SIGN_METHOD_MD5 = "md5";

    public static final String API_SIGN_METHOD_HMAC = "hmac";

    public static final String[] ALLOW_SIGN_METHOD_VALUES = new String[] {
            API_SIGN_METHOD_MD5, API_SIGN_METHOD_HMAC };

    /**
     * 分配给用户的Access Token
     */
    public static final String API_ACCESS_TOKEN = "access_token";

    /**
     * 对 API 输入参数进行 md5或hmac 加密获得
     */
    public static final String API_SIGN = "sign";

    /**
     * 请求参数--登录ID节点 
     */
    public static final String REQUEST_LOGIN_ID_NODE = "login_id";
    
    public static final String REQUEST_LOGIN_USER_ID_NODE = "login_user_id";

    /**
     * 返回结果根节点
     */
    public static final String RESPONSE_ERROR_ROOT_NODE = "error_response";

    public static final String RESPONSE_SUCCESS_NODE = "success";

    public static final String RESPONSE_SUCCESS_NODE_VALUE_FALSE = "false";

    /**
     * 返回结果code节点
     */
    public static final String RESPONSE_ERROR_CODE_NODE = "result_code";

    /**
     * 返回结果msg节点
     */
    public static final String RESPONSE_ERROR_MSG_NODE = "result_code_msg";

    public static final String CONTENT_TYPE_TEXT_XML = "text/xml";

    public static final String SIGN_NODE = "sign";

    /**
     * HTTP默认字符集编码
     */
    public static final String DEFAULT_HTTP_CHARSET = "ISO-8859-1";

    public static final String UTF_8_CHARSET = "UTF-8";

    public static final String PARENT_TYPE_ENUM = "Enum";

    public static final String DATA_TYPE_DATE = "Date";

    public static final String DATA_TYPE_ENUM = "Enum";

    public static final String DATA_TYPE_FILE = "FILE";

    public static final String SLASH = "/";

    public static final String BLANK = " ";

    public static final String DOUBLE_SLASH_STAR = "//*";

    public static final String FALSE = "false";

    public static final String COMMA = ",";

    public static final String POINT = ".";

    public static final String ESCAPE_POINT = "\\.";

    public static final String UNDERLINE = "_";

    public static final String RESPONSE = "response";

    public static final String QUESTION_MARK = "?";

    public static final String ALPHABET_T = "T";

    public static final String ENVELOPE_BODY = "/Envelope/Body/";

    public static final String RESPONSE_RETURN = "Response/return";

    public static final String ONE = "1";

    public static final String TWO = "2";
}

