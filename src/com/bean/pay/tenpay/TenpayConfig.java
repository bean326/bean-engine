package com.bean.pay.tenpay;

import java.util.Map;
import java.util.Properties;

//import com.yuepai.repository.e.E_Environment;

/**
 * tenpay 配置信息
 * @author
 * 2016年4月5日
 */
public class TenpayConfig {

	
	public final static String charsetName = "utf-8";
	
	/**
	 * 统一下单
	 */
	public static String createTradeUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	/**
	 * 查询订单
	 */
	public static String queryTradeUrl = "https://api.mch.weixin.qq.com/pay/orderquery";
	
	/**
	 * 商户号
	 */
	public static String mchId;
	/**
	 * API 密钥
	 */
	public static String apiSecretKey;
	
	/**
	 * app id
	 */
	public static String appid ;
	
	/**
	 * 秘钥
	 */
	public static String appSecret;
	
	public static String tradeType;
	
	public static String packageStr;
	
	public static String deviceInfo;

	/**
	 * 通知URl
	 */
	public static String notifyUrl;
	
	/**
	 *  createTradeUrl 统一下单URL
		queryTradeUrl  查询订单URL
		mchId          商户ID
		apiSecretKey   API密钥
		appid          APPID
		appSecret      密钥
		tradeType      交易类型   移动引用:APP  H5网页:JSAPI
		packageStr     加密串
		deviceInfo     设备信息
		notifyUrl      通知URL
	 * @param properties
	 */
	public static void init(Properties properties)
	{
		createTradeUrl = properties.getProperty("createTradeUrl");
		queryTradeUrl  = properties.getProperty("queryTradeUrl");
		mchId          = properties.getProperty("mchId");
		apiSecretKey   = properties.getProperty("apiSecretKey");
		appid          = properties.getProperty("appid");
		appSecret      = properties.getProperty("appSecret");
		tradeType      = properties.getProperty("tradeType");
		packageStr     = properties.getProperty("packageStr");
		deviceInfo     = properties.getProperty("deviceInfo");
		notifyUrl      = properties.getProperty("notifyUrl");
		
		print();
	}
	
	/**
	 *  createTradeUrl 统一下单URL
		queryTradeUrl  查询订单URL
		mchId          商户ID
		apiSecretKey   API密钥
		appid          APPID
		appSecret      密钥
		tradeType      交易类型  移动引用:APP  H5网页:JSAPI
		packageStr     加密串
		deviceInfo     设备信息
		notifyUrl      通知URL
	 * @param params
	 */
	public static void init(Map<String,String> params)
	{
		createTradeUrl = params.get("createTradeUrl");
		queryTradeUrl  = params.get("queryTradeUrl");
		mchId          = params.get("mchId");
		apiSecretKey   = params.get("apiSecretKey");
		appid          = params.get("appid");
		appSecret      = params.get("appSecret");
		tradeType      = params.get("tradeType");
		packageStr     = params.get("packageStr");
		deviceInfo     = params.get("deviceInfo");
		notifyUrl      = params.get("notifyUrl");
		
		print();
	}

	
	public static void print()
	{
		System.out.println("TenpayConfig.createTradeUrl----【" + TenpayConfig.createTradeUrl + "】");
        System.out.println("TenpayConfig.queryTradeUrl-----【" + TenpayConfig.queryTradeUrl + "】");
        System.out.println("TenpayConfig.mchId-------------【" + TenpayConfig.mchId + "】");
        System.out.println("TenpayConfig.apiSecretKey------【" + TenpayConfig.apiSecretKey + "】");
        System.out.println("TenpayConfig.appid-------------【" + TenpayConfig.appid + "】");
        System.out.println("TenpayConfig.appSecret---------【" + TenpayConfig.appSecret + "】");
        System.out.println("TenpayConfig.tradeType---------【" + TenpayConfig.tradeType + "】");
        System.out.println("TenpayConfig.packageStr--------【" + TenpayConfig.packageStr + "】");
        System.out.println("TenpayConfig.deviceInfo--------【" + TenpayConfig.deviceInfo + "】");
        System.out.println("TenpayConfig.notifyUrl---------【" + TenpayConfig.notifyUrl + "】");
	}

//	public static void init(String notifyUrl){
//		if(null == notifyUrl || "".equals(notifyUrl)){
//			System.err.println("初始化tenpay配置信息失败！");
//			System.exit(-1);
//			return ;
//		}else{
//			TenpayConfig.notifyUrl = notifyUrl;
//		}
//	}
//
//	public static void init(String notifyUrl,String mchId,String apiSecretKey,String appid,String appSecret){
//		if(null == notifyUrl || "".equals(notifyUrl)
//				|| null == mchId || "".equals(mchId)
//				|| null == apiSecretKey || "".equals(apiSecretKey)
//				|| null == appid || "".equals(appid)
//				|| null == appSecret || "".equals(appSecret)){
//			System.err.println("初始化tenpay配置信息失败！");
//			System.exit(-1);
//			return ;
//		}else{
//			TenpayConfig.notifyUrl = notifyUrl;
//			TenpayConfig.mchId = mchId;
//			TenpayConfig.apiSecretKey = apiSecretKey;
//			TenpayConfig.appid = appid;
//			TenpayConfig.appSecret = appSecret;
//		}
//	}
	
	public static void testOther(){
		TenpayConfig.appid = "wxf2b6ade389e88bfc";
		TenpayConfig.appSecret = "10f040ea0abc001674c8bd5817ac603c";
	}


//	/**
//	 * 获取通知URL
//	 * @param env
//	 * @return
//	 */
//	public static String getNotifyUrl(E_Environment env){
//		if(E_Environment.isProduction(env)){
//			return TenpayConfig.PRODUCT_TENPAY_NOTIFY_URL;
//		}else{
//			return TenpayConfig.DEV_TENPAY_NOTIFY_URL;
//		}
//	}

}
