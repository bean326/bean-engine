package com.bean.pay.alipay.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import com.bean.pay.alipay.AlipayConfig;
import com.bean.pay.alipay.AlipayCore;
import com.bean.pay.alipay.sign.RSA;

public class AlipayTradeDatas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//--------不可空------------
	//接口名称
	private final String service = "mobile.securitypay.pay";
	//参数编码字符集
	private String _input_charset = "utf-8";
	//签名方式
	private String sign_type = "RSA";
	//支付类型 length=4         默认值为：1，表示商品购买
	private String payment_type = "1";
	
	
	//合作者身份ID length=16
	private String partner = null;
	//签名
	private String sign = null;
	//服务器异步通知页面路径 length=200
	private String notify_url = null;
	//商户网站唯一订单 length=64
	private String out_trade_no = null;
	//商品名称 length=128
	private String subject = null;
	//卖家支付宝账号(邮箱、手机号)或支付宝唯一用户号(2088开头的纯16位数字) length=16
	private String seller_id = null;
	//总金额 单位RMB-Yuan 取值[0.01-100000000.00] 精确到小数点后两位
	private BigDecimal total_fee = new BigDecimal(0);	
	//商品详情 length=512
	private String body = null;
	
	//-----------可为空--------------
	//客户端号
	private String app_id = null;
	//客户端来源
	private String appenv = null;
	//未付款交易的超时时间
	private String it_b_pay = "30m";
	//授权令牌 length=32
	private String extern_token = null;
	
	
	public AlipayTradeDatas(String out_trade_no , String subject , String body , BigDecimal total_fee) {
		
		this(out_trade_no,subject,body,total_fee,null,null,null,null);
	}

	public AlipayTradeDatas(String out_trade_no , String subject , String body , BigDecimal total_fee , String app_id , String appenv , String it_b_pay , String extern_token) {
		this.partner = AlipayConfig.partner;
		this.seller_id = AlipayConfig.seller_id;
		this.notify_url = AlipayConfig.notify_url;
		this.out_trade_no = out_trade_no;
		this.subject = subject;
		this.body = body;
		if(total_fee != null){
			this.total_fee = total_fee;
		}
		this.app_id = app_id;
		this.appenv = appenv;
		this.it_b_pay = it_b_pay;
		this.extern_token = extern_token;
	}
	private Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("partner", this.partner);
		map.put("seller_id", this.seller_id);
		map.put("out_trade_no", this.out_trade_no);
		map.put("subject", this.subject);
		map.put("body", this.body);
		//保留两位小数，四舍五入
		double totalFee = this.total_fee.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		String totalFeeStr = df.format(totalFee);
		map.put("total_fee", totalFeeStr);
		map.put("notify_url", this.notify_url);
		map.put("service", this.service);
		map.put("payment_type", this.payment_type);
		map.put("_input_charset", this._input_charset);
		map.put("it_b_pay", this.it_b_pay);
		map.put("sign", this.sign);
		map.put("sign_type", this.sign_type);
		map.put("app_id", this.app_id);
		map.put("appenv", this.appenv);
		map.put("extern_token", this.extern_token);
		return map;
	}
	
	
	/**
	 * 参数过滤
	 * @return
	 */
	private Map<String, String> paramsFilter(){
		return AlipayCore.paraFilter(toMap());
	}
	
	private String getPottedParams(Map<String, String> sArray){
		String result = "";
		if (sArray == null || sArray.size() <= 0) {
			return result;
		}
		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result += key + "=\"" + value + "\"";
			result += "&";
		}
		if(!"".equals(result)){
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
	/**
	 * 签名
	 * @return
	 */
	private String sign (){
		String pottedParams = getPottedParams(toMap());
		return RSA.sign(pottedParams, AlipayConfig.private_key, AlipayConfig.input_charset);
	}

	private String sign (String pottedParams){
		return RSA.sign(pottedParams, AlipayConfig.private_key, AlipayConfig.input_charset);
	}

	public String getSignedContent(){
		String signType = sign_type;
		String pottedParams = getPottedParams(toMap());
		String signStr = sign(pottedParams);
		String signedContent = pottedParams + "&sign=\"" + signStr + "\"" + "&sign_type=\"" + signType + "\"";
		setSign(signedContent);
		return signedContent;
	}
	
	
	public boolean verify() {
		boolean flag = true;
		String msg = "true";
		try {
//			Assert.hasLength(partner, "参数[partner]错误");
//			Assert.hasLength(notify_url, "参数[notify_url]错误");
//			Assert.hasLength(out_trade_no, "参数[out_trade_no]错误");
//			Assert.hasLength(subject, "参数[subject]错误");
//			Assert.hasLength(seller_id, "参数[seller_id]错误");
//			Assert.hasLength(body, "参数[body]错误");
			//保留两位小数，四舍五入
			double totalFee = this.total_fee.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			if(totalFee < 0.01d || totalFee > 100000000.00d){
				//msg = "参数[total_fee]错误";
				throw new IllegalArgumentException("参数[total_fee]错误");
			}
		} catch (IllegalArgumentException e) {
			msg = e.getMessage();
			flag = false;
		}
		return flag;
	}

	public String getService() {
		return service;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String get_input_charset() {
		return _input_charset;
	}

	public void set_input_charset(String _input_charset) {
		this._input_charset = _input_charset;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public BigDecimal getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(BigDecimal total_fee) {
		this.total_fee = total_fee;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getAppenv() {
		return appenv;
	}

	public void setAppenv(String appenv) {
		this.appenv = appenv;
	}

	public String getIt_b_pay() {
		return it_b_pay;
	}

	public void setIt_b_pay(String it_b_pay) {
		this.it_b_pay = it_b_pay;
	}

	public String getExtern_token() {
		return extern_token;
	}

	public void setExtern_token(String extern_token) {
		this.extern_token = extern_token;
	}
	
	
}
