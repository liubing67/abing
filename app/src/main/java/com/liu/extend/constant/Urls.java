package com.liu.extend.constant;

/**
 * 项目名称：SecondPay
 * 类描述：接口地址整合类
 * 创建人：Liangtao
 * 创建时间：2016/7/30 15:27
 * 修改人：Administrator
 * 修改时间：2016/7/30 15:27
 * 修改备注：
 */
public class Urls {

    public static final String HOST = "http://miaofu.china-madpay.com/";


    /**
     * 登录
     */
    public static final String LOGIN = HOST + "lklwebapi/core/funcs/security/loginCheck";//已签名
    /**
     * //版本检测更新
     */
    public static final String UPDATE_INFO ="http://www.china-madpay.com/appversion.xml";



    public static final String GET_HOME="http://218.80.1.67:8580/crmapi/core/funcs/main/getHomePageData";
}
