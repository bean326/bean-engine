package com.bean.pay.tenpay.entity;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import com.bean.pay.tenpay.TenpayConfig;
import com.bean.pay.tenpay.util.MD5Util;
import com.bean.util.BeanMapUtils;

/**
 * 微信app支付 - 向微信发起请求参数基础类
 * @author FXW
 * 2016年4月5日
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class TenpayToWeChatBaseInfo implements Serializable {

	
	private static final long serialVersionUID = 9118202330374753095L;

	
	
	public String createRequestXML() {
		try {
			SortedMap parameters = BeanMapUtils.toSortedMap(this);
			StringBuilder sbSign = new StringBuilder();
			StringBuilder sbXML = new StringBuilder();
			sbXML.append("<xml>");
			Set<Map.Entry> set = parameters.entrySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String k = entry.getKey().toString();
				String v = entry.getValue().toString();
				if("sign".equals(k)) continue;
				sbSign.append(k + "=" + v + "&");
				sbXML.append(createXMLElement(k, v));
			}
			sbSign.append("key=");
			sbSign.append(TenpayConfig.apiSecretKey);
			//System.out.println("sbSign = "+sbSign);
			String appsign = MD5Util.MD5Encode(sbSign.toString(), TenpayConfig.charsetName).toUpperCase();
			sbXML.append(createXMLElement("sign", appsign));
			sbXML.append("</xml>");
			return sbXML.toString();
		} catch (IllegalAccessException | InvocationTargetException
				| IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 示例： <return_code><![CDATA[SUCCESS]]></return_code>
	 * @param key
	 * @param value
	 * @return
	 */
	public final static String createXMLElement(String key,String value){
		String xmlElement = "";
		if(key == null || "".equals(key) || value == null || "".equals(value))
			return xmlElement;
		xmlElement = "<" + key + ">" + "<![CDATA[" + value + "]]>" + "</" + key + ">\n";
		return xmlElement;
	}
	
}
