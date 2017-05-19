package com.bean.pay.tenpay.entity;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import com.bean.pay.tenpay.TenpayConfig;
import com.bean.pay.tenpay.util.MD5Util;

/**
 * @author FXW
 * 2016年4月6日
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class TenpayBaseInfo {

	/**
	 * 签名
	 * @param parameters
	 * @return
	 */
	public String doSignature(SortedMap parameters){
		StringBuilder sb = new StringBuilder();
		
		Set<Map.Entry> set = parameters.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = entry.getKey().toString();
			String v = entry.getValue().toString();
			if("sign".equals(k)) continue;
			sb.append(k + "=" + v + "&");
		}
		sb.append("key=");
		sb.append(TenpayConfig.apiSecretKey);
		String appsign = MD5Util.MD5Encode(sb.toString(), TenpayConfig.charsetName).toUpperCase();
		return appsign;
	}
	
}
