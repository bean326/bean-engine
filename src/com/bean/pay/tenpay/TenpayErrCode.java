package com.bean.pay.tenpay;


/**
 * 微信app支付错误代码
	
 * @author FXW
 * 2016年4月5日
 */
public class TenpayErrCode {

	/**
	 * 创建交易订单错误代码枚举
	 *  NOAUTH 	商户无此接口权限 	商户未开通此接口权限 	请商户前往申请此接口权限
		NOTENOUGH 	余额不足 	用户帐号余额不足 	用户帐号余额不足，请用户充值或更换支付卡后再支付
		ORDERPAID 	商户订单已支付 	商户订单已支付，无需重复操作 	商户订单已支付，无需更多操作
		ORDERCLOSED 	订单已关闭 	当前订单已关闭，无法支付 	当前订单已关闭，请重新下单
		SYSTEMERROR 	系统错误 	系统超时 	系统异常，请用相同参数重新调用
		APPID_NOT_EXIST 	APPID不存在 	参数中缺少APPID 	请检查APPID是否正确
		MCHID_NOT_EXIST 	MCHID不存在 	参数中缺少MCHID 	请检查MCHID是否正确
		APPID_MCHID_NOT_MATCH 	appid和mch_id不匹配 	appid和mch_id不匹配 	请确认appid和mch_id是否匹配
		LACK_PARAMS 	缺少参数 	缺少必要的请求参数 	请检查参数是否齐全
		OUT_TRADE_NO_USED 	商户订单号重复 	同一笔交易不能多次提交 	请核实商户订单号是否重复提交
		SIGNERROR 	签名错误 	参数签名结果不正确 	请检查签名参数和方法是否都符合签名算法要求
		XML_FORMAT_ERROR 	XML格式错误 	XML格式错误 	请检查XML参数格式是否正确
		REQUIRE_POST_METHOD 	请使用post方法 	未使用post传递参数  	请检查请求参数是否通过post方法提交
		POST_DATA_EMPTY 	post数据为空 	post数据不能为空 	请检查post数据是否为空
		NOT_UTF8 	编码格式错误 	未使用指定编码格式 	请使用NOT_UTF8编码格式
	 * @author FXW
	 * 2016年4月5日
	 */
	public enum TenpayTradeErrCodeEnum{
		DEFAULT("无对应的错误代码","无对应的错误代码","无对应的错误代码"),
		
		NOAUTH("商户无此接口权限","商户未开通此接口权限","请商户前往申请此接口权限"),
		NOTENOUGH("余额不足","用户帐号余额不足","用户帐号余额不足，请用户充值或更换支付卡后再支付"),
		ORDERPAID("商户订单已支付","商户订单已支付，无需重复操作","商户订单已支付，无需更多操作"),
		ORDERCLOSED("订单已关闭","当前订单已关闭，无法支付","当前订单已关闭，请重新下单"),
		SYSTEMERROR("系统错误","系统超时","系统异常，请用相同参数重新调用"),
		APPID_NOT_EXIST("APPID不存在","参数中缺少APPID","请检查APPID是否正确"),
		MCHID_NOT_EXIST("MCHID不存在","参数中缺少MCHID","请检查MCHID是否正确"),
		APPID_MCHID_NOT_MATCH("appid和mch_id不匹配","appid和mch_id不匹配","请确认appid和mch_id是否匹配"),
		LACK_PARAMS("缺少参数","缺少必要的请求参数","请检查参数是否齐全"),
		OUT_TRADE_NO_USED("商户订单号重复","同一笔交易不能多次提交","请核实商户订单号是否重复提交"),
		SIGNERROR("签名错误","参数签名结果不正确","请检查签名参数和方法是否都符合签名算法要求"),
		XML_FORMAT_ERROR("XML格式错误","XML格式错误","请检查XML参数格式是否正确"),
		REQUIRE_POST_METHOD("请使用post方法","post数据不能为空","请检查post数据是否为空"),
		POST_DATA_EMPTY("post数据为空","post数据不能为空","请检查post数据是否为空"),
		NOT_UTF8("编码格式错误","未使用指定编码格式","请使用NOT_UTF8编码格式"),
		;
		
		String desc;
		String reason;
		String solution;
		
		/**
		 * @param desc
		 * @param reason
		 * @param solution
		 */
		private TenpayTradeErrCodeEnum(String desc, String reason, String solution) {
			this.desc = desc;
			this.reason = reason;
			this.solution = solution;
		}

		public static TenpayTradeErrCodeEnum valueOfFromCode(String errCode) {
			TenpayTradeErrCodeEnum code = TenpayTradeErrCodeEnum.DEFAULT;
			try {
				code = TenpayTradeErrCodeEnum.valueOf(errCode);
			} catch (Exception e) {
			}
			return code;
		}

		public String getDesc() {
			return desc;
		}

		public String getReason() {
			return reason;
		}

		public String getSolution() {
			return solution;
		}
		
	}
	
	/**
	 * 查询订单支付错误代码枚举
	 *  ORDERNOTEXIST 	此交易订单号不存在 	查询系统中不存在此交易订单号 	该API只能查提交支付交易返回成功的订单，请商户检查需要查询的订单号是否正确
		SYSTEMERROR 	系统错误 	后台系统返回错误 	系统异常，请再调用发起查询 
	 * @author FXW
	 * 2016年4月5日
	 */
	public enum TenpayQueryErrCodeEnum{
		DEFAULT("无对应的错误代码","无对应的错误代码","无对应的错误代码"),
		ORDERNOTEXIST("此交易订单号不存在","查询系统中不存在此交易订单号","该API只能查提交支付交易返回成功的订单，请商户检查需要查询的订单号是否正确"),
		SYSTEMERROR("系统错误","后台系统返回错误","系统异常，请再调用发起查询"),
		;
		String desc;
		String reason;
		String solution;
		
		/**
		 * @param desc
		 * @param reason
		 * @param solution
		 */
		private TenpayQueryErrCodeEnum(String desc, String reason, String solution) {
			this.desc = desc;
			this.reason = reason;
			this.solution = solution;
		}

		public static TenpayQueryErrCodeEnum valueOfFromCode(String errCode) {
			TenpayQueryErrCodeEnum code = TenpayQueryErrCodeEnum.DEFAULT;
			try {
				code = TenpayQueryErrCodeEnum.valueOf(errCode);
			} catch (Exception e) {
			}
			return code;
		}
	}
	
	
}
