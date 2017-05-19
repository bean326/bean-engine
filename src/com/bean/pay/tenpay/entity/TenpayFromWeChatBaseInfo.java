package com.bean.pay.tenpay.entity;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.SortedMap;

import com.bean.util.BeanMapUtils;

/**
 * 微信app支付 - 微信返回信息基础类
 * @author FXW
 * 2016年4月5日
 */
public abstract class TenpayFromWeChatBaseInfo extends TenpayBaseInfo{

	
	/**
	 * 验证签名
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean verifySign(){
		try {
			SortedMap parameters = BeanMapUtils.toSortedMap(this);
			String sign = doSignature(parameters);
			String oldSign = parameters.get("sign").toString();
			if(sign.equalsIgnoreCase(oldSign)){
				return true;
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}catch(InvocationTargetException e){
			e.printStackTrace();
		}catch(IntrospectionException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	
}
