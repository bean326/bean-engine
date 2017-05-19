package com.bean.pay.alipay;

import java.util.Map;
import java.util.Properties;

import com.bean.pay.alipay.sign.MD5;
import com.bean.pay.alipay.sign.RSA;


/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */
/**
 * 用户手机充值--alipay配置
 * @author Administrator
 *
 */
public class AlipayConfig {
	
	public final static String SIGN_RSA = "RSA";
	
	public final static String SIGN_MD5 = "MD5";
	
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088021480247590";
	
	//卖家支付宝账号(邮箱、手机号)或支付宝唯一用户号(2088开头的纯16位数字)
	public static String seller_id = "douqu2015@126.com";
	
	// 商户的私钥
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANBldGFUgVgqkQwVYJ0RkaOiYH2RwgUAh+ICe4KM241KMGbHKUNURmX0WJxDWt58WS64b3srIo1uFRpnA8LpPsaFHKvJqF53BoAq70oK6NX2f96Alq0RsaBjmRLPgZKB0NyNXOpkOl5nohCFpD8qnY2U/XrWd20z2DI0a4RwqWllAgMBAAECgYAW7gLXLsAonk5lYPjHb4Zx1cz+JAqQGFXbNWC3pZ4GttIKjQFqShyQ3UjesL6P2mjJrjt99uoPW7xwskMEsDta06jga5yb/Q/00s0Y/wb+0gyhc2dx90o1dmH0QSEnNveHSbDkJTWGZD7DYQgbeSvNsoU0fLIsXAutBxx4skVjRQJBAPKQgFQccTfwyB9GiwbpltX6C/zZSFnqOnTzhmg4TL8pirCpvnfCTW6pnJxb+5KMfxyA8R5QjgTxJq1ZTBLLL1sCQQDb8HVI2XC4LonU2RIVkgzI2p4Bxe+k2bnD7H4zMPo1lJn+NO9IrXl2CUIcNZugnNRzM0NS4O6nbaC/SZvr5+Y/AkBBT6MeAqKBe3WG5kmczNxuNOtxY1fVzfl7b+6qtS4E8ryDINFGEa0jtMuUVjgAl4/KLvW9QddbgUr2teqDzO2tAkEAuRniW3FEwH3KWmxmH+lPASucl3lyepcTFKVQgNzTd4ydHaiHSe9VrdWU4hDr7XyQAHRAUNwlfptxEgxtQVlmGQJBAORTGugFH+b+7usFABpxsuPtUmcdQ1CXoQQpR+RBgxY376/0ZlePoasJl4BXYyhwIYS2Y7VjEEHQUOFLSSszfy8=";
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	
	
	public static String email = "";
	
	
	public static String account_name = "";
	
	
	public static String key = "";
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path;

	// 字符编码格式 目前支持 gbk 或 utf-8
	public final static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type;

	/**
	 * 通知URl
	 */
	public static String notify_url;
	
	
	/**
	 *  partner         合作身份者ID，以2088开头由16位纯数字组成的字符串
		seller_id       卖家支付宝账号(邮箱、手机号)或支付宝唯一用户号(2088开头的纯16位数字)
		private_key     商户的私钥
		ali_public_key  支付宝的公钥
		sign_type       签名方式   RSA MD5
		notify_url      通知URl  
		log_path        日志文件夹路径    
	 * @param properties
	 */
	public static void init(Properties properties)
	{
		partner         = properties.getProperty("partner");
		seller_id       = properties.getProperty("seller_id");
		private_key     = properties.getProperty("private_key");
		ali_public_key  = properties.getProperty("ali_public_key");
		sign_type       = properties.getProperty("sign_type");
		notify_url      = properties.getProperty("notify_url");
		log_path        = properties.getProperty("log_path");
		email           = properties.getProperty("email");
		account_name    = properties.getProperty("account_name");
		key             = properties.getProperty("key");
		
		print();
	}
	
	/**
  	 *  partner         合作身份者ID，以2088开头由16位纯数字组成的字符串
		seller_id       卖家支付宝账号(邮箱、手机号)或支付宝唯一用户号(2088开头的纯16位数字)
		private_key     商户的私钥
		ali_public_key  支付宝的公钥
		sign_type       签名方式     RSA MD5
		notify_url      通知URl  
		log_path        日志文件夹路径    
	 * @param params
	 */
	public static void init(Map<String,String> params)
	{
		partner         = params.get("partner");
		seller_id       = params.get("seller_id");
		private_key     = params.get("private_key");
		ali_public_key  = params.get("ali_public_key");
		sign_type       = params.get("sign_type");
		notify_url      = params.get("notify_url");
		log_path        = params.get("log_path");
		email           = params.get("email");
		account_name    = params.get("account_name");
		key             = params.get("key");
		
		print();
	}
	
	public static void print()
	{
		System.out.println("AlipayConfig.partner-------【" + AlipayConfig.partner + "】");
        System.out.println("AlipayConfig.seller_id-----【" + AlipayConfig.seller_id + "】");
        System.out.println("AlipayConfig.sign_type-----【" + AlipayConfig.sign_type + "】");
        System.out.println("AlipayConfig.email---------【" + AlipayConfig.email + "】");
        System.out.println("AlipayConfig.account_name--【" + AlipayConfig.account_name + "】");
        System.out.println("AlipayConfig.log_path------【" + AlipayConfig.log_path + "】");
        System.out.println("AlipayConfig.key-----------【" + AlipayConfig.key + "】");
        System.out.println("AlipayConfig.notify_url----【" + AlipayConfig.notify_url + "】");
	}
	
	public static boolean isSign(String content, String sign){
		if(SIGN_RSA.equals(sign_type))
			return RSA.verify(content, sign, key, input_charset);
		else
			return MD5.verify(content, sign, key, input_charset);
	}
	
	
	public static String sign(String content){
		if(SIGN_RSA.equals(sign_type))
			return RSA.sign(content, key, input_charset);
		else
			return MD5.sign(content, key, input_charset);
	}

}
