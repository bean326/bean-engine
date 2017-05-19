package com.bean.pay.tenpay.entity;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.SortedMap;

import com.bean.pay.tenpay.TenpayConfig;
import com.bean.pay.tenpay.util.WXUtil;
import com.bean.util.BeanMapUtils;

/**
 * tenpay - app获取签名的信息封装
 *
 * @author FXW
 *         2016年4月6日
 */
public class TenpayAppSignInfo extends TenpayBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    //应用ID 	appid 	String(32) 	是 	wx8888888888888888 	微信开放平台审核通过的应用APPID
    private String appid;
    //商户号 	partnerid 	String(32) 	是 	1900000109 	微信支付分配的商户号
    private String partnerid;
    //预支付交易会话ID 	prepayid 	String(32) 	是 	WX1217752501201407033233368018 	微信返回的支付交易会话ID
    private String prepayid;
    //扩展字段 	package 	String(128) 	是 	Sign=WXPay 	暂填写固定值Sign=WXPay
    private String packageStr;
    //随机字符串 	noncestr 	String(32) 	是 	5K8264ILTKCH16CQ2502SI8ZNMTM67VS 	随机字符串，不长于32位。推荐随机数生成算法
    private String noncestr;
    //时间戳 	timestamp 	String(10) 	是 	1412000000 	时间戳，请见接口规则-参数规定
    private String timestamp;
    //签名 	sign 	String(32) 	是 	C380BEC2BFD727A4B6845133519F3AD6 	签名，详见签名生成算法
    //需要在最后初始化
    private String sign;


    private TenpayAppSignInfo() {
    }

    public static TenpayAppSignInfo defaultInit(String prepayid) {
        TenpayAppSignInfo appSignInfo = new TenpayAppSignInfo();
        appSignInfo.appid = TenpayConfig.appid;
        appSignInfo.noncestr = WXUtil.getNonceStr();
        appSignInfo.partnerid = TenpayConfig.mchId;
        appSignInfo.packageStr = TenpayConfig.packageStr;
        appSignInfo.timestamp = WXUtil.getTimeStamp();
        appSignInfo.prepayid = prepayid;
        /**
         * In the final for Initializing the signature
         */
        appSignInfo.sign = appSignInfo.getSignature();
        return appSignInfo;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private String getSignature() {
        try {
            SortedMap parameters = BeanMapUtils.toSortedMap(this);
            if (parameters.containsKey("packageStr")) {
                parameters.put("package", parameters.get("packageStr"));
                parameters.remove("packageStr");
            }
            String signature = doSignature(parameters);
            //System.out.println("parameters:" + parameters + " \r\n signature:" + signature);
            return signature;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


}
